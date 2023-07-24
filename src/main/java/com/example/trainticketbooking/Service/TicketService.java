package com.example.trainticketbooking.Service;


import com.example.trainticketbooking.Exception.ApiException;
import com.example.trainticketbooking.Model.Ticket;
import com.example.trainticketbooking.Model.Train;
import com.example.trainticketbooking.Model.User;
import com.example.trainticketbooking.Repository.TicketRepository;
import com.example.trainticketbooking.Repository.TrainRepository;
import com.example.trainticketbooking.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {
    private TicketRepository ticketRepository;
    private UserRepository userRepository;

    private TrainRepository trainRepository;
    public TicketService(TicketRepository ticketRepository,UserRepository userRepository,TrainRepository trainRepository){
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
        this.trainRepository = trainRepository;
    }

    // Get All ticket
    public List<Ticket> All(){
        return ticketRepository.findAll();
    }
    //Add Booking
    public void CreateTicket(Integer user_id, Integer train_id,Ticket ticket){
        User user = userRepository.findUserById(user_id);
        Train train = trainRepository.findTrainById(train_id);
        if(user==null|| train==null){
            throw new ApiException("user id or train id not found");
        } else if (!train.isAvailable()) {
            throw new ApiException("The train is not available");
        } else if (!ticket.getStarting_location().equals(train.getStarting_location())&& !ticket.getDestination().equals(train.getDestination())) {
            throw new ApiException("Sorry try another id!!");
        }  else if (train.getAvailable_seats()<ticket.getPassenger()) {
            throw new ApiException("Sorry ,The number of passengers is more than the number of seats...");
        }
        ticket.setTicket_cost(train.getTrain_fare()* ticket.getPassenger());
        if(ticket.getTicket_cost()>user.getBalance()){
            throw new ApiException(" Sorry,Your balance is not enough to purchase tickets!!");
        }
        train.setAvailable_seats(train.getAvailable_seats()-ticket.getPassenger());
        user.setBalance(user.getBalance()-ticket.getTicket_cost());
        ticket.setTrain(train);
        ticket.setUser(user);

        ticketRepository.save(ticket);
    }
    // update booking
    public void UpdateBooking(Integer ticket_id , Ticket update,Integer user_id){
        Ticket old_booking = ticketRepository.findTicketById(ticket_id);
        User user = userRepository.findUserById(user_id);
        if(old_booking==null){
            throw new ApiException("id not found");
        } else if (old_booking.getUser().getId()!=user_id ) {
            throw new ApiException("you do not have the authority to update !");
        }
        old_booking.setStarting_location(update.getStarting_location());
        old_booking.setDestination(update.getDestination());
        old_booking.setDepart(update.getDepart());
        old_booking.setReturn_date(update.getReturn_date());
        old_booking.setTicket_cost(update.getTicket_cost());
        old_booking.setPassenger(update.getPassenger());
        ticketRepository.save(old_booking);
    }


    public void Cancel_Ticket(Integer user_id , Integer ticket_id){
        User user = userRepository.findUserById(user_id);
        Ticket ticket = ticketRepository.findTicketById(ticket_id);

        if(user==null || ticket ==null ){
            throw new ApiException("user id not found or ticket id not found");
        }
        else if (ticket.getUser().getId()!=user_id ) {
            throw new ApiException("you do not have the authority to update !");
        }
       ticket.getTrain().setAvailable_seats(ticket.getTrain().getAvailable_seats()+ticket.getPassenger()) ;
        user.setBalance(user.getBalance()+ticket.getTicket_cost());
        ticket.getUser().getTicketList().remove(ticket);
       ticketRepository.save(ticket);
       userRepository.save(user);

        //ticketRepository.delete(ticket);

    }

    public List<Ticket> GetAllMyTicket(Integer user_id){
        User user = userRepository.findUserById(user_id);
        List<Ticket> mytickets = ticketRepository.findAllByUser(user);
        if(mytickets.isEmpty()){
            throw new ApiException("you dont add ticket");
        }
        return mytickets;
    }
}
