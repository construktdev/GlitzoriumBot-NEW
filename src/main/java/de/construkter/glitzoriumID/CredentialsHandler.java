package de.construkter.glitzoriumID;

import java.io.*;

public class CredentialsHandler {
    private static final String FILE_NAME = "glitzoriumid.txt";

    public static boolean save(String email, String password, String mcname) {
        if (checkDuplicats(email, mcname)) return false;
        String formattedString = email + "_" + password + "_" + mcname + "\n";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(formattedString);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static String[] load(String email) {
        String[] result = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("_");
                if (parts[0].equals(email)) {
                    result = parts;
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static boolean checkDuplicats(String email, String mcName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("_");
                if (parts[0].equals(email) || parts[2].equals(mcName)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
