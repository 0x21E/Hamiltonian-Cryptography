import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles the low-level byte manipulation for the transposition cipher.
 */
public class CryptoManager {

    /**
     * Converts a Lehmer code back into a permutation array (shuffle key).
     */
    public int[] lehmerToPermutation(List<Integer> lehmerCode) {
        int n = lehmerCode.size();
        int[] permutation = new int[n];
        List<Integer> available = new ArrayList<>();
        for (int i = 0; i < n; i++) available.add(i);

        for (int i = 0; i < n; i++) {
            int index = lehmerCode.get(i);
            permutation[i] = available.get(index);
            available.remove(index);
        }
        return permutation;
    }

    /**
     * Generates the reverse permutation required for decryption.
     */
    private int[] getInversePermutation(int[] perm) {
        int[] inverse = new int[perm.length];
        for (int i = 0; i < perm.length; i++) inverse[perm[i]] = i;
        return inverse;
    }

    /**
     * Processes file bytes by applying a block transposition cipher.
     */
    public boolean processFile(File input, File output, List<Integer> lehmer, boolean encrypt) {
        try {
            byte[] data = Files.readAllBytes(input.toPath());
            int[] perm = lehmerToPermutation(lehmer);
            if (!encrypt) perm = getInversePermutation(perm);

            int blockSize = perm.length;
            // Pad data with zeros to fit complete blocks
            int paddedLen = ((data.length + blockSize - 1) / blockSize) * blockSize;
            byte[] result = new byte[paddedLen];

            for (int blockStart = 0; blockStart < paddedLen; blockStart += blockSize) {
                for (int i = 0; i < blockSize; i++) {
                    int targetIdx = blockStart + perm[i];
                    int sourceIdx = blockStart + i;
                    result[targetIdx] = (sourceIdx < data.length) ? data[sourceIdx] : 0;
                }
            }
            Files.write(output.toPath(), result);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}