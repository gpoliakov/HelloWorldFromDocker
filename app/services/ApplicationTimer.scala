package services

import play.inject.ApplicationLifecycle
import javax.inject.Inject
import javax.inject.Singleton
import java.time.Clock
import java.util.concurrent.CompletableFuture

@Singleton
class ApplicationTimer @Inject()(val clock: Clock, val appLifecycle: ApplicationLifecycle){
  private val logger = org.slf4j.LoggerFactory.getLogger("application")
  private val start = clock.instant
  logger.info("ApplicationTimer demo: Starting application at " + start)

  // When the application starts, register a stop hook with the
  // ApplicationLifecycle object. The code inside the stop hook will
  // be run when the application stops.
  appLifecycle.addStopHook(() => {
    def foo() = {
      val stop = clock.instant
      val runningTime = stop.getEpochSecond - start.getEpochSecond
      logger.info("ApplicationTimer demo: Stopping application at " + clock.instant + " after " + runningTime + "s.")
      CompletableFuture.completedFuture(null)
    }
    foo()
  })
}
