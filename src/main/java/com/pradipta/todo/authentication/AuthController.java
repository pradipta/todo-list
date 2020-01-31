package com.pradipta.todo.authentication;

import com.pradipta.todo.dto.AuthenticationRequest;
import com.pradipta.todo.dto.AuthenticationResponse;
import com.pradipta.todo.jwt.JwtUtil;
import com.pradipta.todo.security.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public AuthenticationResponse authenticate(@RequestBody AuthenticationRequest request) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            System.out.println("Passed");
        } catch (BadCredentialsException ex) {
            System.out.println("Failing here");
            throw new Exception("Wrong creds", ex);
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());

        final String jwt = jwtUtil.generateToken(userDetails);

        AuthenticationResponse response = new AuthenticationResponse(jwt);

        return response;
    }
}
