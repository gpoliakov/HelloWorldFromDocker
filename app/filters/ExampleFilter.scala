package filters

import play.mvc.EssentialAction
import play.mvc.EssentialFilter
import javax.inject.Inject
import javax.inject.Singleton
import java.util.concurrent.Executor

@Singleton
class ExampleFilter @Inject()(val exec: Executor) extends EssentialFilter{

  override def apply(next: EssentialAction): EssentialAction =
    EssentialAction.of((request) => next.apply(request).map((result) => result.withHeader("X-ExampleFilter", "foo"), exec))

}
