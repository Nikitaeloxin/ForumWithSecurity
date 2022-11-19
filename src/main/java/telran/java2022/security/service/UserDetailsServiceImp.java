package telran.java2022.security.service;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import telran.java2022.user.dao.UsersRepository;
import telran.java2022.user.exception.UserNotFoundException;
import telran.java2022.user.model.User;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImp implements UserDetailsService {
	
	final UsersRepository usersRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = usersRepository.findById(username).orElseThrow(()-> new UsernameNotFoundException(username));
		String[] roles = user.getRoles().stream().map(r->"ROLE_" + r.toUpperCase())
										.toArray(String[]::new);
		return new org.springframework.security.core.userdetails.User(username, user.getPassword(),
				AuthorityUtils.createAuthorityList(roles));
	}

}
