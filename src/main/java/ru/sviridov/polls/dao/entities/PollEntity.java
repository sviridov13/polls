package ru.sviridov.polls.dao.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "poll")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PollEntity {
  @Id
  @Column
  @SequenceGenerator(name = "seq", sequenceName = "poll_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
  private Long pollId;

  @Column
  private String pollName;

  @Column
  private LocalDate startDate;

  @Column
  private LocalDate endDate;

  @Column
  private Boolean active;

  @OneToMany(mappedBy = "poll", cascade = CascadeType.ALL)
  private Set<PollQuestionEntity> questionEntities;

  public PollEntity(String pollName, LocalDate startDate, LocalDate endDate, Boolean active, Set<PollQuestionEntity> questionEntities) {
    this.pollName = pollName;
    this.startDate = startDate;
    this.endDate = endDate;
    this.active = active;
    this.questionEntities = questionEntities;
    this.questionEntities.forEach(x -> x.setPoll(this));
  }
}
