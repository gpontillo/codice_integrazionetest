package org.base;

public class WordDuplicateRemover {
    
    // Metodo per eliminare i duplicati di una parola all'interno di una stringa
    public String removeWordDuplicates(String input, String word) {
        // T1, T3 fix
        // Controllo nel caso in cui ci siano stringhe null
        if(input == null || word == null)
            return input;

        // T13 Fix: sostituisce temporaneamente con una stringa sanificata da spazi o altri caratteri speciali
        // Controllo e sostituzione nel caso in cui word contenga una frase
        String inputSearch = input;
        String wordSearch = word.replaceAll("[^\\sa-zA-Z0-9]", "").replace(" ", "");
        inputSearch = inputSearch.replace(word, wordSearch);

        // Dividi la stringa in parole
        String[] words = inputSearch.split(" ");
        
        StringBuilder result = new StringBuilder();
        boolean wordFound = false;
        
        // Itera su tutte le parole
        for (String w : words) {
            // T16 fix: non controlla la punteggiatura
            String wordToCheck = w;
            wordToCheck = wordToCheck.replaceAll("[^\\sa-zA-Z0-9]", "");

            // Controlla se la parola corrente è uguale alla parola da eliminare
            if (wordToCheck.equals(wordSearch)) {
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

        // Ripristino della parola originale nel caso in cui sia stata modificata
        String resultString = result.toString().trim().replace(wordSearch, word);
        
        // Rimuovi lo spazio bianco finale e restituisci il risultato
        return resultString;
    }
}
