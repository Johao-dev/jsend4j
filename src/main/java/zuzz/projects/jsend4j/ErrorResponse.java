package zuzz.projects.jsend4j;

/**
 * Implementation of an error JSend response (system exceptions, crashes).
 * Visible only within the package.
 */
class ErrorResponse<T> extends JSendResponse<T> {

    private final String message;
    private final Integer code;
    private final T data;

    public ErrorResponse(String message, Integer code, T data) {
        super(JSendStatus.ERROR);
        this.message = message;
        this.code = code;
        this.data = data;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public T getData() {
        return data;
    }
}
