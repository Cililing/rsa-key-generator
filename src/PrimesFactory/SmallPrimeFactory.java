package PrimesFactory;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Stack;

public class SmallPrimeFactory extends PrimeFactory {

    private static final BigInteger minimumMax = new BigInteger("2");

    private BigInteger max;

    public SmallPrimeFactory(BigInteger max) {
        super();
        this.max = max;
        if (max.compareTo(minimumMax) <= 0) {
            this.max = minimumMax;
        }
        hasNext = true;
        current = new BigInteger("1");
        next = new BigInteger("2");
    }

    @Override
    public boolean hasNext() {
        return hasNext;
    }

    @Override
    public BigInteger next() {

        if (current.compareTo(max) > 0) {
            throw new PrimeFactoryException("No next prime! (current > max)");
        }

        previousPrimes.push(current);
        current = next;
        next = current.nextProbablePrime();

        if (next.compareTo(max) > 0) {
            hasNext = false;
        }

        return current;
    }

    @Override
    public BigInteger previous() {
        if (previousPrimes.size() == 0) {
            throw new PrimeFactoryException("There is no previous primes!");
        }
        next = current;
        current = previousPrimes.pop();
        return current;
    }

    @Override
    public BigInteger current() {
        return current;
    }

    @Override
    public void reset() {
        previousPrimes.clear();
        current = new BigInteger("1");
    }

    public int approxNumberOfPrimes() {
        double log = PrimeFactory.logBigInteger(max);
        BigDecimal bigLog = new BigDecimal(log);
        BigDecimal bigNumber = new BigDecimal(max);
        return  (bigNumber.divideToIntegralValue(bigLog)).intValue();
    }

    public static void main(String... args) {
        SmallPrimeFactory smallPrimeFactory = new SmallPrimeFactory(new BigInteger("20"));
        while (smallPrimeFactory.hasNext()) {
            System.out.println(smallPrimeFactory.next());
        }
        System.out.println(smallPrimeFactory.approxNumberOfPrimes());
        System.out.println(smallPrimeFactory.current());
//        while (smallPrimeFactory.hasPrevious()) {
//            System.out.println(smallPrimeFactory.previous());
//        }
    }
}
