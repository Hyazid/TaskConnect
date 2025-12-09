package com.example.ServiceApp.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "client_profiles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientProfile {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	//relate the client profile to the user
	
	@OneToOne
	@JoinTable(name = "user_id")
	@MapsId
	private User user;
	
	
	//
	private String companyName;
	private String industry;
	public enum ClientType{
		INDIVUDUAL,SMALL_BUISNESS,CORPORATE,GOVERMENT
	}
	@Enumerated(EnumType.STRING)
	private ClientType clientType;
	
	private String nin;
	private String contactPerson;
	private String departement;
	@Builder.Default
	private Double credit = 1000.00;
	
	@Builder.Default
	private Double totalSpent=0.0;
	
	// a client can post job to attract worker
	//a client can post one to many post
	@OneToMany(mappedBy = "client")
	private List<Job> postJob = new ArrayList<>();
	

}
