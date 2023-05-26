package com.poly.dax.store;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.poly.dax.entity.Account;
import com.poly.dax.service.AccountService;

@Service("AccountDetailsServiceImpl")
public class UserService implements UserDetailsService{
	@Autowired
	AccountService accountService;
	@Autowired
	BCryptPasswordEncoder pe;

//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		try {
//			Account ac = accountService.findById(username);
//			System.out.print("username: "+username);
//			String password = passwordEncoder.encode(ac.getPassword());
//			String[] roles = ac.getAuthorities().stream()
//					.map(er -> er.getRole().getId())
//					.collect(Collectors.toList()).toArray(new String[0]);
//			return User.withUsername(username).password(password).roles(roles).build();
//		} catch (NoSuchElementException e) {
//			throw new UsernameNotFoundException(username + " not found");
//		}
//	}
	@Override
	public UserDetails loadUserByUsername(String email) {
        Account account = accountService.findByEmail(email);
        if (account == null) throw new UsernameNotFoundException(email);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        String[] roles = account.getAuthorities().stream()
				.map(er -> er.getRole().getId())
				.collect(Collectors.toList()).toArray(new String[0]); 
        
        for(String role : roles) {         
            if( role.equals("1")) {             
                grantedAuthorities.add(new SimpleGrantedAuthority("admin"));             
                System.out.println("ROLE: ROLE_ADMIN");           
            } else if( role.equals("2")) {            
                grantedAuthorities.add(new SimpleGrantedAuthority("user"));             
                System.out.println("ROLE: ROLE_USER");             
            } else {             
                grantedAuthorities.add(new SimpleGrantedAuthority("guest"));             
                System.out.println("ROLE: ROLE_GUEST");             
            }         
        }              

        return new org.springframework.security.core.userdetails.User(
                account.getEmail(),
                account.getPassword(),
                grantedAuthorities
            );
            
    }

}
