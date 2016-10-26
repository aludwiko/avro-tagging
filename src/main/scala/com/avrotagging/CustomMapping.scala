package com.avrotagging

import com.avrotagging.domain._
import com.sksamuel.avro4s.{FromValue, ToSchema, ToValue}
import com.softwaremill.tagging.Tagger
import org.apache.avro.Schema
import org.apache.avro.Schema.Field

object UserIdCustomMapping {

  implicit object UserIdToSchema extends ToSchema[UserId] {
    override val schema: Schema = Schema.create(Schema.Type.STRING)
  }

  implicit object UserIdToValue extends ToValue[UserId] {
    override def apply(value: UserId): String = value
  }

  implicit object UserIdFromValue extends FromValue[UserId] {
    override def apply(value: Any, field: Field): UserId = value.toString.taggedWith[UserIdTag]
  }
}

object ConversationIdCustomMapping {

  implicit object ConversationIdToSchema extends ToSchema[ConversationId] {
    override val schema: Schema = Schema.create(Schema.Type.STRING)
  }

  implicit object ConversationIdToValue extends ToValue[ConversationId] {
    override def apply(value: ConversationId): String = value
  }

  implicit object ConversationIdFromValue extends FromValue[ConversationId] {
    override def apply(value: Any, field: Field): ConversationId = value.toString.taggedWith[ConversationIdTag]
  }
}

object TimestampCustomMapping {

  implicit object TimestampToSchema extends ToSchema[Timestamp] {
    override val schema: Schema = Schema.create(Schema.Type.LONG)
  }

  implicit object TimestampToValue extends ToValue[Timestamp] {
    override def apply(value: Timestamp): Long = value
  }

  implicit object TimestampFromValue extends FromValue[Timestamp] {
    override def apply(value: Any, field: Field): Timestamp = value.asInstanceOf[Long].taggedWith[TimestampTag]
  }
}
