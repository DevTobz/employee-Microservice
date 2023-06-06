package com.tobi.invoiceService.Model;

import java.util.List;

public record InvoiceDTO(
        int totalPrice,
        List<ProductOrderedDto> productOrderedDtoList,
        PaymentMethod paymentMethod,
        String shippingAddress,
        OrderStatus orderStatus,
        String customerFirstName,
        String customerLastName,
        String customerEmail,
        String customerAddress
) {
}
