package ticketguru.demo.web;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ticketguru.demo.domain.AppUser;
import ticketguru.demo.repositories.UserRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserRepository repository;

    public UserDetailServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser curruser = repository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("Käyttäjää ei löytynyt: " + username));

        String roleName = curruser.getRole().getRoleName(); // esim. "ADMIN"
        return new org.springframework.security.core.userdetails.User(
            curruser.getUsername(),
            curruser.getPasswordHash(),
            AuthorityUtils.createAuthorityList("ROLE_" + roleName.toUpperCase())
        );
    }
}
