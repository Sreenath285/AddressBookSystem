import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class AddressBook {

    Scanner sc = new Scanner(System.in);
    List<Contact> contactList = new ArrayList<>();
    MultipleAddressBook multipleAddressBook = new MultipleAddressBook();

    /***
     * created getList method for choices
     */
    public void getList() {
        String continueList = "";
        do {
            System.out.println("-------------------------------------------");
            System.out.println("1. Add contact \n2. Edit contact \n3. Add multiple contacts " +
                    "\n4. Search contact by city \n5. Search contact by state \n6. Delete contact");
            System.out.println("-------------------------------------------");
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter choice : ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1 :
                    addPerson();
                    break;
                case 2 :
                    editContacts();
                    break;
                case 3 :
                    addMultipleContacts();
                    break;
                case 4 :
                    multipleAddressBook.searchByCity();
                    break;
                case 5 :
                    multipleAddressBook.searchByState();
                    break;
                case 6 :
                    deleteContact();
                    break;
                default :
                    System.out.println("Invalid input");
                    break;
            }
            System.out.print("Do you want to continue (y/n) : ");
            continueList = sc.next();
        }
        while (continueList.equalsIgnoreCase("Y"));
    }

    /***
     * created readLongInput method to read long input
     * @param inputMsg - passing input message
     * @return - returning input
     */
    public long readLongInput(String inputMsg) {
        System.out.print(inputMsg);
        long inputLong = sc.nextLong();
        return  inputLong;
    }

    /***
     * created readStringInput method to read long input
     * @param inputMsg - passing input message
     * @return - returning input
     */
    public String readStringInput(String inputMsg) {
        System.out.print(inputMsg);
        String inputStr = sc.next();
        return  inputStr;
    }

    /***
     * created addMultipleContacts method to add multiple contacts
     */
    public void addMultipleContacts() {
        System.out.print("Enter number of contacts : ");
        int numberOfContacts = sc.nextInt();
        for (int i = 0; i < numberOfContacts; i++) {
            duplicatePersonCheck();
            addPerson();
        }
    }

    /***
     * created duplicatePersonCheck method to check if user already exists or not
     * if exists displaying message - Name already exists
     */
    public void duplicatePersonCheck() {
        System.out.print("Enter first name to check if user exists or not : ");
        String firstName = sc.next();
        for (Contact contact : contactList) {
            if (contact.getFirstName().equals(firstName)) {
                System.out.println("Name already exists");
            }
            return;
        }
        System.out.println("Add new contact");
    }

    /***
     * created addPerson method to enter contact details from user
     */
    public void addPerson() {
        duplicatePersonCheck();
        String firstName = readStringInput("Enter first name : ");
        String lastName = readStringInput("Enter last name : ");
        String address = readStringInput("Enter address : ");
        String city = readStringInput("Enter city : ");
        String state = readStringInput("Enter state : ");
        String email = readStringInput("Enter email : ");
        long phoneNumber = readLongInput("Enter phone number : ");
        long zipCode = readLongInput("Enter zip code : ");

        Contact contact =new Contact(firstName, lastName, address, city, state, email, phoneNumber, zipCode);
        contactList.add(contact);

        System.out.println("Successfully added");
    }

    /***
     * created editContact method to edit contact
     * calling findContact method to find the contact by giving user's first name
     */
    public void editContacts() {
        Contact contact = findContact();
        String continueEdit = "";
        do {
            System.out.println("--------------------------------------------");
            System.out.println("1. Edit first name \n2. Edit last name \n3. Edit address" +
                    "\n4. Edit city \n5. Edit state \n6. Edit email" +
                    "\n7. Edit zip code \n8. Edit phone number");
            System.out.println("--------------------------------------------");
            System.out.print("Enter choice : ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1 :
                    contact.setFirstName(readStringInput("Enter first name : "));
                    break;
                case 2 :
                    contact.setLastName(readStringInput("Enter last name : "));
                    break;
                case 3 :
                    contact.setAddress(readStringInput("Enter address : "));
                    break;
                case 4 :
                    contact.setCity(readStringInput("Enter city : "));
                    break;
                case 5 :
                    contact.setEmail(readStringInput("Enter state : "));
                    break;
                case 6 :
                    contact.setEmail(readStringInput("Enter email : "));
                    break;
                case 7 :
                    contact.setZipCode(readLongInput("Enter zip code : "));
                    break;
                case 8 :
                    contact.setPhoneNumber(readLongInput("Enter phone number : "));
                    break;
                default :
                    System.out.println("Invalid input");
                    break;
            }
            System.out.print("Do you want to continue (y/n) : ");
            continueEdit = sc.next();
        }
        while (continueEdit.equalsIgnoreCase("Y"));
    }

    /***
     * created findContact method to find the contact by giving user's first name
     * @return - returning contact
     */
    private Contact findContact() {
        System.out.print("Enter first name : ");
        String firstName = sc.next();
        int temp = 0;
        Contact tempContact = null;
        for (Contact cont : contactList) {
            if (cont.getFirstName().equals(firstName)) {
                temp++;
                tempContact = cont;
            }
        }
        if (temp == 1) {
            return tempContact;
        } else {
            System.out.println("There is no contact with the given first name");
            System.out.println("Enter correct first name");
            findContact();
        }
        return tempContact;
    }

    /***
     * created deleteContact method to delete a contact
     * calling findContact method to contact by giving user's first name
     */
    public void deleteContact() {
        Contact contact = findContact();
        if (contact == null) {
            System.out.println("There is no contact with the given first name");
            return;
        }
        contactList.remove(contact);
        System.out.println("Successfully deleted");
    }
}
