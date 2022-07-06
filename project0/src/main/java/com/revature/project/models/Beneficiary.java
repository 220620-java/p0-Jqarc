package com.revature.project.models;

import java.util.Objects;

public class Beneficiary {
	private int id;
	private String name;
	
	public Beneficiary() {
		this.id=1;
		this.name = "";
	}
	
	public Beneficiary(int id, String name) {
		this.id=id;
		this.name=name;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Beneficiary other = (Beneficiary) obj;
		return id == other.id && Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "Status [id=" + id + ", name=" + name + "]";
	}
}
