package br.com.matheusbodo.rspt.service;

import br.com.matheusbodo.rspt.entity.User;

public interface UserService {

	User findLoggedUser();

	boolean isValidCurrentPassword(String value);

	void changeCurrentPassword(String value);

	void resetData();

	void deleteAccount();

	User findOne(Long id);

	void changeUserPassword(User user, String value);
}
