package com.template;

// Add these imports:
import co.paralleluniverse.fibers.Suspendable;
import com.google.common.collect.ImmutableList;
import net.corda.core.contracts.Command;
import net.corda.core.contracts.CommandData;
import net.corda.core.contracts.StateAndContract;
import net.corda.core.flows.*;
import net.corda.core.identity.Party;
import net.corda.core.transactions.SignedTransaction;
import net.corda.core.transactions.TransactionBuilder;
import net.corda.core.utilities.ProgressTracker;

import java.security.PublicKey;
import java.util.List;

import static com.template.IOUContract.TEMPLATE_CONTRACT_ID;

// Replace TemplateFlow's definition with:
@InitiatingFlow
@StartableByRPC
public class IOUFlow extends FlowLogic<SignedTransaction> {
    private final String car_chasis;
    // private final String car_name;
    // private final String car_engine;
    private final Float Idv_value;
    private final String cust_name;
    private final String year;
    private final Party insuarance_company;
    private final Party IRDA;

    /**
     * The progress tracker provides checkpoints indicating the progress of the flow to observers.
     */
    private final ProgressTracker progressTracker = new ProgressTracker();

    public IOUFlow(String car_chasis,String cust_name,String year,Float idv_value, Party IRDA,Party insuarance_company) {
        this.car_chasis=car_chasis;
        this.cust_name=cust_name;
        this.year=year;
        this.Idv_value=idv_value;
        this.IRDA= IRDA;
        this.insuarance_company=insuarance_company;
    }

    @Override
    public ProgressTracker getProgressTracker() {
        return progressTracker;
    }

    /**
     * The flow logic is encapsulated within the call() method.
     */
    @Suspendable
    @Override
    public SignedTransaction call() throws FlowException {
        final TransactionBuilder txBuilder=new TransactionBuilder();
        final Party notary = getServiceHub().getNetworkMapCache().getNotaryIdentities().get(0);

        txBuilder.setNotary(notary);
        IOUState outputState = new IOUState(car_chasis,cust_name,year,Idv_value, getOurIdentity(),IRDA);
        StateAndContract outputstateandcontract=new StateAndContract(outputState,IOUContract.TEMPLATE_CONTRACT_ID);
        List<PublicKey> requiredSigners= ImmutableList.of(getOurIdentity().getOwningKey(),IRDA.getOwningKey());
        Command cmd=new Command<>(new IOUContract.Create(),requiredSigners);

        txBuilder.withItems(outputstateandcontract,cmd);
        txBuilder.verify(getServiceHub());
        final SignedTransaction signedTx=getServiceHub().signInitialTransaction(txBuilder);
        FlowSession irdasession=initiateFlow(IRDA);

        SignedTransaction fullySignedTx=subFlow(new CollectSignaturesFlow(
                signedTx, ImmutableList.of(irdasession),CollectSignaturesFlow.tracker()



        ));

       return subFlow(new FinalityFlow(fullySignedTx));









       /* // We retrieve the notary identity from the network map.
        final Party notary = getServiceHub().getNetworkMapCache().getNotaryIdentities().get(0);

        // We create the transaction components.
        IOUState outputState = new IOUState(car_chasis,cust_name,year,Idv_value, getOurIdentity(),IRDA);
        CommandData cmdType = new IOUContract.Commands.Action();
        Command cmd = new Command<>(cmdType, getOurIdentity().getOwningKey());

        // We create a transaction builder and add the components.
        final TransactionBuilder txBuilder = new TransactionBuilder(notary)
                .addOutputState(outputState, TEMPLATE_CONTRACT_ID)
                .addCommand(cmd);

        // Signing the transaction.
        final SignedTransaction signedTx = getServiceHub().signInitialTransaction(txBuilder);

        // Finalising the transaction.
        subFlow(new FinalityFlow(signedTx));

        return null;

        */
    }


}