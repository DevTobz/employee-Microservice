package com.tobi.invoiceService.Service;
import com.tobi.invoiceService.Mapper.InvoiceMapper;
import com.tobi.invoiceService.Model.*;
import com.tobi.invoiceService.OpenClient.CustomerClient;
import com.tobi.invoiceService.Repository.InvoiceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class InvoiceService {
    @Autowired
    private CustomerClient customerClient;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private InvoiceMapper invoiceMapper;

    public InvoiceDTO generateInvoice(Orders orders) {


          Invoice invoice = new Invoice();
          invoice.generateInvoice(orders,invoice);
          Customer customer = customerClient.getCustomer(orders.getCustomerUsername());
          invoice.addCustomerToInvoice(customer, invoice);
          invoiceRepository.save(invoice);
          InvoiceDTO invoiceDTO = invoiceMapper.apply(invoice);
          log.info(invoiceDTO.toString());
          return invoiceDTO;

    }
}
