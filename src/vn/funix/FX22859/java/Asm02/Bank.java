package vn.funix.FX22859.java.Asm02;

import vn.funix.FX22859.java.Asm04.dao.CustomerDao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Bank {

    protected String ID;
    protected List<Customer> customers;

    public Bank() {
        customers = new ArrayList<Customer>();
    }

    public String getID() {
        return ID;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void addCustomer(String customerName, String customerId) {
        Customer customer = new Customer(customerName, customerId);
        customers.add(customer);
    }

    public boolean isCustomerExisted(String customerId) {
        boolean customerExists = customers.stream()
                .anyMatch(customer -> Objects.equals(customerId, customer.getCustomerID()));
        return customerExists;
    }

    public Customer getCustomer(String customerId) {
        return customers.stream()
                .filter(customer -> customer.getCustomerID().equals(customerId))
                .findFirst().orElse(null);
    }

    public void addAccount(String customerId, Account account) {
        customers.stream()
                .filter(customer -> customer.getCustomerID().equals(customerId))
                .findFirst()
                .ifPresent(customer -> {
                    customer.addAccount(account);
                    System.out.println("Thêm tài khoản thành công!");
                });
    }

    public void inputCustomers() throws IOException {
        CustomerDao.save(getCustomers());
    }
}
