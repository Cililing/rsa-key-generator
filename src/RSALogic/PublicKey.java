package RSALogic;

import java.io.Serializable;
import java.math.BigInteger;

public class PublicKey extends AbstractKey implements Serializable {

    BigInteger primesMul;
    BigInteger publicKey;

    public PublicKey(){

    }

    public PublicKey(BigInteger primesMul, BigInteger publicKey) {
        this.primesMul = primesMul;
        this.publicKey = publicKey;
    }

    public BigInteger getPrimesMul() {
        return primesMul;
    }

    public void setPrimesMul(BigInteger primesMul) {
        this.primesMul = primesMul;
    }

    public BigInteger getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(BigInteger publicKey) {
        this.publicKey = publicKey;
    }

}
