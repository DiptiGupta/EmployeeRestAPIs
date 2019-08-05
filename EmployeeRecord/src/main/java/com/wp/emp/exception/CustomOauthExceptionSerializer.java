package com.wp.emp.exception;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.wp.emp.util.ConstantVariables;


import java.io.IOException;
import java.util.Map;

public class CustomOauthExceptionSerializer extends StdSerializer<CustomOAuth2Exception> {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomOauthExceptionSerializer() {
        super(CustomOAuth2Exception.class);
    }

    @Override
    public void serialize(CustomOAuth2Exception value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeStringField(ConstantVariables.REQUEST_STATUS, ConstantVariables.REQUEST_FAIL);
        gen.writeStringField(ConstantVariables.RESPONSE_MSG, value.getMessage());
        gen.writeStringField(ConstantVariables.RESPONSE_CODE,ConstantVariables.INVALID_ADMIN_DETAIL);
        if (value.getAdditionalInformation()!=null) {
            for (Map.Entry<String, String> entry : value.getAdditionalInformation().entrySet()) {
                String key = entry.getKey();
                String add = entry.getValue();
                gen.writeStringField(key, add);
            }
        }
        gen.writeEndObject();
    }
}