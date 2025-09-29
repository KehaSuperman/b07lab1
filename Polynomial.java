import java.io.*;
import java.util.Arrays;

public class Polynomial
{
	double[] coefficients;
	int[] exponents;
	
	public Polynomial()
	{
		coefficients=new double[1];
		exponents=new int[1];
	}
	
	public Polynomial(double[] coe_arr, int[] exp_arr)
	{
		coefficients=new double[coe_arr.length];
		exponents=new int[exp_arr.length];
		System.arraycopy(coe_arr, 0, coefficients, 0, coe_arr.length);
		System.arraycopy(exp_arr, 0, exponents, 0, exp_arr.length);
	}

	public Polynomial(String file_in) throws FileNotFoundException, IOException
	{
		BufferedReader myBuffer = new BufferedReader(new FileReader(file_in));
		String info = myBuffer.readLine();
		String[] info_split;

		if (null!=info)
		{
			info_split = info.split("[+-]");
			coefficients = new double[info_split.length];
			exponents = new int[info_split.length];
			for (int i = 0; i < coefficients.length; i++) 
			{
				if (info_split[i].length()==1)
				{
					coefficients[i] = Double.parseDouble(info_split[i]);
					exponents[i] = 0;
				}
				else
				{
					String[] monomial = info_split[i].split("x");
					coefficients[i] = Double.parseDouble(monomial[1]);
					exponents[i] = Integer.parseInt(monomial[0]);
				}
			}
		}

	}

	private int find(int element, int[] ele_arr)
	{
		for (int i=0; i<ele_arr.length; i++)
		{
			if (element==ele_arr[i]) return i;
		}
		return -1;
	}
	
	public Polynomial add(Polynomial another_p)
	{
		double[] new_coe;
		int[] new_exp = new int[this.exponents.length + another_p.exponents.length];
		int count = 0;

		for (int i = 0; i < new_exp.length; i++) new_exp[i]=-1;	//initialize the array so all the entry are -1
		
		for (int current_exp : this.exponents) 
		{
			if (-1 == find(current_exp, new_exp))	//if doesn't find the current exp then add to the array
			{
				new_exp[count]=current_exp;
				count++;
			}
		}
		for (int current_exp : another_p.exponents) 
		{
			if (-1 == find(current_exp, new_exp))
			{
				new_exp[count]=current_exp;
				count++;
			}
		}	//go through each exponent array and collect all the exponent needed for the new poly

		new_exp=Arrays.copyOfRange(new_exp, 0, count);
		new_coe=new double[count];

		for (int i = 0; i < this.exponents.length; i++) new_coe[find(this.exponents[i], new_exp)] += this.coefficients[i];
		for (int i = 0; i < another_p.exponents.length; i++) new_coe[find(another_p.exponents[i], new_exp)] += another_p.coefficients[i];

		Polynomial result_poly=new Polynomial(new_coe, new_exp);

		return result_poly;
	}

	public double evaluate(double x_value)
	{
		double result=0;
		for (int i=0; i<this.coefficients.length; i++)
		{
			result+=Math.pow(x_value, this.exponents[i])*this.coefficients[i];
		}
		return result;
	}

	public boolean hasRoot(double x_value)
	{
		return evaluate(x_value)==0;
	}

	public Polynomial multiply(Polynomial another_p)
	{
		Polynomial result = new Polynomial();
		for (int i = 0; i < this.coefficients.length; i++) {
			for (int j = 0; j < another_p.coefficients.length; j++) {
				int[] temp_exp = new int[]{this.exponents[i] + another_p.exponents[j]};
				double[] temp_coe = new double[]{this.coefficients[i] * another_p.coefficients[j]};
				Polynomial temp_poly = new Polynomial(temp_coe, temp_exp);
				result =result.add(temp_poly);
			}
		}
		return result;
	}

	public void saveToFile(String targetFile)
	{
		String info="";
		try (FileWriter myWriter = new FileWriter(targetFile))
		{
			for (int i = 0; i < this.exponents.length; i++) 
			{
				if (0 == this.exponents[i])
				{
					info += this.coefficients[i];
				}
				else
				{
					info += this.coefficients[i] + "x" + this.exponents[i];
				}

				if (i!=this.exponents.length-1) info += "+";
			}
			myWriter.write(info + "\n");
		}
		catch (IOException error)
		{
			return;
		}
	}
}
