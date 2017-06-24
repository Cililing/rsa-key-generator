package RSALogic;

import java.io.File;
import java.io.Serializable;
import java.math.BigInteger;

public class PrivateKey extends AbstractKey implements Serializable {

    BigInteger primesMul;
    BigInteger privateKey;

    public PrivateKey() {

    }

    public PrivateKey(BigInteger primesMul, BigInteger privateKey) {
        this.primesMul = primesMul;
        this.privateKey = privateKey;
    }

    public BigInteger getPrimesMul() {
        return primesMul;
    }

    public void setPrimesMul(BigInteger primesMul) {
        this.primesMul = primesMul;
    }

    public BigInteger getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(BigInteger privateKey) {
        this.privateKey = privateKey;
    }
}
