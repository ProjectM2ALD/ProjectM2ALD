package net.ald.projet.entities;



import java.util.HashSet;
import java.util.Set;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


@Entity
@XmlRootElement(name = "artiste")
public class Artiste {
        
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        private int id;
        private String nom;
        private String prenom;
        
        @OneToMany(mappedBy="artiste", orphanRemoval=true)
        private Set<Oeuvre> oeuvres = new HashSet<Oeuvre>();
        
        public Artiste(){
                
        }


        public Artiste(String prenom, String nom) {
                this.nom = nom;
                this.prenom = prenom;
        }

        @XmlElement
        public int getId() {
                return id;
        }

        public void setId(int id) {
                this.id = id;
        }

        @XmlElement
        public String getNom() {
                return nom;
        }

        public void setNom(String nom) {
                this.nom = nom;
        }

        @XmlElement
        public String getPrenom() {
                return prenom;
        }

        public void setPrenom(String prenom) {
                this.prenom = prenom;
        }

        @XmlTransient
        public Set<Oeuvre> getOeuvres() {
                return oeuvres;
        }

        public void setOeuvres(Set<Oeuvre> oeuvres) {
                this.oeuvres = oeuvres;
        }
        
        public void addOeuvre(Oeuvre o){
                this.oeuvres.add(o);
        }
        
        public void removeOeuvre(Oeuvre o){
                this.oeuvres.remove(o);
        }
                
}

