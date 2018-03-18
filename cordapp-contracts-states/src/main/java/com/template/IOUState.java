package com.template;

import net.corda.core.contracts.ContractState;
import net.corda.core.identity.AbstractParty;
import com.google.common.collect.ImmutableList;
import net.corda.core.identity.Party;
import java.util.Collections;
import java.util.List;

/**
 * Define your state object here.
 */
public class IOUState implements ContractState {
   // private final String car_model;
    private final String car_chasis;
   // private final String car_name;
   // private final String car_engine;
    private final Float Idv_value;
    private final String cust_name;
    private final String year;

    public String getCar_chasis() {
        return car_chasis;
    }

    public Float getIdv_value() {
        return Idv_value;
    }

    public String getCust_name() {
        return cust_name;
    }

    public String getYear() {
        return year;
    }

    public Party getIRDA() {
        return IRDA;
    }

    public Party getInsuarance_company() {
        return insuarance_company;
    }


    //private final String cust_contact;
    private final Party IRDA;
    private final Party insuarance_company;

// start IOUFlow arg0: 78, arg1: varnit, arg2: 2010, arg3: 100000, arg4: O=PartyA,L=London,C=GB, arg5: O=IRDA,L=New York,C=US
    public IOUState(String car_chasis, String cust_name, String year, Float Idv_value,Party IRDA, Party insuarance_company) {
        this.car_chasis = car_chasis;
        this.IRDA=IRDA;
        this.Idv_value=Idv_value;
        this.cust_name=cust_name;
        this.year=year;
        this.insuarance_company = insuarance_company;

    }


    @Override
    public List<AbstractParty> getParticipants() {
        return ImmutableList.of(IRDA,insuarance_company);
    }

    /** The public keys of the involved parties. */

}