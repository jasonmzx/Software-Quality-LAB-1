package com.ontariotechu.sofe3980U;

//* Implementation by: Jason Manarroo 100825106

/**
 * Unsigned integer Binary variable
 *
 */
public class Binary
{
	private String number="0";  // string containing the binary value '0' or '1'
	/**
	* A constructor that generates a binary object.
	*
	* @param number a String of the binary values. It should conatins only zeros or ones with any length and order. otherwise, the value of "0" will be stored.   Trailing zeros will be excluded and empty string will be considered as zero.
	*/
    public Binary(String number) {
		for (int i = 0; i < number.length(); i++) {
			// check each character if it's not 0 or 1
			char ch=number.charAt(i);
			if(ch!='0' && ch!='1') {
				number="0"; // if not store "0" and end the function
				return;
			}
		}
		// remove any trailing zeros
		int beg;
		for (beg = 0; beg < number.length(); beg++) {
			if (number.charAt(beg)!='0')
				break;
		}
		//beg has the index of the first non zero digit in the number
		this.number=number.substring(beg); // exclude the trailing zeros if any
		// uncomment the following code
		
		// if(this.number=="") { // replace empty strings with a single zero
		// 	this.number="0";
		// }
		
    }
	/**
	* Return the binary value of the variable
	*
	* @return the binary value in a string format.
	*/
	public String getValue()
	{
		return this.number;
	}
	/**
	* Adding two binary variables. For more information, visit <a href="https://www.wikihow.com/Add-Binary-Numbers"> Add-Binary-Numbers </a>.
	*
	* @param num1 The first addend object
	* @param num2 The second addend object
	* @return A binary variable with a value of <i>num1+num2</i>.
	*/
	public static Binary add(Binary num1,Binary num2)
	{
		// the index of the first digit of each number
		int ind1=num1.number.length()-1;
		int ind2=num2.number.length()-1;
		//initial variable
		int carry=0;
		String num3="";  // the binary value of the sum
		while(ind1>=0 ||  ind2>=0 || carry!=0) // loop until all digits are processed
		{
			int sum=carry; // previous carry
			if(ind1>=0){ // if num1 has a digit to add
				sum += (num1.number.charAt(ind1)=='1')? 1:0; // convert the digit to int and add it to sum
				ind1--; // update ind1
			}
			if(ind2>=0){ // if num2 has a digit to add
				sum += (num2.number.charAt(ind2)=='1')? 1:0; // convert the digit to int and add it to sum
				ind2--; //update ind2
			}
			carry=sum/2; // the new carry
			sum=sum%2;  // the resultant digit
			num3 =( (sum==0)? "0":"1")+num3; //convert sum to string and append it to num3
		}
		Binary result=new Binary(num3);  // create a binary object with the calculated value.
		return result;
		
	}

	/**
	* Adding two binary variables. For more information, visit <a href="https://www.wikihow.com/Add-Binary-Numbers"> Add-Binary-Numbers </a>.
	*
	* @param num1 The first addend object
	* @param num2 The second addend object
	* @return A binary variable with a value of <i>num1 OR num2</i>.
	*/
	public static Binary or(Binary num1, Binary num2)
	{
		// Find the length of the two binary numbers
		int len1 = num1.number.length();
		int len2 = num2.number.length();

		// Determine the maximum length among the two
		int maxLength = Math.max(len1, len2);

		// Create arrays to hold the binary digits, padded with leading zeros if necessary
		//* Let's say num1 is "101" and num2 is "1001", so we need to pad num1 with a 0, "0101"

		char[] binary1 = String.format("%" + maxLength + "s", num1.number).replace(' ', '0').toCharArray();
		char[] binary2 = String.format("%" + maxLength + "s", num2.number).replace(' ', '0').toCharArray();

		// Perform bitwise OR operation
		String result = "";
		for (int i = 0; i < maxLength; i++) {

			//* If Num1 or Num2's [i]th element is a 1, put 1, else: put 0

			result += (binary1[i] == '1' || binary2[i] == '1') ? '1' : '0';
		}

		return new Binary(result);
	}

	/**
	* Adding two binary variables. For more information, visit <a href="https://www.wikihow.com/Add-Binary-Numbers"> Add-Binary-Numbers </a>.
	*
	* @param num1 The first addend object
	* @param num2 The second addend object
	* @return A binary variable with a value of <i>num1 AND num2</i>.
	*/
	public static Binary and(Binary num1, Binary num2)
	{
		// Find the length of the two binary numbers
		int len1 = num1.number.length();
		int len2 = num2.number.length();

		// Determine the maximum length among the two
		int maxLength = Math.max(len1, len2);

		// Create arrays to hold the binary digits, padded with leading zeros if necessary
		//* Let's say num1 is "101" and num2 is "1001", so we need to pad num1 with a 0, "0101"

		char[] binary1 = String.format("%" + maxLength + "s", num1.number).replace(' ', '0').toCharArray();
		char[] binary2 = String.format("%" + maxLength + "s", num2.number).replace(' ', '0').toCharArray();

		// Perform bitwise OR operation
		String result = "";
		for (int i = 0; i < maxLength; i++) {

			//* If Num1 or Num2's [i]th element is a 1, put 1, else: put 0

			result += (binary1[i] == '1' && binary2[i] == '1') ? '1' : '0';
		}

		return new Binary(result);
	}

/**
 * Multiplying two binary variables.
 *
 * @param num1 The first binary operand
 * @param num2 The second binary operand
 * @return A binary variable with a value of <i>num1 * num2</i>.
 */
public static Binary multiply(Binary num1, Binary num2) {
    Binary result = new Binary("0");
    String num2Value = num2.getValue(); 

	int num2Len = num2Value.length() - 1;

    for (int i = num2Len; i >= 0; i--) {
        if (num2Value.charAt(i) == '1') {
            // Left shift num1 by (num2Value.length() - 1 - i) positions
            String shiftedNum1 = num1.getValue() + "0".repeat(num2Len - i);
            Binary shiftedBinary = new Binary(shiftedNum1);

            // Add the shifted num1 to the result
            result = add(result, shiftedBinary);
        }
    }

    return result;
}

}	
