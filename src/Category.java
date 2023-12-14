import java.util.HashMap;
import java.util.Map;

class Category 
{
	//Category HAS-A name
    private String name;
    
    //Category has a hashmap of products
    private Map<String, Product> products;

    //Constructor for category
    public Category(String name) 
    {
        this.name = name;
        this.products = new HashMap<>();
    }

    //Getter for category name
    public String getName() {
        return name;
    }

    //Getter for products in category
    public Map<String, Product> getProducts() 
    {
        return products;
    }

    //Method to add products into category's hashmap of products
    public void addProduct(Product productName) 
    {
        products.put(productName.getName(), productName);
    }
}
