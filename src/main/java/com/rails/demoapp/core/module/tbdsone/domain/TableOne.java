package com.rails.demoapp.core.module.tbdsone.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ds_one_tb_one")
public class TableOne {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)  
	private Long id;
	
	private String col1;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCol1() {
		return col1;
	}

	public void setCol1(String col1) {
		this.col1 = col1;
	}
}
