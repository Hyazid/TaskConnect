package com.example.ServiceApp.models;

import java.lang.foreign.AddressLayout;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "first name required")
	@Size(max = 50)
	private String firstName;
	@NotBlank(message = "lastname required")
	@Size(max = 50)
	private String lastName;
	@Email
	@NotBlank
	@Column(unique = true)
	private String email;
	@NotBlank
	private String password;
	@Size(max = 20)
	private String phoneNumer;
	@Builder.Default
	private Boolean isActive=true;
	@Embedded // Indicates that Address is an embeddable object
    private Address address;
	
	private String imageProfile;
	@CreationTimestamp
	private LocalDateTime createdAt;
	@UpdateTimestamp
	private LocalDateTime updatedAt;
	//Relation 
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_role",joinColumns = @JoinColumn(name="user_id"),inverseJoinColumns = @JoinColumn(name="role_id"))
	private Set<Role> roles = new HashSet<>();
	// a user can have one and only one woker profile
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL,orphanRemoval = true)
	private WorkerProfile workerProfile;
	// a user can have only and only one client profile
	@OneToOne(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true)
	private ClientProfile  clientProfile;
	@OneToOne(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true)
	private AdminProfile adminProfile;
	// a user can be only on admin
	
	
	
}
