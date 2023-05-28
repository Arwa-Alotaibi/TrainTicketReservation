package com.example.trainticketbooking.Service;

import com.example.trainticketbooking.Exception.ApiException;
import com.example.trainticketbooking.Model.Staff;
import com.example.trainticketbooking.Repository.StaffRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffService {
    private StaffRepository staffRepository;

    public StaffService(StaffRepository staffRepository){
        this.staffRepository=staffRepository;
    }


    public List<Staff> GetAll(){
        return staffRepository.findAll();
    }
    public void  AddStaff(Staff staff){
        staffRepository.save(staff);
    }

    public void update_staff(Integer staff_id,Staff update){
        Staff old_staff = staffRepository.findStaffById(staff_id);
        if(old_staff==null){
            throw new ApiException("staff id not found");
        }
        old_staff.setName(update.getName());
        old_staff.setEmail(update.getEmail());
        old_staff.setPhone_number(update.getPhone_number());
        staffRepository.save(old_staff);
    }

    public void delete_staff(Integer id){
        Staff delete_staff = staffRepository.findStaffById(id);
        if(delete_staff==null){
            throw new ApiException("staff id not found");
        }
        staffRepository.delete(delete_staff);
    }
}
