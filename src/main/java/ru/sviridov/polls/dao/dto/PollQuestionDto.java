package ru.sviridov.polls.dao.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PollQuestionDto {

  private String question;

  private Integer number;
}
