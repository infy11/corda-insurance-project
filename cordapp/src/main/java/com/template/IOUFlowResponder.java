package com.template;

import co.paralleluniverse.fibers.Suspendable;
import net.corda.core.contracts.ContractState;
import net.corda.core.flows.*;
import net.corda.core.transactions.SignedTransaction;
import net.corda.core.utilities.ProgressTracker;
import static net.corda.core.contracts.ContractsDSL.requireThat;


@InitiatedBy(IOUFlow.class)
public class IOUFlowResponder extends FlowLogic<Void> {

    private final FlowSession IRDAsession;
    public IOUFlowResponder(FlowSession IRDAsession)
    {
        this.IRDAsession=IRDAsession;
    }


    @Suspendable
    @Override
    public Void call() throws FlowException
    {

        class SignTxFlow extends SignTransactionFlow
        {

            public SignTxFlow(FlowSession otherSideSession, ProgressTracker progressTracker) {
                super(otherSideSession, progressTracker);
            }

            @Override
            protected void checkTransaction(SignedTransaction stx) throws FlowException {
                requireThat(require->{
                    ContractState output=stx.getTx().getOutputs().get(0).getData();
                    require.using("this must be iou",output instanceof IOUState);


                    return null;






                });
            }
        }

        subFlow(new SignTxFlow(IRDAsession,SignTransactionFlow.Companion.tracker()));

        return null;

    }



}
