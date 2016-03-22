package phonedir;
/**
 *
 * @author Hollo-Tips
 */
public class phonedirNode {
    
    	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String name;
	private phonedirNode next;
	private phonedirNode prev;
	
	public phonedirNode() {
		
	}
	
	public phonedirNode(String first, String last, String number) {
		this.firstName = first;
		this.lastName = last;
		this.phoneNumber = number;
		name = firstName + " " + lastName;
		next = null;
	}
	
	public void setInfo(String first, String last, String number) {
		this.firstName = first;
		this.lastName = last;
		this.phoneNumber = number;
		name = firstName + " " + lastName;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setNext(phonedirNode pdn) {
		next = pdn;
	}
	
	public void setPrev(phonedirNode pdn) {
		prev = pdn;
	}
	
	public void setFirstName(String fn) {
		firstName = fn;
	}
	
	public void setLastName(String ln) {
		lastName = ln;
	}
	
	public void setPhone(String phone) {
		phoneNumber = phone;
	}
	
	public phonedirNode getNext() {
		return next;
	}
	
	public phonedirNode getPrev() {
		return prev;
	}
	
	public String getName() {
		return firstName + " " + lastName;
	}
	
	public String toString() {
		return name + " " + phoneNumber;
	}
	
	public String specialString() {
		return firstName + "             " + lastName + "                  " + phoneNumber;
	}
}


