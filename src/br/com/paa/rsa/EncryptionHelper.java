package br.com.paa.rsa;

import java.math.BigInteger;

public class EncryptionHelper {
	
	private static final String FILE_DECRYPTED = "Decrypted.txt";
	private static final String FILE_ENCRYPTED = "Encrypted.txt";
	
	public void encryptMessage(BigInteger e, BigInteger n, String messageToEncrypt) {
		FileManager fileManager = new FileManager();
		fileManager.writeOnFile(FILE_DECRYPTED, messageToEncrypt);
		
		String encryptedMessage = new BigInteger(messageToEncrypt.getBytes()).modPow(e, n).toString();
        System.out.println("msg cifrada: "+ encryptedMessage);
        
        fileManager.writeOnFile(FILE_ENCRYPTED, encryptedMessage);
	}
	
	public void decryptFromFile(BigInteger d, BigInteger n) {
		FileManager fileManager = new FileManager();
		String encryptedMessage = fileManager.readFromFile(FILE_ENCRYPTED);
		
        //mensagem decifrada - RSA_decrypt()
        String decryptedMessage = new String(new BigInteger(encryptedMessage).modPow(d, n).toByteArray());
        System.out.println("msg decifrada: " + decryptedMessage);
	}
	
}
