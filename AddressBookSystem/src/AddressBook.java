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
        // creating contact
        Contact contact =new Contact(firstName, lastName, address, city, state, email, phoneNumber, zipCode);
        // adding contact to contactList
        contactList.add(contact);

        System.out.println("Successfully added");
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
            System.out.println("Email           : " + cont.getEmail());
            System.out.println("Phone number    : " + cont.getPhoneNumber());
            System.out.println("Zip code        : " + cont.getZipCode());
        }
        if (contactList.isEmpty()) {
            System.out.println("Address book is empty");
        }
    }
}
