package com.pradipta.todo.security;

import com.pradipta.todo.user.User;
import com.pradipta.todo.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository repo;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> userOptional = repo.findByUsername(s);
        userOptional.orElseThrow(() -> new UsernameNotFoundException("Not found "+s));
        User user = userOptional.get();
        return new MyUserDetails(user);
    }
}
