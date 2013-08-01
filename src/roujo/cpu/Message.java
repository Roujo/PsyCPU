package roujo.cpu;

public class Message {
	private static boolean isInitialized = false;
	
	private static String[] messages;
	
	public static String get(int msgId) {
		if(!isInitialized)
			init();
		if(msgId >= messages.length) {
			return "";
		} else {
			return messages[msgId];
		}
	}
	
	private static void init() {
		messages = new String[]{
				// TODO: Add more relevant messages
				"You must build additional pylons"				
		};
	}
}
