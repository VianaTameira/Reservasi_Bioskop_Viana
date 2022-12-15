package org.binar.msib.CinemaApp.services.impl;

import org.binar.msib.CinemaApp.dto.OrderInvoiceResponse;
import org.binar.msib.CinemaApp.dto.OrderRequest;
import org.binar.msib.CinemaApp.dto.OrderResponse;
import org.binar.msib.CinemaApp.entity.Film;
import org.binar.msib.CinemaApp.entity.Orders;
import org.binar.msib.CinemaApp.entity.Schedule;
import org.binar.msib.CinemaApp.entity.User;
import org.binar.msib.CinemaApp.repository.FilmRepository;
import org.binar.msib.CinemaApp.repository.OrdersRepository;
import org.binar.msib.CinemaApp.repository.ScheduleRepository;
import org.binar.msib.CinemaApp.repository.UserRepository;
import org.binar.msib.CinemaApp.services.OrdersService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrdersService {
    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private ScheduleRepository scheduleRepository;


    @Override
    public OrderResponse addOrder(OrderRequest orderRequest) {
        try {
            Optional<Film> film = filmRepository.findById(orderRequest.getFilm_code());
            if (film.isPresent()) {
                Optional<User> user = userRepository.findById(orderRequest.getUser_id());
                if (user.isPresent()) {
                    Orders orders = orderRequest.toOrder();
                    try {
                        ordersRepository.save(orders);
                        return OrderResponse.build(orders);
                    } catch (Exception exception) {
                        return null;
                    }
                }
            } else
                return null;
        } catch (Exception exception) {
            return null;
        }
        return null;
    }


    @Override
    public List<OrderInvoiceResponse> getDetailOrder(String username, String film_name) {
        List<Orders> allOrders = ordersRepository.getDetailOrder(username, film_name);
        List<User> allUser = userRepository.getUserOrder(username, film_name);
        List<Film> allFilm = filmRepository.getFilmOrder(username, film_name);
        List<Schedule> allSchedule = scheduleRepository.getScheduleOrder(username, film_name);
        List<OrderInvoiceResponse> detailorder = new ArrayList<>();
        for (Orders orders : allOrders)
        {
            for (User user : allUser)
            {
                for (Film film : allFilm)
                {
                    for (Schedule schedule : allSchedule)
                    {
                    OrderInvoiceResponse allOrderResponse = OrderInvoiceResponse.build(schedule, user, film);
                    detailorder.add(allOrderResponse);
                    break;
                    }
                    break;
                }
                break;
            }

        }
        return detailorder;
    }
}
