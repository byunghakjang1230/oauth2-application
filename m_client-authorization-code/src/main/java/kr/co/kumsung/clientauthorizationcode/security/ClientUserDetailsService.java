package kr.co.kumsung.clientauthorizationcode.security;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import kr.co.kumsung.clientauthorizationcode.domain.ClientUser;
import kr.co.kumsung.clientauthorizationcode.domain.ClientUserRepository;

@Service
public class ClientUserDetailsService implements UserDetailsService {
    private final ClientUserRepository clientUserRepository;

    public ClientUserDetailsService(ClientUserRepository clientUserRepository) {
        this.clientUserRepository = clientUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Optional<ClientUser> optionalClientUser = this.clientUserRepository.findByUserName(username);
        if (!optionalClientUser.isPresent()) {
            throw new UsernameNotFoundException("dkdkdkdkddk");
        }
        return new ClientUserDetails(optionalClientUser.get());
    }
}
