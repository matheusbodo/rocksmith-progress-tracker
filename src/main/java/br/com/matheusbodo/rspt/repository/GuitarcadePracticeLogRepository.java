package br.com.matheusbodo.rspt.repository;

import java.util.Calendar;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.matheusbodo.rspt.entity.GuitarcadePracticeLog;
import br.com.matheusbodo.rspt.entity.User;

@Transactional
public interface GuitarcadePracticeLogRepository extends PagingAndSortingRepository<GuitarcadePracticeLog, Long> {

	@Query("SELECT g.game, SUM(g.minutes) from GuitarcadePracticeLog g WHERE g.user = ?1 AND g.date >= ?2 AND g.date <= ?3 GROUP BY g.game")
	List<Object[]> findMinutesPlayedByUserAndPeriod(User user, Calendar dateFrom, Calendar dateTo);

	@Query("SELECT g.game, SUM(g.minutes), MAX(g.score), MAX(g.date) from GuitarcadePracticeLog g WHERE g.user = ?1 AND g.date >= ?2 AND g.date <= ?3 GROUP BY g.game")
	List<Object[]> findSummaryByUserAndPeriod(User user, Calendar dateFrom, Calendar dateTo);
}
