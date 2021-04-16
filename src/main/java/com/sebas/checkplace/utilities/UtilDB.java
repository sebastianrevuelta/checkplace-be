package com.sebas.checkplace.utilities;

import com.sebas.checkplace.restfulwebservices.jwt.JwtUserDetails;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UtilDB {

    public static List<JwtUserDetails> getUsersFromDB() {

        List<JwtUserDetails> users = new ArrayList<JwtUserDetails>();

        String query = "select * from users";
        Connection conn = null;
        Statement stmt = null;
        String hostDB = System.getenv("MARIADB_HOST");
        String portDB = System.getenv("MARIADB_PORT");
        String user = System.getenv("MARIADB_USER");
        String password = System.getenv("MARIADB_PASS");

        String database = "checkplace";

        String connectionChain = "jdbc:mariadb://" + hostDB + ":" + portDB + "/" + database + "?serverTimezone=UTC&useUnicode=true&characterEncoding=UTF-8";

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            conn = DriverManager.getConnection(connectionChain, user, password);
            stmt = conn.createStatement();

            //SQL injection. No validation of malicious input
            ResultSet rs = stmt.executeQuery(query);

            List<String> list = new ArrayList<String>();
            while (rs.next()) {
                long id = rs.getRow();
                String username = rs.getString("username");
                String pass = rs.getString("password");
                users.add(new JwtUserDetails(id, username,
                        pass, "ROLE_USER_1"));
            }
        } catch (Exception e) {
            e.printStackTrace(); //Bad practice
        } finally {
            try {
                stmt.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace(); //Bad practice
            }
        }
        return users;
    }

}
