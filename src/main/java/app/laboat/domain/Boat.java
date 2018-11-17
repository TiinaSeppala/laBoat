package app.laboat.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Boat {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long bID;
    
    @NotNull
    @Size(min=1, message = "* can't be empty")
    private String name;
    
    @NotNull
    @Size(min=1, message = "* can't be empty")
    private String model;
    
    @NotNull
    @Positive(message = "* value can't be negative")
    @Min(value = 1800, message = "* your boat is too old for our database or try atleast 4 numbers")
    @Max(value = 2019, message = "* your boat is from future or try to input year with 4 numbers")
    private int year;
    
    @NotNull
    @Positive(message = "* value can't be negative")
    private int price;
    
    @NotNull
    @Size(min=2, message="* location should have atleast 2 characters")    
    private String location;
    
    @NotNull
    @Positive(message = "* value can't be negative")
    private double length;
    
    @NotNull
    @Positive(message = "* value can't be negative")
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
