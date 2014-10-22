package br.com.matheusbodo.rspt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@Override
	public User findLoggedUser() {
		String username = SecurityUtil.getLoggedUsername();
		return userRepository.findByEmail(username);
	}

}
