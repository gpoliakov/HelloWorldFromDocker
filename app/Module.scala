import com.google.inject.AbstractModule
import services.{ApplicationTimer, AtomicCounter, CassandraCounter, Counter}
import java.time.Clock

import model.CassandraDAO


class Module extends AbstractModule {
  override def configure(): Unit = {
    // Use the system clock as the default implementation of Clock
    bind(classOf[Clock]).toInstance(Clock.systemDefaultZone)
    // Ask Guice to create an instance of ApplicationTimer when the
    // application starts.
    bind(classOf[ApplicationTimer]).asEagerSingleton()
    // Set CassandraDAO as dao.
    bind(classOf[CassandraDAO]).toInstance(CassandraDAO())
    // Set CassandraCounter as the implementation for Counter.
    bind(classOf[Counter]).to(classOf[CassandraCounter])
  }

}
