package br.com.matheusbodo.rspt.repository;

import java.util.Calendar;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.matheusbodo.rspt.entity.GuitarcadePracticeLog;
import br.com.matheusbodo.rspt.entity.User;
import br.com.matheusbodo.rspt.entity.enums.GuitarcadeGame;
import br.com.matheusbodo.rspt.entity.enums.GuitarcadeGameLevel;

@Transactional
public interface GuitarcadePracticeLogRepository extends PagingAndSortingRepository<GuitarcadePracticeLog, Long> {

	@Query("SELECT g.game, SUM(g.minutes) from GuitarcadePracticeLog g WHERE g.user = ?1 AND g.practiceDate >= ?2 AND g.practiceDate <= ?3 GROUP BY g.game")
	List<Object[]> findMinutesPlayedByUserAndPeriod(User user, Calendar dateFrom, Calendar dateTo);

	@Query("SELECT g.game, SUM(g.minutes), MAX(g.score), MAX(g.practiceDate) from GuitarcadePracticeLog g WHERE g.user = ?1 AND g.practiceDate >= ?2 AND g.practiceDate <= ?3 GROUP BY g.game")
	List<Object[]> findSummaryByUserAndPeriod(User user, Calendar dateFrom, Calendar dateTo);

	@Query("SELECT g from GuitarcadePracticeLog g WHERE g.game = ?1 AND g.gameLevel = ?2 ORDER BY g.practiceDate")
	List<GuitarcadePracticeLog> findByGameAndGameLevelOrderByPracticeDate(GuitarcadeGame game, GuitarcadeGameLevel level);
	
	@Query("SELECT g from GuitarcadePracticeLog g WHERE g.game = ?1 ORDER BY g.practiceDate")
	List<GuitarcadePracticeLog> findByGameOrderByPracticeDate(GuitarcadeGame game);
}
