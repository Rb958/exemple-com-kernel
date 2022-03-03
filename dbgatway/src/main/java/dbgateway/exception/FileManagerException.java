package dbgateway.exception;

public class FileManagerException extends Exception {
    private int code;
    public FileManagerException(int code, String message) {
        super(message);
        this.code = code;
    }
}
