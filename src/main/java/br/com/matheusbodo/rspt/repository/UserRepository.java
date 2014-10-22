package br.com.matheusbodo.rspt.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.matheusbodo.rspt.entity.User;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {

	User findByEmail(String email);
}
