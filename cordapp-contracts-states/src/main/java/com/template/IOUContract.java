package com.template;

import net.corda.core.contracts.CommandData;
import net.corda.core.contracts.CommandWithParties;
import net.corda.core.contracts.Contract;
import net.corda.core.identity.Party;
import net.corda.core.transactions.LedgerTransaction;
import org.apache.qpid.proton.reactor.impl.IOImpl;

import java.security.PublicKey;
import java.util.List;
import static net.corda.core.contracts.ContractsDSL.requireSingleCommand;
import static net.corda.core.contracts.ContractsDSL.requireThat;

/**
 * Define your contract here.
 */
public class IOUContract implements Contract {
    // This is used to identify our contract when building a transaction.
    public static final String TEMPLATE_CONTRACT_ID = "com.template.IOUContract";

    /**
     * A transaction is considered valid if the verify() function of the contract of each of the transaction's input
     * and output states does not throw an exception.
     *
     */

    public static class Create implements CommandData{}
    @Override
    public void verify(LedgerTransaction tx) {
        final CommandWithParties<IOUContract.Create> command=requireSingleCommand(tx.getCommands(), IOUContract.Create.class);
        requireThat(check->{


            check.using("no inputs should be consumed when issuing IOU",tx.getInputs().isEmpty());
            check.using("thee should be one output state of type IOUstate",tx.getOutputs().size()==1);

            final IOUState out=tx.outputsOfType(IOUState.class).get(0);
            final Party company=out.getInsuarance_company();
            final Party irda=out.getIRDA();
            check.using("ird value should not be negative",out.getIdv_value()>0);

            check.using("chasis number should not be null",out.getCar_chasis()!=null);
            check.using("customer name should not be null ",out.getCust_name()!=null);
            check.using("year should not be blank",out.getYear()!=null);
            final List<PublicKey> singers=command.getSigners();
            check.using("there must be two singers",singers.size()==2);


            return null;







    });


}

}

