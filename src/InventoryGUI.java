import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;



public class InventoryGUI extends JFrame
{
    public static final int TEXTAREA_ROWS = 8;
    public static final int TEXTAREA_COLUMNS = 20;
    private static Database db = new Database();
    private static Date date = new Date();
    private static SimpleDateFormat ft = new SimpleDateFormat ("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
    public InventoryGUI ()
    {	
    	Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screen = (Dimension) kit.getScreenSize();
        int width = screen.width;
        int height = screen.height;
        setSize(width/2,height/2);
    	final JTextField itemNumber = new JTextField();
    	final JTextField itemName = new JTextField();
    	final JTextField price = new JTextField();
    	final JTextField quantity = new JTextField();
    	final JTextField threshold = new JTextField();
    	JPanel northPanel = new JPanel();
    	northPanel.setLayout(new GridLayout(5,5));
    	northPanel.add(new JLabel("Item Number ",SwingConstants.RIGHT));
    	northPanel.add(itemNumber);
    	northPanel.add(new JLabel("Item Name ",SwingConstants.RIGHT));
    	northPanel.add(itemName);
    	northPanel.add(new JLabel("Price ",SwingConstants.RIGHT));
    	northPanel.add(price);
    	northPanel.add(new JLabel("Quantity ",SwingConstants.RIGHT));
    	northPanel.add(quantity);
    	northPanel.add(new JLabel("Threshold ",SwingConstants.RIGHT));
    	northPanel.add(threshold);
    	add(northPanel,BorderLayout.NORTH);
    	final JTextArea ta = new JTextArea(TEXTAREA_ROWS,TEXTAREA_COLUMNS);
    	
    	JScrollPane scrollPane = new JScrollPane(ta);
    	add(scrollPane,BorderLayout.CENTER);
    	//add button to append text into the text area
    	JPanel southPanel = new JPanel();
    	JButton addButton = new JButton("Add");
    	southPanel.add(addButton);
    	addButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//db.containKey(Integer.parseInt(itemNumber.getText())) &&
                String strItemNumber = itemNumber.getText();
                String strItemName = itemName.getText();
                String strQuantity = quantity.getText();
                String strThreshold = threshold.getText();
                String strPrice = price.getText();
                if(!strItemNumber.equals("") & !strItemName.equals("")
                		&!strQuantity.equals("") & !strThreshold.equals("")&!strPrice.equals(""))
                {
                	if(!db.containKey(Integer.parseInt(strItemNumber)) & !db.containItemName(strItemName))
    				{
    					db.addItem(Integer.parseInt(strItemNumber), strItemName, 
    							Double.parseDouble(strPrice),
    							Integer.parseInt(strQuantity),
    							Integer.parseInt(strThreshold),ft.format(date));
    					ta.setText("One product has been added into the Database");
    				}
    				else
    				{ta.setText("Key or value exist in the table, can't add into database");}
                }
                else
                {
                	ta.setText("One or more fields are empty!");
                }
		
			}
    		
    	});
    	add(southPanel,BorderLayout.SOUTH);
    	
    	
    }
    public static void writeOutputFile(String outputFile)
    {
    	  String header = "Item Number | Item Name | Price | Quantity | Threshold | Timestamp";
    	try {
            BufferedWriter out = new BufferedWriter(new FileWriter(outputFile));
            Iterator<String> itr = db.returnData().iterator();
            String data ="";
    		while(itr.hasNext())
    		{
    			data += itr.next();
    		}
    		out.write(header);
    		out.write(data);
            out.close();
           
         }
         catch (IOException e) {
         }
      }
    
    public static void populateDatabaseWithDataFromInputFile(String inputFile)
	{
		try
		{
			File xmlFile = new File(inputFile);
			DocumentBuilderFactory dbf =  DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
			Document doc = documentBuilder.parse(xmlFile);
			doc.getDocumentElement().normalize();
			//System.out.println("Root Element :"+doc.getDocumentElement().getNodeName());
			NodeList nodeList = doc.getElementsByTagName("Item");
			//System.out.println("--------------");
			for(int i = 0; i<nodeList.getLength();i++)
			{
				Node node = nodeList.item(i);
				//System.out.println("\nCurrent Element :"+node.getNodeName());
				if(node.getNodeType() == Node.ELEMENT_NODE)
				{
					Element element = (Element) node;
					String id = element.getAttribute("id");
					int intID = Integer.parseInt(id);
					String name = element.getElementsByTagName("name").item(0).getTextContent();
					String price = element.getElementsByTagName("price").item(0).getTextContent();
					double doublePrice = Double.parseDouble(price);
					String quantity = element.getElementsByTagName("quantity").item(0).getTextContent();
					int intQuantity = Integer.parseInt(quantity);
					String threshold = element.getElementsByTagName("threshold").item(0).getTextContent();
					int intThreshold = Integer.parseInt(threshold);
					db.addItem(intID, name, doublePrice,intQuantity,intThreshold,ft.format(date));
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
}

