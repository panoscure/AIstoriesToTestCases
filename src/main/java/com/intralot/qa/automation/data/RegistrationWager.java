package com.intralot.qa.automation.data;

public class RegistrationWager {

    public RegistrationWager() {
    }

    String firstName = "Audrina";
    String title = "MR";
    String lastName = "Larson";
    String gender = "Female";
    String dateOfBirth = "10/25/1940";
    String ethnicity = "White alone";
    String SSN = "3636";
    String wrongSSN = "9999";
    String mobileNumber;
    String formattedMobileNumber;
    String streetNumber = "193";
    String streetName = "Morton Pleasant";
    String zipCode = "20069";
    String state = "District of Columbia";
    String city = "Washington";
    String email;
    String username;
    String password = "123456q!";

    public String getTitle() {
        return title;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getGender() {

        return gender;
    }
    public String getEthnicity() {
        return ethnicity;
    }
    public String getSSN() {
        return SSN;
    }
    public String getWrongSSN() {
        return wrongSSN;
    }
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getStreetNumber() {
        return streetNumber;
    }
    public String getStreetName() {
        return streetName;
    }
    public String getZipCode() {
        return zipCode;
    }
    public String getState() {
        return state;
    }
    public String getCity() {
        return city;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }

    public void setFormattedMobileNumber(String formattedMobileNumber) {
        this.formattedMobileNumber = formattedMobileNumber;
    }
    public String getFormattedMobileNumber() {
        return formattedMobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getUsername() {
        return username;
    }


}
