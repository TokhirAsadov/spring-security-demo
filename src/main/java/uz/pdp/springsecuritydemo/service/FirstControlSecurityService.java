package uz.pdp.springsecuritydemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import uz.pdp.springsecuritydemo.entity.Role;
import uz.pdp.springsecuritydemo.entity.User;
import uz.pdp.springsecuritydemo.entity.enums.RoleName;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import java.util.Set;

@Component
public class FirstControlSecurityService extends OncePerRequestFilter {

    @Autowired
    MyUserDetailsService myUserDetailsService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager manager;

    public User checkAuthority(HttpServletRequest request){
        String authorization = request.getHeader("Authorization");
        String encodedUserNameAndPassword = authorization.substring(6);
        byte[] decode = Base64.getDecoder().decode(encodedUserNameAndPassword);
        String str = new String(decode, StandardCharsets.UTF_8);
       String username=str.substring(0,str.indexOf(":"));
       String password=str.substring(str.indexOf(":")+1);
        User user =(User) myUserDetailsService.loadUserByUsername(username);
//        boolean matches = passwordEncoder.matches(password,user.getPassword());
//        if (matches &&
//                user.isAccountNonExpired()
//                        &&
//                        user.isAccountNonLocked()
//                &&
//                        user.isCredentialsNonExpired()
//                &&
//                        user.isEnabled()
//
//        ){
//            Set<Role> roles =(Set<Role>) user.getAuthorities();
//            for (Role role : roles) {
//                if (role.getAuthority().equals(RoleName.ROLE_ADMIN.name())) {
//                    return user;
//                }
//            }
//        }

        return user;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        User user = checkAuthority(request);
        if (user!=null){
            Authentication authenticate = manager.authenticate(new UsernamePasswordAuthenticationToken(user,null, user.getAuthorities()));
            SecurityContextHolder.getContext().setAuthentication(authenticate);
        }
        filterChain.doFilter(request,response);
    }
}
