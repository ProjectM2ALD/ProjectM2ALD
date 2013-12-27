package net.ald.projet.property;


//import javax.persistence.Embeddable;




public enum SupportOeuvre {


        LIN("Toile de lin"), COTON("Toile de coton"), PAPIER("Papier") ,
        CARTON("carton"),
        BOIS("bois"),
        ALUMINIUM("aluminium") ;        
        
        private final String stringValue;
        private SupportOeuvre(final String s) { stringValue = s; }
        private SupportOeuvre() {stringValue = ""; }
        public String toString() { return stringValue; }


}

