package com.example.controller;

import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.RegisterDTO;
import com.example.model.User;
import com.example.repository.UserRepository;
import com.example.security.JwtTokenUtil;
import com.example.services.RegisterService;
import com.example.utility.HttpStatusCodes;
import com.example.utility.ResponseConversionUtility;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@Api(description = "booking controller")
public class BookingController {

	private Log log = LogFactory.getLog(BookingController.class);
	@Autowired
	private ResponseConversionUtility conversionUtility;

	@Autowired
	private UserRepository userRepository;

	@Value("${jwt.header}")
	private String tokenHeader;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@RequestMapping(value = "${api.route.booking}", method = RequestMethod.POST)
	@ApiOperation(value = "book user", notes = "gets the booking details")
	public ResponseEntity<?> createLoginCredentailsToken(@ApiParam("username") @RequestParam String username,
			HttpServletRequest httpServletRequest) throws ParseException {

		String authToken = httpServletRequest.getHeader(this.tokenHeader);
		String email = jwtTokenUtil.getUsernameFromToken(authToken);
		if (username.equals(email))
			return ResponseEntity
					.ok(conversionUtility.createResponseEntityDTO(HttpStatusCodes.OK, "You are valid user", null));

		return ResponseEntity.ok(
				conversionUtility.createResponseEntityDTO(HttpStatusCodes.VALIDATION_ERROR, "Register first", null));

	}
}
