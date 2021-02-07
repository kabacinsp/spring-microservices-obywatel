package pl.kabacinsp.obywatel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.kabacinsp.obywatel.exception.ResourceNotFoundException;
import pl.kabacinsp.obywatel.model.UserDTO;
import pl.kabacinsp.obywatel.repository.UserRepository;
import pl.kabacinsp.obywatel.security.CurrentUser;
import pl.kabacinsp.obywatel.security.UserPrincipal;

@RestController
@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200"})
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public UserDTO getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        System.out.println(userPrincipal.getId());
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }
}
