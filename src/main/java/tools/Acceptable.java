package tools;

// Interface contains validation patterns
public interface Acceptable {
    // Customer ID: C, G, K followed by 4 digits
    public final String CUS_ID_VALID = "^[C|G|K|c|g|k]\\d{4}$";

    // Name: 2 to 25 chars
    public final String NAME_VALID = "^.{2,25}$";

    // Phone: 10 digits, starts with 0
    public final String PHONE_VALID = "^0\\d{9}$";

    // Email: Standard format
    public final String EMAIL_VALID = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    // Set Menu Code: 5 chars (e.g., PW001)
    public final String SET_MENU_CODE_VALID = "^.{5}$";
    
    // Check validation
    public static boolean isValid(String data, String pattern) {
        return data.matches(pattern);
    }
}