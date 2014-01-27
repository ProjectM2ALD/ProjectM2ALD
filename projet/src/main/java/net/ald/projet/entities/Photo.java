package net.ald.projet.entities;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


@Entity
@XmlRootElement(name = "photo")
public class Photo {


        @Id @GeneratedValue(strategy = GenerationType.AUTO) 
        private int id;
        
        @ManyToOne 
        @JoinColumn (name="oeuvre_id")
        private Oeuvre oeuvre;
        
        private String path;
        
        
        public Photo(){
                
        }
        
        public Photo(String path, Oeuvre o) {
                this.path = path;
                this.oeuvre = o;
        }


        @XmlElement
        public int getId() {
                return id;
        }


        public void setId(int id) {
                this.id = id;
        }


        @XmlTransient
        public Oeuvre getOeuvre() {
                return oeuvre;
        }


        public void setOeuvre(Oeuvre oeuvre) {
                this.oeuvre = oeuvre;
        }
        
}

