package application.domains.user

import application.domains.user.valueObjects.Skill

class User(val name: String, val id: String, val age: Int, val skill: Skill) {
  def validate = name.length > 0 && id.length > 0
}

object User {
  def apply(name: String, id: String, age: Int, skill: Skill) = new User(name, id, age, skill)
}