package com.activity11.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.activity11.dao.TicketDAO;
import com.activity11.models.Ticket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/Tickets")
public class TicketController {
    @Autowired
    private TicketDAO ticketDAO;

    @PostMapping(value = "")
    public ResponseEntity<Ticket> create(@RequestBody Ticket ticket) {
        try {
            Date date=new Date();
            SimpleDateFormat spl=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String d=spl.format(date);
            date=spl.parse(d);
            ticket.setCreatedDate(date);

            Ticket ticketData = ticketDAO.save(ticket);
            return new ResponseEntity<>(ticketData, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value="" , params = "CreatedDate")
    public ResponseEntity<List<Ticket>> getByCreatedDate(@RequestParam String CreatedDate) {
        try {
            String date =CreatedDate.replace(".", ":");

            List<Ticket> tickets = ticketDAO.findByCreatedDate(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(date));
            return new ResponseEntity<>(tickets, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
}
