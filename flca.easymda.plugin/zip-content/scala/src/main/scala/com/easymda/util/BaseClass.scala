package com.easymda.util
 
import org.apache.commons.lang3.builder.ToStringBuilder
 
abstract class FetchDepth()
class FetchEager() extends FetchDepth

abstract class BaseClass() {

  var dirty : Boolean = false;
	
  override def toString() = {
    ToStringBuilder.reflectionToString(this)
  }	
}
