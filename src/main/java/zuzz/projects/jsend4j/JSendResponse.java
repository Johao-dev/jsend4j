package zuzz.projects.jsend4j;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Abstract base class for all JSend responses.
 * <p>
 * This class uses Jackson annotations to ensure proper JSON serialization.
 * Fields compliant with the JSend specification are exposed here.
 * </p>
 *
 * @param <T> The type of the data payload.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class JSendResponse<T> {

    private final JSendStatus status;

    protected JSendResponse(JSendStatus status) {
        this.status = status;
    }

    public JSendStatus getStatus() {
        return status;
    }

    public abstract T getData();
    public abstract String getMessage();
    public abstract Integer getCode();
}
