package model;

import java.util.Date;

public class food {

	private int id;
	private String name;
	private int weight; // gam
	private String type;
	private int place; // chia ngăn
	private String expired;

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

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getPlace() {
		return place;
	}

	public void setPlace(int place) {
		this.place = place;
	}

	public String getExp() {
		return expired;
	}

	public void setExp(String expired) {
		this.expired = expired;
	}

	public food() {
		super();
		// TODO Auto-generated constructor stub
	}

	public food(int id, String name, int weight, String type, int place, String expired) {
		super();
		this.id = id;
		this.name = name;
		this.weight = weight;
		this.type = type;
		this.place = place;
		this.expired = expired;
	}

	@Override
	public String toString() {
		return "Food [ Id: " + id + ", Name: " + name + ", Weight: " + weight + ", Type: " + type + ", Place: " + place
				+ ", Expired date: " + expired + " ]";
	}

	public void xuatAll() {
		System.out.println(toString());
	}

	public void xuat() {
		System.out.format("%-15s | ", getId());
		System.out.format("%-15s | ", getName());
		System.out.format("%-15s | ", getWeight());
		System.out.format("%-15s | ", getType());
		System.out.format("%-15s | ", getPlace());
		System.out.format("%-15s | ", getExp());
		System.out.println();
	}

}
