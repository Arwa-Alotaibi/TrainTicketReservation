package com.example.trainticketbooking.Controller;


import com.example.trainticketbooking.Model.Staff;
import com.example.trainticketbooking.Service.StaffService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/staff")
public class StaffController {
   @Autowired
    private StaffService staffService;

    @GetMapping("/all")
    public ResponseEntity GetAllStaff(){
        List<Staff> staffList = staffService.GetAll();
        return ResponseEntity.status(200).body(staffList);
    }
    @PostMapping("/add")
    public ResponseEntity Add(@Valid @RequestBody Staff staff){
        staffService.AddStaff(staff);
        return ResponseEntity.status(200).body("staff added");
    }

    @PutMapping("/update/{staff_id}")
    public ResponseEntity update(@PathVariable Integer staff_id , @Valid @RequestBody Staff staff){
        staffService.update_staff(staff_id,staff);
        return ResponseEntity.status(200).body("staff updated");
    }

    @DeleteMapping("/delete/{staff_id}")
    public ResponseEntity Delete(@PathVariable Integer staff_id ){
        staffService.delete_staff(staff_id);
        return ResponseEntity.status(200).body("staff deleted");
    }
}
