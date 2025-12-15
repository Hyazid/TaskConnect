package com.example.ServiceApp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.*;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "job_reviews")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobReview {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "job_id",nullable = false)
	private Job job;
	
	@ManyToOne
	@JoinColumn(name = "reviewer_id", nullable = false)
	private User reviewrs;
	
	
	@ManyToOne
	@JoinColumn(name="reviewed_user_id", nullable = false)
	private User reviewdUser;
	
	@Max(1)@Max(5)
	private Integer rating;
	
	private Boolean recommend;
	
}
