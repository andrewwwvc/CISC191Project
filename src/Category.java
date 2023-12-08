import java.util.HashMap;
import java.util.Map;

class Category 
{
    private String name;
    private Map<String, Product> products;

    public Category(String name) 
    {
        this.name = name;
        this.products = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public Map<String, Product> getProducts() 
    {
        return products;
    }

    public void addProduct(String productName, int quantity) 
    {
        products.put(productName, new Product(productName, quantity));
    }
}
