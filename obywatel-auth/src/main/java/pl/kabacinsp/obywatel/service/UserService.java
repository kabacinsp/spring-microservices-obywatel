package pl.kabacinsp.obywatel.service;

import org.springframework.stereotype.Component;
import pl.kabacinsp.obywatel.model.UserDTO;
import pl.kabacinsp.obywatel.repository.UserRepository;

import java.util.List;
import java.util.Random;

@Component
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDTO> getUsers() {
        return userRepository.findAll();
    }

    public UserDTO saveUser(UserDTO user) {
        user.setId(new Random().nextLong());
        return userRepository.save(user);
    }

}
