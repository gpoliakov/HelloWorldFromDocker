package Controllers

import play.mvc.{Controller, Result, Results}
import services.Counter
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HelloController @Inject()(val counter: Counter) extends Controller{

  def hello(): Result = {Results.ok(s"Hello, visit N = ${Integer.toString(counter.nextCount)}")}

}
