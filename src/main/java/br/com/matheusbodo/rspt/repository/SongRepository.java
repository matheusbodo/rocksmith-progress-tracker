package br.com.matheusbodo.rspt.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.matheusbodo.rspt.entity.Song;
import br.com.matheusbodo.rspt.entity.User;

public interface SongRepository extends PagingAndSortingRepository<Song, Long> {

	Song findByIdAndUser(Long id, User user);

}
