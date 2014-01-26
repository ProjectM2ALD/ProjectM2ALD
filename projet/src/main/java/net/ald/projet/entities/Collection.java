package net.ald.projet.entities;
import net.ald.projet.property.EtatCollection;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@XmlRootElement(name = "collection")
public class Collection {
        
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        private int id;
        
        @Enumerated(EnumType.STRING)
        private EtatCollection etat;
        
        @ManyToMany 
        private List<Oeuvre> oeuvres = new ArrayList<Oeuvre>();
        
        //private String libele;
        
        @ElementCollection
        @Column(nullable=true)
        private List<String> commentaire = new ArrayList<String>();
        
        @ElementCollection
        @Column(nullable=true)
        private List<String> tag = new ArrayList<String>();
             
    public Collection(){
                
    }
        
    public Collection(List<Oeuvre> oeuvres) {
	super();
	this.oeuvres = oeuvres;
    }

    public Collection(int id, EtatCollection etat) {
	this.id = id;
	this.etat = etat;
	//this.libele = libele;
    }
        
    public String toString(){
	return null;
    }

    @XmlElement
        public int getId() {
                return id;
        }

        public void setId(int id) {
                this.id = id;
        }


        @XmlElement
        public EtatCollection getEtat() {
                return etat;
        }

        public void setEtat(EtatCollection etat) {
                this.etat = etat;
        }

        @XmlElement
        public List<Oeuvre> getOeuvres() {
                return oeuvres;
        }


        public void setOeuvres(List<Oeuvre> oeuvres) {
                this.oeuvres = oeuvres;
        }
         
        public void addOeuvre(Oeuvre oeuvre){
                oeuvres.add(oeuvre);
        }
        
        public void removeOeuvre(Oeuvre oeuvre){
                oeuvres.remove(oeuvre);
        }
        


        /*@XmlElement
        public String getLibele() {
                return libele;
        }

        public void setLibele(String libele) {
                this.libele = libele;
        }*/

        @XmlElement
        public List<String> getCommentaire() {
                return commentaire;
        }

        public void setCommentaire(List<String> commentaire) {
                this.commentaire = commentaire;
        }
        
        public void addComment(String comment){
                this.commentaire.add(comment);
        }
        
        public void removeComment(String comment){
                this.commentaire.remove(comment);
        }

        @XmlElement
        public List<String> getTags() {
                return tag;
        }

        public void setTag(List<String> tag) {
                this.tag = tag;
        }     
        
        public void addTag(String tag){
                this.tag.add(tag);
        }
        
        public void removeTag(String tag){
                this.tag.remove(tag);
        }             
}

