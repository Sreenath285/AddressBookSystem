import java.util.ArrayList;
import java.util.List;

public class AddressBookService {

    List<AddressBookData> addressBookDataList = new ArrayList<>();
    private AddressBookDB addressBookDB;

    public AddressBookService() {
        addressBookDB = AddressBookDB.getInstance();
    }

    /***
     * created IOService enum to give services
     */
    public enum IOService {
        CONSOLE_IO, FILE_IO, DB_IO, REST_IO;
    }

    /***
     * created readAddressBookData to read data
     * @param ioService - passing ioService
     * @return - returning addressBookDataList
     */
    public List<AddressBookData> readAddressBookData(IOService ioService) {
        if (ioService.equals(IOService.DB_IO)) {
            this.addressBookDataList = addressBookDB.readDataFromDB();
        }
        return addressBookDataList;
    }
}
