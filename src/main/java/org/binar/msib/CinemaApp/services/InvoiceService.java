package org.binar.msib.CinemaApp.services;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

import java.io.FileNotFoundException;

public interface InvoiceService {
    public JasperPrint generateInvoice(String username, String film_name) throws FileNotFoundException, JRException;
}
