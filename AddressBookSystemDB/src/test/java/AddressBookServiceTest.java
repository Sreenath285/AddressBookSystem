import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class AddressBookServiceTest {

    @Test
    public void givenAddressBook_WhenRetrieved_ShouldMatchContactsCount() {
        AddressBookService addressBookService = new AddressBookService();
        List<AddressBookData> addressBookDataList = addressBookService.readAddressBookData(AddressBookService.IOService.DB_IO);
        Assert.assertEquals(4, addressBookDataList.size());
    }
}
