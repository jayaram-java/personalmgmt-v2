package com.company.Personalmgmt.resource;

import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.beans.factory.annotation.Autowired;

import com.company.Personalmgmt.model.WebSiteLinkDetails;
import com.company.Personalmgmt.service.WebSiteLinkService;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "websitelinkdetails")
@Path("/websitelinkdetails")
public class WebSiteLinkResource {

	@Autowired
	WebSiteLinkService webSiteLinkService;

	@POST
	@Consumes("application/json") // The @Consumes annotation is used to specify
									// the MIME media types of representations a
									// resource can consume that were sent by
									// the client.
	public Response saveWebLinkDetails(WebSiteLinkDetails webSiteLinkDetails) throws URISyntaxException {

		boolean response = webSiteLinkService.saveWebLinkDetails(webSiteLinkDetails);

		return Response.status(201).entity("Web site linke is created successfully. Response : " + response).build();

	}

	@GET
	@Produces(MediaType.APPLICATION_JSON) // The @Produces annotation is used to
											// specify the MIME media types of
											// representations a resource can
											// produce and send back to the
											// client: for example,
											// "text/plain".
	public Response retrieveLastData() throws URISyntaxException {
		WebSiteLinkDetails websitedetails = webSiteLinkService.retrieveLastData();

		return Response.status(200).entity(websitedetails).contentLocation(new URI("/websitelinkdetails/")).build();
	}

}
