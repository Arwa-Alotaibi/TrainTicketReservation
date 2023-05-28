package com.example.trainticketbooking.Controller;


import com.example.trainticketbooking.Model.FeedBack;
import com.example.trainticketbooking.Service.FeedBackService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/feedback")
public class FeedBackController {

    private FeedBackService feedBackService;

    public FeedBackController(FeedBackService feedBackService){
        this.feedBackService=feedBackService;
    }
    @GetMapping("/all")
    public ResponseEntity GetAllFeedback(){
        List<FeedBack> feedBackList = feedBackService.All();
        return ResponseEntity.status(200).body(feedBackList);
    }

    @PostMapping("/add/{user_id}")
    public ResponseEntity Add(@PathVariable Integer user_id, @Valid @RequestBody FeedBack newFeedback){
        feedBackService.CreateFeedback(user_id,newFeedback);
        return ResponseEntity.status(200).body("feedback added");
    }
    @PutMapping("/update/feedback/{feedback_id}/user/{user_id}")
    public ResponseEntity FeedbackUpdate(@PathVariable Integer user_id,@PathVariable Integer feedback_id ,@Valid @RequestBody FeedBack update){
        feedBackService.UpdateFeedback(user_id,feedback_id,update);
        return ResponseEntity.status(200).body("feedback updated");
    }

    @PutMapping("/delete/feedback/{feedback_id}/user/{user_id}")
    public ResponseEntity FeedbackDelete(@PathVariable Integer user_id,@PathVariable Integer feedback_id ){
        feedBackService.DeleteFeedback(user_id,feedback_id);
        return ResponseEntity.status(200).body("feedback Deleted");
    }
}
