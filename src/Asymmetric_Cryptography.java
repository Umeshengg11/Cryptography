import javax.crypto.*;
import java.security.*;

public class Asymmetric_Cryptography {
    public static KeyPair generateKey() throws NoSuchAlgorithmException {
        SecureRandom random = new SecureRandom();
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(1024, random);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        return keyPair;
    }

    public static byte[] encryption(String plainText, PublicKey publicKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] b = cipher.doFinal(plainText.getBytes());
        return b;
    }

    public static String decryption(byte[] cipherText, PrivateKey privateKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] b = cipher.doFinal(cipherText);
        String plainText = new String(b);
        return plainText;
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchPaddingException {
        KeyPair keyPair = generateKey();
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();
        String plainText = "Hi guys my name is Umesh Nair";
        byte[] b = encryption(plainText, publicKey);
        for (byte bytes : b) {
            String encrypt = String.format("%02x", bytes);
            System.out.print(encrypt);
        }
        System.out.println();
        String s = decryption(b, privateKey);
        System.out.print(s);


//       for (byte  bytes: publicKey.getEncoded()){
//           String pub = String.format("%02x",bytes);
//           System.out.print(pub);
//        }
//        System.out.println();
//        for (byte  bytes: privateKey.getEncoded()){
//            String pri = String.format("%02x",bytes);
//            System.out.print(pri);
//        }
    }
}
