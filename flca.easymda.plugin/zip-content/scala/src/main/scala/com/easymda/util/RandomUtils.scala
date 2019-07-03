
package com.easymda.util
  
import scala.util.Random
import java.util.Date
import org.joda.time.DateTime
  
object RandomUtils {
  
  def stringValue(): String = {
    val l = Random.nextInt(20)
    Random.alphanumeric.filter(_.isLetter).take(5).mkString
  }
  
  def intValue() = Random.nextInt(5000)
  def intValue(max:Int) = Random.nextInt(max)
  def doubleValue() = intValue + Random.nextDouble()
  def boolValue() = Random.nextBoolean
  
  def dateValue(): Date = {
    val y = 1950 + Random.nextInt(70)
    val m = 1 + Random.nextInt(11)
    val d = if (m == 2) Random.nextInt(27) else {if (m%2 == 0) Random.nextInt(30) else Random.nextInt(29)}
    val dt: DateTime = new DateTime(y, m, d + 1, 0, 0, 0, 0)
    dt.toDate()
  }
}
