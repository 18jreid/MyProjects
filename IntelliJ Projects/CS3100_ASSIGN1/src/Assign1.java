// JUSTIN SAMUEL REID, A02276642 CS3100

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class Assign1 {
    public static void main(String[] args) {
        if (args.length == 0 || args.length % 2 != 0) {
            printProgramFunctions();
        }

        else {
            for (int i = 0; i < args.length; i++) {
                if (args[i].equals("-fib")) {
                    System.out.println("Fibonacci of " + args[i + 1] + " is " + fib(Integer.parseInt(args[i + 1]) + 1));
                }

                else if (args[i].equals("-fac")) {
                    System.out.println("Factorial of " + args[i + 1] + " is " + fac(args[i + 1]));
                }

                else if (args[i].equals("-e")) {
                    System.out.println("Value of e using " + args[i + 1] + " is " + exponential(args[i + 1]));
                }

                else if (args[i].startsWith("-")) {
                    System.out.println("Unknown command line argument: " + args[i] + "\n");
                }
            }
        }
    }

    /**
     * Calculates the Fibonacci solution of the nth term
     * @param n the nth term
     * @return the Fibonacci solution
     */
    static int fib(int n) {
        if (n < 0 || n > 41) {
            System.out.print("\nERROR(-1), Invalid Range ----- ");
            return -1;
        }

        if (n <= 1) {
            return n;
        }

        return fib(n-1) + fib(n-2);
    }

    /**
     * Calculates the factorial of the given integer n
     * @param n the BigInteger to be calculated
     * @return the factorial solution
     */
    static BigInteger fac(String n) {
        BigInteger N = new BigInteger(String.valueOf(n));
        if (N.compareTo(BigInteger.ZERO) < 0 || N.compareTo(new BigInteger("2147483647")) > 0) {
            System.out.print("\nERROR(-1), Invalid Range ----- ");
            return (new BigInteger("-1"));
        }

        BigInteger num = BigInteger.ONE;
        for (int i = 2; i <= N.intValue(); i++) {
            num = num.multiply(BigInteger.valueOf(i));
        }

        return num;
    }

    /**
     * Calculates the value of e using a Taylor series
     * @param n number of iterations
     * @return the approximation of e
     */
    static BigDecimal exponential(String n)
    {
        BigInteger N = new BigInteger(String.valueOf(n));
        if (N.compareTo(BigInteger.ZERO) < 1 || N.compareTo(new BigInteger("2147483647")) > 0) {
            System.out.print("\nERROR(-1), Invalid Range ----- ");
            return (new BigDecimal("-1"));
        }

        BigDecimal x = BigDecimal.ONE;
        BigDecimal sum = BigDecimal.ONE;

        for (int i = Integer.parseInt(n) - 1; i > 0; --i) {
            BigDecimal numerator = x.multiply(sum);
            sum = numerator.divide(BigDecimal.valueOf(i),16, RoundingMode.DOWN);
            sum = sum.add(BigDecimal.ONE);
        }

        return sum;
    }
    
    static void printProgramFunctions() {
        System.out.println("--- Assign 1 Help ---\n" +
                "  -fib [n] : Compute the Fibonacci of [n]; valid range [0, 40]\n" +
                "  -fac [n] : Compute the factorial of [n]; valid range, [0, 2147483647]\n" +
                "  -e [n] : Compute the value of 'e' using [n] iterations; valid range [1, 2147483647]");
    }
}
