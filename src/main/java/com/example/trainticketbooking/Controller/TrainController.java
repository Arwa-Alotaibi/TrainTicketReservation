package com.example.trainticketbooking.Controller;


import com.example.trainticketbooking.Model.Train;
import com.example.trainticketbooking.Service.TrainService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/trains")
public class TrainController {
    @Autowired
    private TrainService trainService ;


    //provide the available Train-list, and Seat-availability
    @GetMapping("/all")
    public ResponseEntity AllTrain(){
        List<Train>trian_list= trainService.GetAll();
        return ResponseEntity.status(200).body(trian_list);
    }

    @PostMapping("/add/train/staff/{staffid}")
    public ResponseEntity AddTrain(@PathVariable Integer staffid,@Valid @RequestBody Train train){
        trainService.AddTrain(staffid,train);
        return ResponseEntity.status(200).body("train added !!");
    }
    @PutMapping("/update/train/{id}/staff/{staffid}")
    public ResponseEntity UpdateTrain(@PathVariable  Integer id,@PathVariable Integer staffid,@Valid @RequestBody Train train){
        trainService.UpdateTrain(id,train,staffid);
        return ResponseEntity.status(200).body("train updated !!");
    }
    @DeleteMapping("/delete/train/{id}/staff/{staffid}")
    public ResponseEntity DeleteTrain(@PathVariable Integer id,@PathVariable Integer staffid){
        trainService.DeleteTrain(id,staffid);
        return ResponseEntity.status(200).body("train delete !!");
    }


    @GetMapping("/seats/{train_id}")
    public ResponseEntity GetAvailable_Seats(@PathVariable Integer train_id){
        return ResponseEntity.status(200).body("The number of seats available:"+" "+ trainService.Available_Seats(train_id));
    }



}
