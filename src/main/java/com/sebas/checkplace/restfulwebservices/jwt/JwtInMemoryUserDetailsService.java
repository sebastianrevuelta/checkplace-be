package com.sebas.checkplace.restfulwebservices.jwt;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtInMemoryUserDetailsService implements UserDetailsService {

  static List<JwtUserDetails> inMemoryUserList = new ArrayList<>();

  static {
    inMemoryUserList.add(new JwtUserDetails(1L, "admin",
        "$2a$10$sexbJzmzDJ8KBnnkGvJr4.5vxWWs/YmtKFFIBvLuROzTgJQnJ4riu", "ROLE_USER_1"));
    
    inMemoryUserList.add(new JwtUserDetails(2L, "sebas",
            "$2a$10$klFrnGdKxUlBCuO6onyDjOFBeuIWHSD2QW5qJZ6M.DBdm8jMqs5ai", "ROLE_USER_2"));
    
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<JwtUserDetails> findFirst = inMemoryUserList.stream()
        .filter(user -> user.getUsername().equals(username)).findFirst();

    if (!findFirst.isPresent()) {
      throw new UsernameNotFoundException(String.format("USER_NOT_FOUND '%s'.", username));
    }

    return findFirst.get();
  }

}


