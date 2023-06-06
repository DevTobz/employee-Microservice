package com.tobi.invoiceService.Mapper;

import com.tobi.invoiceService.Model.ProductOrdered;
import com.tobi.invoiceService.Model.ProductOrderedDto;
import org.springframework.stereotype.Service;

import java.util.function.Function;
@Service
public class ProductOrderedDtoMapper implements Function<ProductOrdered, ProductOrderedDto> {
    @Override
    public ProductOrderedDto apply(ProductOrdered productOrdered) {
        return new ProductOrderedDto(productOrdered.getProductName(), productOrdered.getQuantity());
    }
}
