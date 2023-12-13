import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

class CoffeeShop 
{
	//CoffeeShop HAS-A hashmap of categories
    private Map<String, Category> categories;
    
    //CoffeeShop HAS-A coffeeCategory
    public Category coffeeCategory;
    
    //CoffeeShop HAS-A milkCategory
    private Category milkCategory;
    
    //CoffeeShop HAS-A syrupCategory
    private Category syrupCategory;
    
    //CoffeeShop HAS-A teaCategory
    private Category teaCategory;

    //Constructor for CoffeeShop
    public CoffeeShop() 
    {
        categories = new HashMap<>();
        initializeInventory();
    }

    //Initializes Inventory including products within each category
    private void initializeInventory() 
    {
    	//Coffee Category
        coffeeCategory = new Category("Coffee");
        coffeeCategory.addProduct("Signature Roast", 20);
        coffeeCategory.addProduct("Blonde Roast", 10);
        coffeeCategory.addProduct("Decaf", 5);

        //Milk Category
        milkCategory = new Category("Milk");
        milkCategory.addProduct("2% Milk", 20);
        milkCategory.addProduct("Whole Milk", 15);
        milkCategory.addProduct("Soy", 8);
        milkCategory.addProduct("Almond", 8);
        milkCategory.addProduct("Coconut", 8);
        milkCategory.addProduct("Oatmilk", 8);

        //Syrup Category
        syrupCategory = new Category("Syrup");
        syrupCategory.addProduct("Vanilla", 15);
        syrupCategory.addProduct("Caramel", 10);
        syrupCategory.addProduct("Mocha", 10);
        syrupCategory.addProduct("White Mocha", 15);
        syrupCategory.addProduct("Hazelnut", 8);
        syrupCategory.addProduct("Sugar-Free Vanilla", 5);
        
        //Tea Category
        teaCategory = new Category("Tea");
        teaCategory.addProduct("Black Tea", 7);
        teaCategory.addProduct("Green Tea", 7);
        teaCategory.addProduct("Passion Tea", 7);
        
        //Puts Categories into hashmap with its products
        categories.put("Tea", teaCategory);
        categories.put("Syrup", syrupCategory);
        categories.put("Milk", milkCategory);
        categories.put("Coffee", coffeeCategory);
      
        
    }

    //Getter for categories
    public Map<String, Category> getCategories() 
    {
        return categories;
    }
    
    //Getter for products in category
    public Map<String, Product> getCategory(Category name)
    {
    	return name.getProducts();
    }

    //Increases product quantity with given quantity
	public void addQuantity(String productName, int quantityToAdd) 
	{
		//Iterates through categories
		for(Category category : categories.values())
		{
			//If product name is found, quantity is added to current quantity
			if(category.getProducts().containsKey(productName))
			{
				//Gets product
				Product product = category.getProducts().get(productName);
				
				//Gets current product quantity
				int oldQuantity = product.getQuantity();
				
				//Adds given quantity to add to current quantity
				product.setQuantity(product.getQuantity() + quantityToAdd);
				
				//Writes change in quantity to file
				logQuantityChange(productName, oldQuantity, product.getQuantity());
				break;
			}
		}
		
	}

	//Decreases product quantity with given amount
	public void reduceQuantity(String productName,int quantityToRemove)
	{
		//Iterates through categories
		for(Category category : categories.values())
		{
			//If product name is found, quantity is removed from current quantity
			if(category.getProducts().containsKey(productName))
			{
				//Gets product
				Product product = category.getProducts().get(productName);
				
				//Gets current product quantity
				int oldQuantity = product.getQuantity();
				
				//Checks if current quantity is greater than quantity wanted to remove
				if(product.getQuantity() >= quantityToRemove)
				{
					product.setQuantity(product.getQuantity() - quantityToRemove);
					logQuantityChange(productName, oldQuantity, product.getQuantity());
				}
				else
				{
					System.out.println("Error: Not enough " + productName + " in stock.");
				}
				break;
			}
		}
	}
	
	
	
	public void logQuantityChange(String productName, int oldQuantity, int newQuantity)
	{
		try (PrintWriter pWriter = new PrintWriter(new FileWriter("quantity_changes.txt", true)))
		{
			pWriter.println(productName +" - Quantity changed from " + oldQuantity + " to " + newQuantity);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	
    
}
