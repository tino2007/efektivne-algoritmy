import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
    Contest Scoreboard
    ------------------

    Chcete súťažiť v ACM ICPC? Potom ste mali vedieť ako udržať skóre! Súťažiaci sú hodnotený najprv
    podľa počtu vyriešených problémov (čím viac, tým lepšie), potom podľa klesajúceho množstva
    pokutového času. Ak sú dvaja alebo viac súťažiacich rovnako hodnotení v oboch prípadoch,
    sú zobrazení v poradí podľa čísla tímu vzostupne.

    Problém je považovaný za vyriešený, ak niektorý z odoslaní tohto problému bol vyhodnotený správne.
    Trestný čas sa vypočíta ako počet minút až do prvého správne vyhodnoteného odoslania, plus 20 minút
    za každé nesprávne odoslanie pred správnym riešením. Za vevyriešené problémy sa nedávajú žiadne časové tresty.

    VSTUP
    -----

    Vstup začína s jediným kladným číslom na riadku, ktorý udáva počet prípadov, z ktorých každý je popísaný nižšie.
    Tento riadok je nasledovaný prázdnym riadkom. Prázdny riadok sa tiež nachádza medzi dvoma po sebe nasledujúcimi vstupmi.

    Vstup sa skladá zo radu hodnotení rozhodcov, ktoré obsahujú záznamy od niektorých alebo všetkých súťažiacich 1 až 100
    pri riešení problémov 1 až 9. Každý riadok vstupu sa skladá z troch čísel a písmena vo formáte L,
    kde L je C, I, R, U, alebo E. Čo znamená správne, nesprávne, vysvetlenie požiadavky, nehodnotenné a chybné podanie.
    Posledné tri prípady nemajú vplyv na bodovanie.

    Riadky vstupu sa zobrazujú v poradí, v akom boli prijaté na podanie.

    VÝSTUP
    ------

    Výstup pre každý testovací prípad sa bude skladať z hodnotiacej tabuľky, zoradenej podľa kritérií popísaných vyššie.
    Každý riadok výstupu bude obsahovať číslo súťažiacieho, počet problémov riešených súťažiacim a celkový čas trestu
    nahromadenéhp súťažiacim. Vzhľadom k tomu, že nie všetky súťažiaci sa skutočne zúčastňujú, treba zobraziť iba tých súťažiacich,
    ktorí urobili aspoň jedno odoslanie.

    Výstup z dvoch po sebe idúcich prípadov budú oddelené prázdnym riadkom.
*/

class Main {

    static class Submission implements Comparable <Submission> {

        int contestant;

        int problem;

        int time;

        String verdict;

        public Submission(int contestant, int problem, int time, String verdict) {
            this.contestant = contestant;
            this.problem = problem;
            this.time = time;
            this.verdict = verdict;
        }

        public boolean isCorrect() {
            // jedine C je povazovany za spravny a pripisu sa zan body
            return verdict.equals("C");
        }

        public boolean isIncorrect() {
            // jedine za I sa pridava trestny cas
            return verdict.equals("I");
        }

        @Override
        public String toString() {
            return contestant + " " + problem + " " + time + " " + verdict;
        }

        @Override
        public int compareTo(Submission o) {
            // porovnanie odoslania na tejto baze:
            // 1. cislo sutaziaceho
            // 2. cislo problemu
            // 3. cas
            if (this.contestant > o.contestant) return 1;
            else if (this.contestant < o.contestant) return -1;
            else if (this.problem > o.problem) return 1;
            else if (this.problem < o.problem) return -1;
            else if (this.time > o.time) return 1;
            else if (this.time < o.time) return -1;
            else return 0;
        }
    }

    static class Contestant implements Comparable <Contestant> {

        int number;

        int penaltyTime;

        int[] problems = new int[9];

        int solved = 0;

        int currentPenalty = 0;

        int previousProblem = -1;

        void addSubmission(Submission submission) {

            // ak sa jedna o novy problem tak resetneme penalty
            if (previousProblem != -1 && submission.problem != previousProblem) {
                currentPenalty = 0;
            }

            if (submission.isCorrect()) {

                // spravne riesenie
                int index = submission.problem - 1;

                if (problems[index] == 0) {
                    problems[index] = 1;

                    // spocitame predosle penalty ak nejake boli
                    penaltyTime += currentPenalty + submission.time;

                    // zvysime pocet vyriesenych problemov
                    solved++;
                }

            } else if (submission.isIncorrect()) {

                // nespravne riesenie, pricitame 20 trestnych minut
                currentPenalty += 20;
            }

            // ulozime posledne odoslany problem
            previousProblem = submission.problem;
        }

        @Override
        public String toString() {
            return number + " " + solved + " " + penaltyTime;
        }

        @Override
        public int compareTo(Contestant o) {
            // porovnanie sutiaziaceho na tejto baze:
            // 1. pocet vyriesenych uloh
            // 2. dlzka penalty casu
            // 3. cislo sutaziaceho
            if (this.solved > o.solved) return -1;
            else if (this.solved < o.solved) return 1;
            else if (this.penaltyTime > o.penaltyTime) return 1;
            else if (this.penaltyTime < o.penaltyTime) return -1;
            else if (this.number > o.number) return 1;
            else if (this.number < o.number) return -1;
            else return 0;
        }
    }

    public static void main(String[] args) throws NumberFormatException, IOException {

        final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        // citanie poctu testov, prvy riadok obsahuje jedno cele cislo s ich poctom
        int numTestCases = Integer.parseInt(in.readLine());

        // dalej nasleduje prazdny riadok
        in.readLine();

        // potom nasleduju samotne riesenia
        for (int testCase = 1; testCase <= numTestCases; testCase++) {

            List<Submission> submissions = new ArrayList<Submission>();

            String line;

            // riadok po riadku citame a naplnime pole rieseni
            while ((line = in.readLine()) != null && !line.equals("")) {

                String[] s = line.split(" ");

                submissions.add(new Submission(
                    Integer.parseInt(s[0]),
                    Integer.parseInt(s[1]),
                    Integer.parseInt(s[2]),
                    s[3]
                ));
            }

            // vytvorime vysledkovu listinu zo ziskanych rieseni
            List<Contestant> scoreboard = getScoreboard(submissions);

            StringBuilder builder = new StringBuilder();

            // zostrojime potrebny vystup
            for (int i = 0; i < scoreboard.size(); i++) {

                builder.append(scoreboard.get(i).toString());

                if (i < scoreboard.size() - 1) {
                    builder.append("\n");
                }
            }

            if (testCase < numTestCases) {
                builder.append("\n");
            }

            // vypiseme na standardny vystup
            System.out.println(builder.toString());
        }
    }

    static List <Contestant> getScoreboard(List<Submission> submissions) {

        // zoradime odoslane riesenia
        Collections.sort(submissions);

        List<Contestant> scoreboard = new ArrayList<Contestant>();

        // pokial odoslane riesenia niesu prazdne
        if (!submissions.isEmpty()) {

            Contestant contestant = null;

            // prejdeme odoslanymi rieseniami a priradime ich jednotlivym sutaziacim
            for (Submission submission: submissions) {

                if ((contestant == null) || (submission.contestant != contestant.number)) {
                    contestant = new Contestant();
                    contestant.number = submission.contestant;
                    scoreboard.add(contestant);
                }
                contestant.addSubmission(submission);
            }

            // zoradime vysledkovu listinu
            Collections.sort(scoreboard);
        }

        return scoreboard;
    }
}