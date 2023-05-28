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

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {
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

    private double ticket_cost ;

    @NotNull(message = "the passenger should be not null;")
    private int passenger;

    @ManyToOne
    // name = foreign key  - referencedColumnName =primary key for user
    @JoinColumn(name="user_id",referencedColumnName = "id")
    @JsonIgnore
    private User user;

    @ManyToOne
    @JoinColumn(name = "train_id",referencedColumnName = "id")
    @JsonIgnore
    private Train train;


}
