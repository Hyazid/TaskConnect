package com.example.ServiceApp.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role {
	public enum RoleType{
		ROLE_ADMIN,
        ROLE_CLIENT, 
        ROLE_WORKER,
        ROLE_SUPER_ADMIN
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Column(nullable = false, unique = true)
	@Enumerated(EnumType.STRING)
	private RoleType name;
	
	private String description;

}
