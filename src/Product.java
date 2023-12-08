public class Product 
{
	private String name;
	private String description;
	private int quantity;
	
	public Product(String name, int quantity)
	{
		this.name = name;
		this.quantity = quantity;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public int getQuantity()
	{
		return quantity;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setQuantity(int newQuantity)
	{
		this.quantity = newQuantity;
	}
	
	public void setDescription(String description)
	{
		this.description = description;
	}
}


