import java.util.Objects;

public class AddressBookData {

    private String firstName, lastName, address, city, state, email, type;
    private int zipCode, phoneNumber;

    public AddressBookData(String firstName, String lastName, String address,
                           String city, String state, String email,
                           String type, int zipCode, int phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.email = email;
        this.type = type;
        this.zipCode = zipCode;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "\nAddressBookData{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", email='" + email + '\'' +
                ", type='" + type + '\'' +
                ", zipCode=" + zipCode +
                ", phoneNumber=" + phoneNumber +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressBookData that = (AddressBookData) o;
        return zipCode == that.zipCode
                          && phoneNumber == that.phoneNumber
                          && Objects.equals(firstName, that.firstName)
                          && Objects.equals(lastName, that.lastName)
                          && Objects.equals(address, that.address)
                          && Objects.equals(city, that.city)
                          && Objects.equals(state, that.state)
                          && Objects.equals(email, that.email)
                          && Objects.equals(type, that.type);
    }
}
