package com.example.trainticketbooking.Repository;

import com.example.trainticketbooking.Model.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainRepository extends JpaRepository<Train,Integer> {
    Train findTrainById(Integer id);

    //Train findTrainByAvailable_seats(int available_seats);
    //List<Train> findTrainsByAvailableTrue();

   // List<Train>findAllByAvailableTrue();

    @Query("select s from Train s where s.available_seats >0 and s.available=true")
    List<Train>findAll();

}
