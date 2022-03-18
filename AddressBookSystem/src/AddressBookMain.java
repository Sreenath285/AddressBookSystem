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
         * 1. creating multiple address book object
         */
        MultipleAddressBook multipleAddressBook = new MultipleAddressBook();

        /***
         * 2. iterating while loop for choice
         */
        while (true) {
            System.out.println("-------------------------------------------");
            System.out.println("1. Add address book \n2. Go to existing address book \n3. Show address book \n4. Exit");
            System.out.println("-------------------------------------------");
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter choice : ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1 :
                    multipleAddressBook.addAddressBook();
                    break;
                case 2 :
                    multipleAddressBook.existingAddressBook();
                    break;
                case 3 :
                    multipleAddressBook.showAddressBook();
                    break;
                case 4 :
                    System.exit(0);
                    break;
                default :
                    System.out.println("Invalid input");
                    break;
            }
        }
    }
}
