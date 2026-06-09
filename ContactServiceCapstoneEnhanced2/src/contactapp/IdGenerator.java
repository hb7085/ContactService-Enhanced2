package contactapp;

import java.util.UUID;

/**
 * Utility class responsible for generating unique contact IDs.
 * This keeps ID creation separate from business logic.
 */
public class IdGenerator {

    /**
     * Generates a unique ID with a maximum length of 10 characters.
     * Uses UUID to ensure uniqueness.
     */
    public static String generateId() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 10);
    }
}
