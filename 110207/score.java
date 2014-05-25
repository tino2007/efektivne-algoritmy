import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
   Contest Scoreboard


 */

class Main {

    static class Submission implements Comparable < Submission > {

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
            return verdict.equals("C");
        }

        public boolean isIncorrect() {
            return verdict.equals("I");
        }

        @Override
        public String toString() {
            return contestant + " " + problem + " " + time + " " + verdict;
        }

        @Override
        public int compareTo(Submission o) {
            if (this.contestant > o.contestant) return 1;
            else if (this.contestant < o.contestant) return -1;
            else if (this.problem > o.problem) return 1;
            else if (this.problem < o.problem) return -1;
            else if (this.time > o.time) return 1;
            else if (this.time < o.time) return -1;
            else return 0;
        }
    }

    static class Contestant implements Comparable < Contestant > {

        int number;

        int penaltyTime;

        int[] problems = new int[9];

        int solved = 0;

        int currentPenalty = 0;

        int previousProblem = -1;

        void addSubmission(Submission submission) {

            // reset current penalty if it is a new problem (all problems come in order for this contestant)
            if (previousProblem != -1 && submission.problem != previousProblem) {
                currentPenalty = 0;
            }

            if (submission.isCorrect()) {

                int index = submission.problem - 1;

                if (problems[index] == 0) {
                    problems[index] = 1;
                    penaltyTime += currentPenalty + submission.time;
                    solved++;
                }

            } else if (submission.isIncorrect()) {

                currentPenalty += 20;
            }

            previousProblem = submission.problem;
        }

        @Override
        public String toString() {
            return number + " " + solved + " " + penaltyTime;
        }

        @Override
        public int compareTo(Contestant o) {
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

            for (int i = 0; i < scoreboard.size(); i++) {

                builder.append(scoreboard.get(i).toString());

                if (i < scoreboard.size() - 1) {
                    builder.append("\n");
                }
            }

            if (testCase < numTestCases) {
                builder.append("\n");
            }

            System.out.println(builder.toString());
        }
    }

    static List <Contestant> getScoreboard(List<Submission> submissions) {

        Collections.sort(submissions);
        List<Contestant> scoreboard = new ArrayList<Contestant>();

        if (!submissions.isEmpty()) {

            Contestant contestant = null;

            for (Submission submission: submissions) {

                if ((contestant == null) || (submission.contestant != contestant.number)) {

                    contestant = new Contestant();
                    contestant.number = submission.contestant;
                    scoreboard.add(contestant);
                }
                contestant.addSubmission(submission);
            }

            Collections.sort(scoreboard);
        }

        return scoreboard;
    }
}