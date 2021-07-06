package ar.alejoacosta.examples.customannotation;

import ar.alejoacosta.examples.customannotation.annotations.Init;
import ar.alejoacosta.examples.customannotation.annotations.JsonElement;
import ar.alejoacosta.examples.customannotation.annotations.JsonSerializable;

/* 
 We have an object of type Person that we want to serialize into a JSON string. 
 This type has a method initNames() that capitalizes the first letter of the first and last names. 
 We'll want to call this method before serializing the object.

By using our custom annotations, we're indicating that we can serialize a Person object to a JSON string. 
In addition, the output should contain only the firstName, lastName, and age fields of that object. 
Moreover, we want the initNames() method to be called before serialization.

By setting the key parameter of the @JsonElement annotation to personAge, 
we are indicating that we'll use this name as the identifier for the field in the JSON output.

*/

@JsonSerializable
public class Person {

    @JsonElement
    private String firstName;
    @JsonElement
    private String lastName;
    @JsonElement(key = "personAge")
    private String age;

    private String address;

    public Person(String firstName, String lastName) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Person(String firstName, String lastName, String age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    @Init
    private void initNames() {
        this.firstName = this.firstName.substring(0, 1)
            .toUpperCase() + this.firstName.substring(1);
        this.lastName = this.lastName.substring(0, 1)
            .toUpperCase() + this.lastName.substring(1);
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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
}
