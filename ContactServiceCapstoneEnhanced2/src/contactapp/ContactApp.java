package contactapp;

import java.util.Scanner;
import java.util.logging.Logger;

/**
 * Command-line interface (CLI) for interacting with the ContactService.
 * Allows users to add, update, delete, and view contacts.
 */
public class ContactApp {

    private static final Logger logger = Logger.getLogger(ContactApp.class.getName());

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ContactService service = new ContactService();

        boolean running = true;

        System.out.println("=====================================");
        System.out.println("      CONTACT MANAGEMENT SYSTEM      ");
        System.out.println("=====================================");

        while (running) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Add Contact");
            System.out.println("2. Update Contact");
            System.out.println("3. Delete Contact");
            System.out.println("4. View All Contacts");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            String choice = scanner.nextLine();

            switch (choice) {

                case "1":
                    System.out.print("Enter first name: ");
                    String firstName = scanner.nextLine();

                    System.out.print("Enter last name: ");
                    String lastName = scanner.nextLine();

                    System.out.print("Enter phone (10 digits): ");
                    String phone = scanner.nextLine();

                    System.out.print("Enter address: ");
                    String address = scanner.nextLine();

                    try {
                        service.addContact(firstName, lastName, phone, address);
                        System.out.println("Contact added successfully.");
                    } catch (InvalidContactException e) {
                        System.out.println("Error: " + e.getMessage());
                        logger.warning("Failed to add contact: " + e.getMessage());
                    }
                    break;

                case "2":
                    System.out.print("Enter contact ID to update: ");
                    String updateId = scanner.nextLine();

                    System.out.println("What would you like to update?");
                    System.out.println("1. First Name");
                    System.out.println("2. Last Name");
                    System.out.println("3. Phone");
                    System.out.println("4. Address");
                    System.out.print("Enter choice: ");
                    String updateChoice = scanner.nextLine();

                    switch (updateChoice) {
                        case "1":
                            System.out.print("Enter new first name: ");
                            String newFirst = scanner.nextLine();
                            try {
                                service.updateFirstName(updateId, newFirst);
                                System.out.println("First name updated.");
                            } catch (InvalidContactException e) {
                                System.out.println("Error: " + e.getMessage());
                            }
                            break;

                        case "2":
                            System.out.print("Enter new last name: ");
                            String newLast = scanner.nextLine();
                            try {
                                service.updateLastName(updateId, newLast);
                                System.out.println("Last name updated.");
                            } catch (InvalidContactException e) {
                                System.out.println("Error: " + e.getMessage());
                            }
                            break;

                        case "3":
                            System.out.print("Enter new phone (10 digits): ");
                            String newPhone = scanner.nextLine();
                            try {
                                service.updatePhone(updateId, newPhone);
                                System.out.println("Phone updated.");
                            } catch (InvalidContactException e) {
                                System.out.println("Error: " + e.getMessage());
                            }
                            break;

                        case "4":
                            System.out.print("Enter new address: ");
                            String newAddress = scanner.nextLine();
                            try {
                                service.updateAddress(updateId, newAddress);
                                System.out.println("Address updated.");
                            } catch (InvalidContactException e) {
                                System.out.println("Error: " + e.getMessage());
                            }
                            break;

                        default:
                            System.out.println("Invalid update option.");
                    }
                    break;

                case "3":
                    System.out.print("Enter contact ID to delete: ");
                    String deleteId = scanner.nextLine();
                    service.deleteContact(deleteId);
                    break;

                case "4":
                    System.out.println("\n--- CONTACT LIST ---");
                    if (service.getContacts().isEmpty()) {
                        System.out.println("No contacts found.");
                    } else {
                        for (Contact c : service.getContacts()) {
                            System.out.println("---------------------------");
                            System.out.println("ID: " + c.getContactId());
                            System.out.println("First Name: " + c.getFirstName());
                            System.out.println("Last Name: " + c.getLastName());
                            System.out.println("Phone: " + c.getPhone());
                            System.out.println("Address: " + c.getAddress());
                        }
                    }
                    break;

                case "5":
                    running = false;
                    System.out.println("Exiting Contact Management System...");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }
}
