package com.example.ServiceApp.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
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
    private String phoneNumber; // Corrigé : "phoneNumer" -> "phoneNumber"
    
    @Builder.Default
    private Boolean isActive = true;
    
    @Embedded
    private Address address; // Nécessite une classe Address avec @Embeddable
    
    private String imageProfile;
    
    @CreationTimestamp
    private LocalDateTime createdAt;
    
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    
    // Relations
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_role",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();
    
    // Ces trois relations @OneToOne créent une conception étrange
    // Un utilisateur ne devrait normalement avoir qu'un seul type de profil
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private WorkerProfile workerProfile;
    
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private ClientProfile clientProfile;
    
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private AdminProfile adminProfile;
    
    @OneToMany(mappedBy = "postedBy")
    private List<Job> postedJob = new ArrayList<>();
    
    @OneToMany(mappedBy = "assignedTo")
    private List<Job> assignedJob = new ArrayList<>();
    
    @OneToMany(mappedBy = "applicant")
    private List<JobApplication> jobApplications = new ArrayList<>();
    
    @OneToMany(mappedBy = "reviewer")
    private List<JobReview> reviewsGiven = new ArrayList<>();
    
    @OneToMany(mappedBy = "reviewedUser")
    private List<JobReview> reviewsReceived = new ArrayList<>();
    
    @OneToMany(mappedBy = "proposedBy")
    private List<JobProposal> proposalsMade = new ArrayList<>();
    
    @OneToMany(mappedBy = "proposedTo")
    private List<JobProposal> proposalsReceived = new ArrayList<>();
    
    public boolean hasRole(String roleName) {
        return roles.stream().anyMatch(role -> role.getName().equals(roleName));
    }
    
    public boolean isClient() {
        return hasRole("ROLE_CLIENT");
    }
    
    public boolean isAdmin() {
        return hasRole("ROLE_ADMIN");
    }
    
    public boolean isWorker() {
        return hasRole("ROLE_WORKER");
    }
    
    public void addRole(Role role) {
        this.roles.add(role);
        if ("ROLE_WORKER".equals(role.getName()) && workerProfile == null) {
            this.workerProfile = new WorkerProfile();
            this.workerProfile.setUser(this);
                
               
        } else if ("ROLE_CLIENT".equals(role.getName()) && clientProfile == null) {
            this.clientProfile = new ClientProfile();
            this.clientProfile.setUser(this);
        } else if ("ROLE_ADMIN".equals(role.getName()) && adminProfile == null) {
            this.adminProfile = new AdminProfile();
            this.adminProfile.setUser(this);
        }
    }
    
    public boolean canPostJob() {
        return isActive() && (isClient() || isWorker());
    }
    
    // Méthode getter corrigée
    public Boolean isActive() {
        return this.isActive;
    }
}