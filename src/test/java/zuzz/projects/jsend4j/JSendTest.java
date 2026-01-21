package zuzz.projects.jsend4j;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

class JSendTest {

    private static ObjectMapper serializer;

    @BeforeAll
    static void setup() {
        serializer = new ObjectMapper();
    }

    @Test
    @DisplayName("Should serialize SUCCESS response correctly")
    void testSuccess() throws JsonProcessingException {
        Map<String, String> data = new HashMap<>();
        data.put("post_id", "123");

        JSendResponse<Map<String, String>> response = JSend.success(data);
        String json = serializer.writeValueAsString(response);

        assertEquals(JSendStatus.SUCCESS, response.getStatus());
        assertNotNull(response.getData());

        assertTrue(json.contains("\"status\":\"success\""));
        assertTrue(json.contains("\"post_id\":\"123\""));

        assertFalse(json.contains("\"message\""));
        assertFalse(json.contains("\"code\""));
    }

    @Test
    @DisplayName("Should serialize SUCCESS response without data")
    void testSuccessWithoutData() throws JsonProcessingException {
        JSendResponse<Object> response = JSend.success();
        String json = serializer.writeValueAsString(response);

        assertEquals(JSendStatus.SUCCESS, response.getStatus());
        assertTrue(json.contains("\"status\":\"success\""));
        assertFalse(json.contains("\"data\""));
    }

    @Test
    @DisplayName("Should serialize FAIL response correctly")
    void testFail() throws JsonProcessingException {
        Map<String, String> errors = new HashMap<>();
        errors.put("title", "Is required");

        JSendResponse<Map<String, String>> response = JSend.fail(errors);
        String json = serializer.writeValueAsString(response);

        assertEquals(JSendStatus.FAIL, response.getStatus());
        assertEquals("Is required", response.getData().get("title"));

        assertTrue(json.contains("\"status\":\"fail\""));
        assertTrue(json.contains("\"title\":\"Is required\""));
    }

    @Test
    @DisplayName("Should serialize ERROR response with message only")
    void testErrorWithMessageOnly() throws JsonProcessingException {
        JSendResponse<Object> response = JSend.error("Internal error");
        String json = serializer.writeValueAsString(response);

        assertEquals(JSendStatus.ERROR, response.getStatus());
        assertEquals("Internal error", response.getMessage());

        assertTrue(json.contains("\"status\":\"error\""));
        assertTrue(json.contains("\"message\":\"Internal error\""));
        assertFalse(json.contains("\"code\""));
        assertFalse(json.contains("\"data\""));
    }

    @Test
    @DisplayName("Should serialize ERROR response with message and code")
    void testError() throws JsonProcessingException {
        JSendResponse<Object> response = JSend.error("Unable to communicate with database", 500);
        String json = serializer.writeValueAsString(response);

        assertEquals(JSendStatus.ERROR, response.getStatus());
        assertEquals("Unable to communicate with database", response.getMessage());
        assertEquals(500, response.getCode());

        assertTrue(json.contains("\"status\":\"error\""));
        assertTrue(json.contains("\"code\":500"));
        assertFalse(json.contains("\"data\""));
    }

    @Test
    @DisplayName("Should serialize ERROR response with message, code and data")
    void testErrorWithAllFields() throws JsonProcessingException {
        Map<String, String> details = Map.of("traceId", "abc123");

        JSendResponse<Map<String, String>> response = JSend.error("Fatal error", 500, details);

        String json = serializer.writeValueAsString(response);

        assertEquals(JSendStatus.ERROR, response.getStatus());
        assertEquals(500, response.getCode());
        assertEquals("abc123", response.getData().get("traceId"));

        assertTrue(json.contains("\"traceId\":\"abc123\""));
    }

    @Test
    @DisplayName("JSendStatus should serialize to lowercase values")
    void testStatusEnumSerialization() throws JsonProcessingException {
        String json = serializer.writeValueAsString(JSendStatus.SUCCESS);
        assertEquals("\"success\"", json);
    }
}
