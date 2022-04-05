import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AddressBookDB {

    private static AddressBookDB addressBookDB;

    /***
     * created constructor
     */
    private AddressBookDB() {
    }

    /***
     * created getInstance static method to create instance of class
     * @return - returning addressBookDB
     */
    public static AddressBookDB getInstance() {
        if (addressBookDB == null) {
            addressBookDB = new AddressBookDB();
        }
        return addressBookDB;
    }

    /***
     * created getConnection method to connect database
     * @return - returning connection
     */
    public Connection getConnection() {
        String jdbcURL = "jdbc:mysql://localhost:3306/address_book_service";
        String userName = "root";
        String password = "SreeSree@285";
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            System.out.println("Connecting database : " + jdbcURL);
            connection = DriverManager.getConnection(jdbcURL, userName, password);
            System.out.println("Connected database : " + connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /***
     * created readDataFromDB method to read data from database
     * @return
     */
    public List<AddressBookData> readDataFromDB() {
        String sql = "SELECT * FROM address_book_table";
        return this.getAddressBookDataUsingDB(sql);
    }

    /***
     * created getAddressBookDataUsingDB method to establish DB connection
     * creating statement and executing query
     * @param sql - passing string sql
     * @return - returning addressBookDataList
     */
    private List<AddressBookData> getAddressBookDataUsingDB(String sql) {
        List<AddressBookData> addressBookDataList = new ArrayList<>();
        try(Connection connection = this.getConnection()){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            addressBookDataList = this.getAddressBookData(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return addressBookDataList;
    }

    /***
     * created getAddressBookData to get data from DB
     * @param resultSet - passing resultSet
     * @return - returning addressBookDataList
     */
    private List<AddressBookData> getAddressBookData(ResultSet resultSet) {
        List<AddressBookData> addressBookDataList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                String firstName = resultSet.getString("FirstName");
                String lastName = resultSet.getString("LastName");
                String address = resultSet.getString("Address");
                String city = resultSet.getString("City");
                String state = resultSet.getString("State");
                int zipCode = resultSet.getInt("Zip");
                int phoneNumber = resultSet.getInt("PhoneNumber");
                String email = resultSet.getString("Email");
                String type = resultSet.getString("Type");
                addressBookDataList.add(new AddressBookData(firstName, lastName, address, city, state,
                                                            email, type, zipCode, phoneNumber));
            }
            System.out.println(addressBookDataList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return addressBookDataList;
    }
}
