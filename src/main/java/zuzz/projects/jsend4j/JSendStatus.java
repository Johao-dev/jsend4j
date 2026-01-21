package zuzz.projects.jsend4j;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Enumeration representing the valid statuses of a JSend response.
 * According to the JSend specification, only "success", "fail", and "error" are allowed.
 */
public enum JSendStatus {

    SUCCESS,
    FAIL,
    ERROR;

    @JsonValue
    public String toValue() {
        return this.name().toLowerCase();
    }
}
