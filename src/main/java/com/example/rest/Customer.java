package com.example.rest;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="customer")
@XmlAccessorType(XmlAccessType.FIELD)
public class Customer {
    /**
     * Class that will be used to hold the data for the Rest API Example
     */

    @XmlElement
    private int id;
    @XmlElement
    private String name;
    @XmlElement
    private int age;
    private static int currentId = 0;

    public Customer(String name, int age) {
        this.id = currentId++;
        this.name = name;
        this.age = age;
    }

    //used by unmarshall to have same effect as count
    public Customer(Customer customer){
        this.id = currentId++;
        this.name = customer.getName();
        this.age = customer.getAge();
    }

    //used by Jersey
    public Customer(){}

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String toString() {
        return String.format("Hello, my ID is %s, my name is %s and I am %d years old",
                this.getId(), this.getName(), this.getAge());
    }

}
