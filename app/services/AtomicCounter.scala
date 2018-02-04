package services

import javax.inject.Singleton
import java.util.concurrent.atomic.AtomicInteger

@Singleton
class AtomicCounter extends Counter {

  private val atomicCounter = new AtomicInteger

  override def nextCount: Int = atomicCounter.getAndIncrement()
}
