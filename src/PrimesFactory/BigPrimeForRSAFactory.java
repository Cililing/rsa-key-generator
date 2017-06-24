package PrimesFactory;

import java.math.BigInteger;
import java.util.Random;


/**
 * Can be a lot of bugs!!! Use with caution
 */
public class BigPrimeForRSAFactory extends PrimeFactory {

    private static final int DEFAULT_LENGTH = 64;

    private int length;
    long seed;
    private Random random;
    private BigInteger startBig;

    public BigPrimeForRSAFactory(int length, long seed) {
        super();
        this.length = length;
        this.seed = seed;
        random = new Random(seed);

        if (length <= 0) {
            this.length = DEFAULT_LENGTH;
        }


        hasNext = true;

        current = BigInteger.probablePrime(length, random);
        startBig = current;
        next = current.nextProbablePrime();
    }

    @Override
    public BigInteger next() {

        if (next.bitLength() > length) {
            hasNext = false;
        }

        previousPrimes.push(current);
        current = next;
        next = current.nextProbablePrime();

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
        current = startBig;
    }

    public static void main(String... args) {
        BigPrimeForRSAFactory rsa = new BigPrimeForRSAFactory(128, System.currentTimeMillis());

        while (rsa.hasNext()) {
            System.out.println(rsa.next());
        }
    }
}
