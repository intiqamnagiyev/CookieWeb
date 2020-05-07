package sql;

public class Sql {
    public static final String GET_USER_BY_EMAIL=" select * from users where email=? ";
    public static final String INSERT_HISTORY=" insert into history (number1, operation, number2, result) " +
            "  values (?, ?, ? ,?)  ";
    public static final String INSERT_USER_HISTORY=" insert into user_history (user_id, history_id) values (?, ?) ";

    public static final String GET_HISTORY_WITH_USER=" select u.name, h.number1, h.operation, h.number2, h.result from  " +
            " user_history uh  " +
            " join users u on uh.user_id = u.id  " +
            " join history h on uh.history_id = h.id  " +
            " where u.id=? ";

}
