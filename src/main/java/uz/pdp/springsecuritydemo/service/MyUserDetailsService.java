package uz.pdp.springsecuritydemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uz.pdp.springsecuritydemo.repo.UserRepo;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String phoneNumberOrEmail) throws UsernameNotFoundException {
        return userRepo.findByPhoneNumberOrEmail(phoneNumberOrEmail,phoneNumberOrEmail);
    }
}
