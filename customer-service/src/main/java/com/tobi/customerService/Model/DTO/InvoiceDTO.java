package com.tobi.customerService.Model.DTO;

public record InvoiceDTO(
        CustomerDTO customerDTO,
        OrderDTO orderDTO
) {
}
