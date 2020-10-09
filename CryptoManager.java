/*
 * Author: Jonathan Yang
 * Assignment 3
 * Description: This class creates the encryption and decryption for the Caesar Cipher and Bellaso Cipher.
 * The Caesar Cipher will offset the letter by an amount given by the user.
 * The Bellaso Cipher will offset the letter by an amount from a keyword given by the user. Although this says Bellaso, this is actually the Vigenere cipher.
 */
public class CryptoManager {

	private static final char LOWER_BOUND = ' ';//LOWER is 32
	private static final char UPPER_BOUND = '_';//UPPER IS 95
	private static final int RANGE = UPPER_BOUND - LOWER_BOUND + 1;//63

	/**
	 * This method determines if a string is within the allowable bounds of ASCII codes
	 * according to the LOWER_BOUND and UPPER_BOUND characters
	 * @param plainText a string to be encrypted, if it is within the allowable bounds
	 * @return true if all characters are within the allowable bounds, false if any character is outside
	 */
	public static boolean stringInBounds (String plainText) {
		for(int i = 0; i < plainText.length(); i++)//This loop checks if every character in plainText is within allowable bounds.
		{
			if(!(plainText.charAt(i) >= LOWER_BOUND && plainText.charAt(i) <= UPPER_BOUND))//This if statement checks if the specific character is within allowable bounds.
				return false;//this returns false if the character is  not within allowable bounds.
		}
		return true;//this returns true when all character in plainText is within allowable bounds.
	}

	/**
	 * Encrypts a string according to the Caesar Cipher.  The integer key specifies an offset
	 * and each character in plainText is replaced by the character \"offset\" away from it
	 * @param plainText an uppercase string to be encrypted.
	 * @param key an integer that specifies the offset of each character
	 * @return the encrypted string
	 */
	public static String encryptCaesar(String plainText, int key) {
		String newText = "";//This is the encrypted text that will be returned
		if(!stringInBounds(plainText))//This checks if the plainText is within allowable bounds.
			return "THIS TEXT DOES NOT WORK";//This is what returns if plainText is not within allowable bounds.

		for(int i = 0; i < plainText.length(); i++)//This loop will shift every character by a amount determined by key.
		{
			char letter = plainText.charAt(i);//This variable will take a specific character.

			while(key > RANGE)//If the key is above the range of UPPER_BOUND and LOWER_BOUND. The key will subtract itself until the key would not exceed
				key -= RANGE;//This loop will make sure that if the user put in a crazy high number, the program will not exceed UPPER_BOUND.

			if((letter + key) <= UPPER_BOUND)//This if statement checks if the shifted letter will not exceed the character designated by UPPER_BOUND
				letter += key;//This is the new letter if the shifted letter doesn't exceed UPPER_BOUND.
			else//This else statement is reached if the shifted letter will exceed UPPER_BOUND.
			{
				int letterNum = LOWER_BOUND + (key - (UPPER_BOUND - letter)) - 1; //This line of code shifts the letter in the string into int format. This line expects the key to shift above UPPER_BOUND and will loop the character into LOWER_BOUND. (UPPER_BOUND - letter) is the amount of space between the character and UPPER_BOUND. Key subtracts that amount to get the amount to start from LOWER_BOUND and beyond.
				letter = (char)letterNum;//This line turns letterNum into a char variable.
			}
			newText += letter;//This line adds the character into a new string which will be the encrypted string.
		}
		return newText;//the new encrypted string.
	}
	/**
	 * Decrypts a string according to the Caesar Cipher.  The integer key specifies an offset
	 * and each character in encryptedText is replaced by the character \"offset\" characters before it.
	 * This is the inverse of the encryptCaesar method.
	 * @param encryptedText an encrypted string to be decrypted.
	 * @param key an integer that specifies the offset of each character
	 * @return the plain text string
	 */
	public static String decryptCaesar(String encryptedText, int key) {
		String newText = "";//The string that will be the decrypted text.

		if(!stringInBounds(encryptedText))//Checks if the encrypted text is within allowable bounds.
			return "THE TEXT DOES NOT WORK!";//If the text is not within allowable bounds.

		for(int i = 0; i < encryptedText.length();i++)//This loop will shift each individual characters in the encryptedText string by the amount in key.
		{
			char letter = encryptedText.charAt(i);//This variable stores a letter from the encrypted Text.

			while(key > RANGE)//This while loop subtracts the key into a usable number. It makes sure that if the user enters a crazy high number, the program would not break and go through the allowable_bounds.
				key -= RANGE;//This line subtracts key until it is a usable number that won't go through allowable bounds.

			if((letter - key) >= LOWER_BOUND)//This if statement makes sure that the character does not go below minimum amount which is LOWER_BOUND.
				letter-= key;//If the key will go below LOWER_BOUND, the character is shifted normally.
			else//This else statement is reached if the shifted letter will go below LOWER_BOUND.
			{
				int letterNum = UPPER_BOUND - (key -(letter - LOWER_BOUND)) + 1;//This line of code shift the letter in the string in int format. This line of code expects the character to go below LOWER_BOUND. (letter - LOWER_BOUND) finds the amount of character space between key and LOWER_BOUND. Then key will subtract that amount to get the amount of space to shift from LOWER_BOUND.
				letter = (char)letterNum;//This line turns letterNum into a char variable.
			}
			newText+=letter;//This line adds the character into a new string which will return the plaintext.
		}
		return newText;//This line returns the plaintext.
	}

	/**
	 * Encrypts a string according the Bellaso Cipher.  Each character in plainText is offset
	 * according to the ASCII value of the corresponding character in bellasoStr, which is repeated
	 * to correspond to the length of plainText
	 * @param plainText an uppercase string to be encrypted.
	 * @param bellasoStr an uppercase string that specifies the offsets, character by character.
	 * @return the encrypted string
	 */
	public static String encryptBellaso(String plainText, String bellasoStr) {
		if(!stringInBounds(plainText))
			return "THIS TEXT DOES NOT WORK";

		String newText = "";
		int[] key = new int[bellasoStr.length()];//This letter turns bellasoStr into numbers.

		for(int i = 0; i < bellasoStr.length(); i++)//This loop stores bellasoStr the int of the char variable number.
		{
			char belKey = bellasoStr.charAt(i);
			key[i] = belKey;
		}
		int bcl = 0;//This variable is what goes through all the values in belCode array.
		for(int i = 0; i < plainText.length(); i++)//This loop is to to make the bellaso cipher.
		{
			//Step 1: Put the letter in a string into a char variable
			char letter = plainText.charAt(i);//Store the letter in a char variable to shift.

			//Step 2: Shift the letter
			if((letter + key[bcl]) <= UPPER_BOUND)//This if statement checks if the shifted letter will not exceed the character designated by UPPER_BOUND
				letter += key[bcl];//This is the new letter if the shifted letter doesn't exceed UPPER_BOUND.
			else//This else statement is reached if the shifted letter will exceed UPPER_BOUND.
			{
				int letterNum = LOWER_BOUND + (key[bcl] - (UPPER_BOUND - letter) -1); //This line of code shifts the letter in the string into int format. This line expects the key to shift above UPPER_BOUND and will loop the character into LOWER_BOUND. (UPPER_BOUND - letter) is the amount of space between the character and UPPER_BOUND. Key subtracts that amount to get the amount to start from LOWER_BOUND and beyond.
				while(letterNum > UPPER_BOUND)//This loop checks if letterNum is still above UPPER_BOUND despite after calculation.
					letterNum = LOWER_BOUND + (letterNum - UPPER_BOUND) -1;//If letterNum is still above UPPER_BOUND, then letterNum will wrap around the range until it finds a number that works.
				letter = (char)letterNum;//This line turns letterNum into a char variable.
			}

			//Step 3: this if statement loops the key array.
			if(bcl < key.length-1)//This loops bcl if it reaches length.
				bcl++;
			else//If bcl reaches the max, it resets.
				bcl = 0;

			//Step 4: Put the letter into a new string
			newText+=letter;//This line adds the character into a new string which will return the plaintext.
		}

		//Step 5: Return the new text.
		return newText;//the encrypted text
	}

	/**
	 * Decrypts a string according the Bellaso Cipher.  Each character in encryptedText is replaced by
	 * the character corresponding to the character in bellasoStr, which is repeated
	 * to correspond to the length of plainText.  This is the inverse of the encryptBellaso method.
	 * @param encryptedText an uppercase string to be encrypted.
	 * @param bellasoStr an uppercase string that specifies the offsets, character by character.
	 * @return the decrypted string
	 */
	public static String decryptBellaso(String encryptedText, String bellasoStr) {

		if(!stringInBounds(encryptedText))
			return "THIS TEXT DOES NOT WORK";

		String newText = "";
		int[] key = new int[bellasoStr.length()];//This letter turns bellasoStr into numbers.

		for(int i = 0; i < bellasoStr.length(); i++)//This loop stores bellasoStr the int of the char variable number.
			key[i] = bellasoStr.charAt(i);

		int bcl = 0;//This variable is what goes through all the values in belCode array.
		for(int i = 0; i < encryptedText.length(); i++)//This loop is to to make the bellaso cipher.
		{
			char letter = encryptedText.charAt(i);//Store the letter in a char variable to shift.

			if((letter - key[bcl]) >= LOWER_BOUND)//This if statement checks if the shifted letter will not exceed the character designated by UPPER_BOUND
				letter -= key[bcl];//This is the new letter if the shifted letter doesn't exceed UPPER_BOUND.
			else//This else statement is reached if the shifted letter will exceed UPPER_BOUND.
			{
				int step1 = letter- LOWER_BOUND;//this
				int step2 = key[bcl] - step1 - 1;
				while(step2 > RANGE)
					step2-=RANGE;
				int step3 = UPPER_BOUND - step2;
				letter = (char)step3;
			}

			//this if statement loops the key array.
			if(bcl < key.length-1)//This goes to the next key value in bellasoStr. For example, for HELLO. H -> E
				bcl++;
			else//If bcl reaches the max, it resets.
				bcl = 0;
			newText+=letter;//This line adds the character into a new string which will return the plaintext.
		}

		return newText;//the plaintext
	}
}
