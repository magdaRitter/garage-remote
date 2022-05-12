package at.home.garageremote.cipher;

import at.home.garageremote.exception.MissingKeyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;
import java.security.interfaces.RSAPublicKey;

import static javax.xml.bind.DatatypeConverter.parseBase64Binary;

@Slf4j
@Component
public class Encryptor {

    public String decrypt(String cipherText) throws Exception {
        String decryptionKey = "keys/decryption-private-key.pem";
        File privateKeyFile = getFileFromResource(decryptionKey);
        PrivateKey privateKey = KeyReader.readPrivateKey(privateKeyFile);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] bytes = parseBase64Binary(cipherText);
        byte[] decryptedData = cipher.doFinal(bytes);
        return new String(decryptedData, StandardCharsets.UTF_8);
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

    private File getFileFromResource(String fileName) {
        File file = new File(fileName);
//        File file = new File(this.getClass().getClassLoader().getResource(fileName).getFile());
        if (!file.exists()) {
            throw new MissingKeyException("Could not find key");
        }

        return file;
    }
}

