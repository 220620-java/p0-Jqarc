package com.revature.project.models;

import java.util.Objects;

public class Account {
	private int id;
	private String name;
	private double balance;

	public Account() {
		super();
		this.id = 0;
		this.name = "";
		this.balance = 0.0;
	}

	public Account(String name, double balance) {
		super();
		this.id = 0;
		this.name = "";
		this.balance = 0.0;
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

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, balance);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		return id == other.id && Objects.equals(name, other.name) && balance == other.balance;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", name=" + name + ", balance=" + balance  + "]";
	}
}
