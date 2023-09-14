/**
 * 
 */
package com.company.Personalmgmt.searialization;

import java.io.IOException;
import java.util.List;

import com.company.Personalmgmt.dto.Payee;
import com.company.Personalmgmt.dto.PayeeDetails;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

/**
 * This class is used for
 *
 * @author Jayaram
 */

public class PayeeDeserializer extends StdDeserializer<PayeeDetails> {

	public PayeeDeserializer() {
		this(null);
	}

	public PayeeDeserializer(Class<?> vc) {
		super(vc);
	}

	@Override
	public PayeeDetails deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
			throws IOException, JacksonException {

		JsonNode rootNode = jsonParser.getCodec().readTree(jsonParser);

		// Extract values from the JSON nodes
		String requestId = rootNode.get("requestId").asText();
		boolean backwardFlag = rootNode.get("backwardFlag").asBoolean();
		boolean forwardFlag = rootNode.get("forwardFlag").asBoolean();
		String responseStatus = rootNode.get("responseStatus").asText();
		String timestamp = rootNode.get("timestamp").asText();
		int httpStatusCode = rootNode.get("httpStatusCode").asInt();

		// Deserialize the "billers" array
		List<Payee> billers = deserializationContext.readValue(rootNode.get("billers").traverse(),
				deserializationContext.getTypeFactory().constructCollectionType(List.class, Payee.class));

		// Create and return a payeeDetails object
		PayeeDetails payeeDetails = new PayeeDetails();
		payeeDetails.setRequestId(requestId);
		payeeDetails.setBackwardFlag(backwardFlag);
		payeeDetails.setForwardFlag(forwardFlag);
		payeeDetails.setResponseStatus(responseStatus);
		payeeDetails.setTimestamp(timestamp);
		payeeDetails.setHttpStatusCode(httpStatusCode);
		payeeDetails.setBillers(billers);

		return payeeDetails;

	}

}
