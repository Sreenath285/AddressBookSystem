import java.util.Comparator;

public class ComparePerson implements Comparator<Contact> {
    @Override
    public int compare(Contact person1, Contact person2) {
        return person1.getFirstName().compareTo(person2.getFirstName());
    }
}
