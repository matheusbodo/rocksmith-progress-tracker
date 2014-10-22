package br.com.matheusbodo.rspt.security;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import br.com.matheusbodo.rspt.entity.User;
import br.com.matheusbodo.rspt.entity.UserRole;
import br.com.matheusbodo.rspt.repository.UserRepository;

@Service
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
		for (UserRole userRole : user.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(userRole.getRole().name()));
		}
		return new org.springframework.security.core.userdetails.User(username, user.getPassword(), authorities);
	}

	public static void main(String[] args) {
		System.out.println(BCrypt.hashpw("admin", BCrypt.gensalt()));
	}
}
