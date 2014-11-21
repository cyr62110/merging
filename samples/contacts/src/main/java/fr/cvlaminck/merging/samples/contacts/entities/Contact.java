/**
 * Copyright 2014 Cyril Vlaminck
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
