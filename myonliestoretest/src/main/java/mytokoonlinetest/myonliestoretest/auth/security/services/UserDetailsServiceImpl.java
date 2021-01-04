package mytokoonlinetest.myonliestoretest.auth.security.services;

import mytokoonlinetest.myonliestoretest.auth.model.User;
import mytokoonlinetest.myonliestoretest.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String name)
            throws UsernameNotFoundException {

        User user = userRepository.findByName(name)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User Not Found with -> username or email : " + name)
                );

        return UserPrinciple.build(user);
    }
}
