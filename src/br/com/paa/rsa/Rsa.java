package br.com.paa.rsa;

import java.math.BigInteger;
import java.security.SecureRandom;

public class Rsa  {
	private static SecureRandom random = new SecureRandom();
	private static final int len = 256;
	
	public RsaKey generateKeys() {
		BigInteger prime1 = generatePrimeNumber();
		BigInteger prime2 = generatePrimeNumber();
		
		while(prime1.compareTo(prime2) == 0) {
			prime2 = generatePrimeNumber();
		}
		
		BigInteger nNumber = getNumberN(prime1, prime2);
		
		BigInteger phi = executeTotientFunction(prime1, prime2);
		BigInteger eNumber = generateE(phi);
		BigInteger dNumber = generateD(phi, eNumber);
		
		RsaKey rsaKey = new RsaKey();
		rsaKey.setPrivateKey(new BigInteger[] { dNumber, nNumber });
		rsaKey.setPublicKey(new BigInteger[] { nNumber, eNumber });
		
		return rsaKey;
	}
	
	public RsaKey generateKeysByPrimeNumbers(BigInteger prime1, BigInteger prime2) {
		BigInteger nNumber = getNumberN(prime1, prime2);
		
		BigInteger phi = executeTotientFunction(prime1, prime2);
		BigInteger eNumber = generateE(phi);
		BigInteger dNumber = generateD(phi, eNumber);
		
		RsaKey rsaKey = new RsaKey();
		rsaKey.setPrivateKey(new BigInteger[] { dNumber, nNumber });
		rsaKey.setPublicKey(new BigInteger[] { nNumber, eNumber });
		
		return rsaKey;
	}
	
	/*
	 * Considerado O(log n), tempo de execução de euclides estendido
	 */
	private BigInteger generateD(BigInteger phi, BigInteger eNumber) {
		/* Euclides estendido com BigInteger não está funcionando*/
		BigInteger extendedGcd[] = Euclidean.gcdExtended(phi, eNumber);
		BigInteger x = extendedGcd[2];
		//Verificação para caso x seja um número negativo
		BigInteger dNumber = Euclidean.validateModularInverse(x, phi);
		//BigInteger dNumber = eNumber.modInverse(phi);
		
		return dNumber;
	}
	
	/*
	 * Considerado O(k log n) sendo k o número de tentativas para encontrar
	 * phi e numberE primos
	 */
	private BigInteger generateE(BigInteger phi) {	
		BigInteger numberE = new BigInteger("3");
        
        while(Euclidean.gcd(phi, numberE).compareTo(BigInteger.ONE) != 0) {
        		numberE = numberE.add(new BigInteger("2"));
        }
		return numberE;
		
	}
	
	//Considerado O(1)
	
	private BigInteger getNumberN(BigInteger prime1, BigInteger prime2) {
		BigInteger nNumber = prime1.multiply(prime2);
		return nNumber;
	}
	
	//Considerado O(k log^3 n)
	
	public static BigInteger generatePrimeNumber() {
		BigInteger primeNumber = new BigInteger(len, 100, random);
		
		while(!RabinMiller.isPrimeNumber(primeNumber)) {
			primeNumber = new BigInteger(len, 100, random); 
		}
		return primeNumber;
	}
	
	//Considerado O(1), não consta na documentação 
	
	private BigInteger executeTotientFunction(BigInteger prime1, BigInteger prime2) {	
		BigInteger phi = (prime1.subtract(BigInteger.ONE)).multiply(prime2.subtract(BigInteger.ONE));
		return phi;
	}

}
