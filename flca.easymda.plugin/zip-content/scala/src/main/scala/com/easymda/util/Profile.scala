package com.easymda.util
 
import scala.slick.driver.JdbcProfile

trait Profile {
  val profile: JdbcProfile
}
