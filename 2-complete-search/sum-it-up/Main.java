import java.util.*;

public class Main {

    static int seen[] = new int[13];
    static int vals[] = new int[13];
    static boolean found = false;

    public static void main (String [] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int t = sc.nextInt();
            int n = sc.nextInt();
            if (t == 0 && n == 0)
                break;

            vals = new int[n];
            for (int i = 0; i < n; i++)
                vals[i] = sc.nextInt();

            System.out.format("Sums of %d:\n", t);
            solve2(t);
        }

        sc.close();
    }

    public static void solve2(int total) {
        found = false;
        solveRec(total, 0, 0);
        if (!found)
            System.out.println("NONE");
    }

    public static void solveRec(int total, int size, int start) {
        int sum = 0;
        for (int i = 0; i < vals.length; i++) {
            if (seen[i] == 1) sum += vals[i];
        }

        if (sum == total) {
            found = true;
            boolean first = true;
            for (int i = 0; i < vals.length; i++) {
                if (seen[i] == 1) {
                    if (first)
                        System.out.print(vals[i]);
                    else
                        System.out.print("+" + vals[i]);
                    first = false;
                }
            }
            System.out.println();
        }

        if (size == vals.length)
            return;

        for (int i = start; i < vals.length; i++) {
            seen[i] = 1;
            solveRec(total, size + 1, i + 1);
            int prev = vals[i];
            seen[i] = 0;
            while (i + 1 < vals.length && vals[i + 1] == prev) i++;
        }
    }

    public static void solve(int [] vals, int total) {
        int subset = 0;
        boolean found = false;

        for (int i = 0; i <= 1 << vals.length; i++) {
            subset++;
            int sum = 0;
            List<Integer> list = new ArrayList<Integer>();
            for (int j = 0; j < vals.length; j++) {
                int mask = 1 << j;
                if ((subset & mask) == mask) {
                    sum += vals[j];
                    list.add(vals[j]);
                }
            }
            if (sum == total) {
                System.out.println(list);
                found = true;
            }
        }

        if (!found)
            System.out.println("NONE");
    }

}