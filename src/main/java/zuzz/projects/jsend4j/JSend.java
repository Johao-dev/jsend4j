package zuzz.projects.jsend4j;

/**
 * Facade class for creating JSend compliant responses.
 * <p>
 * This class provides static factory methods to generate success, fail, and error responses
 * without exposing the underlying implementation classes.
 * </p>
 *
 * @author Johao-dev
 * @version 1.0.0
 */
public class JSend {

    /**
     * Creates a success response.
     * Used when an API call completes successfully and returns data.
     *
     * @param data The data wrapper (wrapper for the specific results).
     * @param <T>  The type of the data.
     * @return A {@link JSendResponse} with status "success".
     */
    public static <T> JSendResponse<T> success(T data) {
        return new SuccessResponse<T>(data);
    }

    /**
     * Creates a success response without data.
     * Used when an API call completes successfully but returns no data (null).
     *
     * @return A {@link JSendResponse} with status "success" and null data.
     */
    public static JSendResponse<Object> success() {
        return new SuccessResponse<>(null);
    }

    /**
     * Creates a fail response.
     * Used when there is a problem with the data submitted, or some pre-condition of the API call is not satisfied.
     *
     * @param data The wrapper for the details of why the request failed (e.g., validation errors).
     * @param <T>  The type of the failure data.
     * @return A {@link JSendResponse} with status "fail".
     */
    public static <T> JSendResponse<T> fail(T data) {
        return new FailResponse<T>(data);
    }

    /**
     * Creates an error response.
     * Used when an error occurred in processing the request, i.e., an exception was thrown.
     *
     * @param message A meaningful, human-readable message explaining what went wrong.
     * @param <T>     The type of the optional data.
     * @return A {@link JSendResponse} with status "error".
     */
    public static <T> JSendResponse<T> error(String message) {
        return new ErrorResponse<T>(message, null, null);
    }

    /**
     * Creates an error response with an optional numeric code.
     *
     * @param message A meaningful, human-readable message.
     * @param code    A numeric code corresponding to the error, if applicable.
     * @param <T>     The type of the optional data.
     * @return A {@link JSendResponse} with status "error".
     */
    public static <T> JSendResponse<T> error(String message, Integer code) {
        return new ErrorResponse<>(message, code, null);
    }

    /**
     * Creates an error response with code and optional data.
     *
     * @param message A meaningful, human-readable message.
     * @param code    A numeric code corresponding to the error.
     * @param data    A generic container for any other information about the error.
     * @param <T>     The type of the optional data.
     * @return A {@link JSendResponse} with status "error".
     */
    public static <T> JSendResponse<T> error(String message, Integer code, T data) {
        return new ErrorResponse<>(message, code, data);
    }
}
