package telran.java2022.user.dao;

import org.springframework.data.repository.CrudRepository;

import telran.java2022.user.model.User;

public interface UsersRepository extends CrudRepository<User, String> {
	
}
