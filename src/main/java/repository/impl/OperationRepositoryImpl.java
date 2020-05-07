package repository.impl;

import sql.Sql;
import jdbc.JdbcConnection;
import model.Operation;
import repository.OperationRepository;
import session.Session;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OperationRepositoryImpl implements OperationRepository {
    @Override
    public void save(Operation operation) {
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = JdbcConnection.getConnection();
            connection.setAutoCommit(false);

            ps = connection.prepareStatement(Sql.INSERT_HISTORY, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, operation.getX());
            ps.setString(2, operation.getOp());
            ps.setString(3, operation.getY());
            ps.setString(4, operation.getResult());
            int i = ps.executeUpdate();
            if (i > 0) {
                ResultSet generatedKeys = ps.getGeneratedKeys();
                while (generatedKeys.next()) {
                    long id = generatedKeys.getLong("id");
                    ps = connection.prepareStatement(Sql.INSERT_USER_HISTORY, Statement.RETURN_GENERATED_KEYS);
                    ps.setLong(1, Session.getUser().getId());
                    ps.setLong(2, id);
                    ps.executeUpdate();
                }
            }

        } catch (SQLException sx) {
            try {
                assert connection != null;
                connection.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            sx.printStackTrace();
        } finally {
            try {
                assert connection != null;
                connection.commit();
                assert ps != null;
                ps.close();
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }

    @Override
    public List<Operation> getOperationsByUser(long id) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Operation> operations = new ArrayList<>();
        try {
            connection = JdbcConnection.getConnection();
            connection.setAutoCommit(false);
            ps = connection.prepareStatement(Sql.GET_HISTORY_WITH_USER);
            ps.setLong(1,id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Operation operation = getOperationFromResultset(rs);
                operations.add(operation);
            }
        } catch (SQLException sx) {
            sx.printStackTrace();
        }finally {
            try {
                assert connection != null;
                connection.commit();
                assert ps != null;
                ps.close();
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    return operations;
    }

    private Operation getOperationFromResultset(ResultSet rs) throws SQLException {
        Operation operation = new Operation();
        operation.setUsername(rs.getString("name"));
        operation.setX(rs.getString("number1"));
        operation.setY(rs.getString("number2"));
        operation.setOp(rs.getString("operation"));
        operation.setResult(rs.getString("result"));
        return operation;
    }
}
