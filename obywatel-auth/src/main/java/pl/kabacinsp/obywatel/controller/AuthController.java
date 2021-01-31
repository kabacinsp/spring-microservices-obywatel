package pl.kabacinsp.obywatel.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.kabacinsp.obywatel.model.UserDTO;
import pl.kabacinsp.obywatel.payload.ApiResponse;
import pl.kabacinsp.obywatel.payload.LoginRequest;
import pl.kabacinsp.obywatel.payload.SignUpRequest;
import pl.kabacinsp.obywatel.repository.UserRepository;
import pl.kabacinsp.obywatel.security.TokenProvider;
import pl.kabacinsp.obywatel.service.UserService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

//        String token = tokenProvider.createToken(authentication);
        return ResponseEntity.ok("Fine");
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) throws Exception {
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new Exception();
        }

        // Creating user's account
        UserDTO user = new UserDTO();
        user.setFirstName(signUpRequest.getFirstName());
        user.setLastName(signUpRequest.getLastName());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(signUpRequest.getPassword());

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        UserDTO result = userRepository.save(user);

        URI location =
                ServletUriComponentsBuilder.fromCurrentContextPath()
                        .path("/user/me")
                        .buildAndExpand(result.getId())
                        .toUri();

        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "User registered successfully@"));
    }

    @GetMapping("users")
    public ResponseEntity<?> listUsers() {
        log.info("UsersController:  list users");
        List<UserDTO> resource = userService.getUsers();
        return ResponseEntity.ok(resource);
    }

    @GetMapping("/easy")
    public String anyMethod() throws Exception {
        return "Fajnie było";
    }
}
