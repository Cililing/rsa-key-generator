package RSALogic;

import PrimesFactory.*;

import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class KeyFactory {

    private final BigInteger BIG_ZERO = new BigInteger("0");
    private final BigInteger BIG_ONE = new BigInteger("1");

    public Key generateKeys(int length, long seed) {
        BigPrimeForRSAFactory factory = new BigPrimeForRSAFactory(length, seed);

        BigInteger p1 = factory.next();
        BigInteger p2 = factory.next();

        Key key = this.new Key(p1, p2);

        return key;
    }

    private BigInteger LCM(BigInteger a, BigInteger b) {
        BigInteger gcf = GCD(a, b);
        return a.multiply(b).divide(gcf);
    }

    private BigInteger GCD(BigInteger a, BigInteger b) {
        if (b.intValue() == 0) return a;
        else return (GCD(b, a.mod(b)));
    }

    private BigInteger findRelativePrime(BigInteger a, int k) {
        // a - max prime
        PrimeFactory primeFactory = new SmallPrimeFactory(a);
        BigInteger result = primeFactory.next();

        for (int i = 0; i < k; i++) {
            if (!primeFactory.hasNext()) {
                return result;
            }
            result = primeFactory.next();
        }

        return result;
    }

    public class Key {

        private final int INDEX_OF_RELATIVE_PRIME = 6;
        private long time;

        private BigInteger prime1; // private
        private BigInteger prime2; // private

        private BigInteger mulPrimes; //prime1 * prime2
        private BigInteger totient; // totient(n) = lcm(prim1-1, prime2-1)

        private BigInteger publicKeyPart; // low number without publicKeyPart with mulPrimes
        private BigInteger privateKeyPart; //generated private key

        private PublicKey publicKey = new PublicKey();
        private PrivateKey privateKey = new PrivateKey();

        Key(BigInteger prime1, BigInteger prime2) {

            this.prime1 = prime1;
            this.prime2 = prime2;

            mulPrimes = prime1.multiply(prime2);
            totient = LCM(prime1.subtract(BIG_ONE), prime2.subtract(BIG_ONE));
            publicKeyPart = findRelativePrime(totient, INDEX_OF_RELATIVE_PRIME);

            privateKeyPart = new BigInteger("0");
            privateKeyPart = publicKeyPart.modInverse(totient);

            publicKey.setPrimesMul(mulPrimes);
            publicKey.setPublicKey(publicKeyPart);

            privateKey.setPrimesMul(mulPrimes);
            privateKey.setPrivateKey(privateKeyPart);

            time = System.currentTimeMillis();
        }

        public PublicKey getPublicKey() {
            return publicKey;
        }

        public PrivateKey getPrivateKey() {
            return privateKey;
        }

        @Override
        public String toString() {
            String s = "";
            s = s + "Key details:" +
                    "\nPrime1: " + prime1 +
                    "\nPrime2: " + prime2 +
                    "\nPrimesMul: " + mulPrimes +
                    "\nTotient: " + totient +
                    "\nPublic Key: " + "[" + publicKeyPart + ", " + mulPrimes + "]" +
                    "\nPrivate Key: " + "[" + privateKeyPart + ", " + mulPrimes + "]" +
                    "\nTime: " + time;

            return s;
        }


    }

    public static void main(String... args) {
        KeyFactory factory = new KeyFactory();
        Key key = factory.generateKeys(128, System.currentTimeMillis());
        System.out.println(key);
    }
}
