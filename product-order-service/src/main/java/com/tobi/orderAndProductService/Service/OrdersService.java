package com.tobi.orderAndProductService.Service;

import com.tobi.orderAndProductService.Model.Enitity.Orders;
import com.tobi.orderAndProductService.Model.Enitity.Product;
import com.tobi.orderAndProductService.Model.Enitity.ProductOrdered;
import com.tobi.orderAndProductService.Model.OrderStatus;
import com.tobi.orderAndProductService.Model.Request.OrderRequest;
import com.tobi.orderAndProductService.Model.Request.OrderUpdateRequest;
import com.tobi.orderAndProductService.OpenFeign.InvoiceClient;
import com.tobi.orderAndProductService.Repository.OrderRepository;
import com.tobi.orderAndProductService.Repository.ProductOrderedRepository;
import com.tobi.orderAndProductService.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrdersService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private InvoiceClient invoiceClient;

    @Autowired
    private ProductOrderedRepository orderedRepository;

    @Autowired
    private ProductRepository productRepository;



    public List<Orders> getAllOrders() {
        return orderRepository.findAll();
    }

    public List<Orders> getAllPendingOrders() {
        List<Orders> list = orderRepository.findAll();
        List<Orders> pendingList = new ArrayList<>();
        for(Orders r: list){
            if(!r.getOrderStatus().name().equalsIgnoreCase("Completed")){
                pendingList.add(r);
            }
        }
        return pendingList;
    }


    public String updateOrderById(OrderUpdateRequest request,
                                  Long orderId) {
        Optional<Orders> optionalOrder = orderRepository.findById(orderId);
        if(optionalOrder.isPresent()){

            Orders order = optionalOrder.get();
            order.setOrderStatus(request.getOrderStatus());

            List<ProductOrdered> orderList = order.getProductOrderedList();
            if(request.getOrderStatus().equals(OrderStatus.Completed)){
                for(ProductOrdered productOrdered: orderList){
                    Product product = productRepository.findByName(productOrdered.getProductName());
                    int orderedQuantity = productOrdered.getQuantity();
                    int newQuantity = product.getQuantity() - orderedQuantity;

                    product.setQuantity(newQuantity);
                    productRepository.save(product);
                }
                // generates an invoice with the invoice service

                invoiceClient.generateInvoice(order);
                orderRepository.save(order);
                return "Order has been updated. And Invoice sent. ";
            }else{
                return "Order Status isn't COMPLETED";
            }

        }


        return "Order wasn't Found. ";

    }

    public String saveOrderFromService(OrderRequest orderRequest) {
    Orders orders = new Orders();
    orders.setOrderStatus(OrderStatus.PendingPayment);
    orders.setPaymentMethod(orderRequest.getPaymentMethod());
    orders.setShippingAddress(orderRequest.getShippingAddress());
    orders.setCustomerUsername(orderRequest.getCustomerUsername());

    int totalPrice = 0;


    List<ProductOrdered> orderedList = new ArrayList<>();
    List<ProductOrdered> requestOrderList = orderRequest.getProductOrderedList();

    for(ProductOrdered productOrdered: requestOrderList){
        String productName = productOrdered.getProductName();
        int productQuantity = productOrdered.getQuantity();
        ProductOrdered productOrdered1 = new ProductOrdered();
        productOrdered1.setProductName(productName);
        productOrdered1.setQuantity(productQuantity);
        productOrdered1.setOrders(orders);

        Product product = productRepository.findByName(productName);

       int price = product.getPrice();
        int productAmount = productQuantity * price;

        totalPrice += productAmount;

        orderedList.add(productOrdered1);
    }

    orders.setProductOrderedList(orderedList);
    orders.setTotalPrice(totalPrice);

    orderRepository.save(orders);



        return"Order saved successfully";
    }

    public void saveProductOrdered(ProductOrdered productOrdered) {

        orderedRepository.save(productOrdered);
    }
}

