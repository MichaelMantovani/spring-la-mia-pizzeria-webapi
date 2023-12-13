package org.java.spring.db.pojo;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Offerta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(length = 60, nullable = false)
	private String name;

	@Column
	private LocalDate startDate;

	@Column
	private LocalDate endDate;

	@ManyToOne
	private Pizza pizza;

	public Offerta() {
	}

	public Offerta(String name, LocalDate startDate, LocalDate endDate, Pizza pizza) {
		setName(name);
		setStartDate(startDate);
		setEndDate(endDate);
		setPizza(pizza);

	}

	public Pizza getPizza() {
		return pizza;
	}

	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
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

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		return "Offerta: " + this.name + "[ id: " + getId() + "]";
	}
}
