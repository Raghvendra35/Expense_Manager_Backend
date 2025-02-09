package com.expense.controller;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.expense.dto.ProfileDTO;
import com.expense.io.AuthRequest;
import com.expense.io.AuthResponse;
import com.expense.io.ProfileRequest;
import com.expense.io.ProfileResponse;
import com.expense.service.ProfileService;
import com.expense.service.impl.CustomUserDetailsService;
import com.expense.service.impl.TokenBlackListService;
import com.expense.util.JwtTokenUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthController {

	
	private final ModelMapper modelMapper;
	private final ProfileService profileService;
	private final AuthenticationManager authenticationManager;
	private final JwtTokenUtil jwtTokenUtil;
	private final CustomUserDetailsService customUserDetailsService;
	
	private final TokenBlackListService tokenBlackListService;
	
	/**
	 * API endpoint to register new user
	 * @param profileRequest
	 * @return profileResponse
	 * **/
    @ResponseStatus(HttpStatus.CREATED)	
	@PostMapping("/register")
	public ProfileResponse createProfile(@Valid @RequestBody ProfileRequest profileRequest)
	{
		log.info("API /register is called {}", profileRequest);
		ProfileDTO profileDTO=mapToProfileDTO(profileRequest);
		profileDTO=profileService.createProfile(profileDTO);
	    log.info("Printing the profile dto details {}", profileDTO);
		return mapToProfileResponse(profileDTO);
	}

    
      @PostMapping("/login")    
      public AuthResponse authenticateProfile(@RequestBody AuthRequest authRequest) throws Exception
      {
    	  log.info("API /login is called {} "+ authRequest);
//    	  authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(),authRequest.getPassword()));
    	  authenticate(authRequest);
    	  final UserDetails userDetails=customUserDetailsService.loadUserByUsername(authRequest.getEmail());
    	  
    	  String jwtToken=jwtTokenUtil.generateToken(userDetails);
    	  return new AuthResponse(jwtToken,authRequest.getEmail());
      }

    
      
    private void authenticate(AuthRequest authRequest) throws Exception
    {
        try {		
      	  authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(),authRequest.getPassword()));

        }catch(DisabledException ex)
        {
        	throw new Exception("Profile disabled...");
        }catch(BadCredentialsException ex) {
        	throw new Exception("Bad Credentials..");
        }
	}

    
    
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("/signout")
    public void signout(HttpServletRequest request) {
    	String jwttoken=extractTokenFromRequest(request);
    	if(jwttoken != null) {
    		tokenBlackListService.addTokenToBlackList(jwttoken);
    	  }
       }
       
    private String extractTokenFromRequest(HttpServletRequest request) {
                           
    	String beararToken=request.getHeader("Authorization");
    	if(beararToken != null && beararToken.startsWith("Bearar "))
    	{
    		return beararToken.substring(7);
    	}
    	return null;
	}


	/**
	 * Mapper method to map values from profileDTO to profileResponse
	 * @param profileDTO
	 * @return profileResponse
	 * **/
	private ProfileResponse mapToProfileResponse(ProfileDTO profileDTO) {
		return modelMapper.map(profileDTO, ProfileResponse.class);
	}

	/**
	 * Mapper method to map values from profileRequest to profileDTO
	 * @param profileRequest
	 * @return profileDTO
	 * **/
	private ProfileDTO mapToProfileDTO(@Valid ProfileRequest profileRequest) {
		
		return modelMapper.map(profileRequest, ProfileDTO.class);
	}



}
