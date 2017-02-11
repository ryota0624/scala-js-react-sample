package application


import application.adaptors.user.UserRepositoryImpl
import application.domains.user.User
import application.domains.user.valueObjects.Skill

import scala.scalajs.js.JSApp
import scala.scalajs.concurrent.JSExecutionContext.Implicits.queue
/**
  * Created by ryota on 2017/02/11.
  */
object App extends JSApp {
  def main(): Unit = {
    UserRepositoryImpl.all().foreach(println)
    UserRepositoryImpl.store(User(name = "ryota", id = "11", age = 0, skill = Skill(name = "1", level = 10)))
  }
}