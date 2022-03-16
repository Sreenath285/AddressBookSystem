import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AddressBook {

    List<Contact> contactList = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

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
            addPerson();
        }
    }
    
    /***
     * created addPerson method to enter contact details from user
     */
    public void addPerson() {
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
            System.out.print("Do you want to continue (y/n) :");
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
        }
        else {
            System.out.println("There is no contact with the given first name");
            System.out.println("Enter correct first name");
            findContact();
        }
        return tempContact;
    }

    /***
     * created showContacts method to show contacts
     */
    public void showContacts() {
        for (Contact cont : contactList) {
            System.out.println("--------------------------------------------");
            System.out.println("First name      : " + cont.getFirstName());
            System.out.println("Last name       : " + cont.getLastName());
            System.out.println("Address         : " + cont.getAddress());
            System.out.println("City            : " + cont.getCity());
            System.out.println("State           : " + cont.getState());
            System.out.println("Email           : " + cont.getEmail());
            System.out.println("Phone number    : " + cont.getPhoneNumber());
            System.out.println("Zip code        : " + cont.getZipCode());
            System.out.println("--------------------------------------------");
        }
        if (contactList.isEmpty()) {
            System.out.println("Address book is empty");
        }
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
