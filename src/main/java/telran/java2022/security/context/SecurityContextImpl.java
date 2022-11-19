package telran.java2022.security.context;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

@Component
public class SecurityContextImpl implements SecurityContext {
	Map<String, UserContext> context = new ConcurrentHashMap<>();

	@Override
	public UserContext addUser(UserContext userContext) {
		return context.put(userContext.getUserName(), userContext);
	}

	@Override
	public UserContext removeUser(String userName) {
		return context.remove(userName);
	}

	@Override
	public UserContext getUser(String userName) {
		return context.get(userName);
	}

}
