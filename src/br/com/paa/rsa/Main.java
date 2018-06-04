package br.com.paa.rsa;

import java.math.BigInteger;

public class Main {
	
	public static void main(String[]args) {
		Main main = new Main();
		main.breakKey();
		
	}
	
	public void breakKey() {
		BigInteger prime1 = Rsa.generatePrimeNumber();
		BigInteger prime2 = Rsa.generatePrimeNumber();
		while(prime1.compareTo(prime2) == 0) {
			prime2 = Rsa.generatePrimeNumber();
		}
		BigInteger nNumber = prime1.multiply(prime2);
		
		System.out.println("nNumber: "+nNumber);
		System.out.println("prime1: "+prime1);
		System.out.println("prime2: "+prime2);
		
		System.out.println(Factoring.pollardRho(nNumber));
		
		BigInteger[] factors = Factoring.factoringInPrimeNumbers(nNumber);
		BigInteger primeNumber1 = factors[0];
		BigInteger primeNumber2 = factors[1];
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
