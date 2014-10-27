package br.com.matheusbodo.rspt.repository;

import java.util.Calendar;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.matheusbodo.rspt.entity.LearnSongPracticeLog;
import br.com.matheusbodo.rspt.entity.Song;
import br.com.matheusbodo.rspt.entity.User;
import br.com.matheusbodo.rspt.entity.enums.GamePath;

@Transactional
public interface LearnSongPracticeLogRepository extends PagingAndSortingRepository<LearnSongPracticeLog, Long> {

	@Query("SELECT l from LearnSongPracticeLog l WHERE l.song = ?1 AND l.path = ?2 ORDER BY l.practiceDate")
	List<LearnSongPracticeLog> findBySongAndPathOrderByPracticeDate(Song song, GamePath path);

	@Query("SELECT l.song.id, SUM(l.minutes), MAX(l.completed), MAX(l.practiceDate) from LearnSongPracticeLog l WHERE l.path = ?1 AND l.user = ?2 AND l.practiceDate >= ?3 AND l.practiceDate <= ?4 GROUP BY l.song.id")
	List<Object[]> findSummaryByPathAndUserAndPeriod(GamePath path, User user, Calendar dateFrom, Calendar dateTo);

	void deleteByUser(User user);

}
