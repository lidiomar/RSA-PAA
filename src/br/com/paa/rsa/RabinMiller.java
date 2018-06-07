package br.com.paa.rsa;

import java.math.BigInteger;
import java.security.SecureRandom;

public class RabinMiller {
	private static SecureRandom random = new SecureRandom();
	
	public static Boolean isPrimeNumber(BigInteger number) {
		BigInteger aleatory = getAleatoryNumberMax(number);
		Boolean test = primalityTest(aleatory, number);
		return test;
	}
	
	/*Retorna um número aleatório menor que number*/
	public static BigInteger getAleatoryNumberMax(BigInteger number) {
		BigInteger randomNumber = new BigInteger(number.bitLength(), random);
		while(randomNumber.compareTo(number) != -1) {
			randomNumber = new BigInteger(number.bitLength(), random);
		}
		return randomNumber;
	}
	
	/*Considerado O(k log^3 n)
	 * sendo k o número de testes feitos*/
	
	public static Boolean primalityTest(BigInteger aleatory, BigInteger number) {
		BigInteger numberSubtractedOne = number.subtract(BigInteger.ONE);
		int lowestSetBit = numberSubtractedOne.getLowestSetBit();
		BigInteger d = numberSubtractedOne.shiftRight(lowestSetBit);
		
		BigInteger result = aleatory.modPow(d, number);
		
		if(result.equals(BigInteger.ONE)) { 
			return true;
		}else {
			for(int i=2; i< lowestSetBit; i=i+2) {
				BigInteger exponent = d.multiply(BigInteger.valueOf(i));
				result = aleatory.modPow(exponent, number);
				
				if(result.equals(BigInteger.ONE)) {
					return true;
				}
			}
		}
		
		return false;
	}
}
