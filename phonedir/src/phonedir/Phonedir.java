package phonedir;

import java.io.*;   
import java.util.*;   

public class Phonedir{
    
    private static LinkedList<String> lastN = new LinkedList<>();
    private static LinkedList<String> firstN = new LinkedList<>();
    private static LinkedList<String> phoneN = new LinkedList<>();
        
                    
    private static String lastName;
    private static String firstName;
    private static String phoneNumber;
    private static phonedirNode next;
    private static phonedirNode head;
    private static int indexfirst;
    private static Object scanner;
    private static Object words;
    private static Object phonedir;
        
    public  void Phonedir(String last, String first, String number){
        lastName = last;
        firstName = first;
        phoneNumber = number;
    }//end constructor
        
    public void setLastName(String last){
        lastName = last;
    }
    
    public String getLastName(){
        return lastName;
    }
    
    public void setFirstName(String first){
        firstName = first;
    }
    
    public String getFirstName(){
        return firstName;
    }
    
    public void setPhoneNumber(String number){
        phoneNumber = number;
    }
    
    public String getPhoneNumber(){
        return phoneNumber;
    }
    
    public void setNext(phonedirNode pdn) {
		next = pdn;
	}
	
    public void setHead(phonedirNode pdn) {
		head = pdn;
    }
    
    public phonedirNode getNext(){
        return next;
    }
    
    public phonedirNode getHead(){
        return head;
    }
    
    public String getName(){
        return firstName + "" + lastName;
    }
    
    public String toString(){
        return firstName + "     " + lastName + "      " + phoneNumber;
    }
    
    /*The purpose of the this method is to prompt and recieve input from the 
     * user on which file to read for the phone directory. It returns the inputted file 
     * name in the form of a string fileName.
     */                
    public static String getFile()
    {
        Scanner scanner = new Scanner(System.in);
    	System.out.print("Enter the name of the file to read from: ");
    	String fileName = scanner.nextLine();
    	return fileName;
    }
    
    
    /* Purpose:
     * The purpose of this method is to show all of the records in the phone
     * directory after the user selects the option a.
     */
    private static void showList(){
        
        phonedirList list = new phonedirList();
        try{            
        for (int i = 0; i < firstN.size(); i++ ){
            phonedirNode node = new phonedirNode();
		node.setInfo(firstN.get(i), lastN.get(i), phoneN.get(i));
		list.insert2(node);
        }
         list.print();
            System.out.println();
        }catch(NoSuchElementException e){
            System.out.println("No Current Record");
        }
    }  
     
    /*Purpose:
     * The purpose of this method is to keep the directory in alphabetical order
     * based on last name. The last name of a new entry is compared with the 
     * last name of each entry already in the last name LinkedList and entered 
     * based on the index of the correct position.
     */
    private static void currentPosition(String lastName, String firstName, String phoneNumber){
        int index;
	for(int i = 0; i < lastN.size(); i++){
	    String last = lastN.toString();
	    String currName = (lastN.get(i)).toString();
	    if(last.compareTo(currName) < 0){
		firstN.add(i, firstName);
		lastN.add(i, lastName);
		phoneN.add(i, phoneNumber);
		index = lastN.lastIndexOf(lastN);
		break;
	    }else if(last.compareTo(currName) == 0){
		
                for(int j = 0; j < firstN.size(); j++){
		    String f = firstN.toString();
		    String curName2 = (firstN.get(j)).toString();
		    if(f.compareTo(curName2) < 0){
		        firstN.add(j, firstName);
			lastN.add(j, lastName);
			phoneN.add(j, phoneNumber);
			index = lastN.lastIndexOf(lastN);
			break;
		    }
		}
	    }else if((last.compareTo(currName) > 0) && (i == (lastN.size() - 1))){
	        firstN.add(firstName);
                lastN.add(lastName);
                phoneN.add(phoneNumber);
                index = lastN.lastIndexOf(lastN);
	    }
	}
    } 
     
    /* Purpose:
     * The purpose of this method is to prompt the user with the menu of their
     * options for the phone directory. This method will read the input of the 
     * user selected from the menu.
     */    
    private static char printMenu(){ 
        
           Scanner search = new Scanner(System.in);
         System.out.println("a : Show all the records\n");
         System.out.println("d : Delete the current record\n");
         System.out.println("f : Change the first name in the current record\n");
         System.out.println("l : Change the last name in the current record\n");
         System.out.println("n : Add a new record\n");
         System.out.println("p : Change the phone number in the current record\n");
         System.out.println("q : Quit\n");
         System.out.println("s : Select a record from the list to become current record\n");
         System.out.print("Enter a command from the list above (q to quit):");
         
         String command = search.next();
         char firstChar = command.charAt(0);
         return firstChar;         
    }
    
     /* Purpose:
     * The purpose of this method is to delete the current record in the phone 
     * directory. The name will be removed from the LinkedList.
     */    
    private static void deleteRecord(){
       
        try{
           System.out.println("Deleted: " + firstN.getLast() + "  " + lastN.getLast() + " " + phoneN.getLast());
           firstN.removeLast();
           lastN.removeLast();
           phoneN.removeLast();
           errorStatement();
       }catch(IndexOutOfBoundsException e){
           errorStatement();
       }
    }
    
    /* Purpose:
     * The purpose of this method is to change the first name of the current
     * phone directory after the user selects the option f. The new first name
     * will be stored in the LinkedList.
     */
    private static void changeFirst(){
             Scanner search = new Scanner(System.in);                      
        try{        
             System.out.println("Enter New First Name: ");
             String first = search.next();
             int findLast = firstN.lastIndexOf(firstN.getLast());
             firstN.set(findLast, first);
             currentRecord();
        }catch(NoSuchElementException e){
            errorStatement();
        }                      
        
    }
    
    /* Purpose:
     * The purpose of this method is to change the last name of the current
     * phone directory after the user selects the option l. The new last name
     * will be stored in the LinkedList.
     */
    private static void changeLast(){
            Scanner search = new Scanner(System.in);        
        try{            
            System.out.println("Enter New Last Name: ");
            String last = search.next();
            int findLast = lastN.lastIndexOf(lastN.getLast());
            lastN.set(findLast, last);
            currentRecord();
        }catch(NoSuchElementException e){
            errorStatement();
        }
    }
    
    /* Purpose:
     * The purpose of this method is to add a a new record to the phone directory.
     * The user will be prompted with instructions to enter data. The data will then be 
     * entered, then stored in the LinkedList. Also it will be the current record.
     */
    private static void addRecord(){
        
            Scanner search = new Scanner(System.in);
        System.out.print("Enter a First Name:");
        String first = search.next();
        System.out.print("\nEnter a Last Name:");
        String last = search.next();
        System.out.print("\nEnter Phone Number (ex. XXX-XXX-XXXX):");
        String number = search.next();
        firstN.add(first);
        lastN.add(last);
        phoneN.add(number);
        currentRecord();               
    }
    
    /* Purpose:
     * The purpose of this method is to change the phone number of the current
     * phone directory after the user selects the option p. The new phone number
     * will be stored in the LinkedList.
     */
    private static void changeNumber(){
                Scanner search = new Scanner(System.in);
            try{
                System.out.println("Enter New Last Name: ");
                String change = search.next();
                int findLast = lastN.lastIndexOf(lastN.getLast());
                lastN.set(findLast, change);
                currentRecord();
            }catch(IndexOutOfBoundsException e){
            errorStatement(); 
            }
    }
    
    /* Purpose:
     * The purpose of this method is for the user to select a record from the
     * phone directory to make it the current record. 
     */
    private static void selectRecord(){            
            
            System.out.println("Enter a First Name: ");
            String first = scanner.toString();
            System.out.println("Enter a Last Name: ");
            String last = scanner.toString();
            
            if(lastN.contains(last) != true){
                System.out.println("This Name is not in the Records");
            }
            else{
                int indextfirst = firstN.indexOf(first);
                int indexlast = lastN.indexOf(last);                
                firstN.addLast(firstN.get(indexfirst));
                lastN.addLast(lastN.get(indexlast));
                firstN.remove(indexfirst);
                lastN.remove(indexlast);                
                currentRecord();
            }            
    }
    
    /* Purpose:
     * The purpose of this method is to display the current record in the 
     * phone directory.  
     */
    private static void currentRecord(){
        try{
        System.out.println("Current Record is:" + firstN.getLast() + "" + lastN.getLast() + "" + phoneN.getLast());
        }catch(NoSuchElementException e){
            errorStatement();            
        }        
    }
    
    private static void errorStatement(){
        System.out.println("\n Current entry : Add New Entry");
    }

    /*Purpose:
     * The purpose of this method is to exit the program and store the data
     * within the LinkedList in the file.
     */
    private static void quit(){
            System.exit(1);
    }
    
    
    public static void main(String args[])throws IOException{
       
       System.out.println("C:/phonedir[enter]");
       System.out.println("A program to keep a Phone Directory");
       getFile();
       
       
        while(true){
            char menu = printMenu();
            switch (menu){
                case 'a':   showList();
                            break;
                case 'd':   deleteRecord();
                            break;
                case 'f':   changeFirst();
                            break;
                case 'l':   changeLast();
                            break;
                case 'n':   addRecord();
                            break;
                case 'p':   changeNumber();
                            break;
                case 'q':   quit();
                            break;
                case 's':   selectRecord();
                            break;
                default: System.out.println("Illegal Command");                    
            }
        }           
         
    }         

    private static class phondedirNode {

        public phondedirNode() {
        }
    }
}
         

