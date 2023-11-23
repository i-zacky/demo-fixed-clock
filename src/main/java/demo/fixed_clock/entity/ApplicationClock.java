package demo.fixed_clock.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "application_clock")
@IdClass(ApplicationClock.class)
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApplicationClock implements Serializable {

  @Id
  @Column(name = "time_zone")
  String timeZone;

  @Id
  @Column(name = "base_time")
  LocalDateTime baseTime;
}
