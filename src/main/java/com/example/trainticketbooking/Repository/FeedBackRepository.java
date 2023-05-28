package com.example.trainticketbooking.Repository;


import com.example.trainticketbooking.Model.FeedBack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedBackRepository extends JpaRepository<FeedBack,Integer> {
    FeedBack findFeedBackById(Integer id);
    List<FeedBack>findByOrderByRatingAsc();
    List<FeedBack>findByOrderByRatingDesc();

}
