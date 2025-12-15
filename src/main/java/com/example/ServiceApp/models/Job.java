package com.example.ServiceApp.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.dialect.type.AbstractPostgreSQLJsonPGObjectType;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "jobs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Job {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String title;
	
	@Column(columnDefinition = "TEXT")
	private String description;
	public enum JobType{
		OFFER,REQUEST
	}
	@Enumerated(EnumType.STRING)
	private JobType jobType;
	
	public enum JobStatus{
		POSTED,ASSIGNED,CANCELED,IN_PROGRESS, COMPLETED
	}
	@Enumerated(EnumType.STRING)
	private JobStatus jobStatus;
	 
	private Double budget;
	private String Location;
	
	@ElementCollection
	@CollectionTable(name="job_skills_required")
	private Set<String> requiredSkill  = new HashSet<>();
	
	@ElementCollection
	@CollectionTable(name="job_skills_required")
	private List<String> images  = new ArrayList<>();
	
	private LocalDateTime postedAt;
	private LocalDateTime updatedAt;
	private LocalDateTime assignedAt;
	private LocalDateTime completedAt;
	//relations
	
	//user create one or many jobs
	//many jobs can be posted by on user
	@ManyToOne
	@JoinColumn(name = "posted_by_user_id")
	private User postedBy;
	
	@ManyToOne
	@JoinColumn(name = "assigned_by_user_id")
	private User assignedTo;
	
	//job can have one or multipl job applcation
	@OneToMany(mappedBy = "job", cascade = CascadeType.ALL)
	private List<JobApplication> applications = new ArrayList<>();
	
	@OneToMany(mappedBy = "job", cascade = CascadeType.ALL)
	private List<JobReview> reviews  = new ArrayList<>();
	
	@OneToMany(mappedBy = "job", cascade = CascadeType.ALL)
	private List<JobProposal> proposals = new ArrayList<>();
	
	
	public boolean canApplay(User user) {
		return !user.equals(postedBy)&& jobStatus == JobStatus.POSTED && (user.isWorker()|| user.isClient() );
	}
	public boolean isPostedByWorker() {
		return postedBy.isWorker();
	}
	public boolean isPostedByClient() {
		return postedBy.isClient();
	}
	
	
	
	

}
