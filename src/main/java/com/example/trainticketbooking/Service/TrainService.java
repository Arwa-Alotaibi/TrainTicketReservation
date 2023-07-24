package com.example.trainticketbooking.Service;


import com.example.trainticketbooking.Exception.ApiException;
import com.example.trainticketbooking.Model.Staff;
import com.example.trainticketbooking.Model.Train;
import com.example.trainticketbooking.Repository.StaffRepository;
import com.example.trainticketbooking.Repository.TrainRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TrainService {
    private TrainRepository trainRepository;
    private StaffRepository staffRepository;

    public TrainService(TrainRepository trainRepository, StaffRepository staffRepository){
        this.trainRepository=trainRepository;
        this.staffRepository=staffRepository;
    }

    //all
    public List<Train> GetAll(){
        return trainRepository.findAll();
    }

    //Add
    public void AddTrain(Integer staff_id,Train train){
        Staff staff = staffRepository.findStaffById(staff_id);
        if(staff==null){
            throw new ApiException("id not found");
        }
        train.setStaff(staff);
        trainRepository.save(train);
    }

    public void UpdateTrain(Integer id ,Train update_train,Integer staff_id){
        Train old_train = trainRepository.findTrainById(id);
        Staff staff = staffRepository.findStaffById(staff_id);
        if(old_train==null){
            throw new ApiException("train id not found!!");
        } else if (old_train.getStaff().getId()!=staff_id) {
            throw new ApiException("you do not have the authority to update !");
        }
        old_train.setTrain_fare(update_train.getTrain_fare());
        old_train.setStarting_location(update_train.getStarting_location());
        old_train.setDestination(update_train.getDestination());
        old_train.setDepart(update_train.getDepart());
        old_train.setReturn_date(update_train.getReturn_date());
        old_train.setAvailable_seats(update_train.getAvailable_seats());
        old_train.setAvailable(update_train.isAvailable());
        trainRepository.save(old_train);
    }
    public void DeleteTrain(Integer id,Integer staff_id){
        Train delete_train = trainRepository.findTrainById(id);
        Staff staff = staffRepository.findStaffById(staff_id);
        if(delete_train==null){
            throw new ApiException("train id not found!!");
        }
        else if (delete_train.getStaff().getId()!=staff_id) {
            throw new ApiException("you do not have the authority to update !");
        }
        trainRepository.delete(delete_train);
    }


    //Checking for availability of seats in the train.
    public int Available_Seats(Integer train_id){
        Train train= trainRepository.findTrainById(train_id);
        int seat = train.getAvailable_seats();
        if(train==null){
            throw new ApiException("train id not found !!");
        }
        else if(train.getAvailable_seats()==0){
            throw new ApiException("Sorry, there are no seats available");
        }

        return seat;

}

















}
