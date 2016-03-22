package phonedir;

import java.util.*;

public class phonedirList extends LinkedList<String> {

    private static final long serialVersionUID = 1L;
    private phonedirNode head;
	
    public phonedirList(){
        head = null;
    }
	
    public boolean isEmpty() {
        return head == null;
    }
	
    public phonedirNode findInsertionPoint(phonedirNode head, phonedirNode node){
	if (head == null || node.getLastName().compareTo(head.getLastName()) < 0){
			return null;
        }
           
           phonedirNode curr = head;           
           while (curr != null){
            if (curr.getLastName().compareTo(node.getLastName()) == 0){
                    return curr;
            }
              else if(curr.getNext() == null || curr.getNext().getLastName().compareTo(node.getLastName()) > 0){
                  return curr;
              }
            else{ 
		curr = curr.getNext();
            }
           }
            return null;
    }
	
    public void insert2(phonedirNode node){
        phonedirNode newNode = node;
	phonedirNode insertPoint = this.findInsertionPoint(this.head, node);
		
        if (insertPoint == null){
            newNode.setNext(this.head);
            this.head = newNode;
	}else{
            if (insertPoint.getLastName().compareTo(node.getLastName()) == 0) {
                insertPoint.setNext(insertPoint.getNext());
            }else{
                    newNode.setNext(insertPoint.getNext());
                    insertPoint.setNext(newNode);
             }
         }
    }
	
    public void insert(phonedirNode newNode) {
        if (isEmpty()){
		head = newNode;
        }        
         else{
            phonedirNode current = head;
            while(current.getNext() != null){
		current = current.getNext();
		current.setNext(newNode);
            }
	}
    }
	
    public void insertAtFront(phonedirNode newNode){
	newNode.setNext(head);
	head = newNode;
    }
	
    public String remove(String name){
        if (isEmpty()){
            return "Phone directory is empty.";
        }
            phonedirNode current = head;
            phonedirNode previous = null;
            
            if (current.getName().equals(name)) {
		head = current.getNext();
		return "Removed " + current.toString();
            }
            while ((current.getNext() != null) && (!current.getName().equals(name))){
			previous = current;
			current = current.getNext();
            }
            if (current.getName().equals(name)){
		previous.setNext(current.getNext());
		return "Removed " + current.toString();
            }else{
		return ("Sorry. No entry for " + name);
             }
    }	
	
    public void changeFirstName(String change) {
	if (isEmpty()){ 
            System.out.println("No current record");
        }else{
            head.setInfo(change, head.getLastName(), head.getPhoneNumber());
            System.out.println("Current record is: " + head.toString());
	 }
    }
	
    public void changeLastName(String change){
	if (isEmpty()){
            System.out.println("No current record");
        }else{
            head.setInfo(head.getFirstName(), change, head.getPhoneNumber());
            System.out.println("Current record is: " + head.toString());
         }
    }
	
    public void changePhone(String change) {
	if (isEmpty()){
            System.out.println("No current record");
        }else{
            head.setInfo(head.getFirstName(), head.getLastName(), change);
            System.out.println("Current record is: " + head.toString());
	  }
    }
	
    public String getCurrentName() {
	
        phonedirNode current = head;
	return current.getName();
    }
	
    public String search(String firstName, String lastName) {
	if(isEmpty()){
            return "Phone directory is empty";
        }else{
            phonedirNode current = head;
            while (current.getNext() != null && (!current.getFirstName().equals(firstName)) && (!current.getLastName().equals(lastName))){
                    current = current.getNext();
            }        
                if (current.getFirstName().equals(firstName) && current.getLastName().equals(lastName)){
                    head = current;
                    return "Current record is " + head.toString();
                }else{
                    return "No matching record found.";
                 }
         }
    }
	
    public void print(){
		
        phonedirNode current = head;
	System.out.println("First Name                Last Name                      Phone Number");
	System.out.println("-------------             -------------                  ------------------");
	while (current != null){
            System.out.println(current.specialString());
            current = current.getNext();
	}
    }
}
