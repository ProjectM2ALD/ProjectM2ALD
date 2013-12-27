package net.ald.projet.property;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;






@SuppressWarnings("serial")
@Embeddable
public class Dimension implements Serializable{
        @Column(nullable = true)
        int hauteur;
        int largeur;
        int profondeur;
        
        
        public Dimension(){
                
        }
        
        public Dimension(int hauteur, int largeur, int profondeur) {
                super();
                this.hauteur = hauteur;
                this.largeur = largeur;
                this.profondeur = profondeur;
        }


        public int getHauteur() {
                return hauteur;
        }


        public void setHauteur(int hauteur) {
                this.hauteur = hauteur;
        }


        public int getLargeur() {
                return largeur;
        }


        public void setLargeur(int largeur) {
                this.largeur = largeur;
        }


        public int getLongueur() {
                return profondeur;
        }


        public void setLongueur(int profondeur) {
                this.profondeur = profondeur;
        }
        
        
}

