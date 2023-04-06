package services;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * AuthService is a singleton class responsible for managing authentication
 * services. It provides methods for accessing and managing the public and
 * private keys used in the authentication process.
 */
public class AuthService {

    /**
     * The single instance of the AuthService class.
     */
    private static AuthService instance;

    /**
     * The key pair used for encryption and decryption.
     */
    private KeyPair keyPair;

    /**
     * Initializes a new instance of the AuthService class and generates an RSA
     * key pair.
     */
    private AuthService() {
        try {
            KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
            kpg.initialize(2048);
            keyPair = kpg.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Unable to initialize AuthService", e);
        }
    }

    /**
     * Returns the singleton instance of the AuthService class, creating a new
     * instance if necessary.
     *
     * @return The singleton instance of the AuthService class.
     */
    public static synchronized AuthService getInstance() {
        if (instance == null) {
            instance = new AuthService();
        }
        return instance;
    }

    /**
     * Gets the private key used for encryption and decryption.
     *
     * @return The private key associated with the key pair.
     */
    public PrivateKey getPrivateKey() {
        return keyPair.getPrivate();
    }

    /**
     * Gets the public key used for encryption and decryption.
     *
     * @return The public key associated with the key pair.
     */
    public PublicKey getPublicKey() {
        return keyPair.getPublic();
    }
}
