import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import java.io.*;
import java.security.*;
import java.security.cert.CertificateException;

public class SecretKeyGeneration {
    public static void main(String[] args) throws NoSuchAlgorithmException, UnrecoverableKeyException, CertificateException, KeyStoreException, IOException {
        SecretKey key = keyGenerator();
        for (byte b : key.getEncoded()) {
        String st = String.format("%02X",b);
            System.out.print(st);
        }
        generateKeystore(key);
        System.out.println();
        getKeyFromKeystore();
    }

    public static SecretKey keyGenerator() throws NoSuchAlgorithmException {
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[20];
        random.nextBytes(bytes);
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(256, random);
        SecretKey key = keyGenerator.generateKey();
        return key;


    }
    public static void generateKeystore(SecretKey key) throws KeyStoreException, IOException, CertificateException, NoSuchAlgorithmException, UnrecoverableKeyException {
        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        char[] password = "umeshengg11".toCharArray();
        keyStore.load(null);
        KeyStore.SecretKeyEntry secretKeyEntry = new KeyStore.SecretKeyEntry(key);
        KeyStore.ProtectionParameter protectionParameter = new KeyStore.PasswordProtection(password);
        keyStore.setEntry("TestEntry",secretKeyEntry,protectionParameter);
        FileOutputStream keystoreFile = new FileOutputStream("keystore.ks");
        keyStore.store(keystoreFile,password);
    }
    public static void getKeyFromKeystore() throws KeyStoreException, IOException, CertificateException, NoSuchAlgorithmException, UnrecoverableKeyException {
        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        char[] password = "umeshengg11".toCharArray();
        FileInputStream file = new FileInputStream("keystore.ks");
        keyStore.load(file,password);
        SecretKey key = (SecretKey) keyStore.getKey("TestEntry",password);
        for (byte b : key.getEncoded()) {
            String st = String.format("%02X", b);
            System.out.print(st);
        }
    }
}
