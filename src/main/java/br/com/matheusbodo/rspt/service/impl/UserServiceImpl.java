package br.com.matheusbodo.rspt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.matheusbodo.rspt.entity.User;
import br.com.matheusbodo.rspt.repository.UserRepository;
import br.com.matheusbodo.rspt.service.UserService;
import br.com.matheusbodo.rspt.util.SecurityUtil;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public User findLoggedUser() {
		String username = SecurityUtil.getLoggedUsername();
		return userRepository.findByEmail(username);
	}

	@Override
	public boolean isValidCurrentPassword(String currentPassword) {
		User user = findLoggedUser();
		return passwordEncoder.matches(currentPassword, user.getPassword());
	}

	@Override
	public void changeCurrentPassword(String value) {
		User user = findLoggedUser();
		user.setPassword(passwordEncoder.encode(value));
		userRepository.save(user);
	}
	

}
