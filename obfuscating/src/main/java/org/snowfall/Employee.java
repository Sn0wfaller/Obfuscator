package org.snowfall;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Employee {
    @JacksonXmlProperty(isAttribute = true)
    private String id;
    private String firstName;
    private String lastName;
    private String location;

    private static final String[] values = new String[] {
            "id", "firstName", "lastName", "location" };

    public static String[] getValues() {
        return values;
    }

    public String getValue(String key) throws Exception {
        switch (key) {
            case "id":
                return getId();
            case "firstName":
                return getFirstName();
            case "lastName":
                return getLastName();
            case "location":
                return getLocation();
            default:
                throw new Exception("Illegal key value " + key +
                        ". Available keys: " + String.join(", ", values));
        }
    }

    public void setValue(String key, String value) throws Exception {
        switch (key) {
            case "id":
                setId(value);
                break;
            case "firstName":
                setFirstName(value);
                break;
            case "lastName":
                setLastName(value);
                break;
            case "location":
                setLocation(value);
                break;
            default:
                throw new Exception("Illegal key value " + key +
                        ". Available keys: " + String.join(", ", values));
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
