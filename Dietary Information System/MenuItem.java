public class MenuItem implements Comparable<MenuItem>
{
	private String type;
	private String name;
	private int values[]=new int [5];

	public MenuItem (String t,String n,int v[])
	{
		type=t;
		name=n;
		values=v;
	}

	public String toString()
	{
		String s=type+"\t"+name+"\t\t";
		if(name.equals("Egg on a Bagel  Bacon Cheese"))
			s+="\t";
		if(name.equals("Meat Lasagna"))
			s+="\t\t";
		for(int i=0;i<5;i++)
			s+=values[i]+"\t";
		s+="\n";
		return s;
	}

	public String getType()
	{
		return type;
	}

	public String getName()
	{
		return name;
	}

	public int[] getValue()
	{
		return values;
	}

	public int compareTo(MenuItem m)
	{
		return type.compareTo(m.type);
	}
}

