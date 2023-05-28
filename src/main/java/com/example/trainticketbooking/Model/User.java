package com.example.trainticketbooking.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

//@Entity annotation defines that a class can be mapped to a table
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "the name should be not null!!")
    private String name;

    @NotNull(message = "the name should be not null!!")
    @Email(message = "the email should be a valid email")
    private String email;

    private int phone_number;

    private String address;

    @Positive(message = "the balance should be a positive number!!")
    private double balance;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
    private List<Ticket> ticketList;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
    private List<FeedBack>feedBackList;


}
