package me.abs0luty.school.service;

import lombok.AllArgsConstructor;
import me.abs0luty.school.dto.UserDataResponse;
import me.abs0luty.school.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public ResponseEntity<UserDataResponse> findUserById(Long id) {
        return userRepository.findById(id)
                        .map(appUser -> ResponseEntity.ok(
                            modelMapper.map(appUser, UserDataResponse.class)))
                        .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.maybeFindByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
