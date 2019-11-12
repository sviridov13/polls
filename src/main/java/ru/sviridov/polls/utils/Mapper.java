package ru.sviridov.polls.utils;

import lombok.experimental.UtilityClass;
import ru.sviridov.polls.dao.dto.PollDto;
import ru.sviridov.polls.dao.dto.PollQuestionDto;
import ru.sviridov.polls.dao.entities.PollEntity;
import ru.sviridov.polls.dao.entities.PollQuestionEntity;
import ru.sviridov.polls.dao.models.PollModel;
import ru.sviridov.polls.dao.models.PollQuestionModel;

import java.util.stream.Collectors;

@UtilityClass
public class Mapper {

  public PollModel toPollModel(PollEntity pollEntity) {
    return PollModel.builder()
            .pollName(pollEntity.getPollName())
            .active(pollEntity.getActive())
            .endDate(pollEntity.getEndDate())
            .pollId(pollEntity.getPollId())
            .startDate(pollEntity.getStartDate())
            .pollQuestions(pollEntity.getQuestionEntities().stream().map(Mapper::toPollQuestionModel).collect(Collectors.toList()))
            .build();
  }

  public PollQuestionEntity toPollQuestionEntity(PollQuestionDto pollQuestionDto) {
    return PollQuestionEntity.builder()
            .number(pollQuestionDto.getNumber())
            .question(pollQuestionDto.getQuestion())
            .build();
  }

  private PollQuestionModel toPollQuestionModel(PollQuestionEntity pollQuestionEntity) {
    return PollQuestionModel.builder()
            .number(pollQuestionEntity.getNumber())
            .question(pollQuestionEntity.getQuestion())
            .pollUrl(pollQuestionEntity.getPollUrl())
            .pollQuestionId(pollQuestionEntity.getPollQuestionId())
            .build();
  }

  public PollEntity toPollEntity(PollDto pollDto) {
    return new PollEntity(pollDto.getPollName(), pollDto.getStartDate(), pollDto.getEndDate(), pollDto.getActive(),
            pollDto.getPollQuestions().stream().map(Mapper::toPollQuestionEntity).collect(Collectors.toSet()));
  }
}
