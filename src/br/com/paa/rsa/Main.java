package br.com.paa.rsa;

import java.math.BigInteger;

public class Main {
	
	public static void main(String[]args) {
		/*BigInteger eNumber = new BigInteger("36163");
		BigInteger phi = new BigInteger("1058");
		
		BigInteger[] a = Euclidean.gcdExtended(new BigInteger("36163"), new BigInteger("1058"));
		System.out.println(a[0]);
		System.out.println(a[1]);
		System.out.println(a[2]);
		
		BigInteger dNumber = eNumber.modInverse(phi);
		System.out.println(dNumber);*/
		Main main = new Main();
		main.execute();
	}
	
	public void execute() {
		Rsa rsa = new Rsa();
		RsaKey rsaKey = rsa.generateKeys();
		
		BigInteger d = rsaKey.getPrivateKey()[0];
		BigInteger n = rsaKey.getPrivateKey()[1];
		BigInteger e = rsaKey.getPublicKey()[1];
		
		EncryptionHelper encryptionHelper = new EncryptionHelper();
		
		String messageToEncrypt = "Mensagem simples para criptografia";
		encryptionHelper.encryptMessage(e, n, messageToEncrypt);
		encryptionHelper.decryptFromFile(d, n);
	}
	
}
