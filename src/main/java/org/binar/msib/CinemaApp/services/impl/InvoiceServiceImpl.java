package org.binar.msib.CinemaApp.services.impl;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import org.binar.msib.CinemaApp.dto.OrderInvoiceResponse;
import org.binar.msib.CinemaApp.entity.Film;
import org.binar.msib.CinemaApp.entity.Orders;
import org.binar.msib.CinemaApp.entity.Schedule;
import org.binar.msib.CinemaApp.entity.User;
import org.binar.msib.CinemaApp.repository.FilmRepository;
import org.binar.msib.CinemaApp.repository.OrdersRepository;
import org.binar.msib.CinemaApp.repository.ScheduleRepository;
import org.binar.msib.CinemaApp.repository.UserRepository;
import org.binar.msib.CinemaApp.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InvoiceServiceImpl implements InvoiceService{
    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private ScheduleRepository scheduleRepository;

    @Override
    public JasperPrint generateInvoice(String username, String film_name) throws FileNotFoundException, JRException {
        List<Orders> allOrders = ordersRepository.getDetailOrder(username,film_name);
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
        String path = "C:\\Users\\ASUS";
        File file = ResourceUtils.getFile("classpath:order.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(detailorder);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy","viana");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        JasperExportManager.exportReportToPdfFile(jasperPrint, path+"\\order.pdf");
        return jasperPrint;
    }
}
