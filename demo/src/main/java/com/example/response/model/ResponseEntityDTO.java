package com.example.response.model;

import com.example.utility.HttpStatusCodes;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "response body")
public class ResponseEntityDTO {

	@ApiModelProperty(name = "response_code")
	int response_code;
	@ApiModelProperty(name = "response_message")
	String response_message;
	@ApiModelProperty(name = "response_body")
	Object response_body;

	public ResponseEntityDTO(int response_code, String response_message, Object response_body) {
		super();
		this.response_code = response_code;
		this.response_message = response_message;
		this.response_body = response_body;
	}

	private ResponseEntityDTO(Builder builder) {
		response_code = builder.response_code;
		response_message = builder.response_message;
		response_body = builder.response_body;

	}

	public ResponseEntityDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getResponse_code() {
		return response_code;
	}

	public void setResponse_code(int response_code) {
		this.response_code = response_code;
	}

	public String getResponse_message() {
		return response_message;
	}

	public void setResponse_message(String response_message) {
		this.response_message = response_message;
	}

	public Object getResponse_body() {
		return response_body;
	}

	public void setResponse_body(Object response_body) {
		this.response_body = response_body;
	}

	public static Builder response() {
		return new Builder();
	}

	public static final class Builder {
		private int response_code;
		private String response_message;
		private Object response_body;

		private Builder() {
		}

		public Builder withResponseCode(HttpStatusCodes val) {
			response_code = val.value();
			return this;
		}

		public Builder withResponseMessage(String val) {
			response_message = val;
			return this;
		}

		public Builder withResponseBody(Object val) {
			response_body = val;
			return this;
		}

		public ResponseEntityDTO build() {
			return new ResponseEntityDTO(this);
		}

	}

}
