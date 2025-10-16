import java.util.List;

abstract public class SpecialNumber
{
	public abstract SpecialNumber add(SpecialNumber another_num) throws Lab3Exception;
	public abstract SpecialNumber divideByInt(int input_int) throws Lab3Exception;
	/**
	* This method computes and returns the average of a sequence of SpecialNumber
	* @param num_list the given sequence of SpecialNumber to use to compute the average
	* @return The average of the given sequence of SpecialNumber
	*/
	public static SpecialNumber computeAverage(List<SpecialNumber> num_list) throws Lab3Exception
	{
		if (num_list == null || num_list.size() == 0) throw new Lab3Exception("List cannot be empty");
		if (num_list.size() == 1) return num_list.get(0);
		SpecialNumber avg = num_list.get(0);
		for (int i = 1 ; i < num_list.size() ; i++)
		{
			avg = avg.add(num_list.get(i));
		}
		avg = avg.divideByInt(num_list.size());
		
		return avg;
	}
}