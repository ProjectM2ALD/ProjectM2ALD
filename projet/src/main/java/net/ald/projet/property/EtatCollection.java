package net.ald.projet.property;


//import javax.persistence.Embeddable;

public enum EtatCollection {
        
        EXPOSED("Expose"),
        RESERVE("En reserve"),
        RESTORATION("En restoration");
        


        private final String stringValue;
        private EtatCollection(final String s) { stringValue = s; }
        private EtatCollection(){ stringValue = ""; }
        public String toString() { return stringValue; }
        
        
        
}

