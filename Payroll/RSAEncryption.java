package Payroll;

import java.math.BigInteger;

public class RSAEncryption {
	
	private static final BigInteger p = BigInteger.valueOf(7);
	private static final BigInteger q = BigInteger.valueOf(37);
	private static final BigInteger e = BigInteger.valueOf(257);
	private BigInteger n = BigInteger.ZERO;
	private BigInteger z = BigInteger.ZERO;
	private BigInteger d = BigInteger.ZERO;
	static char[] dataToProcess = null;
	static BigInteger[] encryptedPW = null;
	
	RSAEncryption(){
		n = p.multiply(q);				
		z = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));										
		d = e.modInverse(z);
	}
	
	//function t encrypt with rsa
	public BigInteger[] encryptPassword(String password) {
		
		dataToProcess = password.toCharArray();
		encryptedPW = new BigInteger[dataToProcess.length];
		for(int t = 0; t < dataToProcess.length; t++ )
		{
			char ch = dataToProcess[t];
			int msg = ch;		
			BigInteger encryptedMsg = BigInteger.valueOf(msg).pow(e.intValue()).mod(n);
			encryptedPW[t] = encryptedMsg;			
		}		
		
		return encryptedPW;
	}
	
	//function to decrypt with rsa
	public String decryptPassword(BigInteger[] encrypted) {
		char[] decryptedArray = new char[encrypted.length];
		for(int i = 0; i<encrypted.length; i++) {
			BigInteger bigInt = (encrypted[i].pow(d.intValue())).mod(n);
			int toInt = bigInt.intValue();
			char toChar = (char)toInt;
			decryptedArray[i] = toChar;
		}
		//String decryptedString = decryptedArray.toString();
		String decryptedString = new String(decryptedArray);
		return decryptedString;
	}
}
