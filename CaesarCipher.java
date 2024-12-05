import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

public class CaesarCipher {
    public static String encrypt(String text, int shift) {
        if (shift < 0 || shift > 25) {
            throw new IllegalArgumentException("Shift must be between 0 and 25");
        }
        
        StringBuilder result = new StringBuilder();
        
        for (char character : text.toCharArray()) {
            if (Character.isLetter(character)) {
                char base = Character.isUpperCase(character) ? 'A' : 'a';
                result.append((char) (((character - base + shift) % 26) + base));
            } else {
                result.append(character);
            }
        }
        
        return result.toString();
    }
    
    public static String decrypt(String text, int shift) {
        return encrypt(text, 26 - shift);
    }
    
    public static void encryptFile(String inputFile, String outputFile, int shift) {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             FileWriter writer = new FileWriter(outputFile)) {
            
            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(encrypt(line, shift) + "\n");
            }
            
            System.out.println("File encrypted successfully.");
        } catch (IOException e) {
            System.err.println("Error encrypting file: " + e.getMessage());
        }
    }
    
    public static void decryptFile(String inputFile, String outputFile, int shift) {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             FileWriter writer = new FileWriter(outputFile)) {
            
            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(decrypt(line, shift) + "\n");
            }
            
            System.out.println("File decrypted successfully.");
        } catch (IOException e) {
            System.err.println("Error decrypting file: " + e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Caesar Cipher Encryption/Decryption Tool");
        System.out.println("1. Encrypt/Decrypt Text");
        System.out.println("2. Encrypt/Decrypt File");
        System.out.print("Choose an option (1/2): ");
        
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        try {
            if (choice == 1) {
                System.out.print("Enter text: ");
                String text = scanner.nextLine();
                
                System.out.print("Enter shift value (0-25): ");
                int shift = scanner.nextInt();
                
                String encrypted = encrypt(text, shift);
                String decrypted = decrypt(encrypted, shift);
                
                System.out.println("Original Text: " + text);
                System.out.println("Encrypted Text: " + encrypted);
                System.out.println("Decrypted Text: " + decrypted);
            } else if (choice == 2) {
                System.out.print("Enter input file path: ");
                String inputFile = scanner.nextLine();
                
                System.out.print("Enter output file path: ");
                String outputFile = scanner.nextLine();
                
                System.out.print("Enter shift value (0-25): ");
                int shift = scanner.nextInt();
                
                System.out.print("Encrypt or Decrypt? (E/D): ");
                String mode = scanner.next().toUpperCase();
                
                if (mode.equals("E")) {
                    encryptFile(inputFile, outputFile, shift);
                } else if (mode.equals("D")) {
                    decryptFile(inputFile, outputFile, shift);
                } else {
                    System.out.println("Invalid mode. Please choose E or D.");
                }
            } else {
                System.out.println("Invalid option.");
            }
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}