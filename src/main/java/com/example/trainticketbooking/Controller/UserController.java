package com.example.trainticketbooking.Controller;

import com.example.trainticketbooking.Model.User;
import com.example.trainticketbooking.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private UserService userService;
    public UserController(UserService userService){
        this.userService=userService;
    }
    //Get All user
    @GetMapping("/all/user")
    public ResponseEntity GetAll(){
        List<User> UserList= userService.GetAll();
        return ResponseEntity.status(200).body(UserList);
    }
    @PostMapping("/add")
    public ResponseEntity Add(@Valid @RequestBody User user){
        userService.AddUser(user);
        return ResponseEntity.status(200).body("user added");
    }
    @PutMapping("/update/{user_id}")
    public ResponseEntity Update(@PathVariable Integer user_id,@Valid @RequestBody User user){
        userService.UpdateUser(user_id,user);
        return ResponseEntity.status(200).body("user updated");
    }
    @DeleteMapping("/delete/{user_id}")
    public ResponseEntity Delete(@PathVariable Integer user_id){
        userService.DeleteUser(user_id);
        return ResponseEntity.status(200).body("user deleted");

    }
}
