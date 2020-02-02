package com.pradipta.todo.authentication;

import com.pradipta.todo.dto.AuthenticationRequest;
import com.pradipta.todo.dto.AuthenticationResponse;
import com.pradipta.todo.jedis.JwtCheckRepositoryImpl;
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

import javax.servlet.http.HttpServletRequest;

@RestController
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private JwtCheckRepositoryImpl repo;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public AuthenticationResponse authenticate(@RequestBody AuthenticationRequest request) throws Exception {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            System.out.println("Passed");
        } catch (BadCredentialsException ex) {
            System.out.println("Failing here");

        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());

        final String jwt = jwtUtil.generateToken(userDetails);

        AuthenticationResponse response = new AuthenticationResponse(jwt);

        return response;
    }

    @RequestMapping(value = "/signout", method = RequestMethod.PUT)
    public String logout(HttpServletRequest request) {
        final String authorization = request.getHeader("Authorization");
        String username = null;

        String jwt = null;
        if (authorization != null && authorization.startsWith("Bearer")) {
            jwt = authorization.substring(7);
            username = jwtUtil.extractUsername(jwt);
        }
        try {
            repo.putJwtInactive(jwt);
        } catch (Exception ex) {
            System.out.println(ex);
        }

        return "User "+username+" successfully loggoed out";
    }
}
