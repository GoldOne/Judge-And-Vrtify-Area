//@Author Yi Jin ID:s0273749
public class MenuItem 
{  
	final private static String food[]={"Caprese Sandwich",
					    "Chicken Pomodoro Sandwich",
				            "Egg on a Bagel",
					    "Macaroni & Cheese",
					    "Meat Lasagna",
					    "Roasted Angus Steak"};
	final private static String drink[]={"Caffe Latte",
					     "Orange Juice",
					     "Root Beer\t",
					     "Sierra Mist\t",
                                             "Sweet Tea\t",
                                             "Vanilla Latte"};
	final private static int fnv[][]={{550,13,30,860,3},//Food Nutrition Values
					  {680,13,105,1410,4},
					  {510,6,350,880,2},
					  {710,24,125,1560,3},
					  {470,11,100,1080,5},
					  {630,4,80,1100,3}};
	final private static int dnv[][]={{140,4,20,105,0},//Beverage Nutrition Values
					  {220,0,0,5,1},
				          {300,0,0,45,0},
					  {300,0,0,60,0},
					  {250,0,0,20,0},
					  {240,4,20,105,0}};
	private static MenuItem instance;  
	private MenuItem (){}
	public static synchronized MenuItem getInstance() 
	{  
		if(instance==null)   
			instance=new MenuItem();  
		 
		return instance;  
	}  
	
	public static String[] getFood()
	{
		return food;
	}
	public static String[] getDrink()
	{
		return drink;
	}
	public static int[] getFoodValue(int fn)
	{
		return fnv[fn];
	}
	public static int[] getDrinkValue(int dn)
	{
		return dnv[dn];
	}
	public static String displayFood(int fn)
	{
		String result;
		if(fn!=1)
			result=food[fn]+"\t";
		else
			result=food[fn];

		for(int i=0;i<5;i++)
			result+=fnv[fn][i]+"\t";
		return result;
	}
	public static String displayDrink(int dn)
	{
		String result="     "+drink[dn]+"\t";

		for(int i=0;i<5;i++)
			result+=dnv[dn][i]+"\t";
		return result;
	}
}  

