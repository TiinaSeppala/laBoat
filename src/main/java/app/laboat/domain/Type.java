package app.laboat.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Type {
	
	
	// validointi PUUTTUU
	
	
	
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long tID;
	private String typeName;
	
	
	//kukin vene kuuluu vain yhteen luokkaan
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "type")
	private List<Boat> boats;

	public Type() {}

	public Type(String typeName) {
		this.typeName = typeName;
	}

	public Long gettID() {
		return tID;
	}

	public void settID(Long tID) {
		this.tID = tID;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public List<Boat> getBoats() {
		return boats;
	}

	public void setBoats(List<Boat> boats) {
		this.boats = boats;
	}

	@Override
	public String toString() {
		return "Type [typeName=" + typeName + "]";
	}
	
}