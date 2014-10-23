package br.com.matheusbodo.rspt.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.matheusbodo.rspt.entity.GuitarcadePracticeLog;

@Transactional
public interface GuitarcadePracticeLogRepository extends PagingAndSortingRepository<GuitarcadePracticeLog, Long> {

}
