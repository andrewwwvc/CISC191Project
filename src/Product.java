public abstract class Product 
{
	//Product HAS-A name
	private String name;
	//Product HAS-A quantity
	private int quantity;
	
	//Constructor for product including name and quantity
	public Product(String name, int quantity)
	{
		this.name = name;
		this.quantity = quantity;
	}
	
	//Getter for product name
	public String getName()
	{
		return name;
	}
	
	//Getter for product quantity
	public int getQuantity()
	{
		return quantity;
	}
	
	//Setter for product name
	public void setName(String name)
	{
		this.name = name;
	}
	
	//Setter for product quantity
	public void setQuantity(int newQuantity)
	{
		this.quantity = newQuantity;
	}
	
}


