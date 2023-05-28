package com.example.trainticketbooking.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Train {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @NotNull(message = "the starting location should be not null;")
    private String starting_location;

    @NotNull(message = "the destination should be not null;")
    private String destination;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date depart;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date return_date ;

    //@Positive(message = "the available seats should be a positive number!!")
    private int available_seats;
    @Positive(message = "the train fare should be a positive number!!")
    private double train_fare;
    private boolean available;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "train")
    private List<Ticket> ticketList;

    @ManyToOne
    @JoinColumn(name = "staff_id",referencedColumnName = "id")
    @JsonIgnore
    private Staff staff;

}
