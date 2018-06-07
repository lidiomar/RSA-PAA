package br.com.paa.rsa;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
	
	public static void main(String[]args) {
		Main main = new Main();
		//main.execute();
		
		BigInteger a = Rsa.generatePrimeNumber();
		BigInteger b = Rsa.generatePrimeNumber();
		
		
		BigInteger ab = a.multiply(b);
		
		
		BigInteger[]factors = Factoring.getProductFactorsBruteForce(ab);
		System.out.println(factors[0] +" * "+factors[1]);
		System.out.println("#######################");
		System.out.println(a +" - "+b);
		System.out.println("ab - "+ab);
	}
	
	public void breakKey() {
		BigInteger prime1 = Rsa.generatePrimeNumber();
		BigInteger prime2 = Rsa.generatePrimeNumber();
		
		while(prime1.compareTo(prime2) == 0) {
			prime2 = Rsa.generatePrimeNumber();
		}
		BigInteger nNumber = prime1.multiply(prime2);
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date dateStart = new Date();
		
		Factoring.pollardRho(nNumber);
		
		Date dateFinish = new Date();
		float diff = (float) (dateFinish.getTime() - dateStart.getTime());
		float diffSeconds = diff /1000;
		System.out.println(diffSeconds);
		
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
		
		encryptionHelper.encryptMessage(e, n);
		encryptionHelper.decryptFromFile(d, n);
	}
	
}
