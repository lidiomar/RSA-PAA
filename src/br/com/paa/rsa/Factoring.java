package br.com.paa.rsa;

import java.math.BigInteger;
import java.security.SecureRandom;

public class Factoring {
	private static SecureRandom random = new SecureRandom();
	
	public static BigInteger[] factoringInPrimeNumbers(BigInteger number) {
		BigInteger factor1 = pollardRho(number);
		BigInteger factor2 = number.divide(factor1);
		
		System.out.println(factor1.toString() +" * "+ factor2.toString() +" = "+ number.toString());
		return new BigInteger[] {factor1, factor2};
	}
	
	public static BigInteger pollardRho(BigInteger n){
		BigInteger i = new BigInteger("1");
		BigInteger k = new BigInteger("2");
		
		BigInteger xi = new BigInteger(n.bitLength(), random);		
		while(xi.compareTo(n) >= 0){
			xi = new BigInteger(n.bitLength(), random);
		}
		
		BigInteger y = xi;
		
		while (true){
			i = i.add(BigInteger.ONE);
			xi = xi.multiply(xi).subtract(BigInteger.ONE).mod(n);
			
			BigInteger d = Euclidean.gcd(y.subtract(xi), n);
			
			if (!d.equals(BigInteger.ONE) && !d.equals(n)){
				return d;
			}
			
			if(i.equals(k)){
				y = xi;
				k = k.multiply(new BigInteger("2"));
			}			
		}
	}
	
	public static void imLucky(BigInteger number) {
		
		//BigInteger randomNumber = new BigInteger(number.bitLength(), random);
		BigInteger randomNumber = new BigInteger("20");

		while(randomNumber.compareTo(number) == 1 || randomNumber.compareTo(new BigInteger("2")) == -1
				|| randomNumber.remainder(new BigInteger("2")).equals(BigInteger.ZERO)) {
			randomNumber = new BigInteger(number.bitLength(), random);
		}
		
		System.out.println("Random number: "+randomNumber.toString());
		if(number.mod(randomNumber).equals(BigInteger.ZERO)) {
			System.out.println(randomNumber.toString());
		}else {
			System.out.println("Not found :/");
		}
	}
	
	/*
	 *  
	 *  1. Start with random x and c. Take y equal to x and f(x) = x2 + c.
		2. While a divisor isn’t obtained
			a. Update x to f(x) (modulo n) [Tortoise Move]
			b. Update y to f(f(y)) (modulo n) [Hare Move]
			c. Calculate GCD of |x-y| and n
			d. If GCD is not unity
				i.  If GCD is n, repeat from step 2 with another set of x, y and c
				ii. Else GCD is our answer*/
	
	public static BigInteger pollardRhoFail(BigInteger n) {
		BigInteger x = getRandom(n);
		BigInteger c = new BigInteger("2");
		
		BigInteger fx = func(x,c);
		BigInteger ffy = func(fx, c);
		
		while(true) {
			BigInteger fxMod = fx.mod(n);
			BigInteger ffyMod = ffy.mod(n);
			
			BigInteger gcd = Euclidean.gcd(fxMod.subtract(ffyMod).abs(), n);
			if(gcd.compareTo(BigInteger.ONE) != 0) {
				return gcd;
			}
			fx = ffy;
			ffy = func(ffy,c);
			
			System.out.println(" ... ");
		}
		
	}
	
	public static BigInteger func(BigInteger x, BigInteger c) {
		BigInteger x2 = x.multiply(x);
		return x2.add(c);
	}
	
	public static BigInteger getRandom(BigInteger n) {
		BigInteger randomNumber = new BigInteger(n.bitLength(), random);		
		while(randomNumber.compareTo(n) != -1){
			randomNumber = new BigInteger(n.bitLength(), random);
		}
		return randomNumber;
	}
	
}
