package application

import application.domains.user.User

/**
  * Created by ryota on 2017/02/12.
  */
object AppModel {
  sealed abstract class Msg
  case class StoreUser(user: User) extends Msg

  case class AppState(users: List[User]) {
  }

  trait ConnectFlux extends LinkFlux[AppModel.Msg, AppState] {
    val flux = new Flux[AppModel.Msg, AppState](reducer ,AppState(List()))

    def reducer(msg: AppModel.Msg, state: AppState): AppState = {
      println(msg)
      msg match {
        case AppModel.StoreUser(user) => state.copy(users = state.users ++ List(user))
      }
    }
  }
}



