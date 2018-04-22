package pl.hubertgawrys.securityspring.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.hubertgawrys.securityspring.repositories.UserRepository;

import java.util.Collections;

@Service
public class SecureDetailsUserModel implements UserDetailsService {

    final UserRepository userRepository;

    @Autowired
    public SecureDetailsUserModel(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserModel userModel = userRepository.findByLogin(s);
        if (userModel == null){
            throw new UsernameNotFoundException(s);
        }
        System.out.println(userModel.getLogin());
        System.out.println(userModel.getPassword());

        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(userModel.getUserType());
        return new User(userModel.getLogin(), userModel.getPassword(), Collections.singletonList(grantedAuthority));
    }
}
