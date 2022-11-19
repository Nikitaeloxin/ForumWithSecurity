package telran.java2022.user.service;


import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import telran.java2022.user.dao.UsersRepository;
import telran.java2022.user.dto.LoginDto;
import telran.java2022.user.dto.RoleDto;
import telran.java2022.user.dto.UserDto;
import telran.java2022.user.dto.UserRegisterDto;
import telran.java2022.user.dto.UserUpdate;
import telran.java2022.user.exception.NotValidPassowordException;
import telran.java2022.user.exception.UserAlreadyExistException;
import telran.java2022.user.exception.UserNotFoundException;
import telran.java2022.user.model.User;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, CommandLineRunner {

	final UsersRepository usersRepository;
	final ModelMapper modelMapper;
	final PasswordEncoder passwordEncoder;

	@Override
	public UserDto userRegister(UserRegisterDto userRegisterDto) {
		if (usersRepository.existsById(userRegisterDto.getLogin())) {
			throw new UserAlreadyExistException(userRegisterDto.getLogin());
		}
		userRegisterDto.setChangePasswordDate(LocalDateTime.now().toString());
		User user = modelMapper.map(userRegisterDto, User.class);
		String password = passwordEncoder.encode(user.getPassword());
		user.setPassword(password);
		usersRepository.save(user);
		return modelMapper.map(user, UserDto.class);
	}

	@Override
	public UserDto userLogin(LoginDto loginDto) {
		User user = usersRepository.findById(loginDto.getLogin())
				.orElseThrow(() -> new UserNotFoundException(loginDto.getLogin()));
		if (!user.validatePassword(loginDto.getPassword())) {
			throw new NotValidPassowordException();
		}
		return modelMapper.map(user, UserDto.class);
	}

	@Override
	public UserDto userDelete(String login) {
		User user = usersRepository.findById(login).orElseThrow(() -> new UserNotFoundException(login));
		usersRepository.deleteById(login);
		return modelMapper.map(user, UserDto.class);
	}

	@Override
	public UserDto userUpdate(String login, UserUpdate userUpdate) {
		User user = usersRepository.findById(login).orElseThrow(() -> new UserNotFoundException(login));
		user.setFirstName(userUpdate.getFirstName());
		user.setLastName(userUpdate.getLastName());
		usersRepository.save(user);
		return modelMapper.map(user, UserDto.class);
	}

	@Override
	public RoleDto addRole(String login, String role) {
		User user = usersRepository.findById(login).orElseThrow(() -> new UserNotFoundException(login));
		user.addRole(role.toUpperCase());
		usersRepository.save(user);
		return modelMapper.map(user, RoleDto.class);
	}

	@Override
	public RoleDto deleteRole(String login, String role) {
		User user = usersRepository.findById(login).orElseThrow(() -> new UserNotFoundException(login));
		user.deleteRole(role);
		usersRepository.save(user);
		return modelMapper.map(user, RoleDto.class);
	}

	@Override
	public void changePassword(String login, String newPassword) {
		User user = usersRepository.findById(login).orElseThrow(() -> new UserNotFoundException(newPassword));
		String password = passwordEncoder.encode(newPassword);
		user.setPassword(password);
		user.setChangePasswordDate(LocalDateTime.now().toString());
		usersRepository.save(user);
	}

	@Override
	public UserDto getUser(String login) {
		User user = usersRepository.findById(login).orElseThrow(() -> new UserNotFoundException(login));
		return modelMapper.map(user, UserDto.class);
	}

	@Override
	public void run(String... args) throws Exception {
		if (!usersRepository.existsById("admin")) {
			String password = passwordEncoder.encode("admin");
			User user = new User("admin", password, "	", "");
			user.addRole("MODERATOR");
			user.addRole("ADMINISTRATOR");
			usersRepository.save(user);
		}

	}
}
