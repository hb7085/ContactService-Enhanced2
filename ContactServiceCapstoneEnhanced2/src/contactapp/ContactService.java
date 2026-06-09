package contactapp;

import java.util.ArrayList;

import java.util.Map;
import java.util.HashMap;

import contactapp.IdGenerator;

import java.util.logging.Logger;

/**
 * Service class responsible for managing Contact objects.
 * Provides operations for creating, updating, and deleting contacts.
 * Uses logging to track all operations for debugging and maintainability.<br><br>
 *
 * Enhancement 1 includes:
 * - Improved encapsulation and ID generation logic
 * - Improved documentation
 * - Custom exception and logging
 * - Added a CLI to make application more usable<br><br>
 *
 * Enhancement 2 includes:
 * - HashMap for O(1) lookup
 * - Sorting algorithms
 * - Partial-match searching
 */
public class ContactService {
	private static final Logger logger = Logger.getLogger(ContactService.class.getName());
	/** Stores all contacts currently managed by the service. */
    private ArrayList<Contact> contacts = new ArrayList<>();
    /** 
     * Enhancement 2: HashMap for O(1) contact lookup by ID.
     * Keeps contacts synchronized with the ArrayList.
     */
    private Map<String, Contact> contactMap = new HashMap<>();

    /**
     * Retrieves a contact using its unique ID in O(1) time.
     * This method uses the internal HashMap for fast lookup.
     *
     * @param id the unique contact ID
     * @return the matching Contact, or null if no contact exists with that ID
     */
    public Contact findContactById(String id) {
        return contactMap.get(id);
    }

    /**
     * Creates a new Contact with a generated unique ID and adds it to the list.
     *
     * @param firstName the contact's first name
     * @param lastName the contact's last name
     * @param phone the contact's phone number (must be 10 digits)
     * @param address the contact's address
     */
    public void addContact(String firstName, String lastName, String phone, String address) {
    	String id = IdGenerator.generateId();
    	logger.info("Creating new contact with ID: " + id);
    	
        Contact newContact = new Contact(id, firstName, lastName, phone, address);
        contacts.add(newContact);
        contactMap.put(id, newContact);

        
        logger.info("Contact added successfully: " + id);
    }
    /**
     * Deletes a contact that matches the given ID.
     * Logs whether the deletion was successful or if the ID was not found.
     *
     * @param contactId the ID of the contact to delete
     */
    public void deleteContact(String contactId) {
        logger.info("Attempting to delete contact with ID: " + contactId);

        contactMap.remove(contactId);
        boolean removed = contacts.removeIf(c -> c.getContactId().equals(contactId));

        if (removed) {
            logger.info("Contact deleted: " + contactId);
        } else {
            logger.warning("Delete failed — no contact found with ID: " + contactId);
        }
    }

    /**
     * Updates the first name of the contact with the given ID.
     * Logs success or failure if the ID does not exist.
     *
     * @param contactId the ID of the contact to update
     * @param firstName the new first name
     */
    public void updateFirstName(String contactId, String firstName) {
        logger.info("Updating first name for contact ID: " + contactId);

        Contact contact = findContactById(contactId);
        if (contact == null) {
            logger.warning("Update failed — no contact found with ID: " + contactId);
            return;
        }

        contact.setFirstName(firstName);
        logger.info("First name updated for contact ID: " + contactId);
    }

    /**
     * Updates the last name of the contact with the given ID.
     * Logs success or failure if the ID does not exist.
     *
     * @param contactId the ID of the contact to update
     * @param lastName the new last name
     */
    public void updateLastName(String contactId, String lastName) {
        logger.info("Updating last name for contact ID: " + contactId);

        Contact contact = findContactById(contactId);
        if (contact == null) {
            logger.warning("Update failed — no contact found with ID: " + contactId);
            return;
        }

        contact.setLastName(lastName);
        logger.info("Last name updated for contact ID: " + contactId);
    }

    /**
     * Updates the phone number of the contact with the given ID.
     * Logs success or failure if the ID does not exist.
     *
     * @param contactId the ID of the contact to update
     * @param phone the new phone number (must be 10 digits)
     */
    public void updatePhone(String contactId, String phone) {
        logger.info("Updating phone number for contact ID: " + contactId);

        Contact contact = findContactById(contactId);
        if (contact == null) {
            logger.warning("Update failed — no contact found with ID: " + contactId);
            return;
        }

        contact.setPhone(phone);
        logger.info("Phone number updated for contact ID: " + contactId);
    }

    /**
     * Updates the address of the contact with the given ID.
     * Logs success or failure if the ID does not exist.
     *
     * @param contactId the ID of the contact to update
     * @param address the new address
     */
    public void updateAddress(String contactId, String address) {
        logger.info("Updating address for contact ID: " + contactId);

        Contact contact = findContactById(contactId);
        if (contact == null) {
            logger.warning("Update failed — no contact found with ID: " + contactId);
            return;
        }

        contact.setAddress(address);
        logger.info("Address updated for contact ID: " + contactId);
    }
    /**
     * Sorts all contacts alphabetically by first name (case-insensitive).
     * Uses a lambda comparator for clean and efficient sorting.
     */
    public void sortByFirstName() {
        contacts.sort((a, b) -> a.getFirstName().compareToIgnoreCase(b.getFirstName()));
    }
    /**
     * Sorts all contacts alphabetically by last name (not case-sensitive).
     * Demonstrates use of Collections sorting with custom comparators.
     */
    public void sortByLastName() {
        contacts.sort((a, b) -> a.getLastName().compareToIgnoreCase(b.getLastName()));
    }
    /**
     * Sorts all contacts by their unique contact ID.
     * Useful for maintaining consistent ordering in the system.
     */
    public void sortById() {
        contacts.sort((a, b) -> a.getContactId().compareTo(b.getContactId()));
    }
    /**
     * Searches for a contact by exact phone number match.
     *
     * @param phone the phone number to search for
     * @return the matching Contact, or null if none found
     */
    public Contact findContactByPhone(String phone) {
        for (Contact c : contacts) {
            if (c.getPhone().equals(phone)) {
                return c;
            }
        }
        return null;
    }
    /**
     * Finds all contacts whose last name matches the given value (case-insensitive).
     * Demonstrates linear search and string comparison logic.
     *
     * @param lastName the last name to search for
     * @return a list of contacts with the matching last name
     */
    public java.util.List<Contact> findContactsByLastName(String lastName) {
        java.util.List<Contact> results = new java.util.ArrayList<>();
        for (Contact c : contacts) {
            if (c.getLastName().equalsIgnoreCase(lastName)) {
                results.add(c);
            }
        }
        return results;
    }
    /**
     * Searches for contacts whose first or last name contains the given partial text.
     * This method is case-insensitive and supports substring matching.
     *
     * @param partial the partial name text to search for
     * @return a list of contacts whose names contain the partial text
     */
    public java.util.List<Contact> searchByPartialName(String partial) {
        java.util.List<Contact> results = new java.util.ArrayList<>();
        String lower = partial.toLowerCase();

        for (Contact c : contacts) {
            if (c.getFirstName().toLowerCase().contains(lower) ||
                c.getLastName().toLowerCase().contains(lower)) {
                results.add(c);
            }
        }
        return results;
    }

    /**
     * Returns the full list of contacts currently stored in memory.
     *
     * @return an ArrayList containing all Contact objects
     */
    public ArrayList<Contact> getContacts() {
        return contacts;
    }
}
