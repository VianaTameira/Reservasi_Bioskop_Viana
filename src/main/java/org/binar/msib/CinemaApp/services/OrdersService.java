package org.binar.msib.CinemaApp.services;

import org.binar.msib.CinemaApp.dto.OrderInvoiceResponse;
import org.binar.msib.CinemaApp.dto.OrderRequest;
import org.binar.msib.CinemaApp.dto.OrderResponse;
import org.springframework.stereotype.Service;

import java.util.List;


public interface OrdersService {
    OrderResponse addOrder(OrderRequest orderRequest);
    List<OrderInvoiceResponse> getDetailOrder(String username, String film_name);
}
