package com.example.trainticketbooking.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "the name should be not null!!")
    private String name;

    @NotNull(message = "the name should be not null!!")
    @Email(message = "the email should be a valid email")
    private String email;
    private int phone_number;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "staff")
    private List<Train> trainList;
}
