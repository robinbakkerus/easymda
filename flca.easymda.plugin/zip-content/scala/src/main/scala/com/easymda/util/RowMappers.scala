package com.easymda.util
  
import java.sql.Date
import java.util.Date
 
trait RowMappers {
  def toDate(date: java.sql.Date) = new java.util.Date(date.getTime())
  def toDate(date: Option[java.sql.Date]) = if (date.isDefined) new java.util.Date(date.get.getTime()) else null
  def toSqlDate(date: java.util.Date) = new java.sql.Date(date.getTime)
  def toSqlDate(date: Option[java.util.Date]) = if (date.isDefined) new java.sql.Date(date.get.getTime()) else null
}
