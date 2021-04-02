import java.io.*; 

public class FileIO {

        private File outputFile = new File("OrderForm.txt"); 


        public String[] inputFetching() throws IOException
        {
            System.out.println();
            System.out.println();
            System.out.println("Hello, welcome to the Supply Chain Management (SCM) application");
            System.out.println("To begin the program, please enter your order request in the following format:");
            System.out.println("         Furniture type Furniture Category, quantity");
            System.out.println("eg:      Executive chair, 1       or        Standing desk, 3");
            System.out.println();
            System.out.println("Please ensure that the format is followed strictly in the specified order, to ensure"); 
            System.out.println("that your request is processed correctly. When you are satisfied with your input,");
            System.out.println("please press enter.");
            System.out.println();
            System.out.println();
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); 
            String input = reader.readLine(); 
            String [] order = input.split("\\s");
            order[1] = order[1].substring(0, order[1].length()-1) ; 
            for (int i=0; i < 3; i++)
                System.out.println(order[i]);
            System.out.println();
            return order;
        }

        public void FormattedFormOutput(Furniture[] filledOrder, String[] clientOrder)
        {
            boolean orderFilled = true; 
            for (int i=0; i < filledOrder.length; i++)
            {
                orderFilled = (!filledOrder[i].isFilled())? false : true;
            }
            if (orderFilled)
            {
                FileWriter fw = FileWriter(this.outputFile, true);
                try( BufferedWriter writer = new BufferedWriter(fw)){
                    writer.write("Furniture Order Form\n\nFaculty Name:\nContact:\nDate\n\nOrigional Request: " 
                                    + clientOrder[0] + " " + clientOrder[1] + ", " 
                                            + clientOrder[2] + "\n\nItems Ordered\n" );
                    int totalPrice=0; 
                    for (int i=0; i < filledOrder.length; i++)
                    {
                        writer.write("ID: " + filledOrder[i].getID() + "\n");
                        totalPrice += filledOrder[i].getPrice(); 
                    }
                    writer.write("\n");
                    writer.write("Total Price: " + totalPrice);
                    writer.flush();
                    writer.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
            else
            {
                // prints manufactuers to the screen or something. need to get manufacutrers and pass in
            }
        }
}