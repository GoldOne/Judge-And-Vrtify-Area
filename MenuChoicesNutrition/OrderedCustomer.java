public class OrderedCustomer extends Customer
{
	private int total[]=new int[5];
	public OrderedCustomer(String n,int num)
	{
		super(n,num);
		for(int i=0;i<5;i++)
			total[i]=0;
	}
	public void Count(int fn,int dn)
	{	
		int farr[]=MenuItem.getFoodValue(fn);
		int darr[]=MenuItem.getDrinkValue(dn);
		for(int i=0;i<5;i++)
			total[i]+=farr[i]+darr[i];
	}
	public String toString()
	{
		String show=super.toString();
		show+="---------------------------------------------------\n\n";
		show+="       Menu Item\tCalorie\tSaturated Fat\t Cholesterol\tSodium\tFiber\n";
		show+=" ---------------------------------------------------------------------------------------\n";
		return show;
	}
	public String show()
	{
		String result="Total \t";
		for(int i=0;i<5;i++)
			result+=total[i]+"\t";
		return result;
	}
	public void clear()
	{
		for(int i=0;i<5;i++)
			total[i]=0;
	}	
	public boolean check()
	{
		for(int i=0;i<5;i++)
			if(total[i]==0)
				return true;
		return false;
	}
}

