package application


import application.adaptors.user.UserRepositoryImpl
import application.domains.user.User
import application.domains.user.valueObjects.Skill
import application.views.components.user.{UserContainer, UserListComponent}
import japgolly.scalajs.react.ReactDOM

import scala.scalajs.js.JSApp
import scala.scalajs.concurrent.JSExecutionContext.Implicits.queue
import org.scalajs.dom.document

/**
  * Created by ryota on 2017/02/11.
  */
object App extends JSApp {
  def main(): Unit = {
    val app = document.getElementById("app")

    ReactDOM.render(UserContainer(UserContainer.Props(show = true)), app)
//    UserRepositoryImpl.store(User(name = "ryota", id = "11", age = 0, skill = Skill(name = "1", level = 10)))
  }
}