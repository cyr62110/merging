package fr.cvlaminck.merging.samples.contacts.entities;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Entity representing a contact in your address book application.
 * Public Getters and Setters are required to merge object using default implementations.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Contact {

    private String firstName;

    private String lastName;

    private String streetName;

    private String postalCode;

    private String city;

    private Collection<String> phoneNumbers;

    public Contact() {
        this.phoneNumbers = new ArrayList<>();
    }

    public Contact(String firstName, String lastName, String streetName, String postalCode, String city) {
        this();
        this.firstName = firstName;
        this.lastName = lastName;
        this.streetName = streetName;
        this.postalCode = postalCode;
        this.city = city;
    }

    public Contact(String firstName, String lastName, String phoneNumber) {
        this();
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumbers.add(phoneNumber);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Collection<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(Collection<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }
}
