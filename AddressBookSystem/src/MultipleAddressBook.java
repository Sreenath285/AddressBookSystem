import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MultipleAddressBook {

    Map<String, AddressBook> multipleAddressBookMap = new HashMap<>();
    Scanner sc = new Scanner(System.in);

    /***
     * created addAddressBook method to add a new address book
     */
    public void addAddressBook () {
        System.out.print("Enter address book name : ");
        String addressBookName = sc.next();
        if (multipleAddressBookMap.containsKey(addressBookName)) {
            System.out.println("Address book already exists");
        }
        else {
            AddressBook addressBook = new AddressBook();
            multipleAddressBookMap.put(addressBookName, addressBook);
            multipleAddressBookMap.get(addressBookName).getList();
        }
    }

    /***
     * created existingAddressBook method to get existing address book
     */
    public void existingAddressBook() {
        System.out.print("Enter address book name : ");
        String existingAddressBookName = sc.next();
        if (multipleAddressBookMap.get(existingAddressBookName) == null) {
            System.out.println("There is no address book with the given name");
        }
        else {
            multipleAddressBookMap.get(existingAddressBookName).getList();
        }
    }

    /***
     * created showAddressBook method to show address book contacts
     */
    public void showAddressBook() {
        for (String name : multipleAddressBookMap.keySet()) {
            System.out.println(name);
            System.out.println(multipleAddressBookMap.get(name).contactList);
        }
    }
}
