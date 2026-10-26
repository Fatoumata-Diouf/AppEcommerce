package sn.isi.authservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sn.isi.authservice.dto.AuthResponse;
import sn.isi.authservice.dto.LoginRequest;
import sn.isi.authservice.dto.RegisterRequest;
import sn.isi.authservice.entity.Role;
import sn.isi.authservice.entity.User;
import sn.isi.authservice.exception.DuplicateUserException;
import sn.isi.authservice.repository.UserRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthResponse register(RegisterRequest r) {
        if (userRepository.existsByUsername(r.getUsername())) {
            throw new DuplicateUserException("Cet utilisateur existe déjà");

        }

        User user = User.builder()
                .username(r.getUsername())
                .password(passwordEncoder.encode(r.getPassword()))
                .email(r.getEmail())
                .role(Role.USER)
                .build();

        User saved = userRepository.save(user);
        String token = jwtService.generateToken(saved);

        return AuthResponse.builder()
                .token(token)
                .username(saved.getUsername())
                .role("USER")
                .expiresIn(3600)
                .build();
    }

    public AuthResponse login(LoginRequest r) {
        User user = userRepository.findByUsername(r.getUsername())
                .orElseThrow(() -> new BadCredentialsException("Identifiants invalides"));

        if (!passwordEncoder.matches(r.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Identifiants invalides");
        }

        String token = jwtService.generateToken(user);

        return AuthResponse.builder()
                .token(token)
                .username(user.getUsername())
                .role(user.getRole().name())
                .expiresIn(3600)
                .build();
    }
}
