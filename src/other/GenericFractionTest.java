package other;


import java.math.BigInteger;
import java.util.Scanner;

public class GenericFractionTest {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double n1 = scanner.nextDouble();
        double d1 = scanner.nextDouble();
        float n2 = scanner.nextFloat();
        float d2 = scanner.nextFloat();
        int n3 = scanner.nextInt();
        int d3 = scanner.nextInt();
        try {
            GenericFraction<Double, Double> gfDouble = new GenericFraction<Double, Double>(n1, d1);
            GenericFraction<Float, Float> gfFloat = new GenericFraction<Float, Float>(n2, d2);
            GenericFraction<Integer, Integer> gfInt = new GenericFraction<Integer, Integer>(n3, d3);
            System.out.printf("%.2f\n", gfDouble.toDouble());
            System.out.println(gfDouble.add(gfFloat));
            System.out.println(gfInt.add(gfFloat));
            System.out.println(gfDouble.add(gfInt));
            gfInt = new GenericFraction<Integer, Integer>(n3, 0);
        } catch (ZeroDenominatorException e) {
            System.out.println(e.getMessage());
        }

        scanner.close();
    }

    public static class GenericFraction<T extends Number, U extends Number> {

        T numerator;
        U denominator;

        public GenericFraction(T numerator, U denominator) throws ZeroDenominatorException {
            this.numerator = numerator;
            if (denominator.doubleValue() == 0.0) {
                throw new ZeroDenominatorException();
            } else {
                this.denominator = denominator;
            }
        }

        public GenericFraction<Double, Double> add(GenericFraction<? extends Number, ? extends Number> gf) throws ZeroDenominatorException {

            double d = denominator.doubleValue() * gf.denominator.doubleValue();
            double n = numerator.doubleValue() * (d / denominator.doubleValue())
                    + gf.numerator.doubleValue() * (d / gf.denominator.doubleValue());

            return new GenericFraction<Double, Double>(n, d);
        }

        public String toString() {

            BigInteger a = BigInteger.valueOf((int) numerator.doubleValue());
            BigInteger b = BigInteger.valueOf((int) denominator.doubleValue());

            double gcd = a.gcd(b).intValue();
            return String.format("%.2f / %.2f", numerator.doubleValue() / gcd, denominator.doubleValue() / gcd);
        }

        public double toDouble() {
            return numerator.doubleValue() / denominator.doubleValue();
        }
        
        
    }

    private static class ZeroDenominatorException extends Exception {

        public ZeroDenominatorException() {
            super("Denominator cannot be zero");
        }
    }

}

// вашиот код овде
