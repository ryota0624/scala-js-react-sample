package application.domains.user

import scala.concurrent.Future

trait UserRepository {
  def store(user: User): Future[Either[String, User]]
  def all(): Future[Either[String, List[User]]]
}

trait UseUserRepository {
  val userRepository: UserRepository
}