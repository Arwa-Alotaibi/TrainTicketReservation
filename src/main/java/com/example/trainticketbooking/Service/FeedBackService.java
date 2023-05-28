package com.example.trainticketbooking.Service;

import com.example.trainticketbooking.Exception.ApiException;
import com.example.trainticketbooking.Model.FeedBack;
import com.example.trainticketbooking.Model.User;
import com.example.trainticketbooking.Repository.FeedBackRepository;
import com.example.trainticketbooking.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedBackService {
    private FeedBackRepository feedBackRepository;

    private UserRepository userRepository;

    public FeedBackService(FeedBackRepository feedBackRepository,UserRepository userRepository){
        this.feedBackRepository=feedBackRepository;
        this.userRepository=userRepository;
    }

    public List<FeedBack> All(){
        return feedBackRepository.findAll();
    }
    public void CreateFeedback(Integer user_id, FeedBack feedBack){
        User user = userRepository.findUserById(user_id);
        if(user==null){
            throw new ApiException("user id not found");
        }
        feedBack.setUser(user);
        feedBackRepository.save(feedBack);
    }

    public void UpdateFeedback(Integer user_id,Integer feedback_id , FeedBack update){
        FeedBack old_feedback = feedBackRepository.findFeedBackById(feedback_id);
        User user = userRepository.findUserById(user_id);
        if(old_feedback==null || user==null){
            throw new ApiException("feedback id not found or user id not found");
        } else if (old_feedback.getUser().getId()!=user.getId()) {
            throw new ApiException("you do not have the authority to update !");
        }
        old_feedback.setRating(update.getRating());
        old_feedback.setMessage(update.getMessage());
        feedBackRepository.save(old_feedback);
    }

    public void  DeleteFeedback(Integer user_id , Integer feedback_id){
        User user = userRepository.findUserById(user_id);
        FeedBack feedBack = feedBackRepository.findFeedBackById(feedback_id);
        if(feedBack==null || user==null){
            throw new ApiException("feedback id not found or user id not found");
        }
        else if (feedBack.getUser().getId()!=user.getId()) {
            throw new ApiException("you do not have the authority to update !");
        }

        //feedBack.getUser().getFeedBackList().remove(feedBack);
        feedBackRepository.delete(feedBack);
    }
}
