import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static String term = "abc";
    static String nonTerm = "SBC";

    static int m = term.length();
    static int n = nonTerm.length();

    static String[] termFrom = new String[m];
    static String[][] pairFrom = new String[n][n];

    public static void main(String[] args) {

        for (int j = 0; j < m; j++) {
            termFrom[j] = "";
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                pairFrom[i][j] = "";
            }
        }
        addTerm("S", "a");
        addTerm("B", "b");
        addTerm("C", "c");
        printArr(termFrom);

        addPair("S", "SB");
        addPair("B", "BC");
        addPair("C", "CC");
        printArr(pairFrom[0]);
        printArr(pairFrom[1]);
        printArr(pairFrom[2]);

        Scanner sc = new Scanner(System.in);
        System.out.println(language(sc.next()));
    }

    public static void addTerm(String nc, String tc) {
        try {
            termFrom[term.indexOf(tc)] += nc;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("no such element - " + tc);
        }
    }

    public static void addPair(String nc, String pnc) {
        try {
            pairFrom[nonTerm.indexOf(pnc.substring(0, 1))][nonTerm.indexOf(pnc.substring(1))] += nc;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("no such element - " + pnc);
        }
    }

    public static void printArr(String[] arr) {
        for (String s : arr) {
            if (s == "") {
                System.out.print("\t");
            } else {
                System.out.print(s + "\t");
            }
        }
        System.out.println();
    }

    public static boolean language(String r) {
        String[][] res = new String[r.length()][r.length()];
        for (int i = 0; i < r.length(); i++) {
            for (int j = 0; j < r.length(); j++) {
                res[i][j] = "";
            }
        }
        int i = 0, j = 1;
        while (i < r.length()) {
            res[i][i] = termFrom[term.indexOf(r.charAt(i))];
            i++;
        }
        int tr = 1;
        j = 0;
        for (i = 1; i < r.length(); i++) {
            int jj = j;
            for (int l = tr; l > 0; l--) {
                try {
                    //System.out.println("try: " + res[i - l][j] + "(" + (i - l) + ";" + j + ") " + res[i][jj + 1] + "(" + (i) + "; " + (jj + 1));
                    String temp = pairFrom[nonTerm.indexOf(res[i - l][j].charAt(0))][nonTerm.indexOf(res[i][++jj].charAt(0))];
                    if (res[i][j].indexOf(temp) == -1)
                        res[i][j] += temp;
                } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {
                }

                //System.out.println("\t\tout" + res[i][j] + "(" + i + ";" + j + ")");

            }
            j++;

            if (i == r.length() - 1 && tr < r.length()) {
                i = tr++;
                j = 0;
            }
        }

        for (int p = 0; p < r.length(); p++)
            printArr(res[p]);

        return res[r.length() - 1][0].equals(termFrom[0]);
    }
}
