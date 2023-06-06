package com.tobi.orderAndProductService.Model.DTO;

public record InvoiceDTO(
        CustomerDTO customerDTO,
        OrderDTO orderDTO
) {
}
