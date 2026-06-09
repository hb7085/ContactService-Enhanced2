package contacttest;

import org.junit.jupiter.api.Test;

import contactapp.ContactService;
import contactapp.Contact;

import static org.junit.jupiter.api.Assertions.*;




//This class tests the ContactService to make sure it behaves correctly.
public class ContactServiceTest {

    @Test
    public void testAddContact() {
    	// Create a new service and add a contact.
        ContactService service = new ContactService();
        service.addContact("Jane", "Smith", "0987654321", "456 Elm St");
        // After adding, the list should contain exactly one contact.
        assertEquals(1, service.getContacts().size());
    }

    @Test
    public void testDeleteContact() {
        ContactService service = new ContactService();
        service.addContact("Jane", "Smith", "0987654321", "456 Elm St");
        // Get the ID of the contact we just added.
        String id = service.getContacts().get(0).getContactId();
        // Delete the contact and verify the list is empty.
        service.deleteContact(id);
        assertEquals(0, service.getContacts().size());
    }

    @Test
    public void testUpdateFields() {
        ContactService service = new ContactService();
        service.addContact("Jane", "Smith", "0987654321", "456 Elm St");
        String id = service.getContacts().get(0).getContactId();
        // Update two fields.
        service.updateFirstName(id, "Alice");
        service.updatePhone(id, "1234567890");
        // Verify the updates were applied.
        Contact updated = service.getContacts().get(0);
        assertEquals("Alice", updated.getFirstName());
        assertEquals("1234567890", updated.getPhone());
    }
}