package com.tobi.invoiceService.Repository;

import com.tobi.invoiceService.Model.Invoice;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends MongoRepository<Invoice,String> {
}
