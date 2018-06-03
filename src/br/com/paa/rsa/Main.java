package br.com.paa.rsa;

import java.math.BigInteger;

public class Main {
	
	public static void main(String[]args) {
		Rsa rsa = new Rsa();
		RsaKey rsaKey = rsa.generateKeys();
		Main main = new Main();
		
		BigInteger d = rsaKey.getPrivateKey()[0];
		BigInteger n = rsaKey.getPrivateKey()[1];
		BigInteger e = rsaKey.getPublicKey()[1];
		
		EncryptionHelper encryptionHelper = new EncryptionHelper();
		
		/*String messageToEncrypt = "Mensagem simples para criptografia";
		encryptionHelper.encryptMessage(e, n, messageToEncrypt);*/
		
		encryptionHelper.decryptFromFile(d, n);
	}
	
}
