package org.base;

public class WordDuplicateRemover {
    
    // Metodo per eliminare i duplicati di una parola all'interno di una stringa
    public String removeWordDuplicates(String input, String word) {
        // Dividi la stringa in parole
        String[] words = input.split("\\s+");
        
        StringBuilder result = new StringBuilder();
        boolean wordFound = false;
        
        // Itera su tutte le parole
        for (String w : words) {
            // Controlla se la parola corrente è uguale alla parola da eliminare
            if (w.equals(word)) {
                // Se la parola è già stata trovata, salta la sua ripetizione
                if (wordFound) {
                    continue;
                }
                // Altrimenti, aggiungi la parola al risultato e segna che è stata trovata
                result.append(w).append(" ");
                wordFound = true;
            } else {
                // Se non è uguale, aggiungi semplicemente la parola al risultato
                result.append(w).append(" ");
            }
        }
        
        // Rimuovi lo spazio bianco finale e restituisci il risultato
        return result.toString().trim();
    }
    
    public static void main(String[] args) {
        WordDuplicateRemover remover = new WordDuplicateRemover();
        
        // Esempio di utilizzo del metodo
        String input = "hello world hello universe world";
        String wordToRemove = "hello";
        System.out.println("Stringa originale: " + input);
        
        String result = remover.removeWordDuplicates(input, wordToRemove);
        System.out.println("Stringa senza duplicati della parola \"" + wordToRemove + "\": " + result);
    }
}
