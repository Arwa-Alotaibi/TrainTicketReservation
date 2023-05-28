package com.example.trainticketbooking.Repository;

import com.example.trainticketbooking.Model.Ticket;
import com.example.trainticketbooking.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket,Integer> {
    Ticket findTicketById(Integer id);

    List<Ticket>findAllByUser(User user);
}
