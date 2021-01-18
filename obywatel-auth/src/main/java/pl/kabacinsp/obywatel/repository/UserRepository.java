package pl.kabacinsp.obywatel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kabacinsp.obywatel.model.UserDTO;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserDTO, Long> {

    Optional<UserDTO> findByEmail(String email);

    Boolean existsByEmail(String email);
}
