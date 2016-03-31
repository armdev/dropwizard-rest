package com.project.dropwizard.resources;

import com.project.dropwizard.dao.ContactDAO;
import com.project.dropwizard.model.Contact;
import java.net.URI;
import java.net.URISyntaxException;
import javax.validation.Validator;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import org.skife.jdbi.v2.DBI;

@Path("/contact")
@Produces(MediaType.APPLICATION_JSON)
public class ContactResource {

    private final ContactDAO contactDao;
    private final Validator validator;

    public ContactResource(DBI jdbi, Validator validator) {
        contactDao = jdbi.onDemand(ContactDAO.class);
        this.validator = validator;

    }

    @GET
    @Path("/{id}")
    public Response getContact(@PathParam("id") int id) {
        // retrieve information about the contact with the provided id
        Contact contact = contactDao.getContactById(id);
        return Response
                .ok(contact)
                .build();
    }

    @POST
   // @Path("/{save}")
    public Response createContact(Contact contact) throws URISyntaxException {
        // store the new contact
        int newContactId = contactDao.createContact(contact.getFirstName(), contact.getLastName(), contact.getPhone());
        return Response.created(new URI(String.valueOf(newContactId))).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteContact(@PathParam("id") int id) {
        // delete the contact with the provided id
        contactDao.deleteContact(id);
        return Response.noContent().build();
    }

    @PUT
    @Path("/{id}")
    public Response updateContact(@PathParam("id") int id, Contact contact) {
        // update the contact with the provided ID
        contactDao.updateContact(id, contact.getFirstName(),
                contact.getLastName(), contact.getPhone());
        return Response.ok(
                new Contact(id, contact.getFirstName(), contact.getLastName(),
                        contact.getPhone())).build();
    }

}
