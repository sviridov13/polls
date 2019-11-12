package ru.sviridov.polls.dao.models;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class PollModel {

  private String pollName;

  private Long pollId;

  private LocalDate startDate;

  private LocalDate endDate;

  private Boolean active;

  private List<PollQuestionModel> pollQuestions;
}
