//@Author Yi Jin ID:s0273749
public class Customer
{
	protected String name;
	protected int number;
	public Customer(String n,int num)
	{
		name=n;
		number=num;
	}

	public void setName(String n)
	{
		name=n;
	}
	
	public void setNumber(int n)
	{
		number=n;
	}

	public String toString()
	{
		return "\tCustomer Name: "+name+"  Table Number: "+number+"\n";
	}
}
