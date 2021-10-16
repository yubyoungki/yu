package com.mongta.myspringboot.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class User {
	@Id
	@GeneratedValue
	private Long id;
	@Column
	@NotBlank(message="Name은 필수 입력 입니다.")
	private String name;
	@Column(unique=true)
	@NotBlank(message="email은 필수 입력 입니다.")
	private String email;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
