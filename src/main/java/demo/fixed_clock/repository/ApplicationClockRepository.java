package demo.fixed_clock.repository;

import demo.fixed_clock.entity.ApplicationClock;
import demo.fixed_clock.entity.ApplicationClockPK;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationClockRepository
    extends JpaRepository<ApplicationClock, ApplicationClockPK> {

  List<ApplicationClock> findAll();
}
