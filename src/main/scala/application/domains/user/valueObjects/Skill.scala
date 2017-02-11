package application.domains.user.valueObjects

/**
  * Created by ryota on 2017/02/12.
  */
case class Skill(name: String, level: Int) {
  def validate = name.length > 0 && level > 0
}