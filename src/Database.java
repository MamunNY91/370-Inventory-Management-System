import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;

public class Database 
{
   private Hashtable<Integer,Item> inventory;
   private Item item;
   public Database()
   {
	   inventory = new Hashtable<Integer,Item>();
   }
   public void addItem(int id,String name, double price,int quantity,int threshold,String date)
   {
	   item = new Item();
	   item.setItemNumber(id);
	   item.setItemDescription(name);
	   item.setPrice(price);
	   item.setQuantity(quantity);
	   item.setThreshold(threshold);
	   item.setDate(date);
	   inventory.put(item.getItemNumber(), item);
	   
   }
   public boolean containKey(int id)
   {
	   return inventory.containsKey(id);
   }
   public boolean containItemName(String name)
   {
	   Enumeration<Integer> keys;
	   Integer keyNames;
	   keys = inventory.keys();
	   while(keys.hasMoreElements())
	   {
		   keyNames = keys.nextElement();
		   Item i = inventory.get(keyNames);
		   if (name.equalsIgnoreCase(i.getItemDescription()))
		   {
			   return true;
		   }
	    }
	    return false;   
   }
   public ArrayList<String> returnData()
   {
	 
	   ArrayList<String> dataList = new ArrayList<String>();
	   String data = "";
	   Enumeration<Integer> keys;
	   Integer keyNames;
	   keys = inventory.keys();
	   //System.out.println(keys);
	   
	   while(keys.hasMoreElements())
	   {
		   data = "";
		   keyNames = keys.nextElement();
		   Item i = inventory.get(keyNames);
		   data = keyNames +" | "+i.getItemDescription()+" | "+i.getPrice()
		   +" | "+i.getQuantity()+" | "+i.getThreshold()+" | "+i.getDate();
		   dataList.add(data);
		   
	   }
	   return dataList;
   }
}
