//package com.poly.dax.store;
//
//import java.util.Arrays;
//import java.util.Collection;
//import java.util.stream.Collectors;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import com.poly.dax.entity.Account;
//
//public class AccountDetails implements UserDetails{
//	private Account account;
//
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(account.getAuthorities().stream()
//                .map(a -> a.getRole().getId())
//                .collect(Collectors.toList()).toArray(new String[0]));
//		return Arrays.asList(simpleGrantedAuthority);
//	}
//
//	@Override
//	public String getPassword() {
//		// TODO Auto-generated method stub
//		return account.getPassword();
//	}
//
//	@Override
//	public String getUsername() {
//		// TODO Auto-generated method stub
//		return account.getEmail();
//	}
//
//	@Override
//	public boolean isAccountNonExpired() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//
//	@Override
//	public boolean isAccountNonLocked() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//
//	@Override
//	public boolean isCredentialsNonExpired() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//
//	@Override
//	public boolean isEnabled() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//
//}
