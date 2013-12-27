package net.ald.projet.property;


//import javax.persistence.Embeddable;




public enum SupportReproduction {


        CARTE("Carte postale"),
        TOILE("Toile de lin") ,
        AFFICHE ("Affiche");        


        private final String stringValue;
        private SupportReproduction(final String s) { stringValue = s; }
        private SupportReproduction() { stringValue = ""; }
        public String toString() { return stringValue; }

}

