package net.ald.projet.property;


//import javax.persistence.Embeddable;




public enum Realisation {
	
        HUILE("Peinture a l'huile"),
        GOUACHE("Gouache") ,
        AQUARELLE("Aquarelle"),
        ACRYLIQUE("Acrylique");


        private final String stringValue;
        private Realisation(final String s) { stringValue = s; }
        private Realisation() {stringValue = ""; }
        public String toString() { return stringValue; }
}

