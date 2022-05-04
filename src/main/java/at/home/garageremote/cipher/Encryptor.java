package at.home.garageremote.cipher;

import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Component
public class Encryptor {

    public String decrypt(byte[] encryptedMessageBytes) throws Exception {
        String decryptionKey = "keys/decryption-private-key.pem";
        File privateKeyFile = getFileFromResource(decryptionKey);
        RSAPrivateKey privateKey = KeyReader.readPrivateKey(privateKeyFile);

        Cipher decryptCipher = Cipher.getInstance("RSA");
        decryptCipher.init(Cipher.DECRYPT_MODE, privateKey);

        byte[] decryptedMessageBytes = decryptCipher.doFinal(encryptedMessageBytes);

        return new String(decryptedMessageBytes, StandardCharsets.UTF_8);
    }

    public byte[] encrypt(String message) throws Exception {
        String encryptionKey = "keys/encryption-public-key.pem";
        File publicKeyFile = getFileFromResource(encryptionKey);
        RSAPublicKey publicKey = KeyReader.readPublicKey(publicKeyFile);

        Cipher encryptCipher = Cipher.getInstance("RSA");
        encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);

        byte[] encryptedMessageBytes = message.getBytes(StandardCharsets.UTF_8);

        return encryptCipher.doFinal(encryptedMessageBytes);
    }

    private File getFileFromResource(String fileName) throws IOException {

        File file = new File(fileName);
        if(!file.exists()){
            throw new IOException("File not found! " + fileName);
        }

        return file;
    }
}

