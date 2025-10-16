public class RationalNumber extends SpecialNumber implements Comparable<RationalNumber>
{
	public int numerator, denominator;

	/**
	* This method returns the greatest common factor of two integers
	* @param num1 the first input integer
	* @param num2 the second input integer
	* @return The greatest common factor of the given two integers
	*/
	public static int findGCF(int num1, int num2) 
	{
        while (num2 != 0) {
            int temp = num2;
            num2 = num1 % num2;
            num1 = temp;
        }
        return num1;
    }
	
	public RationalNumber()
	{
		this.numerator = 0;
		this.denominator = 1;
	}
	
	/**
	* This method construct a RationalNumber object using parameters nume and denom
	* @param nume the value of the field numerator wanted to initialize the object
	* @param num2 the value of the field denominator wanted to initialize the object
	*/
	public RationalNumber(int nume, int denom) throws Lab3Exception 
	{
		if (0 == denom) throw new Lab3Exception("Denominator cannot be zero");
		
		int GCF = RationalNumber.findGCF(nume, denom);
		nume = nume / GCF;
		denom = denom / GCF;

		this.numerator = nume;
		this.denominator = denom;
	}
	
	/**
	* This method returns the sum of two RationalNumber
	* @param another_num another RationalNumber to be added
	* @return sum of two RationalNumber
	*/
	@Override
	public SpecialNumber add(SpecialNumber another_num) throws Lab3Exception 
	{
		RationalNumber result;
		
		if (!(another_num instanceof RationalNumber)) 
		{
			throw new Lab3Exception("Cannot add an incompatible type");
		}

		RationalNumber temp = (RationalNumber)another_num;
		int new_deno = this.denominator * temp.denominator;
		int new_nume = this.numerator * temp.denominator + this.denominator * temp.numerator;
		int GCF = RationalNumber.findGCF(new_deno, new_nume);

		new_deno = new_deno / GCF;
		new_nume = new_nume / GCF;

		result = new RationalNumber(new_nume, new_deno);		
		return result;
		
	}

	/**
	* This method returns the result of RationalNumber divided by the input integer
	* @param input_int the integer that will be used to divide the RationalNumber
	* @return result of RationalNumber divided by the input integer
	*/
	@Override
	public SpecialNumber divideByInt(int input_int) throws Lab3Exception 
	{
		if (input_int == 0) throw new Lab3Exception("Cannot divide by zero");

		RationalNumber result;
		int new_deno = this.denominator * input_int;
		int new_nume = this.numerator;
		int GCF = RationalNumber.findGCF(new_deno, new_nume);

		new_deno = new_deno / GCF;
		new_nume = new_nume / GCF;

		result = new RationalNumber(new_nume, new_deno);		
		return result;

	}

	/**
	* This method compares two RationalNumber objects
	* @param another_num the RationalNumber to be compared
	* @return -1 if another_num is less than this RationalNumber, 0 if they are
	* equal, and 1 otherwise
	*/
	@Override
	public int compareTo(RationalNumber another_num)
	{
		if (this.numerator * another_num.denominator > another_num.numerator * this.denominator)
		{
			return 1;
		}
		else if (this.numerator * another_num.denominator < another_num.numerator * this.denominator)
		{
			return -1;
		}
		return  0;
	}
	
	/**
	* This method determines if two RationalNumber objects are equal
	* @param another_num the RationalNumber to be compared
	* @return true if two RationalNumber are equal, false if they are not
	*/
	@Override
	public boolean equals(Object obj)
	{
		if (!(obj instanceof RationalNumber)) 
		{
			return false;
		}

		RationalNumber another_num = (RationalNumber)obj;
		return (this.denominator == another_num.denominator && this.numerator == another_num.numerator);
	}
	
	/**
	* This method returns the hashcode of this RationalNumber
	* @return hashcode of this RationalNumber
	*/
	@Override
	public int hashCode()
	{
		return this.denominator + this.numerator;
	}
	
}