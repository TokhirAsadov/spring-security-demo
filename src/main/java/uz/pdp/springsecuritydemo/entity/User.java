package uz.pdp.springsecuritydemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uz.pdp.springsecuritydemo.entity.temolate.AbsEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.util.Collection;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")
public class User extends AbsEntity implements UserDetails {
    private String fio;

    @Column(unique = true,nullable = false)
    private String phoneNumber;

    @Column(unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private boolean accountNonExpired=true;
    private boolean accountNonLocked=true;
    private boolean credentialsNonExpired=true;
    private boolean enabled=true;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Role> roles;

    public User(String fio, String phoneNumber, String email, String password, Set<Role> roles) {
        this.fio = fio;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
