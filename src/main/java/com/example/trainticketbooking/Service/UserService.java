package com.example.trainticketbooking.Service;

import com.example.trainticketbooking.Exception.ApiException;
import com.example.trainticketbooking.Model.User;
import com.example.trainticketbooking.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    // get All user
    public List<User> GetAll(){
        return userRepository.findAll();
    }

    // add user
    public void AddUser(User user){
        userRepository.save(user);
    }

    // update user
    public void  UpdateUser(Integer user_id,User user){
        User old_user= userRepository.findUserById(user_id);
        if(old_user==null){
            throw new ApiException("user id not found");
        }
        old_user.setName(user.getName());
        old_user.setBalance(user.getBalance());
        old_user.setAddress(user.getAddress());
        old_user.setPhone_number(user.getPhone_number());
        old_user.setAddress(user.getAddress());
        userRepository.save(old_user);
    }
    //delete user
    public void DeleteUser(Integer user_id){
        User delete_user = userRepository.findUserById(user_id);
        if(delete_user==null){
            throw new ApiException("user id not found");
        }
        userRepository.delete(delete_user);
    }
}
