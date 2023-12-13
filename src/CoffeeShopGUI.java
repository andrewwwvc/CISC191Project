import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.*;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicTabbedPaneUI;

public class CoffeeShopGUI extends JFrame
{
	 CoffeeShop coffeeShop;
	 private JFrame frame;
	 private JTabbedPane tabbedPane;

	 public CoffeeShopGUI(CoffeeShop coffeeShop)
	 {
		 //Sets coffeeShop as given model
		 this.coffeeShop = coffeeShop;
		 
		 frame = new JFrame("Coffee Shop Inventory");
	     frame.setLayout(new BorderLayout());
	     frame.setSize(1400, 1200);
	     
	     Font titleFont = new Font("Sans Serif", Font.BOLD, 40);
	     
	     JPanel welcomePanel = new JPanel();
	     Box welcomeBox = Box.createVerticalBox();
	     welcomePanel.add(welcomeBox);

	     JTextArea welcome = new JTextArea("Welcome to your Coffee Shop Inventory!");
	     welcome.setFont(titleFont);
	     welcome.setForeground(new Color(153,102,0));
	     welcome.setAlignmentX(Component.CENTER_ALIGNMENT);
	     welcomeBox.add(welcome);

	     frame.add(welcomePanel, BorderLayout.NORTH);

	     
	     

	     tabbedPane = new JTabbedPane();
	     tabbedPane.setUI(new BasicTabbedPaneUI()
	     {
	    	 @Override
	    	 protected int calculateTabWidth(int tabPlacement, int tabIndex, FontMetrics metrics)
	    	 {
	    		 return 200;
	    	 } 
	     });

	     // Create a tab for each category
	     for (Category category : coffeeShop.getCategories().values()) 
	     {
	    	 JPanel categoryPanel = createCategoryPanel(category);
	    	 tabbedPane.addTab(category.getName(), categoryPanel);
	     }
	     
	     frame.add(tabbedPane, BorderLayout.CENTER);
	     
	     JPanel lowQuantityPanel = new JPanel();
	     JButton checkLowQuantityButton = new JButton("Check Low Quantity");
	        checkLowQuantityButton.addActionListener(new ActionListener() 
	        {
	            @Override
	            public void actionPerformed(ActionEvent e) 
	            {
	                checkLowQuantity();
	            }
	        });

	     lowQuantityPanel.add(checkLowQuantityButton);
	     frame.add(lowQuantityPanel, BorderLayout.SOUTH); 

	     updateInventoryText();
	    
	     
	     frame.setVisible(true);
	     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	     
	 }

	 
	  private JPanel createCategoryPanel(Category category) 
	  {
	        JPanel panel = new JPanel(new GridLayout(0, 4));
	        
	  
	        for (Product product : category.getProducts().values()) 
	        {
	        	Font productFont = new Font("Comic Sans", Font.PLAIN, 20);
	        	
	            JLabel nameLabel = new JLabel(product.getName());
	            nameLabel.setFont(productFont);
	            
	            JLabel quantityLabel = new JLabel(Integer.toString(product.getQuantity()));
	            quantityLabel.setFont(productFont);

                JTextField quantityTextField = new JTextField("Qty");
                JButton addButton = new JButton("Add");
                addButton.setBackground(Color.GREEN);
                
                JButton remButton = new JButton("Remove");
                remButton.setBackground(Color.RED);
	            
	            quantityTextField.addFocusListener(new FocusAdapter() 
	            {
	                @Override
	                public void focusGained(FocusEvent e) 
	                {
	                    if ("Qty".equals(quantityTextField.getText())) 
	                    {
	                        quantityTextField.setText("");
	                    }
	                }
	            });
	            
	            

	            addButton.addActionListener(new ActionListener() 
	            {
	                @Override
	                public void actionPerformed(ActionEvent e) 
	                {
	                    try 
	                    {
	                        int quantityToAdd = Integer.parseInt(quantityTextField.getText());
	                        coffeeShop.addQuantity(product.getName(), quantityToAdd);
	                        updateInventoryText();
	                    } catch (NumberFormatException ex) 
	                    {
	                        JOptionPane.showMessageDialog(frame, "Please enter a valid number.");
	                    }
	                }
	            });

	            remButton.addActionListener(new ActionListener() 
	            {
	                @Override
	                public void actionPerformed(ActionEvent e) 
	                {
	                    try {
	                        int quantityToRemove = Integer.parseInt(quantityTextField.getText());
	                        coffeeShop.reduceQuantity(product.getName(), quantityToRemove);
	                        updateInventoryText();
	                    } catch (NumberFormatException ex) 
	                    {
	                        JOptionPane.showMessageDialog(frame, "Please enter a valid number.");
	                    }
	                }
	            });

	            panel.add(nameLabel);
	            panel.add(quantityLabel);
	            panel.add(quantityTextField);
	            panel.add(addButton);
	            panel.add(remButton);
	        }

	        return panel;
	    }
	  
	  public void checkLowQuantity() 
	  {
	        StringBuilder lowQuantityMessage = new StringBuilder("Low Quantity Alert:\n");

	        for (Category category : coffeeShop.getCategories().values()) 
	        {
	            for (Product product : category.getProducts().values()) 
	            {
	                if (product.getQuantity() < 5) 
	                {
	                    lowQuantityMessage.append(product.getName())
	                            .append(" is running low (Quantity: ")
	                            .append(product.getQuantity())
	                            .append(")\n");
	                }
	            }
	        }

	        if (lowQuantityMessage.length() > "Low Quantity Alert:\n".length()) 
	        {
	            JOptionPane.showMessageDialog(frame, lowQuantityMessage.toString());
	        } 
	        else 
	        {
	            JOptionPane.showMessageDialog(frame, "All products have sufficient quantity.");
	        }
	    }

	    public void updateInventoryText() 
	    {
	    	Font productFont = new Font("Comic Sans", Font.PLAIN, 20);
	    	Font headerFont = new Font("Comic Sans", Font.BOLD, 36);
	    	
	    	
	    	for (int i = 0; i < tabbedPane.getTabCount(); i++) 
	    	{
	            Category category = coffeeShop.getCategories().get(tabbedPane.getTitleAt(i));
	            JPanel categoryPanel = (JPanel) tabbedPane.getComponentAt(i);

	            // Clear existing components in the panel
	            categoryPanel.removeAll();
	            
	            // Create a new layout for the panel
	            categoryPanel.setLayout(new GridLayout(category.getProducts().size() + 1, 5));

	            // Add labels for column headers
	            JLabel productHeader = new JLabel("Product");
	            productHeader.setFont(headerFont);
	            categoryPanel.add(productHeader);
	            
	            JLabel qtyHeader = new JLabel("Quantity");
	            qtyHeader.setFont(headerFont);
	            categoryPanel.add(qtyHeader);
	            
	            JLabel entrQtyHeader = new JLabel("Enter Quantity");
	            entrQtyHeader.setFont(headerFont);
	            categoryPanel.add(entrQtyHeader);
	            
	            // Empty space for formatting
	            categoryPanel.add(new JLabel("")); 
	            categoryPanel.add(new JLabel(""));

	            // Iterate through the products of the category and add them to the panel
	            for (Product product : category.getProducts().values()) 
	            {
		        	
		            JLabel nameLabel = new JLabel(product.getName());
		            nameLabel.setFont(productFont);
		            
		            JLabel quantityLabel = new JLabel(Integer.toString(product.getQuantity()));
		            quantityLabel.setFont(productFont);

	                JTextField quantityTextField = new JTextField();
	                JButton addButton = new JButton("Add");
	                addButton.setBackground(new Color(102,255,102));
	                
	                JButton remButton = new JButton("Remove");
	                remButton.setBackground(new Color(255,102,102));

	                addButton.addActionListener(new ActionListener() 
	                {
	                    @Override
	                    public void actionPerformed(ActionEvent e) 
	                    {
	                        try 
	                        {
	                            int quantityToAdd = Integer.parseInt(quantityTextField.getText());
	                            coffeeShop.addQuantity(product.getName(), quantityToAdd);
	                            updateInventoryText();
	                        } 
	                        catch (NumberFormatException ex)
	                        {
	                            JOptionPane.showMessageDialog(frame, "Please enter a valid number.");
	                        }
	                    }
	                });

	                remButton.addActionListener(new ActionListener() 
	                {
	                    @Override
	                    public void actionPerformed(ActionEvent e) 
	                    {
	                    	try 
	                    	{
	                            int quantityToRemove = Integer.parseInt(quantityTextField.getText());
	                            if (quantityToRemove > product.getQuantity()) 
	                            {
	                                JOptionPane.showMessageDialog(frame, "Error: Quantity to remove exceeds current quantity.");
	                            } 
	                            else 
	                            {
	                                coffeeShop.reduceQuantity(product.getName(), quantityToRemove);
	                                updateInventoryText();
	                            }
	                        } 
	                        catch (NumberFormatException ex) 
	                        {
	                            JOptionPane.showMessageDialog(frame, "Please enter a valid number.");
	                        }
	                    }
	                });

	                categoryPanel.add(nameLabel);
	                categoryPanel.add(quantityLabel);
	                categoryPanel.add(quantityTextField);
	                categoryPanel.add(addButton);
	                categoryPanel.add(remButton);
	            }
	            
	        }

	 
	    	
	    	
	    	
	        // Repaint the frame to reflect the changes
	        frame.revalidate();
	        frame.repaint();
	    }

	    
	    
	    public static void main(String[] args) {
	        CoffeeShop coffeeShop = new CoffeeShop();
	        CoffeeShopGUI coffeeShopGUI = new CoffeeShopGUI(coffeeShop);

	    }
	}
