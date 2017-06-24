package PrimesFactory;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.Stack;

public abstract class PrimeFactory implements Iterator<BigInteger> {

    private static final double LOG2 = Math.log(2.0);

    protected Stack<BigInteger> previousPrimes;
    protected boolean hasNext;
    protected BigInteger current;
    protected BigInteger next;

    public PrimeFactory() {
        previousPrimes = new Stack<>();
    }

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public abstract BigInteger next();


    public abstract BigInteger previous();
    public abstract BigInteger current();
    public abstract void reset();


    public boolean hasPrevious() {
        return previousPrimes.size() > 0;
    }


    public static double logBigInteger(BigInteger val) {
        int blex = val.bitLength() - 1022; // any value in 60..1023 is ok
        if (blex > 0)
            val = val.shiftRight(blex);
        double res = Math.log(val.doubleValue());
        return blex > 0 ? res + blex * LOG2 : res;
    }
}
