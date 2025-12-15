package com.example.ServiceApp.repositories;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.example.ServiceApp.models.Job;
import com.example.ServiceApp.models.JobReview;
import com.example.ServiceApp.models.User;

public interface JobReviewRepository extends JpaRepository<JobReview, Long>  {
	List<JobReview> findByReviedUser(User user);
	List<JobReview> findByJob(Job job);
	@Query("SELECT AVG(r.rating) FROM JobReview r WHERE r.reviewedUser.id = :userId")
    Double findAverageRatingByUser(@Param("userId") Long userId);
}
