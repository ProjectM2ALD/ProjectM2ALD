
package net.ald.projet.entities;


import java.util.List;


import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.xml.bind.annotation.XmlRootElement;


import net.ald.projet.property.Dimension;
import net.ald.projet.property.Realisation;
import net.ald.projet.property.SupportOeuvre;




@Entity
@XmlRootElement(name = "peinture")
public class Peinture extends Oeuvre{
        @Enumerated(EnumType.STRING)
        private SupportOeuvre support;
        @Enumerated(EnumType.STRING) 
        private Realisation realisation;


        
        public Peinture(){
                
        }
        
  
        public Peinture(Dimension dimension, boolean hasBeenReproduced,
                        Artiste artiste, List<Photo> photo, Integer annee, String caracteristique,
                        String titre, String resume, List<String> commentaire, String tag, SupportOeuvre support, Realisation realisation) {
                super(dimension, hasBeenReproduced, artiste, photo, annee, caracteristique, titre,
                                resume, commentaire, tag);
                this.support = support;
                this.realisation = realisation;
        }

    public Peinture(int id, String titre, boolean hasBeenReproduced) {
	super(id, titre, "Peinture", hasBeenReproduced);
    }

    public SupportOeuvre getSupport() {
	return support;
    }


    public void setSupport(SupportOeuvre support) {
                this.support = support;
        }


        public Realisation getRealisation() {
                return realisation;
        }


        public void setRealisation(Realisation realisation) {
                this.realisation = realisation;
        }
        
        
}

