package dbgateway.exception;

public class DBConnectionConfigException extends DdGatewayException {
    public DBConnectionConfigException() {
        super(4010, "Bad Connection configuration : Check the database connection configuration");
    }

    public DBConnectionConfigException(String message) {
        super(4010, message);
    }
}
