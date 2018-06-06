package br.com.paa.rsa;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;

public class EncryptionHelper {
	
	private static final String FILE_DECRYPTED = "Decrypted.txt";
	private static final String FILE_ENCRYPTED = "Encrypted.txt";
	private static final int BLOCK_SIZE = 16;
	
	public void encryptMessage(BigInteger e, BigInteger n) {
		
		FileManager fileManager = new FileManager();
		
		String messagesToEncrypt = fileManager.readFromFile(FILE_DECRYPTED);
		String encryptedMessage  = encryptMessageByBlocks(messagesToEncrypt, e, n);
		
        System.out.println("msg cifrada: "+ encryptedMessage);
        fileManager.writeOnFile(FILE_ENCRYPTED, encryptedMessage);
        
	}
	
	public void decryptFromFile(BigInteger d, BigInteger n) {
		FileManager fileManager = new FileManager();
		String encryptedMessage = fileManager.readFromFile(FILE_ENCRYPTED);
		
        String decryptedMessage = decryptMessageByBlocks(encryptedMessage, d, n);
        System.out.println("msg decifrada: " + decryptedMessage);
	}
	
	public String decryptMessageByBlocks(String encryptedMessage, BigInteger d, BigInteger n) {
		
		String decryptedMessage = "";
		String[] lines = encryptedMessage.split(System.lineSeparator());
		
		for(String line : lines) {
			String decryptedSlice = new String((new BigInteger(line).modPow(d, n)).toByteArray());
			decryptedMessage = decryptedMessage + decryptedSlice;
		}
		
		return decryptedMessage;
	}
	
	public String encryptMessageByBlocks(String messageToEncrypt, BigInteger e, BigInteger n) {
		ArrayList<byte[]> slices = getSlices(messageToEncrypt);
		
		String encryptedMessage = new BigInteger(slices.get(0)).modPow(e, n).toString();
		
		for(int i=1; i< slices.size(); i++) {
			String encryptedSlice = System.lineSeparator() + new BigInteger(slices.get(i)).modPow(e, n).toString();
			encryptedMessage = encryptedMessage + encryptedSlice;   
		}
		
		return encryptedMessage;
	}
	
	public ArrayList<byte[]> getSlices(String message) {
		byte[] messageInBytes = message.getBytes();
		ArrayList<byte[]> slices = new ArrayList<>();
		
		int j = 0;
		int k = BLOCK_SIZE;
		
		while(j < messageInBytes.length) {
			
			if(k > messageInBytes.length)
				k = messageInBytes.length;
			
			byte[] slice = Arrays.copyOfRange(messageInBytes, j, k);
			slices.add(slice);
			j = j + BLOCK_SIZE;
			k = k + BLOCK_SIZE;
		}
		
		return slices;
		
	}
	
}
