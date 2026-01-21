package zuzz.projects.jsend4j;

/**
 * Implementation of a successful JSend response.
 * Visible only within the package to enforce usage of the {@link JSend} facade.
 */
class SuccessResponse<T> extends JSendResponse<T> {

    private final T data;

    public SuccessResponse(T data) {
        super(JSendStatus.SUCCESS);
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
