package com.example.ServiceApp.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "worker_profiles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class WorkerProfile {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id ;
		//relation with user (Entity)table
		@OneToOne
		@JoinTable(name = "user_id")
		@MapsId
		private User user;
		
		public enum WorkerType {
	        PLUMBER, ELECTRICIAN, CARPENTER, PAINTER, 
	        HVAC_TECH, GARDENER, CLEANER, HANDYMAN
	    }
		
		private WorkerType workerType;
		private String specialization;
		private Integer yearOfExp;
		
		@Builder.Default
		private Double hourlyRate=0.0;
		@Builder.Default
		private Boolean isAvailable = true;
		
		@ElementCollection
		@CollectionTable(name = "worker_skills")
		private Set<String> skills = new HashSet<>();
		
		@ElementCollection
		@CollectionTable(name="worker_availability")
		@MapKeyColumn(name ="day_of_week")
		@Column(name = "available_hours")
		private Map<String,String>availabilitySchedule = new HashMap<>();
		
		private Double rating;
		private Integer totalJobsCompleted;
		//worker can post many jobApplication
		@OneToMany(mappedBy = "worker")
		private List<JobApplication>jobApplicationd = new ArrayList<>();
		public void setUser(User user2) {
			// TODO Auto-generated method stub
			this.user = user2;
		}
		
}
