import java.util.*;
import java.util.stream.Collectors;

public class MultipleAddressBook {

    Map<String, AddressBook> multipleAddressBookMap = new HashMap<>();
    Scanner sc = new Scanner(System.in);
    List<Contact> contacts = new ArrayList<>();

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

    /***
     * created searchByCity method to search contacts by city
     */
    public void searchByCity() {
        System.out.print("Enter city : ");
        String cityName = sc.next();
        for (String name : multipleAddressBookMap.keySet()) {
            List<Contact> city = multipleAddressBookMap.get(name).contactList;
            city.stream().filter(person -> person.getCity().equals(cityName))
                    .forEach(person -> System.out.println(person.getFirstName()));
        }
    }

    /***
     * created searchByCity method to search contacts by city name
     */
    public void searchByState() {
        System.out.print("Enter state : ");
        String stateName = sc.next();
        for (String name : multipleAddressBookMap.keySet()) {
            List<Contact> state = multipleAddressBookMap.get(name).contactList;
            state.stream().filter(person -> person.getCity().equals(stateName))
                    .forEach(person -> System.out.println(person.getFirstName()));
        }
    }

    /***
     * created displayPeopleByPlace method to display person either by city or by state
     * @param addressBookMap - passing hash map
     */
    public void displayPeopleByPlace(HashMap<String, ArrayList<Contact>> addressBookMap) {
        System.out.print("Enter place name : ");
        String placeName = sc.next();
        addressBookMap.values().stream()
                .map(place -> place.stream()
                .filter(person -> person.getCity().equals(placeName) || person.getState().equals(placeName)))
                .forEach(person -> person.forEach(personDetails -> System.out.println(personDetails)));
    }

    /***
     * created countPeopleByPlace method to display number of person either by city or by state
     * @param noOfPeople - passing hash map
     */
    public void countPeopleByPlace(HashMap<String, ArrayList<Contact>> noOfPeople) {
        System.out.print("Enter place name :");
        String placeName = sc.next();
        long countPeople = noOfPeople.values().stream()
                .map(place -> place.stream().filter(person -> person.getState().equals(placeName) || person.getCity().equals(placeName)))
                .count();
        System.out.println("Number of people in " + placeName + " are : " + countPeople +"\n");
    }
}
