package br.com.paa.rsa;

import java.math.BigInteger;

public class Euclidean {
	
	public static BigInteger[] gcdExtended(BigInteger a, BigInteger b){
		if (b.equals(BigInteger.ZERO))
			return new BigInteger[] {a, BigInteger.ONE, BigInteger.ZERO};
		                                                         //  0   1   2 
		BigInteger[] triple = gcdExtended(b, a.mod(b)); //retorna d', x', y'
		BigInteger x = triple[1];
		triple[1] = triple[2];
		triple[2] = x.subtract(a.divide(b).multiply(triple[2]));
		return triple;
	}
	
	//Complexidade O(log n)
	public static BigInteger gcd(BigInteger p, BigInteger q) {
		if(q.equals(BigInteger.ZERO)) {
			return p;
		}
		return gcd(q, p.mod(q));
	}
}
