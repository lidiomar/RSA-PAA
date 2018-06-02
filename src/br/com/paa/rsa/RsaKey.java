package br.com.paa.rsa;

import java.math.BigInteger;

public class RsaKey {
	BigInteger[] publicKey;
	BigInteger[] privateKey;
	
	public BigInteger[] getPublicKey() {
		return publicKey;
	}
	public void setPublicKey(BigInteger[] publicKeys) {
		this.publicKey = publicKeys;
	}
	public BigInteger[] getPrivateKey() {
		return privateKey;
	}
	public void setPrivateKey(BigInteger[] privateKeys) {
		this.privateKey = privateKeys;
	}
	
	
}
