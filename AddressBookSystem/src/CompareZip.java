import java.util.Comparator;

public class CompareZip implements Comparator<Contact> {
    @Override
    public int compare(Contact person1, Contact person2) {
        if (person1.getZipCode() == person2.getZipCode()) {
            return 0;
        }
        else if (person1.getZipCode() > person2.getZipCode()) {
            return 1;
        }
        else {
            return -1;
        }
    }
}
