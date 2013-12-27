package net.ald.projet.property;


//import javax.persistence.Embeddable;


public enum Materiaux {
        
        PLATRE("platre et stuc"),
        CARTON("Carton pate"),
        CIRE("Cire") ,
        ARGILE("Argile"),
        PLASTILINE("Palstiline"),
        STUFF("Stuff") ,
        CIMENT("Ciment"),
        PIERRE("Pierre"),
        BOIS("Bois");


        private final String stringValue;
        private Materiaux(final String s) { stringValue = s; }
        private Materiaux(){ stringValue = ""; }
        public String toString() { return stringValue; }
}

