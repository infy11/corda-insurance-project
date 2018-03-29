package com.template;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import net.corda.core.contracts.StateAndRef;
import net.corda.core.identity.CordaX500Name;
import net.corda.core.identity.Party;
import net.corda.core.messaging.CordaRPCOps;
import net.corda.core.node.NodeInfo;
import net.corda.core.transactions.SignedTransaction;
import net.corda.core.transactions.TransactionBuilder;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import static java.util.stream.Collectors.toList;
import static javax.ws.rs.core.Response.Status.BAD_REQUEST;
import static javax.ws.rs.core.Response.Status.CREATED;

// This API is accessible from /api/template. The endpoint paths specified below are relative to it.
@Path("template")
public class TemplateApi {
    private final CordaRPCOps rpcOps;
    private final CordaX500Name myLegalName;

    private final List<String> serviceNames = ImmutableList.of("Notary", "Network Map Service");

    public TemplateApi(CordaRPCOps services) {
        this.rpcOps = services;
        this.myLegalName = rpcOps.nodeInfo().getLegalIdentities().get(0).getName();
    }

    /**
     * Accessible at /api/template/templateGetEndpoint.
     */
    @GET
    @Path("templateGetEndpoint")
    @Produces(MediaType.APPLICATION_JSON)
    public Response templateGetEndpoint() {
        return Response.ok("party a get point ").build();
    }

    @GET
    @Path("ious")
    @Produces(MediaType.APPLICATION_JSON)
    public List<StateAndRef<IOUState>> getIOUs() {
        return rpcOps.vaultQuery(IOUState.class).getStates();
    }



    @GET
    @Path("peers")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, List<CordaX500Name>> getPeers() {
        List<NodeInfo> nodeInfoSnapshot = rpcOps.networkMapSnapshot();
        return ImmutableMap.of("peers", nodeInfoSnapshot
                .stream()
                .map(node -> node.getLegalIdentities().get(0).getName())
                .filter(name -> !name.equals(myLegalName) && !serviceNames.contains(name.getOrganisation()))
                .collect(toList()));
    }

    @PUT
    @Path("create-iou")
    public Response createIOU(@QueryParam("chasis") String chasis,@QueryParam("cust_name") String custname,@QueryParam("year") String year,@QueryParam("idv_value") Float idv_value,@QueryParam("irda") CordaX500Name irda, @QueryParam("partyName") CordaX500Name  partyName) throws InterruptedException, ExecutionException {
        if (chasis ==null) {
            return Response.status(BAD_REQUEST).entity("Query parameter 'iouValue' must be non-negative.\n").build();
        }
        if (partyName == null) {
            return Response.status(BAD_REQUEST).entity("Query parameter 'partyName' missing or has wrong format.\n").build();
        }



        final Party otherParty = rpcOps.wellKnownPartyFromX500Name(partyName);
        final Party irdaParty=rpcOps.wellKnownPartyFromX500Name(irda);
        if (otherParty == null) {
            return Response.status(BAD_REQUEST).entity("Party named " + partyName + "cannot be found.\n").build();
        }
        if(irdaParty==null)
        {
            return Response.status(BAD_REQUEST).entity("irda named " + partyName + "cannot be found.\n").build();

        }

        try {


          // rpcOps.startTrackedFlowDynamic(IOUFlow.class,chasis,custname,year,idv_value,irdaParty,otherParty).getReturnValue().get();

           final SignedTransaction signedTx=rpcOps.startTrackedFlowDynamic(IOUFlow.class,chasis,custname,year,idv_value,irdaParty,otherParty)
                   .getReturnValue().get();

           //final SignedTransaction signedTx = rpcOps
            //        .startTrackedFlowDynamic(IOUFlow.class, iouValue, otherParty)
            //        .getReturnValue()
            //        .get();

            final String msg = String.format("Transaction id  committed to ledger."+signedTx.getId());
            return Response.status(CREATED).entity(msg).build();

        } catch (Throwable ex) {
            final String msg = "its in catch block "+ex.getMessage();

            return Response.status(BAD_REQUEST).entity(msg).build();
        }
    }



}
