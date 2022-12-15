package org.binar.msib.CinemaApp.controller;

import org.binar.msib.CinemaApp.dto.MessageResponse;
import org.binar.msib.CinemaApp.dto.OrderInvoiceResponse;
import org.binar.msib.CinemaApp.dto.OrderRequest;
import org.binar.msib.CinemaApp.dto.OrderResponse;
import org.binar.msib.CinemaApp.services.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrdersService ordersService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<MessageResponse> addOrder (@RequestBody OrderRequest orderRequest)
    {
        MessageResponse messageResponse = new MessageResponse();
        OrderResponse orderResponse = ordersService.addOrder(orderRequest);
        if(orderResponse == null)
        {
            messageResponse.setStatus(HttpStatus.BAD_REQUEST.value());
            messageResponse.setMessage("Failed to add order");
            return ResponseEntity.badRequest().body(messageResponse);
        }
        else
        {
            messageResponse.setStatus(HttpStatus.OK.value());
            messageResponse.setMessage("Add new order");
            messageResponse.setData(orderResponse);
            return ResponseEntity.ok().body(messageResponse);
        }
    }
    @GetMapping("/get-all/order/{username}/{film_name}")
    public ResponseEntity<MessageResponse> getDetailOrder(@PathVariable String username,@PathVariable String film_name){
        MessageResponse messageResponse = new MessageResponse();
        try {
            List<OrderInvoiceResponse> orderInvoiceResponses = ordersService.getDetailOrder(username, film_name);
            messageResponse.setMessage("Success get order detail");
            messageResponse.setStatus(HttpStatus.OK.value());
            messageResponse.setData(orderInvoiceResponses);
            return ResponseEntity.ok().body(messageResponse);
        }catch (Exception exception)
        {
            messageResponse.setMessage("Failed get order detail");
            messageResponse.setStatus(HttpStatus.BAD_GATEWAY.value());
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY.value()).body(messageResponse);
        }
    }
}
