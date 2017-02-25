package org.rmt2.rest.media;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;
import org.rmt2.jaxb.MimeContentType;
import org.rmt2.jaxb.MultimediaResponse;
import org.rmt2.jaxb.ObjectFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Path("/media")
public class DocumentMediaResource {
    private static final Logger LOGGER = Logger
            .getLogger(DocumentMediaResource.class);

    public DocumentMediaResource() {

    }

    @GET
    @Path("forms/{contentId}/image-attachment")
    // @Produces({ "image/png", "image/jpeg", "image/gif", "application/pdf" })
    @Produces(MediaType.APPLICATION_JSON)
    public Response fetchImageContent(
            @PathParam("contentId") final long contentId) {
        LOGGER.info(
                "REST method, forms/{contentId}/image-attachment, was called");
        ObjectFactory f = new ObjectFactory();
        MultimediaResponse r = f.createMultimediaResponse();
        MimeContentType content = f.createMimeContentType();
        r.getContent().add(content);
        // Marshal to JSON
        final Gson gson = new GsonBuilder().create();
        return Response.status(Status.OK).type(MediaType.APPLICATION_JSON_TYPE)
                .entity(gson.toJson(r)).build();
    }

}