package com.tobi.invoiceService.Mapper;

import com.tobi.invoiceService.Model.Invoice;
import com.tobi.invoiceService.Model.InvoiceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.function.Function;
import java.util.stream.Collectors;

@Repository
public class InvoiceMapper implements Function<Invoice, InvoiceDTO> {
    @Autowired
    private ProductOrderedDtoMapper mapper;
    @Override
    public InvoiceDTO apply(Invoice invoice) {
        return new InvoiceDTO(invoice.getTotalPrice()
                , invoice.getProductOrderedList().
                        stream().
                        map(productOrdered -> mapper.apply(productOrdered)).
                        collect(Collectors.toList()),
                invoice.getPaymentMethod(),
                invoice.getShippingAddress(),
                invoice.getOrderStatus(),
                invoice.getCustomerFirstName(),
                invoice.getCustomerLastName(),
                invoice.getCustomerEmail(),
                invoice.getCustomerAddress());
    }
}
