public class Main {

    /***
     * main method for manipulation
     * @param args - default param(not used)
     */
    public static void main(String[] args) {
        /***
         * PROCEDURE
         * 1. calling getConnection method
         */
        AddressBookDB addressBookDB = AddressBookDB.getInstance();

        /***
         * 1. calling getConnection method
         */
        addressBookDB.getConnection();
    }
}
