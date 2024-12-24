package com.example.utils

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.{DeserializationContext, JsonDeserializer}
import scala.collection.immutable.Map
import scala.jdk.CollectionConverters._

class ScalaMapDeserializer extends JsonDeserializer[Map[String, String]] {
  override def deserialize(p: JsonParser, ctxt: DeserializationContext): Map[String, String] = {
    val javaMap = ctxt.readValue(p, classOf[java.util.Map[String, String]])
    javaMap.asScala.toMap // Convert Java Map to Scala Map
  }
}
