package demo.fixed_clock.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApplicationClockPK implements Serializable {

  String timeZone;

  LocalDateTime baseTime;
}
