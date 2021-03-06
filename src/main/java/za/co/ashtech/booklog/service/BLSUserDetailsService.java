package za.co.ashtech.booklog.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import za.co.ashtech.booklog.db.dao.BookLogDao;
import za.co.ashtech.booklog.db.entity.BooklogUserEntity;
import za.co.ashtech.booklog.db.entity.UserRoleEntity;

@Service
public class BLSUserDetailsService implements UserDetailsService {

	@Autowired
	private BookLogDao dao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		BooklogUserEntity user = null;
		try {
			user = dao.getUser(username);
		} catch (EmptyResultDataAccessException e) {
			 throw new UsernameNotFoundException(username);
		 }catch (Exception e) {
			 throw new UsernameNotFoundException(username);
		}
		MyUserPrincipal myUser = new MyUserPrincipal(user);
		return myUser;
	}
	
	/* inner class for user details */
	class MyUserPrincipal implements UserDetails {

		private static final long serialVersionUID = 1L;
		private BooklogUserEntity user;

	    public MyUserPrincipal(BooklogUserEntity user) {
	        this.user = user;
	    }

		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			
			List<SimpleGrantedAuthority> roles = new ArrayList<>();
			for(UserRoleEntity ure:this.user.getBooklogRoles()) {
				roles.add(new SimpleGrantedAuthority(ure.getAuthority()));
			}
			
			return roles;
		}

		@Override
		public String getPassword() {
			return this.user.getPassword();
		}

		@Override
		public String getUsername() {
			return this.user.getUsername();
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
			return true;
		}
	}

}
