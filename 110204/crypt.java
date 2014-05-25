import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Scanner;
import java.util.StringTokenizer;

/*
    Crypt kicker
    ------------

    Častá, ale nie moc bezpečná metóda šifrovania textu je permutácia písmen abecedy.
    Inými slovami, každé písmeno v abecede je nahradené v texte iným písmenom.
    Aby bolo zaistené, že šifrovanie je obojstranné, žiadne dva písmená niesú nahradené rovnakým písmenom.

    Vašou úlohou je dešifrovať niekoľko zakódovaných riadkov textu, za predpokladu, že každý riadok
    používa inú sadu náhrad, a že všetky slová v dešifrovanom texte sú zo slovníka známych slov.

    VSTUP
    -----

    Vstup sa skladá z riadku, ktorý obsahuje celé číslo n, nasledovaný n slovami zložených z malých písmen,
    jedno slovo na riadok, v abecednom poradí. Týchto n slov skladá slovník slov, ktoré sa môžu objaviť v dešifrovanom texte.
    Po slovníku nasleduje niekoľko riadkov vstupu. Každý riadok je zašifrovaný, ako je popísané vyššie.
    Slovník obsahuje do 1000 slov. Žiadne slovo nepresahuje dĺžku 16 znakov.
    Šifrované riadky obsahujú iba malé písmená a medzery, a neprekračujú dĺžku 80 znakov.

    VÝSTUP
    ------

    Dešifruj každý riadok a vypíš ich na štandardný výstup. Ak existuje viac riešení, vyber jedno z nich.
    Ak riešenie neexistuje, vymeň každé písmeno abecedy za hviezdičku.
 */

class Main {

    private static class Word {

        private String text;

        // celkovy pocet podobnych pismen
        private int nbrSimilarLetters;

        // zoznam ktory obsahuje zoznamy podobnych slov
        private final List<List<Integer>> similarLetters = new LinkedList<List<Integer>>();

        public Word(String s) {

            // text slova
            this.text = s;

            // indexy podobnych pismen ulozime v mape pismeno => zoznam indexov
            Map<Character, List<Integer>> c2Indices = new HashMap<Character, List<Integer>>();

            // prejdeme slovom po znaku
            for (int i = 0; i < s.length(); ++i) {

                // skusime ziskat zoznam z mapy pre dane pismeno na aktualnom indexe
                List<Integer> l = c2Indices.get(s.charAt(i));

                // ak este zoznam este v mape neexistuje tak vo vytvorime
                if (l == null) {
                    l = new LinkedList<Integer>();
                    c2Indices.put(s.charAt(i), l);
                }

                // do zoznamu v mape pre dane pismeno vlozime aktualny index
                l.add(i);
            }

            /*
             * (e.g. football: similarLetters:[[1,2], [6,7]], nbrSimilarLetters:
             * 2 for o + 2 for l = 4
             */

            // znovu prejdeme slovom
            for (int i = 0; i < s.length(); ++i) {

                // ziskame zoznam pre aktualne pismeno
                List<Integer> l = c2Indices.get(s.charAt(i));

                // pokial v zozname mame nejake indexy
                // pridame zoznam do hlavneho zoznamu podobnych slov
                // pricitame do celkoveho poctu podobnych pismen dlzku zoznamu
                if (l.size() > 1) {
                    similarLetters.add(l);
                    nbrSimilarLetters += l.size();
                }
            }
        }

        public String getText() {
            return text;
        }

        public int getLength() {
            return text.length();
        }

        public int getNbrSimilarLetters() {
            return nbrSimilarLetters;
        }

        public void setTextWithoutParsing(String text) {
            this.text = text;
        }

        public boolean equals(Word word, Map<Character, Character> l2lEncrypt) {

            // porovanie slov na zaklade celkoveho poctu podobnych pismen
            if (word.nbrSimilarLetters != this.nbrSimilarLetters) {
                return false;
            }

            // porovnanie slov na zaklade dlzky zoznamov podobnych pismen
            if (word.similarLetters.size() != this.similarLetters.size()) {
                return false;
            }

            // porovnanie jednotlivych zoznamov podobnych pismen
            for (int i = 0; i < this.similarLetters.size(); ++i) {

                List<Integer> lA = this.similarLetters.get(i);
                List<Integer> lB = word.similarLetters.get(i);

                if (lA.size() != lB.size()) {
                    return false;
                }

                for (int ii = 0; ii < lA.size(); ++ii) {

                    if (!lA.get(ii).equals(lB.get(ii))) {
                        return false;
                    }
                }
            }

            // kontrola hviezdiciek
            for (int i = 0; i < word.text.length(); ++i) {

                char c1 = word.text.charAt(i);
                char c2 = this.text.charAt(i);

                if ((c1 == STAR && l2lEncrypt.get(c2) == STAR) || (c2 == STAR && l2lEncrypt.get(c1) == STAR)) {

                    continue;
                }

                if (c1 != c2) {
                    return false;
                }
            }

            return true;
        }
    }

    private static final char STAR = '*';

    /*
     * Partition on number of letters and then on number of similar letters.
     * (e.g. football, number of letters:8, similar letters: 2 for o + 2 for l
     * equals 4.
     */
    private final Map<Integer, Map<Integer, List<Word>>> dict = new HashMap<Integer, Map<Integer, List<Word>>>();

    // desifrovacia mapa pismeno => pismeno
    private Map<Character, Character> decryptDict = new HashMap<Character, Character>();

    // sifrovacia mapa pismeno => pismeno
    private final Map<Character, Character> encryptDict = new HashMap<Character, Character>();

    // Indication whether backtrack algorithm should continue or stop.
    private boolean backtrackFinished = false;

    public static void main(String[] args) {

        new Main().begin();
    }

    private void begin() {

        Scanner sc = new Scanner(System.in);
        int length = Integer.valueOf(sc.nextLine());

        // Read the dictionary words.
        Set<String> isWordReadSet = new HashSet<String>();

        for (int i = 0; i < length; ++i) {

            String s = sc.nextLine();
            Word word = new Word(s);

            // Avoid duplicated dictionary words.
            if (isWordReadSet.contains(s)) {
                continue;
            } else {
                isWordReadSet.add(s);
            }

            // Assign * to all letters of dictionary words.
            for (int ii = 0; ii < s.length(); ++ii) {
                encryptDict.put(s.charAt(ii), STAR);
            }

            // Assign the dictionary word to the correct partition,
            // for efficient search capability.
            Map<Integer, List<Word>> wordsMap = dict.get(s.length());

            if (wordsMap == null) {

                wordsMap = new HashMap<Integer, List<Word>>();
                List<Word> words = new LinkedList<Word>();
                words.add(word);
                wordsMap.put(word.getNbrSimilarLetters(), words);
                dict.put(s.length(), wordsMap);
            } else {

                List<Word> words = wordsMap.get(word.getNbrSimilarLetters());

                if (words == null) {
                    words = new LinkedList<Word>();
                    wordsMap.put(word.getNbrSimilarLetters(), words);
                }
                words.add(word);
            }
        }

        // Read the encrypted lines and decrypt using backtracking algorithm.
        while (sc.hasNextLine()) {

            String line = sc.nextLine();
            String decrLine = decrypt(line);
            System.out.println(decrLine);
        }
    }

    private String decrypt(String line) {

        // Read the unique words.
        StringTokenizer st = new StringTokenizer(line);
        Map<String, Word> uniqueWords = new HashMap<String, Word>();

        decryptDict.clear();

        while (st.hasMoreTokens()) {

            String s = st.nextToken();

            if (uniqueWords.get(s) == null) {

                uniqueWords.put(s, new Word(s));

                // Assign * to the letters of the encrypted words.
                for (int i = 0; i < s.length(); ++i) {
                    decryptDict.put(s.charAt(i), STAR);
                }
            }
        }

        List<Word> words = new ArrayList<Word>(uniqueWords.values());
        backtrackFinished = false;
        backtrack(words, decryptDict, encryptDict, 0);

        // Decrypt each letter of the encrypted line.
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < line.length(); ++i) {

            if (line.charAt(i) == ' ') {
                sb.append(' ');
            } else {
                sb.append(decryptDict.get(line.charAt(i)));
            }

        }

        return sb.toString();
    }

    private void backtrack(List<Word> words,
                           Map<Character, Character> decryptDict,
                           Map<Character, Character> encryptDict, int wordIndex) {

        // If a solution is found then process the solution and return.
        if (wordIndex >= words.size()) {
            processSolution(decryptDict);
            return;
        }

        Map<Character, Character> l2lDecrypt = new HashMap<Character, Character>(decryptDict);

        // Find the candidates.
        Word word = words.get(wordIndex);
        String origText = word.getText();
        StringBuilder text = new StringBuilder();

        for (int i = 0; i < origText.length(); ++i) {
            text.append(decryptDict.get(origText.charAt(i)));
        }

        word.setTextWithoutParsing(text.toString());
        List<Word> candidates = getCandidates(word, encryptDict);
        word.setTextWithoutParsing(origText);

        // Backtrack for the found candidates.
        for (Word candidate : candidates) {

            String cndText = candidate.getText();
            Map<Character, Character> l2lEncrypt = new HashMap<Character, Character>(
                    encryptDict);

            // Fill in the letter to letter dictionaries with the new finding.
            for (int i = 0; i < origText.length(); i++) {
                l2lDecrypt.put(origText.charAt(i), cndText.charAt(i));
                l2lEncrypt.put(cndText.charAt(i), origText.charAt(i));
            }

            backtrack(words, l2lDecrypt, l2lEncrypt, wordIndex + 1);

            if (backtrackFinished) {
                return;
            }
        }
    }

    private void processSolution(Map<Character, Character> decryptDict) {

        this.decryptDict = decryptDict;
        backtrackFinished = true;
    }

    private List<Word> getCandidates(Word word, Map<Character, Character> l2lEncrypt) {

        List<Word> candidates = new LinkedList<Word>();

        // Search on word length.
        Map<Integer, List<Word>> wordsMap = dict.get(word.getLength());

        if (wordsMap == null) {
            return candidates;
        }

        // Search on similar letters.
        List<Word> words = wordsMap.get(word.getNbrSimilarLetters());

        if (words == null) {
            return candidates;
        }

        // Search for all possible candidates.
        for (Word w : words) {

            if (w.equals(word, l2lEncrypt)) {
                candidates.add(w);
            }
        }

        return candidates;
    }
}