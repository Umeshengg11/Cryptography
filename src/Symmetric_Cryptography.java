import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Symmetric_Cryptography {

    /*First process is to generate a secret Key
     * For that two classes are used SecureRandom and KeyGenerator
     * SecureRandom used to generate a secureRandom Object
     *KeyGenerator is used to generate the secretKey using secureRandom Object
     */
    public static SecretKey generateKey() throws NoSuchAlgorithmException {
        SecureRandom random = new SecureRandom();
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128, random);
        SecretKey key = keyGenerator.generateKey();
        return key;
    }

    /*Used for generating initialisation Vector*/
    public static byte[] initialisation_vector() {
        byte[] bytes = new byte[16];
        SecureRandom random = new SecureRandom();
        random.nextBytes(bytes);
        return bytes;
    }

    /*Used for encryption */
    public static byte[] encryptionProcess(String plainText, SecretKey key, byte[] initialisationVector) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(initialisationVector);
        cipher.init(Cipher.ENCRYPT_MODE, key, ivParameterSpec);
        byte[] cipherText = cipher.doFinal(plainText.getBytes());
        return cipherText;
    }

    /*Used for decryption*/
    public static String decryptionProcess(byte[] cipherText, SecretKey key, byte[] initialisationVector) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(initialisationVector);
        cipher.init(Cipher.DECRYPT_MODE, key, ivParameterSpec);
        byte[] plainText = cipher.doFinal(cipherText);
        return new String(plainText);
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, InvalidAlgorithmParameterException, NoSuchPaddingException {
        SecretKey key = generateKey();
        byte[] initialization = initialisation_vector();
        String plainText = "My daughters name is Anaya Nair. She is two years old";
        byte[] cipherText = encryptionProcess(plainText, key, initialization);
        for (byte b : cipherText) {
            String st = String.format("%02X", b);
            System.out.print(st);
        }
        System.out.println();
        String recoveredText = decryptionProcess(cipherText, key, initialization);
        System.out.println(recoveredText);
    }
}
