package application.views.components.user

import application.AppModel.{AppState, ConnectFlux, Msg, StoreUser}
import application.LinkFlux
import application.adaptors.user.{UserRepositoryImpl, UserRepositoryImplComponent}
import application.domains.user.valueObjects.Skill
import application.domains.user.{UseUserRepository, User, UserRepository}
import japgolly.scalajs.react.{BackendScope, ComponentDidUpdate, ReactComponentB, ReactElement}
import japgolly.scalajs.react.vdom.prefix_<^._
import japgolly.scalajs.react.Callback

import scala.concurrent.ExecutionContext

/**
  * Created by ryota on 2017/02/12.
  */
object UserComponent {

  case class Props(user: User, onClickUser: (User => Unit))

  def onClickUser(props: Props)() = Callback {
    props.onClickUser(props.user)
  }

  def apply(props: Props) = ReactComponentB[Props]("User")
    .render_P(props => <.li(<.div(props.user.name, ^.onClick --> onClickUser(props))))
    .build(props)
}

object UserListComponent {

  case class Props(users: List[User], onClickUser: (User => Unit))

  def apply(props: Props) = ReactComponentB[Props]("Users")
    .render_P(props =>
      <.ul(props.users.map({ user =>
        UserComponent(UserComponent.Props(user = user, onClickUser = props.onClickUser))
      })))
    .build(props)
}

trait UserContainer extends UseUserRepository with LinkFlux[Msg, AppState] {

}

object UserContainer extends UserContainer with UserRepositoryImplComponent with ConnectFlux {

  case class Props(show: Boolean)

  case class State(users: List[User])

  class Backend($: BackendScope[Props, State]) {
    def callDispatcher(implicit ec: ExecutionContext) = Callback {
      flux.subscribe({ state =>
        $.setState(State(users = state.users)).runNow()
      })

      flux.dispatch(StoreUser(User(name = "hoge", id = "huga", age = 19, skill = Skill(name = "", level = 9))))
      flux.dispatch(StoreUser(User(name = "hoge", id = "haauga", age = 19, skill = Skill(name = "", level = 9))))

    }

    def printUser(user: User) = {
      println(user)
    }

    def render(p: Props, s: State): ReactElement = {
      <.div(
        <.div("Users"),
        UserListComponent(UserListComponent.Props(users = s.users, onClickUser = printUser))
      )
    }
  }


  private def component(implicit ec: ExecutionContext) =
    ReactComponentB[Props]("UserContainer")
      .initialState(State(users = List()))
      .renderBackend[Backend]
      .componentDidMount(_.backend.callDispatcher)
      .build


  def apply(p: Props)(implicit ec: ExecutionContext): ReactElement = component.withKey("user-container")(p)
}
