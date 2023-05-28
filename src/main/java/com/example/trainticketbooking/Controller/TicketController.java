package com.example.trainticketbooking.Controller;


import com.example.trainticketbooking.Model.Ticket;
import com.example.trainticketbooking.Service.TicketService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ticket")
public class TicketController {
    private TicketService ticketService;

    public TicketController(TicketService ticketService){
        this.ticketService=ticketService;
    }

    @GetMapping("/all")
    public ResponseEntity GetAllTicket(){
        List<Ticket>List_all = ticketService.All();
        return ResponseEntity.status(200).body(List_all);
    }
    @PostMapping("/booking/{user_id}/train/{train_id}")
    public ResponseEntity AddTicket(@PathVariable Integer user_id,@PathVariable Integer train_id ,@Valid @RequestBody Ticket ticket){
        ticketService.CreateTicket(user_id, train_id, ticket);
        return ResponseEntity.status(200).body("The ticket has been successfully purchased");
    }
    @PutMapping("/update/ticket/{ticket_id}/user/{user_id}")
    public ResponseEntity UpdateTicket(@PathVariable Integer ticket_id ,@PathVariable Integer user_id, @Valid @RequestBody Ticket ticket){
        ticketService.UpdateBooking(ticket_id,ticket,user_id);
        return ResponseEntity.status(200).body("ticket updated");
    }


    @DeleteMapping("/cancel/user/{user_id}/ticket/{ticket_id}")
    public ResponseEntity Cancel(@PathVariable Integer user_id , @PathVariable Integer ticket_id){
        ticketService.Cancel_Ticket(user_id,ticket_id);
        return ResponseEntity.status(200).body("The ticket has been successfully cancelled");
    }

    @GetMapping("/myticket/{user_id}")
    public ResponseEntity tickets(@PathVariable Integer user_id){
        return ResponseEntity.status(200).body(ticketService.GetAllMyTicket(user_id));
    }
}
