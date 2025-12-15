package com.example.ServiceApp.models;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "job_proposals")
@Getter
@Setter

public class JobProposal {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@ManyToOne
	@JoinColumn(name = "job_id")
	private Job job;
	
	@ManyToOne
	@JoinColumn(name = "proposed_by_user_id")
	private User proposedBy;
	
	@ManyToOne
	@JoinColumn(name = "proposed_to_user")
	private User proposedTo;
	
	private Double proposedAmount;
	private String term;
	
	private LocalDateTime proposedAt;
	private LocalDateTime respondedAt;
	
}
