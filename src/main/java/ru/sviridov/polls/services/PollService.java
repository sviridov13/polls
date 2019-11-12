package ru.sviridov.polls.services;

import org.springframework.data.crossstore.ChangeSetPersister;
import ru.sviridov.polls.dao.dto.PollDto;
import ru.sviridov.polls.dao.models.PollModel;
import java.util.List;

public interface PollService {

  List<PollModel> getAllPolls(Integer firstPage, Integer sizePage, String orderBy);

  PollModel save(PollDto pollDto);

  PollModel update(PollDto pollDto, Long id);

  PollModel delete(Long id);

}
