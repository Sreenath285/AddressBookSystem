import java.util.Scanner;

public class AddressBookMain {

    /***
     * main method for manipulation
     * @param args - default param(not used)
     */
    public static void main(String[] args) {
        /***
         * PROCEDURE
         * 1. creating addressBook
         * 2. iterating while loop for choice
         */

        /***
         * 1. creating addressBook
         */
        AddressBook addressBook = new AddressBook();
        /***
         * 2. iterating while loop for choice
         */
        while (true) {
            System.out.println("-------------------------------------------");
            System.out.println("1. Add contact \n2. Edit contact \n3. Add multiple contacts " +
                                "\n4. Show contacts \n5. Delete contact \n6. Exit");
            System.out.println("-------------------------------------------");
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter choice : ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1 :
                    addressBook.addPerson();
                    break;
                case 2 :
                    addressBook.editContacts();
                    break;
                case 3 :
                    addressBook.addMultipleContacts();
                    break;
                case 4 :
                    addressBook.showContacts();
                    break;
                case 5 :
                    addressBook.deleteContact();
                    break;
                case 6 :
                    System.exit(0);
                    break;
                default :
                    break;
            }
        }
    }
}
