package kuehlfrank.backend.repositories;

import org.springframework.data.repository.CrudRepository;

import kuehlfrank.backend.model.User;

public interface UserRepository extends CrudRepository<User, String>{

}
