package app.laboat.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


//Jokaisella tietokantaan tallennettavalla luokalla tulee olla annotaatio @Entity 
//sekä @Id-annotaatiolla merkattu attribuutti, joka toimii tietokantataulun ensisijaisena avaimena.
@Entity
// @Table(name = "user")
public class User /*extends AbstractPersistable<Long>*/ {
	
    @Id
    // antaa id-kentän arvojen luomisen vastuun tietokannalle
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // ei voi olla 0 eikä arvoa voi päivittää
    @Column(name = "uID", nullable = false, updatable = false)
    private Long uID;

    // uniikki käyttäjätunnus
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String passwordHash;

    @Column(name = "role", nullable = false)
    private String role;
    
    public User() {}

	public User(String username, String passwordHash, String role) {
		this.username = username;
		this.passwordHash = passwordHash;
		this.role = role;
	}

	public Long getuID() {
		return uID;
	}

	public void setuID(Long uID) {
		this.uID = uID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [uID=" + uID + ", username=" + username + ", passwordHash=" + passwordHash + ", role=" + role
				+ "]";
	}
    
}