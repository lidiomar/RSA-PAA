package br.com.paa.rsa;

import java.math.BigInteger;

public class Euclidean {
	
	public static BigInteger[] gcdExtended(BigInteger a, BigInteger b){
		if (b.equals(BigInteger.ZERO))
			return new BigInteger[] {a, BigInteger.ONE, BigInteger.ZERO};
		                                                      
		BigInteger[] triple = gcdExtended(b, a.mod(b)); 
		BigInteger x = triple[1];
		triple[1] = triple[2];
		triple[2] = x.subtract(a.divide(b).multiply(triple[2]));
	
		return triple;
	}
	public static BigInteger validateModularInverse(BigInteger b, BigInteger phi) {
		if(b.compareTo(BigInteger.ZERO) == -1) {
			return b.mod(phi);
		}
		return b;
	}
	//Complexidade O(log n)
	public static BigInteger gcd(BigInteger p, BigInteger q) {
		if(q.equals(BigInteger.ZERO)) {
			return p;
		}
		return gcd(q, p.mod(q));
	}
}
