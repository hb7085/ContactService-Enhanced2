package contactapp;

import contactapp.InvalidContactException;

/**
 * Represents a single contact in the system.
 * Enforces validation rules for all fields and provides controlled access
 * through getters and setters. Validation failures throw InvalidContactException.
 */
public class Contact {
	
	/** Unique identifier for the contact (max length 10). */
    private final String contactId;
    /** First name of the contact (max length 10). */
    private String firstName;
    /** Last name of the contact (max length 10). */
    private String lastName;
    /** Phone number of the contact (must be exactly 10 digits). */
    private String phone;
    /** Address of the contact (max length 30). */
    private String address;

    /**
     * Creates a new Contact object after validating all fields.
     *
     * @param contactId unique ID for the contact (max length 10)
     * @param firstName the contact's first name (max length 10)
     * @param lastName the contact's last name (max length 10)
     * @param phone the contact's phone number (must be 10 digits)
     * @param address the contact's address (max length 30)
     * @throws InvalidContactException if any field fails validation
     */
    public Contact(String contactId, String firstName, String lastName, String phone, String address) {
        if (contactId == null || contactId.length() > 10)
            throw new InvalidContactException("Invalid contact ID");
        if (firstName == null || firstName.length() > 10)
            throw new InvalidContactException("Invalid first name");
        if (lastName == null || lastName.length() > 10)
            throw new InvalidContactException("Invalid last name");
        // Phone must be exactly 10 digits.
        if (phone == null || !phone.matches("\\d{10}"))
            throw new InvalidContactException("Invalid phone number");
        if (address == null || address.length() > 30)
            throw new InvalidContactException("Invalid address");

        // Assign values after validation passes.
        this.contactId = contactId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
    }

    // Getter methods return the values of each field.
    /**
     * @return the unique contact ID
     */
    public String getContactId() { return contactId; }
    /**
     * @return the contact's first name
     */
    public String getFirstName() { return firstName; }
    /**
     * @return the contact's last name
     */
    public String getLastName() { return lastName; }
    /**
     * @return the contact's phone number
     */
    public String getPhone() { return phone; }
    /**
     * @return the contact's address
     */
    public String getAddress() { return address; }

    /**
     * Updates the contact's first name after validation.
     *
     * @param firstName the new first name (max length 10)
     * @throws InvalidContactException if the name is invalid
     */
    public void setFirstName(String firstName) {
        if (firstName == null || firstName.length() > 10)
            throw new InvalidContactException("Invalid first name");
        this.firstName = firstName;
    }
    /**
     * Updates the contact's last name after validation.
     *
     * @param lastName the new last name (max length 10)
     * @throws InvalidContactException if the name is invalid
     */
    public void setLastName(String lastName) {
        if (lastName == null || lastName.length() > 10)
            throw new InvalidContactException("Invalid last name");
        this.lastName = lastName;
    }
    /**
     * Updates the contact's phone number after validation.
     *
     * @param phone the new phone number (must be 10 digits)
     * @throws InvalidContactException if the phone number is invalid
     */
    public void setPhone(String phone) {
        if (phone == null || !phone.matches("\\d{10}"))
            throw new InvalidContactException("Invalid phone number");
        this.phone = phone;
    }
    /**
     * Updates the contact's address after validation.
     *
     * @param address the new address (max length 30)
     * @throws InvalidContactException if the address is invalid
     */
    public void setAddress(String address) {
        if (address == null || address.length() > 30)
            throw new InvalidContactException("Invalid address");
        this.address = address;
    }
    
}
