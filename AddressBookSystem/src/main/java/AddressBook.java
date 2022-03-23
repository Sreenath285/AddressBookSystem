import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import netscape.javascript.JSObject;
import org.json.JSONObject;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class AddressBook {

    Scanner sc = new Scanner(System.in);
    List<Contact> contactList = new ArrayList<>();
    MultipleAddressBook multipleAddressBook = new MultipleAddressBook();
    public static HashMap<String, ArrayList<Contact>> personByCity  = new HashMap<String, ArrayList<Contact>>();
    public static HashMap<String, ArrayList<Contact>> personByState = new HashMap<String, ArrayList<Contact>>();

    /***
     * created getList method for choices
     */
    public void getList() throws IOException {
        String continueList = "";
        do {
            System.out.println("-------------------------------------------");
            System.out.println("1. Add contact \n2. Edit contact \n3. Add multiple contacts " +
                    "\n4. Delete contact \n5. Menu \n6. Read address book");
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
                    deleteContact();
                    break;
                case 5 :
                    menu();
                    break;
                case 6 :
                    readFiles();
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

    private void menu() {
        System.out.println("1. Search by city \n2. Search by state \n3. View person by city" +
                "\n4. View person by state \n5. Count people by city \n6. Count people by state" +
                "\n7. Sort person alphabetically \n8. Sort person by Zip code");
        System.out.print("Enter choice : ");
        int menuChoice = sc.nextInt();
        switch (menuChoice) {
            case 1 :
                multipleAddressBook.searchByCity();
                break;
            case 2 :
                multipleAddressBook.searchByState();
                break;
            case 3 :
                multipleAddressBook.displayPeopleByPlace(personByCity);
                break;
            case 4 :
                multipleAddressBook.displayPeopleByPlace(personByState);
                break;
            case 5 :
                multipleAddressBook.countPeopleByPlace(personByCity);
                break;
            case 6 :
                multipleAddressBook.countPeopleByPlace(personByState);
                break;
            case 7 :
                sortAlphabetically();
                break;
            case 8 :
                sortByZipCode();
                break;
            default :
                System.out.println("Invalid input");
                break;
        }
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
    public void addMultipleContacts() throws IOException {
        System.out.print("Enter number of contacts : ");
        int numberOfContacts = sc.nextInt();
        for (int i = 0; i < numberOfContacts; i++) {
            duplicatePersonCheck();
        }
    }

    /***
     * created duplicatePersonCheck method to check if user already exists or not
     * if exists displaying message - Name already exists
     */
    public void duplicatePersonCheck() throws IOException {
        System.out.print("Enter first name to check if user exists or not : ");
        String firstName = sc.next();
        for (Contact contact : contactList) {
            if (contact.getFirstName().equals(firstName)) {
                System.out.println("Name already exists");
            }
            return;
        }
        System.out.println("Add new contact");
        addPerson();
    }

    /***
     * created addPerson method to enter contact details from user
     */
    public void addPerson() throws IOException {
        String firstName = readStringInput("Enter first name : ");
        String lastName = readStringInput("Enter last name : ");
        String address = readStringInput("Enter address : ");
        String city = readStringInput("Enter city : ");
        String state = readStringInput("Enter state : ");
        String email = readStringInput("Enter email : ");
        long zipCode = readLongInput("Enter zip code : ");
        long phoneNumber = readLongInput("Enter phone number : ");

        Contact contact =new Contact(firstName, lastName, address, city, state, email, zipCode, phoneNumber);
        addPersonToCity(contact);
        addPersonToState(contact);
        contactList.add(contact);
        writingIntoFiles();
        System.out.println("Successfully added");
    }

    /***
     * created writingIntoFiles method to write contact details into
     * text and csv files
     */
    private void writingIntoFiles() {
        // writing contact into a text file
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(
                    new FileWriter("C:\\Users\\sreen\\Desktop\\AddressBookSystem\\src\\main\\resources\\AddressBook.txt"));
            bufferedWriter.write(String.valueOf(contactList));
            bufferedWriter.close();
        }
        catch (Exception e ){
            System.out.println(e.getMessage());
        }

        // writing contact into a csv file
        try {
            FileWriter fileWriter = new FileWriter("C:\\Users\\sreen\\Desktop\\AddressBookSystem\\src\\main\\resources\\AddressBook.csv");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            StatefulBeanToCsv<Contact> beanToCsv = new StatefulBeanToCsvBuilder<Contact>(bufferedWriter).withQuotechar(CSVWriter.NO_QUOTE_CHARACTER).build();
            beanToCsv.write(contactList);
            bufferedWriter.flush();
        }
        catch (CsvRequiredFieldEmptyException e) {
            e.printStackTrace();
        } catch (CsvDataTypeMismatchException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
     * created addPersonToCity method to check person by city
     * @param contact - passing contact
     */
    public void addPersonToCity(Contact contact) {
        if (personByCity.containsKey(contact.getCity())) {
            personByCity.get(contact.getCity()).add(contact);
        }
        else {
            ArrayList<Contact> cityList = new ArrayList<Contact>();
            cityList.add(contact);
            personByCity.put(contact.getCity(), cityList);
        }
        personByCity.size();
    }

    /***
     * created addPersonToState method to check person by state
     * @param contact - passing contact
     */
    public void addPersonToState(Contact contact) {
        if (personByState.containsKey(contact.getState())) {
            personByState.get(contact.getState()).add(contact);
        }
        else {
            ArrayList<Contact> stateList = new ArrayList<Contact>();
            stateList.add(contact);
            personByState.put(contact.getState(), stateList);
        }
        personByState.size();
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

    /***
     * created sortAlphabetically method to sort person alphabetically
     */
    private void sortAlphabetically() {
        List<Contact> sortAlphabetically = contactList.stream().sorted(new ComparePerson()).collect(Collectors.toList());
        for (Contact contact : sortAlphabetically) {
            System.out.println(contact);
        }
    }

    /***
     * created sortByZipCode method to sort person by zip code
     */
    private void sortByZipCode() {
        List<Contact> sortByZipcode = contactList.stream().sorted(new CompareZip()).collect(Collectors.toList());
        for (Contact contact : sortByZipcode) {
            System.out.println(contact);
        }
    }

    private void readFiles() throws IOException {
        String choiceFile = "";
        do {
            System.out.println("1. Read text file \n2. Read CSV file");
            System.out.print("Enter choice : ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    readContactTextFile();
                    break;
                case 2:
                    readContactCSVFile();
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
            System.out.print("Do you want to continue (y/n) : ");
            choiceFile = sc.next();
        }
        while(choiceFile.equalsIgnoreCase("Y"));
    }

    /***
     * created readContactTextFile method to read the contact from AddressBook.txt
     */
    private void readContactTextFile() {
        try {
            BufferedReader bufferedReader = new BufferedReader(
                    new FileReader("C:\\Users\\sreen\\Desktop\\AddressBookSystem\\src\\main\\resources\\AddressBook.txt"));
            String line = " ";
            while (line != null){
                System.out.println(line);
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
        }
        catch (IOException e ){
            System.out.println(e.getMessage());
        }
    }

    /***
     * created readContactCSVFile method to read the contact from AddressBook.csv
     */
    private void readContactCSVFile() {
        try {
            BufferedReader bufferedReader = new BufferedReader(
                    new FileReader("C:\\Users\\sreen\\Desktop\\AddressBookSystem\\src\\main\\resources\\AddressBook.csv"));
            CsvToBean<Contact> csvToBean = new CsvToBeanBuilder<Contact>(bufferedReader).withType(Contact.class).withIgnoreLeadingWhiteSpace(true).build();
            Iterator<Contact> contactIterator = csvToBean.iterator();
            while (contactIterator.hasNext()){
                System.out.println(contactIterator.next());
            }
        }catch (IOException e ){
            System.out.println(e.getMessage());
        }
    }

}
