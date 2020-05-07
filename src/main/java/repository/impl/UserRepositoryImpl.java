package repository.impl;

import sql.Sql;
import jdbc.JdbcConnection;
import model.User;
import repository.UserRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {

    @Override
    public Optional<User> getUserByEmail(String email){
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Optional<User> optionalUser= Optional.empty();
        try {
            connection= JdbcConnection.getConnection();
            ps = connection.prepareStatement(Sql.GET_USER_BY_EMAIL);
            ps.setString(1,email);
            rs = ps.executeQuery();
            while (rs.next()){
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                optionalUser=Optional.of(user);
            }
        } catch (SQLException sqlx) {
            throw  new RuntimeException("sql ex");
        }
        finally {
            try {
                assert rs != null;
                rs.close();
                ps.close();
                connection.close();
            }catch (Exception x){
               x.printStackTrace();
            }
        }
        return optionalUser;
    }
}
