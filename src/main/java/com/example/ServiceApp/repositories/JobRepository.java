package com.example.ServiceApp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ServiceApp.models.Job;
import com.example.ServiceApp.models.User;

public interface JobRepository extends JpaRepository<Job, Long> {
	List<Job>findByPostedBy(User user);
	List<Job>findByAssignedTo(User user);
	List<Job>findByStatus(Job.JobStatus status);
}
