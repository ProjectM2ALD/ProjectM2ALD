package net.ald.projet.entities;


import java.util.List;


import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.xml.bind.annotation.XmlRootElement;


import net.ald.projet.property.Dimension;
import net.ald.projet.property.SupportOeuvre;




@Entity
@XmlRootElement(name = "photographie")
public class Photographie extends Oeuvre {
        
        @Enumerated(EnumType.STRING)
        private SupportOeuvre support;


        public Photographie(){
                
        }
        
        
        public Photographie(Dimension dimension, boolean hasBeenReproduced,
                        Artiste artiste,  List<Photo> photo, Integer annee, String caracteristique,
			    String titre, String resume, List<String> commentaire, String tag, SupportOeuvre support) {
	    super(dimension, hasBeenReproduced, artiste, photo, annee, caracteristique, titre,
		  resume, commentaire, tag);
	    this.support = support;
        }

    public Photographie(int id, String titre, boolean hasBeenReproduced) {
	super(id, titre, "Photographie", hasBeenReproduced);


    }

    public SupportOeuvre getSupport() {
                return support;
        }

        public void setSupport(SupportOeuvre support) {
                this.support = support;
        }
        
        
}

