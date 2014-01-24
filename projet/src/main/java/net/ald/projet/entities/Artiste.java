package net.ald.projet.entities;



import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


@Entity
@Table(name="Artiste")
@XmlRootElement(name = "artiste")
public class Artiste {
        
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        private int id;
        private String first_name;
        private String last_name;
        
        @OneToMany(mappedBy="artiste", orphanRemoval=true)
        private Set<Oeuvre> oeuvres = new HashSet<Oeuvre>();
        
        public Artiste(){
                
        }
        
        public Artiste(int id, String first_name, String last_name, Set<Oeuvre> oeuvre) {
        	this.id = id;
        	this.first_name = first_name;
        	this.last_name= last_name;
            this.oeuvres = oeuvre;
        }
        
        public Artiste(int id, String first_name, String last_name) {
        	this.id = id;
        	this.first_name = first_name;
            this.last_name= last_name;
        }

        @XmlElement
        public int getId() {
                return id;
        }

        public void setId(int id) {
                this.id = id;
        }

        @XmlElement
		public String getFirst_name() {
			return first_name;
		}
       
		public void setFirst_name(String first_name) {
			this.first_name = first_name;
		}
        @XmlElement
		public String getLast_name() {
			return last_name;
		}
       
		public void setLast_name(String last_name) {
			this.last_name = last_name;
		}

        @XmlTransient
        public Set<Oeuvre> getOeuvres() {
                return oeuvres;
        }

        public void setOeuvres(Set<Oeuvre> oeuvres) {
                this.oeuvres = oeuvres;
        }
        
        public void addOeuvre(Oeuvre oeuv){
                this.oeuvres.add(oeuv);
        }
        
        public void removeOeuvre(Oeuvre oeuv){
                this.oeuvres.remove(oeuv);
        }
        
                
}

