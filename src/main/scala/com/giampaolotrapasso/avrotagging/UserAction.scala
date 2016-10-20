package com.giampaolotrapasso.avrotagging

import com.giampaolotrapasso.avrotagging.domain.{MessageId, Timestamp, UserId}
import com.sksamuel.avro4s.ToSchema
import org.apache.avro.Schema

case class UserAction(userId: UserId, timestamp: Timestamp)

/*
object UserAction {
  implicit object UserActionToSchema extends ToSchema[UserAction] {
    override val schema: Schema = {
      println("Manual UserAction schema")

      val s = """{
                |   "type" : "record",
                |   "namespace" : "com.giampaolotrapasso.avrotagging",
                |   "name" : "UserAction",
                |   "fields" : [
                |      { "name" : "userId" , "type" : "string" },
                |      { "name" : "timestamp" , "type" : "long" }
                |   ]
                |}""".stripMargin

      (new Schema.Parser).parse(s)
    }
  }

  /*
  implicit object DateTimeToValue extends ToValue[DateTime] {
    override def apply(value: DateTime): String = ISODateTimeFormat.dateTime().print(value)
  }

  implicit object DateTimeFromValue extends FromValue[DateTime] {
    override def apply(value: Any, field: Field): DateTime = ISODateTimeFormat.dateTime().parseDateTime(value.toString())
  }

 */

}
 */
