package com.example.utility;

import org.springframework.stereotype.Component;

import com.example.response.model.ResponseEntityDTO;


@Component
public class ResponseConversionUtility {

	public ResponseEntityDTO createResponseEntityDTO(HttpStatusCodes httpStatusCodes, String message, Object body) {
		return ResponseEntityDTO.response().withResponseCode(httpStatusCodes).withResponseMessage(message)
				.withResponseBody(body).build();
	}
}
