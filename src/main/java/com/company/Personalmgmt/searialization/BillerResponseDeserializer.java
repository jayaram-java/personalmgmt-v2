/**
 * 
 */
package com.company.Personalmgmt.searialization;

import java.io.IOException;

import com.company.Personalmgmt.dto.BillerDTO;
import com.company.Personalmgmt.dto.BillerDetails;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

/**
 * This class is used for
 *
 * @author Jayaram
 */

public class BillerResponseDeserializer extends StdDeserializer<BillerDTO> {

	public BillerResponseDeserializer() {
		this(null);
	}

	public BillerResponseDeserializer(Class<?> vc) {
		super(vc);
	}

	  @Override
	    public BillerDTO deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
	        JsonNode node = jp.getCodec().readTree(jp);

	        BillerDTO dto = new BillerDTO();
	        dto.setRequestId(node.get("requestId").asText());
	        dto.setResponseStatus(node.get("responseStatus").asText());
	        dto.setTimestamp(node.get("timestamp").asText());

	        BillerDetails billerDetails = new BillerDetails();
	        JsonNode billerNode = node.get("billers").get(0); 
	        billerDetails.setPayeeId(billerNode.get("billerId").asText());
	        billerDetails.setPayeeName(billerNode.get("billerName").asText());
	        billerDetails.setNickName(billerNode.get("nickName").asText());
	        billerDetails.setAccountNumber(billerNode.get("accountNumber").asText());
	        billerDetails.setStatus(billerNode.get("status").asText());
	        billerDetails.setAddressLine1(billerNode.get("address").get("addressLine1").asText());
	        billerDetails.setCity(billerNode.get("address").get("city").asText());
	        billerDetails.setState(billerNode.get("address").get("state").asText());
	        billerDetails.setZipCode(billerNode.get("address").get("zipCode").asText());
	        billerDetails.setCountry(billerNode.get("address").get("country").asText());
	        billerDetails.setBusinessDaysToDeliver(billerNode.get("BusinessDaysToDeliver").asText());
	        billerDetails.setEbillSupport(billerNode.get("ebillSupport").asText());
	        billerDetails.seteBillStatus(billerNode.get("eBillStatus").asText());
	        billerDetails.setNextAvailablePaymentDeliveryDate(billerNode.get("NextAvailablePaymentDeliveryDate").asText());
	        billerDetails.setPaymentMethod(billerNode.get("paymentMethod").asText());
	        billerDetails.setIsManuallyAdded(" "); 

	        dto.setBiller(billerDetails);

	        return dto;
	    }

}
