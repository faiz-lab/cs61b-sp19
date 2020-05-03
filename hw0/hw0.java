public class hw0 {
    // * Creative Exercise 1b: DrawTriangle
    public static void drawTriangle(int N) {
        int i = 0;
        while (i < N) {
            for (int j = 0; j < i; j++) {
                System.out.print("*");
            }
            System.out.println("*");
            i++;
        }
    }

    // * Exercise 2
    /** Returns the maximum value from m. */
    public static int max(int[] m) {
        int maxValue = m[0];
        for (int i = 0; i < m.length; i++) {
            if (m[i] > maxValue) {
                maxValue = m[i];
            }
        }
        System.out.println(maxValue);
        return 0;
    }

    // * Exercise 4
    public static void windowPosSum(int[] a, int n) {
        /** your code here */
        for (int i = 0; i < a.length; i++) {
            if (a[i] >= 0) {
                for (int j = i + 1; j <= i + n; j++) {
                    if (j == a.length) {
                        break;
                    }
                    a[i] += a[j];
                }
            }
            if (a[i] < 0) {
                continue;
            }
        }
    }

    // * Creative Exercise 1a: Drawing a Triangle
    public static void main(String[] args) {
        // drawTriangle(5);
        // int[] numbers = new int[] { 9, 2, 15, 2, 22, 10, 6 };
        // max(numbers);
        int[] a = { 1, 2, -3, 4, 5, 4 };
        int n = 3;
        windowPosSum(a, n);

        // Should print 4, 8, -3, 13, 9, 4
        System.out.println(java.util.Arrays.toString(a));
    }
}
