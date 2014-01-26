package net.ald.projet.entities;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import net.ald.projet.property.Connexion;

@Entity
@XmlRootElement(name = "user")
public class User implements java.io.Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String login;
	private String password;
	private String statut;
	
	public User(){

	}
	
	public User(int id, String login, String password, String statut) {
		this.id = id;
		this.login = login;
		this.password = password;
		this.statut = statut;
	}

	
	public User(String login, String password, String statut) {
		super();
		this.login = login;
		this.password = password;
		this.statut = statut;
	}

	@XmlElement
	public String getLogin() {
		return login;
	}

	public void setId(int id) {
		this.id=id;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}

	@XmlElement
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@XmlElement
	public int getId() {
		return id;
	}

	@XmlElement
	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}

}