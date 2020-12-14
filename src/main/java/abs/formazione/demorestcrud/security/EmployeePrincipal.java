package abs.formazione.demorestcrud.security;

import abs.formazione.demorestcrud.entity.Employee;
import abs.formazione.demorestcrud.entity.Role;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.*;

/*
This class represents an authenticated Spring Security principal. By implementing the OAuth2User
and the UserDetails interfaces, it will be to provide the details of the authenticated user.
This class will be used together with the @AuthenticationPrincipal annotation and the implementation
of a OAuth2UserService.

 */
public class EmployeePrincipal implements OAuth2User, UserDetails {

    @Getter
    private Employee employee;
    private Collection<? extends GrantedAuthority> authorities;
    private Map<String, Object> attributes;

    public EmployeePrincipal(Employee employee){
        this.employee = employee;

        /* Depending on the role defined at the creation of the employee, we convert the String
        *  representing the role to a proper GrantedAuthority object.
        */
        Set<Role> roles = this.employee.getRoles();
        List<SimpleGrantedAuthority> authorities_list = new ArrayList<>();
        for (Role role : roles){
            authorities_list.add(new SimpleGrantedAuthority(role.getRole_name()));
        }
        this.authorities = authorities_list;
    }

    public EmployeePrincipal(Employee employee, Map<String, Object> attributes){
        this(employee);
        this.attributes = attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return employee.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return employee.isEnabled();
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }


    @Override
    public String getName() {
        return String.valueOf(employee.getId());
    }
}
