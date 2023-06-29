package com.company.Personalmgmt.practise;

import com.company.Personalmgmt.exception.AgeException;

public class Test5 {

	public static void main(String[] args) {

		int age = 17;
		
		try {
			
			checkAge(age);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	
	}
	
	
	private static void checkAge(int age) throws AgeException {
		
		if(age < 18) {
			
			throw new AgeException("\n"+"You must be 18+");
			
		}else {
			System.out.println("you are okay");
		}
		
	}


	public void encryption() {
		
		final String secretKey = "federal";

		String customerId = "24901157";
		String encryptedString = AES.encrypt(customerId, secretKey);
		String decryptedString = AES.decrypt(encryptedString, secretKey);

		System.out.println("CustomerId = " + customerId);
		System.out.println("Encryption = " + encryptedString);
		System.out.println("Decryption = " + decryptedString);

		
	}

}
