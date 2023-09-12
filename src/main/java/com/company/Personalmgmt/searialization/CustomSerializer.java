/**
 * 
 */
package com.company.Personalmgmt.searialization;

import java.io.IOException;

import com.company.Personalmgmt.dto.KeyNotesDto;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

/**
* This class is used for 
*
* @author Jayaram
*/

public class CustomSerializer extends StdSerializer<KeyNotesDto>{

	/**
	 * @param t
	 * @param dummy
	 */
	protected CustomSerializer(Class<KeyNotesDto> t) {
		super(t);
		// TODO Auto-generated constructor stub
	}
	
	public CustomSerializer() {
		this(null);
	}

	@Override
	public void serialize(KeyNotesDto value, JsonGenerator gen, SerializerProvider provider) throws IOException {

		gen.writeStartObject();
		gen.writeStringField("Name__", value.getName());
		gen.writeStringField("Link", value.getLink());
		gen.writeStringField("parent", value.getParent());
		gen.writeEndObject();
		
	}

}
