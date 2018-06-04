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
		/*Verificar número primo:
		 * Ex.: Número 321
		 * 		Exibir o número "n" no seguinte padrão: 
		 * 		n-1 = 2ˆs.d
		 * 		320 = 2ˆ6.5 
		 * 
		 * sendo "d" um número ímpar
		 * 
		 * Escolher um número aleatório "a" entre 2 e n-1, então:
		 *  
		 *  aˆd mod (n) 
		 *  
		*/
		
		BigInteger numberSubtractedOne = number.subtract(BigInteger.ONE);
		int lowestSetBit = numberSubtractedOne.getLowestSetBit();
		BigInteger d = numberSubtractedOne.shiftRight(lowestSetBit);
		
		BigInteger result = aleatory.modPow(d, number);
		
		//System.out.println(aleatory+" ^ "+d +" (mod "+ number +") = " + result );
		
		if(result.equals(BigInteger.ONE)) { 
			return true;
		}else {
			
			for(int i=2; i< lowestSetBit; i=i+2) {
				BigInteger exponent = d.multiply(BigInteger.valueOf(i));
				result = aleatory.modPow(exponent, number);
				
				//System.out.println(aleatory+" ^ "+ exponent +" (mod "+ number +") = " + result );
				
				if(result.equals(BigInteger.ONE)) {
					return true;
				}
			}
			
			/*BigInteger index = new BigInteger("2");
			while(index.compareTo(d) == -1) {
				BigInteger exponent = d.multiply(index);
				result = aleatory.modPow(exponent, number);
				System.out.println(aleatory+" ^ "+ exponent +" (mod "+ number +") = " + result );
				
				if(result.equals(BigInteger.ONE)) {
					return true;
				}
				index = index.add(new BigInteger("2"));
			}*/
			
		}
		
		return false;
	}
}
