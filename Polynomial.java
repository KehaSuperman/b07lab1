public class Polynomial
{
	double[] coefficients;
	
	public Polynomial()
	{
		coefficients=new double[1];
	}
	
	public Polynomial(double[] target_array)
	{
		coefficients=new double[target_array.length];
		for (int i=0; i<target_array.length; i++)
		{
			coefficients[i]=target_array[i];
		}
	}
	
	public Polynomial add(Polynomial another_p)
	{
		int max_len=Math.max(coefficients.length, another_p.coefficients.length);
		double[] result_coe=new double[max_len];
		for (int i=0; i<max_len; i++)
		{
			if (this.coefficients.length>=i+1) result_coe[i]+=this.coefficients[i];
			if (another_p.coefficients.length>=i+1) result_coe[i]+=another_p.coefficients[i];
		}
		Polynomial result_poly=new Polynomial(result_coe);

		return result_poly;
	}

	public double evaluate(double x_value)
	{
		double result=0;
		for (int i=0; i<this.coefficients.length; i++)
		{
			result+=Math.pow(x_value, i)*this.coefficients[i];
		}
		return result;
	}

	public boolean hasRoot(double x_value)
	{
		return evaluate(x_value)==0;
	}
}
