package dbgateway.exception;

public class DdGatewayException extends Exception{
    protected final int code;

    public DdGatewayException(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
