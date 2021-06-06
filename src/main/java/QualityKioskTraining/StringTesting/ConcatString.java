package QualityKioskTraining.StringTesting;

public class ConcatString {
	
	public String concatString(String first,String second) {
	
		return first+" "+second;
	}
	
	public String concatString(Object first,Object second) {
		
		return "Not a String value";
	}
	
}
