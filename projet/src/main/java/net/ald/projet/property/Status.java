package net.ald.projet.property;


public enum Status {
        
        CONSERVATEUR("Conservateur"),
        LIBRAIRE("Libraire");


        private final String stringValue;
        private Status(final String s) { stringValue = s; }
        private Status() {stringValue = ""; }
        public String toString() { return stringValue; }
        
}

