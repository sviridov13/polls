package ru.sviridov.polls.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sviridov.polls.dao.dto.PollDto;
import ru.sviridov.polls.dao.entities.PollEntity;
import ru.sviridov.polls.dao.models.PollModel;
import ru.sviridov.polls.dao.repositories.PollRepository;
import ru.sviridov.polls.services.PollService;
import ru.sviridov.polls.utils.Mapper;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PollServiceImpl implements PollService {

  @Autowired
  private PollRepository pollRepository;

  @Override
  public List<PollModel> getAllPolls(Integer firstPage, Integer sizePage, String orderBy) {
    Page<PollEntity> pollEntities = pollRepository.findAll(PageRequest.of(firstPage, sizePage, Sort.by(orderBy)));
    return pollEntities.stream().map(Mapper::toPollModel).collect(Collectors.toList());
  }

  @Override
  @Transactional
  public PollModel save(PollDto pollDto) {
    PollEntity pollEntity = Mapper.toPollEntity(pollDto);
    pollEntity = pollRepository.save(pollEntity);
    return Mapper.toPollModel(pollEntity);
  }

  @Override
  @Transactional
  public PollModel update(PollDto pollDto, Long id) {
    PollEntity pollEntity = pollRepository.findByPollId(id).orElseThrow(EntityNotFoundException::new);
    pollEntity.setPollName(pollDto.getPollName());
    pollEntity.setActive(pollDto.getActive());
    pollEntity.setStartDate(pollDto.getStartDate());
    pollEntity.setEndDate(pollDto.getEndDate());
    pollEntity.setQuestionEntities(pollDto.getPollQuestions().stream().map(Mapper::toPollQuestionEntity).collect(Collectors.toSet()));
    pollRepository.save(pollEntity);
    return Mapper.toPollModel(pollEntity);
  }

  @Override
  @Transactional
  public PollModel delete(Long id) {
    PollEntity pollEntity = pollRepository.findByPollId(id).orElseThrow(EntityNotFoundException::new);
    pollRepository.delete(pollEntity);
    return Mapper.toPollModel(pollEntity);
  }
}
