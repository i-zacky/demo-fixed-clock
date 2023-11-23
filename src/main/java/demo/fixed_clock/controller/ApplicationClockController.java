package demo.fixed_clock.controller;

import demo.fixed_clock.utils.DateTimeHelper;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ApplicationClockController {

  @GetMapping(value = "/v1/application-clock/now", produces = "application/json")
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public Map<String, Object> now() {
    return Map.of(
        "DateTimeHelper.today",
        DateTimeHelper.today(),
        "LocalDate.now",
        LocalDate.now(),
        "DateTimeHelper.now",
        DateTimeHelper.now(),
        "LocalDateTime.now",
        LocalDateTime.now());
  }
}
