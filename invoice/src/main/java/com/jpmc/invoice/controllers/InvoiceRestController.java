package com.jpmc.invoice.controllers;

import com.jpmc.invoice.model.Customer;
import com.jpmc.invoice.model.Invoice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;

@RestController
public class InvoiceRestController {


    private static Logger _log = LoggerFactory.getLogger(InvoiceRestController.class);

    @PostMapping(path = "/v1/invoices")
    public ResponseEntity handlePostInvoice(@RequestBody Invoice invoice) {

        Integer customerId = invoice.getCustomerId();

        // from the API which happens to be in training prpject
        // and the API call would look like :
        // http://localhost:8080/v1/customers/customerId
        _log.info("postInvoice is executed and have received customerId as : " + customerId);
        // may be I want to check the creditLimit

        String url = "http://localhost:8080/v1/customers/" + customerId;

        RestTemplate restTemplate = new RestTemplate();



        ResponseEntity<Customer> customerResponseEntity =
                restTemplate.exchange(url, HttpMethod.GET, null, Customer.class)


        Customer customer = customerResponseEntity.getBody();
        _log.info("The customer object received from the micro-service is :" +
                customer.getName() + " -- " + customer.getAddress());

        return new ResponseEntity(HttpStatus.OK);

    }


}
