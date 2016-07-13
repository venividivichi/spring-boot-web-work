package guru.springframework.services;


import guru.springframework.domain.Customer;

public interface CustomerService {

    Iterable<Customer> listAllCustomers();

    Customer getCustomerById(Integer id);

    Customer saveCustomer(Customer customer);

    void deleteCustomer(Integer id);

}
