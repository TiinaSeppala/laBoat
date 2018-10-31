package app.laboat.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Boat {
	
	
	// validointi PUUTTUU + kommentit mitä mikäki osa tekee!!
	
	
	
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long bID;
    private String name;
    private String model;
    private int year;
    private int price;
    private String location;
    private double length;
    private double width;
    
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "tID")
    private Type type;
    
	public Boat() {}

	public Boat(String name, String model, int year, Type type, int price, String location, double length, double width) {
		this.name = name;
		this.model = model;
		this.year = year;
		this.type = type;
		this.price = price;
		this.location = location;
		this.length = length;
		this.width = width;
		
	}

//	public Boat(Long bID, String name, String model, int year, Type type, int price, String location, double length, double width) {
//		super();
//		this.bID = bID;
//		this.name = name;
//		this.model = model;
//		this.year = year;
//		this.type = type;
//		this.price = price;
//		this.location = location;
//		this.length = length;
//		this.width = width;
//	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Long getbID() {
		return bID;
	}

	public void setbID(Long bID) {
		this.bID = bID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	@Override
	public String toString() {
		if (this.type != null) {
			return "Boat [bID=" + bID + ", name=" + name + ", model=" + model + ", year=" + year + ", type=" + this.getType() +", price=" + price
				+ ", location=" + location + ", length=" + length + ", width=" + width + "]";
		} else {
			return "Boat [bID=" + bID + ", name=" + name + ", model=" + model + ", year=" + year + ", price=" + price + ", location=" + location + ", length=" + length + ", width=" + width + "]";	
		}
			
	}
    
}
