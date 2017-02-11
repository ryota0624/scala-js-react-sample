package application.adaptors.user

import application.Request
import application.domains.user.valueObjects.Skill
import application.domains.user.{User, UserRepository}

import scala.concurrent.Future
import scala.scalajs.js

/**
  * Created by ryota on 2017/02/12.
  */
object UserRepositoryImpl extends UserRepository {

  import scala.scalajs.concurrent.JSExecutionContext.Implicits.queue

  def all(): Future[Either[String, List[User]]] = {
    Request.getJSON[UsersResponse]("/users.json").map({ users =>
      Right(users.value.map(userDto2Model).toList)
    })
  }

  def store(user: User): Future[Either[String, User]] = {
    Request.postFormData[UserDto]("/user.json", userToFormData(user) ).map({ userDto =>
      Right(userDto2Model(userDto))
    })
  }

  def encode(value: String) = js.URIUtils.encodeURIComponent(value)

  private def userToFormData(user: User): Map[String, Any] = {
    Map("name" -> user.name, "age" -> user.age, "skill" -> user.skill)
  }

  private def userDto2Model(dto: UserDto) = User(dto.name, dto.id, dto.age, skillDto2Model(dto.skill))

  private def skillDto2Model(dto: SkillDto) = Skill(dto.name, dto.level)

}


@js.native
trait UserDto extends js.Object {
  val name: String = js.native
  val age: Int = js.native
  val id: String = js.native
  val skill: SkillDto = js.native

}


@js.native
trait SkillDto extends js.Object {
  val name: String = js.native
  val level: Int = js.native

}


@js.native
trait UsersResponse extends js.Object {
  val value: js.Array[UserDto] = js.native
}