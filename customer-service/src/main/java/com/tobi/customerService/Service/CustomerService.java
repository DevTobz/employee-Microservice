package com.tobi.customerService.Service;

import com.tobi.customerService.Model.Customer;
import com.tobi.customerService.Model.Orders;
import com.tobi.customerService.Model.Product;
import com.tobi.customerService.Model.Request.CustomerOrderRequest;
import com.tobi.customerService.Model.Request.CustomerRequest;
import com.tobi.customerService.Model.ProductOrdered;
import com.tobi.customerService.Model.Request.OrderRequest;
import com.tobi.customerService.OpenFeign.OrderClient;
import com.tobi.customerService.OpenFeign.ProductClient;
import com.tobi.customerService.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.tobi.customerService.Model.OrderStatus.PendingPayment;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductClient productClient;

    @Autowired
    private OrderClient orderClient;

    private final int minimumStockQuantity = 20;



    //Create new orders and store them in the database
    public void createOrder(String username, CustomerOrderRequest request){

        Customer customer = customerRepository.findByUsername(username);

        OrderRequest ordersRequest = new OrderRequest();


        ordersRequest.setCustomerUsername(customer.getUsername());

        //Try implementing service while saving order
        //ordersRequest.setOrderStatus(PendingPayment);

        ordersRequest.setShippingAddress(request.getShippingAddress());
        ordersRequest.setPaymentMethod(request.getPaymentMethod());



        List<ProductOrdered> requestProductOrderedList = request.getProductOrderedList();
        List<ProductOrdered> productOrderedList = new ArrayList<>();

      for(ProductOrdered requestProductOrdered:requestProductOrderedList){
          String productName = requestProductOrdered.getProductName();
          int quantity = requestProductOrdered.getQuantity();

          ProductOrdered productOrdered = new ProductOrdered();
          productOrdered.setProductName(productName);
          productOrdered.setQuantity(quantity);
          productOrderedList.add(productOrdered);


      }
      ordersRequest.setProductOrderedList(productOrderedList);
      orderClient.saveOrderFromService(ordersRequest);

    }

    public String createCustomer(CustomerRequest request) {
        Customer customer = Customer.builder().
                username(request.getUsername()).
                firstName(request.getFirstname()).
                lastName(request.getLastname()).
                email(request.getEmail()).
                address(request.getAddress()).
                build();
        customerRepository.save(customer);

        return "Done";
    }

    public Customer getCustomerByUsername(String userName) {
       return customerRepository.findByUsername(userName);
    }
}
