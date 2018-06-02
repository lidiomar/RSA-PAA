package br.com.paa.rsa;

import java.math.BigInteger;
import java.security.SecureRandom;

public class Rsa {
	private static SecureRandom random = new SecureRandom();
	private static final int len = 64;
	
	public static void main(String...args) {
		BigInteger prime1 = generatePrimeNumber();
		BigInteger prime2 = generatePrimeNumber();
		BigInteger nNumber = getNumberN(prime1, prime2);
		BigInteger phi = executeTotientFunction(prime1, prime2);
		BigInteger eNumber = generateE(phi);
		BigInteger dNumber = generateD(phi, eNumber);
		System.out.println("d1: "+dNumber.toString());
		dNumber = eNumber.modInverse(phi);
		System.out.println("d2: "+dNumber.toString());
		
		//mensagem cifrada - RSA_encrypt()
		String msg = "Homem das cavern";
        String msgcifrada = new BigInteger(msg.getBytes()).modPow(eNumber, nNumber).toString();
        System.out.println("msg cifrada: "+ msgcifrada);

        //mensagem decifrada - RSA_decrypt()
        String msgdecifrada = new String(new BigInteger(msgcifrada).modPow(dNumber, nNumber).toByteArray());
        System.out.println("msg decifrada: " +msgdecifrada);
        
        BigInteger[] a = Euclidean.gcdExtended(new BigInteger("36163"), new BigInteger("21199"));
        System.out.println(a[0]);
        System.out.println(a[1]);
        System.out.println(a[2]);
	}
	
	
	public static BigInteger[] generatePublicKeys(BigInteger prime1, BigInteger prime2, BigInteger phi) {
		BigInteger nNumber = getNumberN(prime1, prime2);
		BigInteger eNumber = generateE(phi);
		
		return new BigInteger[] {nNumber, eNumber};
	}
	
	public static BigInteger[] generatePrivateKeys(BigInteger prime1, BigInteger prime2, BigInteger phi, BigInteger eNumber) {
		BigInteger dNumber = generateD(phi, eNumber);
		BigInteger nNumber = getNumberN(prime1, prime2);
		
		return new BigInteger[] {dNumber, nNumber};
	}
	
	public static BigInteger generateD(BigInteger phi, BigInteger eNumber) {
		BigInteger extendedGcd[] = Euclidean.gcdExtended(phi, eNumber);
		BigInteger dNumber = extendedGcd[0];
		System.out.println("########################");
		System.out.println(extendedGcd[0].toString());
		System.out.println(extendedGcd[1].toString());
		System.out.println(extendedGcd[2].toString());
		System.out.println("########################");
		return dNumber;
	}
	
	public static BigInteger generateE(BigInteger phi) {	
		BigInteger numberE = new BigInteger("3");
        
        while(Euclidean.gcd(phi, numberE).compareTo(BigInteger.ONE) != 0) {
        		numberE = numberE.add(new BigInteger("2"));
        }
		return numberE;
		
	}
	
	public static BigInteger getNumberN(BigInteger prime1, BigInteger prime2) {
		BigInteger nNumber = prime1.multiply(prime2);
		return nNumber;
	}
	
	public static BigInteger generatePrimeNumber() {
		BigInteger primeNumber = new BigInteger(len, 100, random);
		
		while(!RabinMiller.isPrimeNumber(primeNumber)) {
			primeNumber = new BigInteger(len, 100, random); 
		}
		return primeNumber;
	}
	
	public static BigInteger executeTotientFunction(BigInteger prime1, BigInteger prime2) {
		
		BigInteger phi = (prime1.subtract(BigInteger.ONE))
                .multiply(prime2.subtract(BigInteger.ONE));
		
		return phi;
	}
}
