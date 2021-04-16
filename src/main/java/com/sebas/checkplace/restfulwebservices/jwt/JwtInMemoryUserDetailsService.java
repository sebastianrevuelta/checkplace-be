package com.sebas.checkplace.restfulwebservices.jwt;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.sebas.checkplace.utilities.UtilDB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@Service
public class JwtInMemoryUserDetailsService implements UserDetailsService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    static List<JwtUserDetails> inMemoryUserList = new ArrayList<>();
    static List<JwtUserDetails> inMemoryUserListFromDB;

    static {
/*        inMemoryUserList.add(new JwtUserDetails(1L, "admin",
                "$2a$10$sexbJzmzDJ8KBnnkGvJr4.5vxWWs/YmtKFFIBvLuROzTgJQnJ4riu", "ROLE_USER_1"));

        inMemoryUserList.add(new JwtUserDetails(2L, "sebas",
                "$2a$10$klFrnGdKxUlBCuO6onyDjOFBeuIWHSD2QW5qJZ6M.DBdm8jMqs5ai", "ROLE_USER_2"));*/
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("Reading users from database");
        List<JwtUserDetails> inMemoryUserListFromDB = UtilDB.getUsersFromDB();
        logger.info("Reading " + inMemoryUserListFromDB.size() + " users");

        Optional<JwtUserDetails> findFirst = inMemoryUserListFromDB.stream()
                .filter(user -> user.getUsername().equals(username)).findFirst();

        if (!findFirst.isPresent()) {
            throw new UsernameNotFoundException(String.format("USER_NOT_FOUND '%s'.", username));
        }

        return findFirst.get();
    }

}


