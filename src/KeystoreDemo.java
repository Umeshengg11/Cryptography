import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

public class KeystoreDemo {

    public static <Keystore> void makeKeyStore() throws KeyStoreException, IOException, CertificateException, NoSuchAlgorithmException {
        KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
        char[] password = "umeshengg11".toCharArray();
        InputStream keystoreFile = new FileInputStream("keystore.ks");
        keystore.load(keystoreFile,password);

    }



}
