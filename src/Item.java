
import java.util.Date;

public class Item 
{
   private int itemNumber;
   private String itemDescription;
   private double price;
   private int quantity;
   private int threshold;
   private String date;
   public Item()
   {
	   itemNumber = 0;
	   itemDescription ="";
	   price = 0;
	   quantity = 0;
	   threshold = 0;
	   date = "";
	   
   }
   public void setItemNumber(int newNumber)
   {
	   itemNumber = newNumber;
   }
   public void setItemDescription(String newDescription)
   {
	   itemDescription = newDescription;
   }
   public void setPrice(Double newPrice)
   {
	   price = newPrice;
   }
   public void setQuantity(int newQuantity)
   {
	   quantity = newQuantity;
   }
   
   public void setThreshold(int newThreshold)
   {
	   threshold = newThreshold;
   }
   public void setDate(String d)
   {
	   date = d;
   }
   public int getItemNumber(){return itemNumber;}
   public String getItemDescription(){return itemDescription;}
   public double getPrice(){return price;}
   public int getQuantity(){return quantity;}
   public int getThreshold(){return threshold;}
   public String getDate(){return date;}
   
   
}
