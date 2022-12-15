package org.binar.msib.CinemaApp.controller;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.binar.msib.CinemaApp.dto.MessageResponse;
import org.binar.msib.CinemaApp.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {
    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    HttpServletResponse response;

    @GetMapping("/generate/{username}/{film_name}")
    public void ResponseEntity(@PathVariable String username, @PathVariable String film_name ) throws Exception
    {
        MessageResponse messageResponse = new MessageResponse();
        JasperPrint jasperPrint = invoiceService.generateInvoice(username, film_name);
        if(jasperPrint != null)
        {
            response.setContentType(MediaType.APPLICATION_PDF_VALUE);
            response.setHeader("Content-Disposition", "C:\\Users\\ASUS\\order.pdf");
            messageResponse.setStatus(HttpStatus.OK.value());
            messageResponse.setMessage("Generated invoice");
            JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
            response.flushBuffer();
        }
    }
}
