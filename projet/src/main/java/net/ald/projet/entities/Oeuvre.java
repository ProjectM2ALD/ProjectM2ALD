package net.ald.projet.entities;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlRootElement;
import net.ald.projet.property.Dimension;


@Entity 
@XmlRootElement 
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@XmlSeeAlso({Peinture.class, Sculpture.class, Photographie.class})
public class Oeuvre {

        @Id @GeneratedValue(strategy = GenerationType.AUTO) 
        private int id;
        
        @Embedded 
        private Dimension dimension;
        
        @ManyToOne (fetch=FetchType.LAZY)
        @JoinColumn(name="artiste_id") 
        //@XmlIDREF
        private Artiste artiste;
        
        @OneToMany (mappedBy="oeuvre",cascade={CascadeType.PERSIST, CascadeType.REMOVE})
        private List<Photo> photo = new ArrayList<Photo>();
        
        @Column(nullable=true) 
        private Integer annee;
        
        @Column(nullable=true) 
        private String caracteristique;

        @Column(nullable=true) 
        private String titre;

        @Column(nullable=true) 
        private String type;
        
        @ElementCollection
        @Column(nullable=true)
        private List<String> commentaire = new ArrayList<String>();
        
        @Column(nullable=true)
        private String tag;
        
        public Oeuvre(){
        }
        
        public Oeuvre(Artiste artiste, String titre, Integer annee, Dimension dimension, List<Photo> photo, String caracteristique,
                      List<String> commentaire, String tag) {
                super();
                this.dimension = dimension;
                this.artiste = artiste;
                this.artiste.addOeuvre(this);
                if(photo != null){
		    this.photo=photo;
                }
                this.annee = annee;
                this.caracteristique = caracteristique;
                this.titre = titre;
                this.commentaire = commentaire;
                this.tag = tag;
        }

    public Oeuvre(int id, Artiste artiste, String titre, Integer annee, String type){
	super();
	this.id = id;
	this.artiste = artiste;
	this.titre = titre;
	this.annee = annee;
	this.type = type;
    }

    public Oeuvre(int id, String titre) {
		this.id = id;
		this.titre = titre;
	}

	@XmlElement
        public int getId() {
	return id;
        }

        public void setId(int id) {
                this.id = id;
        }

        @XmlElement
        public Dimension getDimension() {
                return dimension;
        }

        public void setDimension(Dimension dimension1) {
                this.dimension = dimension1;
        }

        @XmlElement
        public Artiste getArtiste() {
                return artiste;
        }

        public void setArtiste(Artiste artiste) {
                this.artiste = artiste;
                artiste.addOeuvre(this);
        }

        @XmlElement
        public List<Photo> getPhoto() {
                return photo;
        }

        public void setPhoto(List<Photo> photo) {
                this.photo = photo;
        }

        public void addPhoto(Photo photo){
                this.photo.add(photo);
                photo.setOeuvre(this);
        }

        @XmlElement
        public Integer getAnnee() {
                return annee;
        }
        
        public void setAnnee(Integer annee) {
                this.annee = annee;
        }


        @XmlElement
        public String getCaracteristique() {
                return caracteristique;
        }
        
        public void setCaracteristique(String caracteristique) {
                this.caracteristique = caracteristique;
        }

        @XmlElement
        public String getTitre() {
                return titre;
        }

        public void setTitre(String titre) {
                this.titre = titre;
        }

        public void setCommentaire(List<String> commentaire) {
                this.commentaire = commentaire;
        }

        public void addCommentaire(String commentaire){
                this.commentaire.add(commentaire);
        }

        @XmlElement
        public String getTag() {
                return tag;
        }

        public void setTag(String tag) {
                this.tag = tag;
        }
}

