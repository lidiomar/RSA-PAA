package br.com.paa.rsa;

import java.math.BigInteger;

public class Main {
	
	public static void main(String[]args) {
		Rsa rsa = new Rsa();
		RsaKey rsaKey = rsa.generateKeys();
		
		BigInteger d = rsaKey.getPrivateKey()[0];
		BigInteger n = rsaKey.getPrivateKey()[1];
		
		BigInteger e = rsaKey.getPublicKey()[1];
		
		//mensagem cifrada - RSA_encrypt()
		String msg = "Vamo que vamo";
        String msgcifrada = new BigInteger(msg.getBytes()).modPow(e, n).toString();
        System.out.println("msg cifrada: "+ msgcifrada);

        //mensagem decifrada - RSA_decrypt()
        String  msgdecifrada = new String(new BigInteger(msgcifrada).modPow(d, n).toByteArray());
        System.out.println("msg decifrada: " +msgdecifrada);
	
	}
}
