package otahiri.design.pattern.command;

public class TVRemote {
	
	public static ElectronicDevice getDevice(){
		
		return new Television();
		
	}
	
}