package pl.hubertgawrys.securityspring.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.hubertgawrys.securityspring.models.UserModel;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserModel, Integer> {
    UserModel findByLogin(String login);
}
