package com.tobi.invoiceService.Model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@Document
@NoArgsConstructor
@Data

public class Invoice {
    @MongoId
    private String id;
   private int totalPrice;
   private List<ProductOrdered> productOrderedList;
   private PaymentMethod paymentMethod;
   private String shippingAddress;
   private OrderStatus orderStatus;
   private String customerFirstName;
   private String customerLastName;
   private String customerEmail;
   private String customerAddress;

   public Invoice(int totalPrice,
                  List<ProductOrdered> productOrderedList,
                  PaymentMethod paymentMethod,
                  String shippingAddress,
                  OrderStatus orderStatus) {
      this.totalPrice = totalPrice;
      this.productOrderedList = productOrderedList;
      this.paymentMethod = paymentMethod;
      this.shippingAddress = shippingAddress;
      this.orderStatus = orderStatus;
   }

   public Invoice generateInvoice(Orders orders, Invoice invoice) {
      invoice.setShippingAddress(orders.getShippingAddress());
      invoice.setPaymentMethod(orders.getPaymentMethod());
      invoice.setOrderStatus(orders.getOrderStatus());
      invoice.setTotalPrice(orders.getTotalPrice());
      invoice.setProductOrderedList(orders.getProductOrderedList());
      return invoice;
   }

   public Invoice addCustomerToInvoice(Customer customer, Invoice invoice) {
      invoice.setCustomerEmail(customer.getEmail());
      invoice.setCustomerLastName(customer.getLastName());
      invoice.setCustomerFirstName(customer.getFirstName());
      invoice.setCustomerAddress(customer.getAddress());
      return invoice;

   }
}
