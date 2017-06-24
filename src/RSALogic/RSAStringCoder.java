package RSALogic;

import PrimesFactory.PrimeFactory;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.Charset;

public class RSAStringCoder {

    private static final Charset UTF8_CHARSET = Charset.forName("UTF-8");

    /**
     * Encrypt message
     * Remeber, choose your key length properly (cant be too short)
     *
     * @param message message
     * @param key public user key (.RSALogic.PublicKey)
     * @return ecrypted message as string byte[]
     */
    private synchronized static byte[] encrypt(byte[] message, PublicKey key) {
        return (new BigInteger(message).modPow(key.getPublicKey(), key.getPrimesMul())).toByteArray();
    }

    /**
     * Decrypt encrypted message
     *
     * @param message string byte[] in UTF-8
     * @param key private user key (.RSALogic.PrivateKey)
     * @return decrypted UTF-8 string byte[]
     * @throws UnsupportedEncodingException
     */
    private synchronized static byte[] decrypt(byte[] message, PrivateKey key) throws UnsupportedEncodingException {
        BigInteger integer = (new BigInteger(message)).modPow(key.getPrivateKey(), key.getPrimesMul());
        return integer.toByteArray();
    }

    public static void main(String... args) throws UnsupportedEncodingException {
        KeyFactory keyFactory = new KeyFactory();

        KeyFactory.Key key = keyFactory.generateKeys(2048, System.currentTimeMillis());
        PublicKey publicKey = key.getPublicKey();
        PrivateKey privateKey = key.getPrivateKey();

        final String message = "Maciej co u Ciebie slychac, wiadomosc w formacie UTF-8";
        byte[] messageBytes = message.getBytes(UTF8_CHARSET);
        BigInteger messageInt = new BigInteger(messageBytes);

        byte[] encrypted = encrypt(messageBytes, publicKey);
        String encryptedMessage = new String(encrypted, UTF8_CHARSET);
        BigInteger enryptedInt = new BigInteger(encrypted);

        byte[] decrypted = decrypt(encrypted, privateKey);
        String decryptedMessage = new String(decrypted, UTF8_CHARSET);
        BigInteger decryptedInt = new BigInteger(decrypted);

        System.out.println(key);
        System.out.println("----------------");
        System.out.println("Message: " + message);
        System.out.println("Message: " + messageInt);
        System.out.println("----------------");
        System.out.println("Encrypted Message: " + encryptedMessage);
        System.out.println("Encrypted Message: " + enryptedInt);
        System.out.println("----------------");
        System.out.println("Decrypted Message: " + decryptedMessage);
        System.out.println("Decrypted Message: " + decryptedInt);
    }

}
