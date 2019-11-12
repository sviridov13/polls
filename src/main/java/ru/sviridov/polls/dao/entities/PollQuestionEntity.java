package ru.sviridov.polls.dao.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "poll_question")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PollQuestionEntity {

  @Id
  @Column
  @SequenceGenerator(name = "seq", sequenceName = "poll_question_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
  private Long pollQuestionId;

  @Column
  private String question;

  @Column
  private String pollUrl;

  @ManyToOne
  @JoinColumn
  private PollEntity poll;

  @Column
  private Integer number;
}
