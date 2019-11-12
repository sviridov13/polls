package ru.sviridov.polls.controllers;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sviridov.polls.dao.dto.PollDto;
import ru.sviridov.polls.dao.models.PollModel;
import ru.sviridov.polls.services.PollService;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/polls")
@Api(tags = "polls")
public class PollController {

  @Autowired
  private PollService service;

  @GetMapping
  public List<PollModel> getAllPolls(@RequestParam String firstPage, @RequestParam String sizePage, @RequestParam String orderBy) {
    return service.getAllPolls(Integer.valueOf(firstPage), Integer.valueOf(sizePage), orderBy);
  }

  @PostMapping
  public PollModel savePoll(@RequestBody PollDto pollDto) {
    return service.save(pollDto);
  }

  @PutMapping(path = "/{pollId}")
  public ResponseEntity updatePoll(@RequestBody PollDto dto, @PathVariable Long pollId) {

    try {
      PollModel pollModel = service.update(dto, pollId);
      return new ResponseEntity<>(pollModel, HttpStatus.OK);
    } catch (EntityNotFoundException e) {
      return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }
  }

  @DeleteMapping(path = "/{pollId}")
  public ResponseEntity deletePoll(@PathVariable Long pollId) {
    try {
      PollModel pollModel = service.delete(pollId);
      return new ResponseEntity<>(pollModel, HttpStatus.OK);
    } catch (EntityNotFoundException e) {
      return new ResponseEntity<>("Запрашиваемые данные не найдены", HttpStatus.BAD_REQUEST);
    }
  }
}
