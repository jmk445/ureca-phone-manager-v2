package com.mycom.myapp.phone.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="phone")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Phone {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int phoneId;
	String phoneName;
	int phonePrice;
	String phoneMaker;
	int phoneRemain;	
}
