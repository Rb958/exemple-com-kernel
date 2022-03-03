package dbgateway.exception;

public class RepositoryException extends DdGatewayException{
    public RepositoryException(String message) {
        super(4011, message);
    }
}
