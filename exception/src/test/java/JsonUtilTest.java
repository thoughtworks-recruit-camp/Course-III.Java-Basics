import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JsonUtilTest {

    @Test
    void should_return_json_when_convert_to_json_given_valid_person() {
        Person person = new Person("Solider", 19);
        String json = JsonUtil.convertToJson(person);
        assertEquals("{\"name\":\"Solider\",\"age\":19}", json);
    }

    @Test
    void should_customize_exception_message() {
        try {
            throw new ValueReadException("Read value error");
        } catch (ValueReadException error) {
            assertEquals("Read value error", error.getMessage());
        }
    }

    @Test
    void should_customize_exception_cause() {
        Exception exception = new Exception("Read value error");
        try {
            throw new ValueReadException("Read value error", exception);
        } catch (ValueReadException error) {
            assertEquals("Read value error", error.getMessage());
            assertEquals(exception, error.getCause());
        }
    }

    @Test
    void test_try_catch_finally_block() {
        int expectedValue = 0;
        assertEquals(expectedValue, getValue(2));
    }

    private int getValue(int value) {
        try {
            return value * value;
        } catch (Exception e) {
            return value;
        } finally {
            if (value == 2) {
                return 0;
            }
        }
    }
}
