package net.ald.projet.entities;


import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


import net.ald.projet.property.SupportReproduction;


@Entity
@XmlRootElement(name = "reproduction")
public class Reproduction {


        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        private int id;
        @OneToOne
        @JoinColumn(name="oeuvre_id")
        private Oeuvre oeuvre;
        private double prix;
        @Enumerated(EnumType.STRING)
        private SupportReproduction support;


        public Reproduction() {

        }
        
        public Reproduction(Oeuvre oeuvre, int prix, SupportReproduction support) {
                super();
                this.oeuvre = oeuvre;
                this.prix = prix;
                this.support = support;
        }


        @XmlElement
        public int getId() {
                return id;
        }


        public void setId(int id) {
                this.id = id;
        }


        @XmlElement
        public Oeuvre getOeuvre() {
                return oeuvre;
        }

        public void setOeuvre(Oeuvre oeuvre) {
                this.oeuvre = oeuvre;
        }

        @XmlElement
        public double getPrix() {
                return prix;
        }


        public void setPrix(double price) {
                this.prix = price;
        }


        @XmlElement
        public SupportReproduction getSupport() {
                return support;
        }


        public void setSupport(SupportReproduction support) {
                this.support = support;
        }

}

