package ACL.Service.services.ImpService;

import ACL.Service.config.JwtService;
import ACL.Service.models.Role;
import ACL.Service.models.User;
import ACL.Service.payloads.AuthenticationRequest;
import ACL.Service.payloads.AuthenticationResponse;
import ACL.Service.payloads.RegisterRequest;
import ACL.Service.repositories.RoleRepository;
import ACL.Service.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .userName(request.getUsername())
                .userFirstName(request.getFirstname())
                .userLastName(request.getLastName())
                .email(request.getEmail())
                .userPassword(passwordEncoder.encode(request.getPassword()))
                .role((Set<Role>) roleRepository.findByRoleName("user"))
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();

    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
