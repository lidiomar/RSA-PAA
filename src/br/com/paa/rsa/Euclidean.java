package br.com.paa.rsa;

import java.math.BigInteger;

public class Euclidean {
	
	//return array [d, a, b] such that d = gcd(p, q), ap + bq = d
	public static BigInteger[] gcdExtended(BigInteger p, BigInteger q) {
      if (q.equals(BigInteger.ZERO))
         return new BigInteger[] { p, BigInteger.ONE, BigInteger.ZERO };
	      	
      BigInteger[] vals = gcdExtended(q, p.remainder(q));
      BigInteger d = vals[0];
      BigInteger a = vals[2];
      BigInteger b = vals[1].subtract((p.divide(q)).multiply(vals[2]));
      
      return new BigInteger[] { d, a, b };
	}
	
	public static BigInteger gcd(BigInteger p, BigInteger q) {
		if(q.equals(BigInteger.ZERO)) {
			return p;
		}
		return gcd(q, p.mod(q));
	}
}
