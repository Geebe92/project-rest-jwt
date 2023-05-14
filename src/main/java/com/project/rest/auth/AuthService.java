package com.project.rest.auth;

import com.project.rest.config.JwtService;
import com.project.rest.model.CustomUserDetails;
import com.project.rest.model.Role;
import com.project.rest.model.Student;
import com.project.rest.service.StudentService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final StudentService studentService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(Student student) {
        student.setPassword(passwordEncoder.encode(student.getPassword()));
        student.setRole(Role.USER);
        var user = new CustomUserDetails(studentService.setStudent(student));
        var token = jwtService.generateToken(user);
        return AuthResponse.builder().token(token).build();
    }
    public AuthResponse authenticate(@NotNull String email, @NotNull String password){
        authenticationManager
                .authenticate((new UsernamePasswordAuthenticationToken(email, password)));
        var user = studentService
                .searchByEmail(email)
                .map(s -> new CustomUserDetails(s))
                .orElseThrow(() ->
                        new UsernameNotFoundException(String.format("User '%s' not found!", email)));
        var token = jwtService.generateToken(user);
        return  AuthResponse.builder()
                .token(token)
                .build();

    }
    public AuthResponse authenticate(AuthRequest request){
        return authenticate(request.getEmail(), request.getPassword());
    }
}
