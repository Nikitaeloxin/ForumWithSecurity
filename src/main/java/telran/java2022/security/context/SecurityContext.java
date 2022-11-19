package telran.java2022.security.context;

public interface SecurityContext {
	
	UserContext addUser(UserContext userContext);
	
	UserContext removeUser(String userName);
	
	UserContext getUser(String userName);

}
