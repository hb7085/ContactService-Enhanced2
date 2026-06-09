package contacttest;

import org.junit.jupiter.api.Test;

import contactapp.Contact;

import static org.junit.jupiter.api.Assertions.*;

import contactapp.InvalidContactException;
//This class tests the Contact class itself.
public class ContactTest {

    @Test
    public void testValidContactCreation() {
    	// Create a valid contact and verify the fields were set correctly.
        Contact contact = new Contact("ABC1234567", "John", "Doe", "1234567890", "123 Main St");
        assertEquals("John", contact.getFirstName());
        assertEquals("Doe", contact.getLastName());
        assertEquals("1234567890", contact.getPhone());
        assertEquals("123 Main St", contact.getAddress());
    }

    @Test
    public void testInvalidContactId() {
    	// Passing null should trigger an exception.
        assertThrows(InvalidContactException.class, () -> {
            new Contact(null, "John", "Doe", "1234567890", "123 Main St");
        });
    }

    @Test
    public void testInvalidPhoneNumber() {
    	// Phone number must be exactly 10 digits.
        assertThrows(InvalidContactException.class, () -> {
            new Contact("ABC1234567", "John", "Doe", "12345", "123 Main St");
        });
    }

    @Test
    public void testSettersValidation() {
        Contact contact = new Contact("ABC1234567", "John", "Doe", "1234567890", "123 Main St");
        // Invalid updates should also throw exceptions.
        assertThrows(InvalidContactException.class, () -> contact.setFirstName(null));
        assertThrows(InvalidContactException.class, () -> contact.setPhone("abc"));
    }
}