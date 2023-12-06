package org.vad1mchk.webprogr.lab04.security;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import static org.vad1mchk.webprogr.lab04.security.SecurityUtils.concatenateByteArrays;

/**
 * Implementation of the {@link Hasher} interface using the SHA-512 hashing algorithm.
 * Allows for the use of both salt and pepper for enhanced security.
 */
public class Sha512Hasher implements Hasher {
    public static final int HASHED_LENGTH_BYTES = 64;

    private final MessageDigest digest;
    private final byte[] pepper;

    /**
     * Default constructor initializing the hasher without a pepper value.
     *
     * @throws NoSuchAlgorithmException If the SHA-512 algorithm is not available.
     */
    public Sha512Hasher() throws NoSuchAlgorithmException {
        this.digest = MessageDigest.getInstance("SHA-512");
        this.pepper = new byte[0];
    }

    /**
     * Constructor initializing the hasher with a specified pepper value.
     *
     * @param pepper The pepper byte array to use in the hashing process.
     * @throws NoSuchAlgorithmException If the SHA-512 algorithm is not available.
     */
    public Sha512Hasher(byte[] pepper) throws NoSuchAlgorithmException {
        this.digest = MessageDigest.getInstance("SHA-512");
        this.pepper = pepper != null ? pepper : new byte[0];
    }

    @Override
    public byte[] hash(String string) {
        byte[] stringAsBytes = string.getBytes(StandardCharsets.UTF_8);
        return digest.digest(
                concatenateByteArrays(
                        digest.digest(stringAsBytes), pepper
                )
        );
    }

    public byte[] hash(String string, byte[] salt) {
        byte[] stringAsBytes = string.getBytes(StandardCharsets.UTF_8);
        return digest.digest(
                concatenateByteArrays(
                        digest.digest(stringAsBytes), concatenateByteArrays(salt, pepper)
                )
        );
    }

    public byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[HASHED_LENGTH_BYTES];
        random.nextBytes(salt);
        return salt;
    }

    public boolean verifyPassword(String password, byte[] hashedPassword, byte[] salt) {
        return MessageDigest.isEqual(
                hashedPassword,
                hash(password, salt)
        );
    }
}