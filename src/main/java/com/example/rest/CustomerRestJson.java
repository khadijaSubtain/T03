package com.example.rest;

import org.apache.http.HttpResponse;
import sun.security.provider.certpath.OCSPResponse;

import javax.ws.rs.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Path("customerjson")
public class CustomerRestJson {

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
    @Produces("application/json")
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
    @Produces("application/json")
    @Path("{id}")
    public Customer getCustomerList(@PathParam("id") int id) {
        traceMethod(Thread.currentThread());
        return customers.stream().filter(customer1 -> customer1.getId() == id)
                .findFirst()
                .orElse(null);
    }

    /**
     * @param customer
     */
    @POST
    @Consumes("application/json")
    public void createCustomer(Customer customer) {
        traceMethod(Thread.currentThread());
        Customer newCustomer = new Customer(customer);
        customers.add(newCustomer);
    }

    /**
     * @param id
     * @param customer
     */
    @PUT
    @Path("{id}")
    @Consumes("application/json")
    public void modifyCustomer(@PathParam("id") int id, Customer customer) {
        traceMethod(Thread.currentThread());
        deleteCustomer(id);
        customers.add(new Customer(customer));
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