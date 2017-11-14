package com.realdolmen.payment.services;

import com.realdolmen.payment.domain.CreditCard;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Variant;
import java.util.List;

@Path("creditcard")
@RequestScoped
public class CreditCardService {

    @PersistenceContext
    private EntityManager entityManager;

//    @GET
//    @Path("{ccid}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public CreditCard findCreditCardById(@PathParam("ccid") Integer ccid) {
//        return entityManager.find(CreditCard.class, ccid);
//    }

//    @GET
//    @Path("ccnumber/{cardNumber}")
//    public Response findCreditCardByCardNumber(@PathParam("cardNumber") String cardNumber) {
//        CreditCard cc;
//
//        try {
//            cc = entityManager
//                    .createQuery("select c from credit_card c where c.cardNumber='" + cardNumber + "'", CreditCard.class)
//                    .getSingleResult();
//        } catch (Exception e) {
//            Response.ResponseBuilder builder = Response.serverError();
//            builder.entity("false");
//            throw new WebApplicationException(builder.build());
//        }
//
//        Response.ResponseBuilder builder = Response.ok();
//        builder.entity("true");
//        return builder.build();
//    }

//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public List<CreditCard> findAllCreditCards() {
//        return entityManager
//                .createQuery("select c from credit_card c", CreditCard.class)
//                .getResultList();
//    }

    // TODO: accept json requests
    @POST
    @Path("validation")
    public Response validate(@FormParam("cardNumber") String cardNumber,
                             @FormParam("name") String name,
                             @FormParam("validDate") String validDate,
                             @FormParam("ccv") String ccv) {
        CreditCard cc;

        try {
            System.out.println(cardNumber);
            cc = entityManager
                    .createQuery("select c from credit_card c where c.cardNumber='" + cardNumber + "'", CreditCard.class)
                    .getSingleResult();
        } catch (Exception e) {
            Response.ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
            builder.entity("false\nCreditcard does not exist.");
            throw new WebApplicationException(builder.build());
        }

        if (!cc.getName().equals(name)) {
            Response.ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
            builder.entity("false\nName wrong.");
            throw new WebApplicationException(builder.build());
        } else if (!cc.getValidDate().equals(validDate)) {
            Response.ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
            builder.entity("false\nDate wrong.");
            throw new WebApplicationException(builder.build());
        } else if (!cc.getCcv().equals(ccv)) {
            Response.ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
            builder.entity("false\nCcv wrong.");
            throw new WebApplicationException(builder.build());
        } else {
            Response.ResponseBuilder builder = Response.ok();
            builder.entity("true\nCredit card accepted.");
            return builder.build();
        }

    }

}
