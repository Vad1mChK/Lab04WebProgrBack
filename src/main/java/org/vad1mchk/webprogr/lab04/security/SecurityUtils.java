package org.vad1mchk.webprogr.lab04.security;

/**
 * Utility class with common functions for security measures.
 */
public final class SecurityUtils {
    /**
     * Helper method to concatenate two byte arrays.
     *
     * @param left  The first byte array.
     * @param right The second byte array to be appended.
     * @return A concatenated byte array.
     */
    public static byte[] concatenateByteArrays(byte[] left, byte[] right) {
        int totalLength = left.length + right.length;
        byte[] concatenated = new byte[totalLength];
        System.arraycopy(left, 0, concatenated, 0, left.length);
        System.arraycopy(right, 0, concatenated, left.length, right.length);
        return concatenated;
    }
}