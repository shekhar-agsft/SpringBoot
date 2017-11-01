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
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.RegisterDTO;
import com.example.model.User;
import com.example.repository.UserRepository;
import com.example.security.JwtAuthenticationRequest;
import com.example.security.JwtAuthenticationResponse;
import com.example.security.JwtTokenUtil;
import com.example.security.JwtUserDetailsServiceImpl;
import com.example.services.RegisterService;
import com.example.utility.HttpStatusCodes;
import com.example.utility.ResponseConversionUtility;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@Api(description = "registers a new user")
public class LoginRestController {

	private Log log = LogFactory.getLog(LoginRestController.class);
	@Autowired
	private ResponseConversionUtility conversionUtility;
	@Autowired
	private RegisterService RegisterService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserRepository userRepository;

	@Value("${jwt.header}")
	private String tokenHeader;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsServiceImpl jwtUserDetailsServiceImpl;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@RequestMapping(value = "${api.route.login}", method = RequestMethod.POST)
	@ApiOperation(value = "register user for login", notes = "gets the user details")
	public ResponseEntity<?> createLoginCredentailsToken(
			@ApiParam("username, fullname, email and password") @Valid @RequestBody RegisterDTO registerDTO,
			HttpServletRequest httpServletRequest, BindingResult bindingResult) throws ParseException {
		if (bindingResult.hasErrors())
			return ResponseEntity.ok(
					conversionUtility.createResponseEntityDTO(HttpStatusCodes.VALIDATION_ERROR, "UnRegistered", null));
		// String authtoken = httpServletRequest.getHeader(tokenHeader);
		String pass = bCryptPasswordEncoder.encode(registerDTO.getPassword());
		registerDTO.setPassword(pass);

		User user = RegisterService.resgisterUser(registerDTO);

		if (!user.get_id().isEmpty())
			return ResponseEntity.ok(conversionUtility.createResponseEntityDTO(HttpStatusCodes.VALIDATION_ERROR,
					"You already exixts", null));
		return ResponseEntity.ok(conversionUtility.createResponseEntityDTO(HttpStatusCodes.OK, "Registered", user));

	}

	@RequestMapping(value = "${jwt.route.authentication.path}", method = RequestMethod.POST)
	@ApiOperation(value = "Sign in", notes = "gets the user sign in")
	public ResponseEntity<?> Login(
			@ApiParam("username and password") @Valid @RequestBody JwtAuthenticationRequest authenticationRequest,
			BindingResult bindingResult) throws AuthenticationException, ParseException {
		if (bindingResult.hasErrors())
			return ResponseEntity.ok(conversionUtility.createResponseEntityDTO(HttpStatusCodes.VALIDATION_ERROR,
					"validation error", null));

		final Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
						authenticationRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);

		final UserDetails user = jwtUserDetailsServiceImpl.loadUserByUsername(authenticationRequest.getUsername());
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		if (encoder.matches(authenticationRequest.getPassword(), user.getPassword())) {
			User users = userRepository.findByEmail(user.getUsername());
			final String token = jwtTokenUtil.generateToken(users);
			System.out.println(token);
			// User user = userRepository.findByUsername(loginDetails.getUsername());
			User userobj = null;
			if (users == null)
				return ResponseEntity.ok(conversionUtility.createResponseEntityDTO(HttpStatusCodes.VALIDATION_ERROR,
						"Wrong credentails", userobj));

			userobj = RegisterService.loginUser(users, token);
			return ResponseEntity.ok(
					conversionUtility.createResponseEntityDTO(HttpStatusCodes.OK, "Logged in successfully", userobj));
		}

		return ResponseEntity.ok(
				conversionUtility.createResponseEntityDTO(HttpStatusCodes.VALIDATION_ERROR, "Wrong credentails", null));
	}

}
