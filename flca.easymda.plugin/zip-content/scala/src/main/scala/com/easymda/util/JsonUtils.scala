package com.easymda.util

import akka.http.model._
import akka.util._
import akka.stream.FlowMaterializer
import scala.concurrent._
import scala.concurrent.duration._
import com.fasterxml.jackson.databind.{ DeserializationFeature, ObjectMapper }
import com.fasterxml.jackson.module.scala.experimental.ScalaObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.fasterxml.jackson.core.JsonParser
 
object JsonUtils {
	
	val mapper = new ObjectMapper() with ScalaObjectMapper
	mapper.registerModule(DefaultScalaModule)
	mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

	def toJson(value: Map[Symbol, Any]): String = {
		toJson(value map { case (k,v) => k.name -> v})
	}

	def toJson(value: Any): String = {
		mapper.writeValueAsString(value)
	}

	def toMap[V](json:String)(implicit m: Manifest[V]) = fromJson[Map[String,V]](json)

	def fromJson[T](src: String)(implicit m : Manifest[T]): T = {
		val json = if (src.startsWith("'")) src.substring(1, src.length()-1) else src 
		mapper.readValue[T](json)
	}

	def getPostData(req: HttpRequest)(implicit mat:FlowMaterializer): String = {
		val postDataF: Future[ByteString] = {
			req.entity.dataBytes.fold(ByteString("")) { (acc, s) => acc ++ s }
		}
		Await.result(postDataF, 500.millis).decodeString("UTF-8")
	}
	
	def getReqParamAsInt(req:HttpRequest, paramName:String) : Option[Int] = {
		val reqval = req.uri.query.get(paramName)
		if (reqval.isDefined) {
			return Some(reqval.get.toInt)
		} else {
			None
		}
	}
	
}
