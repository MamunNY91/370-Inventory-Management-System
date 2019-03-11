import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

public class Driver {
    public static String inputFile = "";
    public static String i = "/Users/abdullahamamun/Desktop/Java/TextComponentFrame2/src/input.xml";
	public static void main(String[] args) 
	{
        String outputFile = "";
		for (int i=0 ; i < args.length ; i++) 
		{
            if (args[i].startsWith("-")) 
            {
            	String argument = args[i].toLowerCase();
            	if(argument.equalsIgnoreCase("-i"))
            	{
            		inputFile = args[++i];
            		if(inputFile.equalsIgnoreCase("-o"))
            		{
            			chooseInputFile();
            			
            			if(!inputFile.equalsIgnoreCase(""))
            			{
            				InventoryGUI.populateDatabaseWithDataFromInputFile(inputFile);
            				InventoryGUI.writeOutputFile("/Users/abdullahamamun/Desktop/output.txt");
            				openMainFrame();
            			}
            		}
            	 }         	
            }

	     }
		
		/*
		InventoryGUI.populateDatabaseWithDataFromInputFile(inputFile);
		InventoryGUI.writeOutputFile("/Users/abdullahamamun/Desktop/output.txt");
		openMainFrame();
		*/
		
 }
	
	public static void openMainFrame()
	{
		JFrame f = new InventoryGUI();
		f.setTitle("Inventory");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
	public static void chooseInputFile()
	{
		JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		j.setAcceptAllFileFilterUsed(false); 
		j.setDialogTitle("Select a .xml file");
		 j.setAcceptAllFileFilterUsed(false); 
            FileNameExtensionFilter restrict = new FileNameExtensionFilter("Only .xml files", "xml"); 
            j.addChoosableFileFilter(restrict); 

            int r = j.showOpenDialog(null); 

            if (r == JFileChooser.APPROVE_OPTION) { 
           
                inputFile = j.getSelectedFile().getAbsolutePath(); 
                System.out.println(inputFile);
            }
            else
                System.out.println("the user cancelled the operation"); 
	}
}
	