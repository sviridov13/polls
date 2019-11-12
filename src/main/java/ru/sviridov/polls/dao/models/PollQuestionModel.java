package ru.sviridov.polls.dao.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PollQuestionModel {

  private Long pollQuestionId;

  private String question;

  private String pollUrl;

  private Integer number;
}
