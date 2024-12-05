import java.util.Scanner;

public class CaesarCipher {
    public static String encrypt(String text, int shift) {
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
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Caesar Cipher Encryption/Decryption");
        System.out.print("Enter text: ");
        String text = scanner.nextLine();
        
        System.out.print("Enter shift value: ");
        int shift = scanner.nextInt();
        
        String encrypted = encrypt(text, shift);
        String decrypted = decrypt(encrypted, shift);
        
        System.out.println("Original Text: " + text);
        System.out.println("Encrypted Text: " + encrypted);
        System.out.println("Decrypted Text: " + decrypted);
        
        scanner.close();
    }
}