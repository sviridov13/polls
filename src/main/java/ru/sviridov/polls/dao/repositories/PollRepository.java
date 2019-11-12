package ru.sviridov.polls.dao.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.sviridov.polls.dao.entities.PollEntity;

import java.util.Optional;

@Repository
public interface PollRepository extends PagingAndSortingRepository<PollEntity, Long> {

  Page<PollEntity> findAll(Pageable pageable);

  @Override
  <S extends PollEntity> S save(S entity);

  @Override
  void delete(PollEntity entity);

  Optional<PollEntity> findByPollId(Long pollId);

}
