package net.ald.projet.entities;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.xml.bind.annotation.XmlRootElement;

import net.ald.projet.property.Dimension;
import net.ald.projet.property.Materiaux;


@Entity
@XmlRootElement(name = "sculpture")
public class Sculpture extends Oeuvre{


        @Enumerated(EnumType.STRING)
        private Materiaux materiaux;


        public Sculpture(){
                
        }

    public Sculpture(int id, Artiste artiste, String titre, Integer annee) {
	super(id, artiste, titre, annee, "Sculpture");
    }

               
    public Sculpture(Dimension dimension,
                        Artiste artiste, List<Photo> photo, Integer annee, String caracteristique,
                        String titre, String resume, List<String> commentaire, String tag, Materiaux materiaux) {
                super(artiste, titre, annee, dimension, photo, caracteristique, commentaire, tag);
                this.materiaux = materiaux;
        }


        public Sculpture(int id, String titre) {
        	super(id, titre);
	}

		public Materiaux getMateriaux() {
                return materiaux;
        }


        public void setMateriaux(Materiaux materiaux) {
                this.materiaux = materiaux;
        }    
}

