import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Base64;

import java.security.MessageDigest;

public class Storage {

    private static final String ENCRYPTION_ALGORITHM = "AES";
    private static final String PATH = "E:\\data.csv";
    private static final String KEY_STRING = System.getenv("KEY_STRING");

    public static ArrayList<SiteUserPassword> getData() throws Exception {
        ArrayList<SiteUserPassword> supList = new ArrayList<SiteUserPassword>();
        BufferedReader reader = new BufferedReader(new FileReader(PATH));
        String line;
        while ((line = reader.readLine()) != null) {
            supList.add(decrypt(line, getKey(KEY_STRING)));
        }
        reader.close();
        return supList;
    }

    public static void saveData(ArrayList<SiteUserPassword> supList) throws Exception {
        String res = "";
        for (SiteUserPassword sup : supList) {
            res += encrypt(sup, getKey(KEY_STRING)) + "\n";
        }
        if (res.length() > 0) {
            res = res.substring(0, res.length() - 1);
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(PATH));
        writer.write(res);
        writer.close();
    }

    public static boolean checkFile() {
        File f = new File(PATH);
        return f.exists() && !f.isDirectory();
    }

    private static String encrypt(SiteUserPassword sup, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance(ENCRYPTION_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedSite = cipher.doFinal(sup.getSite().getBytes());
        byte[] encryptedUser = cipher.doFinal(sup.getUser().getBytes());
        byte[] encryptedPass = cipher.doFinal(sup.getPassword().getBytes());
        return Base64.getEncoder().encodeToString(encryptedSite) + ","
        + Base64.getEncoder().encodeToString(encryptedUser) + ","
        + Base64.getEncoder().encodeToString(encryptedPass);
    }

    private static SiteUserPassword decrypt(String encryptedRow, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance(ENCRYPTION_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        String decryptedSite = new String(cipher.doFinal(Base64.getDecoder().decode(encryptedRow.split(",")[0])));
        String decryptedUser = new String(cipher.doFinal(Base64.getDecoder().decode(encryptedRow.split(",")[1])));
        String decryptedPass = new String(cipher.doFinal(Base64.getDecoder().decode(encryptedRow.split(",")[2])));
        return new SiteUserPassword(decryptedSite, decryptedUser, decryptedPass);
    }

    private static SecretKey getKey(String key) {
        byte[] decodedKey = Base64.getDecoder().decode(key);
        return new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
    }

    public static String calculateSHA256(String input) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashedBytes = md.digest(input.getBytes());

        StringBuilder hexStringBuilder = new StringBuilder();
        for (byte b : hashedBytes) {
            hexStringBuilder.append(String.format("%02x", b));
        }

        return hexStringBuilder.toString();
    }
}
