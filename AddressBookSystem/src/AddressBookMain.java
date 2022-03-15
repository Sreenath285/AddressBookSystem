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
            System.out.println("1. Add person \n2. Show contact \n3. Exit");
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter choice : ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1 :
                    addressBook.addPerson();
                    break;
                case 2 :
                    addressBook.showContacts();
                    break;
                case 3 :
                    System.exit(0);
                    break;
                default :
                    break;
            }
        }
    }
}
