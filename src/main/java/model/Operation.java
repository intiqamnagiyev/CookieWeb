package model;

public class Operation {
    private long id;
    private String username;
    private String x;
    private String y;
    private String op;
    private String result;

    public Operation() {
    }

    public Operation(String x, String y, String op, String result) {
        this.x = x;
        this.y = y;
        this.op = op;
        this.result = result;
    }

    public Operation(long id, String username, String x, String y, String op, String result) {
        this(x, y, op, result);
        this.id = id;
        this.username = username;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return String.format("Operation{id=%d, username='%s', x='%s', y='%s', op='%s', result='%s'}"
                , id, username, x, y, op, result);
    }
}
