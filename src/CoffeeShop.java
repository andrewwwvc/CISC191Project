import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

class CoffeeShop 
{
    private Map<String, Category> categories;
    
    public Category coffeeCategory;
    
    private Category milkCategory;
    
    private Category syrupCategory;
    
    private Category teaCategory;

    public CoffeeShop() 
    {
        categories = new HashMap<>();
        initializeInventory();
    }

    private void initializeInventory() 
    {
        coffeeCategory = new Category("Coffee");
        coffeeCategory.addProduct("Signature Roast", 20);
        coffeeCategory.addProduct("Blonde Roast", 10);
        coffeeCategory.addProduct("Decaf", 5);

        milkCategory = new Category("Milk");
        milkCategory.addProduct("2% Milk", 20);
        milkCategory.addProduct("Whole Milk", 15);
        milkCategory.addProduct("Soy", 8);
        milkCategory.addProduct("Almond", 8);
        milkCategory.addProduct("Coconut", 8);
        milkCategory.addProduct("Oatmilk", 8);

        syrupCategory = new Category("Syrup");
        syrupCategory.addProduct("Vanilla", 15);
        syrupCategory.addProduct("Caramel", 10);
        syrupCategory.addProduct("Mocha", 10);
        syrupCategory.addProduct("White Mocha", 15);
        syrupCategory.addProduct("Hazelnut", 8);
        syrupCategory.addProduct("Sugar-Free Vanilla", 5);
        
        
        teaCategory = new Category("Tea");
        teaCategory.addProduct("Black Tea", 7);
        teaCategory.addProduct("Green Tea", 7);
        teaCategory.addProduct("Passion Tea", 7);

        categories.put("Tea", teaCategory);
        categories.put("Syrup", syrupCategory);
        categories.put("Milk", milkCategory);
        categories.put("Coffee", coffeeCategory);
      
        
    }

    public Map<String, Category> getCategories() 
    {
        return categories;
    }
    
    public Map<String, Product> getCategory(Category name)
    {
    	return name.getProducts();
    }

	public void addQuantity(String productName, int quantityToAdd) 
	{
		for(Category category : categories.values())
		{
			if(category.getProducts().containsKey(productName))
			{
				Product product = category.getProducts().get(productName);
				product.setQuantity(product.getQuantity() + quantityToAdd);
				break;
			}
		}
		
	}

	public void reduceQuantity(String productName,int quantityToRemove)
	{
		for(Category category : categories.values())
		{
			if(category.getProducts().containsKey(productName))
			{
				Product product = category.getProducts().get(productName);
				if(product.getQuantity() >= quantityToRemove)
				{
					product.setQuantity(product.getQuantity() - quantityToRemove);
				}
				else
				{
					System.out.println("Error: Not enough " + productName + " in stock.");
				}
				break;
			}
		}
	}

	
    
}
