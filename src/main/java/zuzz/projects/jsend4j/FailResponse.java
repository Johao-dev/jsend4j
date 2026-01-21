package zuzz.projects.jsend4j;

/**
 * Implementation of a failed JSend response (validation errors, etc.).
 * Visible only within the package.
 */
class FailResponse<T> extends JSendResponse<T> {

    private final T data;

    protected FailResponse(T data) {
        super(JSendStatus.FAIL);
        this.data = data;
    }

    @Override
    public T getData() {
        return data;
    }

    @Override
    public String getMessage() {
        return null;
    }

    @Override
    public Integer getCode() {
        return null;
    }
}
