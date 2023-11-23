package demo.fixed_clock.utils;

import demo.fixed_clock.repository.ApplicationClockRepository;
import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class DateTimeHelper {

  private final ApplicationClockRepository applicationClockRepository;

  @Value("${application.clock.mode:SYSTEM}")
  private ClockMode clockMode;

  private static Clock clock = Clock.systemDefaultZone();

  public static LocalDate today() {
    var isBusinessDate = false;
    return LocalDate.now(clock);
  }

  public static LocalDateTime now() {
    return LocalDateTime.now(clock);
  }

  @Scheduled(fixedDelay = 30, timeUnit = TimeUnit.SECONDS)
  private void reloadClock() {
    if (ClockMode.SYSTEM == clockMode) {
      return;
    }

    applicationClockRepository.findAll().stream()
        .findFirst()
        .ifPresentOrElse(
            v -> {
              try {
                var zone = ZoneId.of(v.getTimeZone());
                var instant = v.getBaseTime().atZone(zone).toInstant();
                var changed = Clock.fixed(instant, zone);

                if (!Objects.equals(clock, changed)) {
                  log.info(
                      "load application_clock: time_zone={}, base_time={}",
                      v.getTimeZone(),
                      v.getBaseTime());
                  clock = Clock.fixed(instant, zone);
                }
              } catch (Exception e) {
                log.error("failed to setting application clock. see application_clock rows.", e);
                clock = Clock.systemDefaultZone();
              }
            },
            () -> clock = Clock.systemDefaultZone());
  }
}
