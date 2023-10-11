package com.app.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Type")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "firstname" , nullable = false , length = 80)
	@NotEmpty(message = "Le prenom est obligatoire")
	private String firstName;
	
	@Column(name = "lastname" , nullable = false , length = 80)
	@NotEmpty(message = "Le nom est obligatoire")
	private String lastname;
	
	@Column(unique = true, length = 80 , nullable = false)
	@NotEmpty(message = "L'email est obligatoire")
	private String email;
	
	@Column(nullable = false , length = 80)
	@NotEmpty(message = "Le mot de passe est obligatoire")
	private String password;
	
	@Column(name="is_enable",columnDefinition = "boolean default true")
	private Boolean isEnable;
	
	@Column(columnDefinition = "TEXT",nullable = true)
	private String address;
	
	@ManyToMany (fetch = FetchType.EAGER , cascade = CascadeType.ALL)
	@JoinTable(
		name ="users_roles" , 
		joinColumns = {@JoinColumn(name="user_id")},
		inverseJoinColumns ={@JoinColumn(name="role_id")})
	private List<Role> roles = new ArrayList<>();
	
	public User() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(Boolean isEnable) {
		this.isEnable = isEnable;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
}
