package br.com.paa.rsa;

import java.math.BigInteger;

public class Euclidean {
	
	/*
	 *  (b%a).x1 + a.y1 = gcd
	 *  
	 *   ax + by = gcd
	 *   
	 *   b%a = b - [b/a].a
	 *   
	 *   Substituindo:
	 *   gcd = (b%a).x1 + a.y1 = ( b - [b/a].a ).x1 + ay1
	 *   
	 *   Agrupando os termos:
	 *   
	 *   gcd = b.x1 + a. (y1 - (b/a).x1 )
	 *   
	 *  Então:
	 *  
	 *  x = y1 - (b/a). x1
	 *  y = x1
	 *  
	 * */
	
	public static BigInteger[] gcdExtended(BigInteger a, BigInteger b){
		if (b.equals(BigInteger.ZERO))
			return new BigInteger[] {a, BigInteger.ONE, BigInteger.ZERO}; // resposta GCD = a
		                                                      
		BigInteger[] triple = gcdExtended(b, a.mod(b));
		BigInteger x = triple[1];
		triple[1] = triple[2]; 
		triple[2] = x.subtract(a.divide(b).multiply(triple[2])); //segundo multiplicador
		
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
