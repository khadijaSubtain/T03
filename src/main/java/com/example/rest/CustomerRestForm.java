package com.example.rest;

import javax.ws.rs.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Path("customerform")
public class CustomerRestForm {

    /**
     * Class for holding the list of customers and handling the requests
     */

    private static ArrayList<Customer> customers = new ArrayList<>();

    /**
     * Meant for returning the list of customers
     *
     * @return A concatenation of the toString method for all customers
     */
    @GET
    @Produces("application/xml")
    public ArrayList<Customer> getCustomer() {
        traceMethod(Thread.currentThread());
        return customers;
    }

    /**
     * Meant for getting a customer with a specific ID
     *
     * @param id of the customer
     * @return toString method of customer
     */
    @GET
    @Produces("application/xml")
    @Path("{id}")
    public Customer getCustomerList(@PathParam("id") int id) {
        traceMethod(Thread.currentThread());
        return customers.stream().filter(customer1 -> customer1.getId() == id)
                .findFirst()
                .orElse(null);
    }

    /**
     *
     * @param name
     * @param age
     */
    @POST
    public void createCustomer(@FormParam("name") String name, @FormParam("age") int age) {
        traceMethod(Thread.currentThread());
        Customer newCustomer = new Customer(name, age);
        customers.add(newCustomer);
    }

    /**
     *
     * @param id
     * @param name
     * @param age
     */
    @PUT
    @Path("{id}")
    @Consumes("application/xml")
    public void modifyCustomer(@PathParam("id") int id, @FormParam("name") String name, @FormParam("age") int age) {
        traceMethod(Thread.currentThread());
        deleteCustomer(id);
        customers.add(new Customer(name, age));
    }

    /**
     * Meant for deleting customer with specific ID
     *
     * @param id of the customer
     */
    @DELETE
    @Path("{id}")
    public void deleteCustomer(@PathParam("id") int id) {
        traceMethod(Thread.currentThread());
        customers = customers.stream().filter(customer -> customer.getId() != id)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Debugging statement that prints the current state of the list of customers
     */
    private void printCustomers() {
        traceMethod(Thread.currentThread());
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }

    private void traceMethod(Thread thread) {
        System.out.println(new Timestamp(System.currentTimeMillis()) + ": " + thread.getStackTrace()[2].getMethodName());
    }
}