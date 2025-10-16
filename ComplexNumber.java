public class ComplexNumber extends SpecialNumber implements Comparable<ComplexNumber>
{
    double real, imaginary;
    
    public ComplexNumber() 
    {
    }

    public ComplexNumber(double real_in, double imaginary_in) 
    {
        this.real = real_in;
        this.imaginary = imaginary_in;
    }

    @Override
	public SpecialNumber add(SpecialNumber another_num) throws Lab3Exception 
	{
		ComplexNumber result;
		
		if (!(another_num instanceof ComplexNumber)) 
		{
			throw new Lab3Exception("Cannot add an incompatible type");
		}

        ComplexNumber temp = (ComplexNumber)another_num;
        double new_real = this.real + temp.real;
        double new_imaginary = this.imaginary + temp.imaginary;

        result = new ComplexNumber(new_real, new_imaginary);
        return result; 
    }

    @Override
    public SpecialNumber divideByInt(int input_int) throws Lab3Exception
    {
    	if (input_int == 0) throw new Lab3Exception("Cannot divide by zero");
    	
		ComplexNumber result;

		double new_real = this.real / input_int;
		double new_imaginary = this.imaginary / input_int;

		result = new ComplexNumber(new_real, new_imaginary);		
		return result;

    }

    @Override
	public int compareTo(ComplexNumber another_num)
    {
        double this_magnitude = Math.sqrt(Math.pow(this.real, 2) + Math.pow(this.imaginary, 2));
        double other_magnitude = Math.sqrt(Math.pow(another_num.real, 2) + Math.pow(another_num.imaginary, 2));
    
        if (this_magnitude > other_magnitude)
        {
            return 1;
        }
        else if (this_magnitude < other_magnitude)
        {
            return -1;
        }

        return 0;
    }

	@Override
	public boolean equals(Object obj)
    {
        if (!(obj instanceof ComplexNumber)) 
		{
			return false;
		}

		ComplexNumber another_num = (ComplexNumber)obj;
        return (this.real == another_num.real && this.imaginary == another_num.imaginary);
    }

	@Override
	public int hashCode()
    {
        return (int)(this.real + this.imaginary);
    }

}