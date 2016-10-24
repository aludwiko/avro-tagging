package com.avrotagging

import java.security.PublicKey

import com.avrotagging.domain._

import com.sksamuel.avro4s.{FromValue, ToSchema, ToValue}
import com.softwaremill.tagging.Tagger
import org.apache.avro.Schema
import org.apache.avro.Schema.Field
import org.apache.commons.codec.binary.Base64
import org.joda.time.DateTime
import org.joda.time.format.ISODateTimeFormat

object DateTimeCustomMapping {

  implicit object DateTimeToSchema extends ToSchema[DateTime] {
    override val schema: Schema = Schema.create(Schema.Type.STRING)
  }

  implicit object DateTimeToValue extends ToValue[DateTime] {
    override def apply(value: DateTime): String = ISODateTimeFormat.dateTime().print(value)
  }

  implicit object DateTimeFromValue extends FromValue[DateTime] {
    override def apply(value: Any, field: Field): DateTime =
      ISODateTimeFormat.dateTime().parseDateTime(value.toString())
  }
}

object UserIdCustomMapping {

  implicit object UserIdToSchema extends ToSchema[UserId] {
    override val schema: Schema = Schema.create(Schema.Type.STRING)
  }

  implicit object UserIdToValue extends ToValue[UserId] {
    override def apply(value: UserId): String = value
  }

  implicit object UserIdFromValue extends FromValue[UserId] {
    override def apply(value: Any, field: Field): UserId = value.toString().taggedWith[UserIdTag]
  }
}

object RequestIdCustomMapping {

  implicit object RequestIdToSchema extends ToSchema[RequestId] {
    override val schema: Schema = Schema.create(Schema.Type.STRING)
  }

  implicit object RequestIdToValue extends ToValue[RequestId] {
    override def apply(value: RequestId): String = value
  }

  implicit object RequestIdFromValue extends FromValue[RequestId] {
    override def apply(value: Any, field: Field): RequestId = value.toString().taggedWith[RequestIdTag]
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
    override def apply(value: Any, field: Field): ConversationId = value.toString().taggedWith[ConversationIdTag]
  }
}

object MessageIdCustomMapping {

  implicit object MessageIdToSchema extends ToSchema[MessageId] {
    override val schema: Schema = Schema.create(Schema.Type.STRING)
  }

  implicit object MessageIdToValue extends ToValue[MessageId] {
    override def apply(value: MessageId): String = value
  }

  implicit object MessageIdFromValue extends FromValue[MessageId] {
    override def apply(value: Any, field: Field): MessageId = value.toString().taggedWith[MessageIdTag]
  }
}

object MediaIdCustomMapping {

  implicit object MediaIdToSchema extends ToSchema[MediaId] {
    override val schema: Schema = Schema.create(Schema.Type.STRING)
  }

  implicit object MediaIdToValue extends ToValue[MediaId] {
    override def apply(value: MediaId): String = value
  }

  implicit object MediaIdFromValue extends FromValue[MediaId] {
    override def apply(value: Any, field: Field): MediaId = value.toString().taggedWith[MediaIdTag]
  }
}

object DeviceIdCustomMapping {

  implicit object DeviceIdToSchema extends ToSchema[DeviceId] {
    override val schema: Schema = Schema.create(Schema.Type.STRING)
  }

  implicit object DeviceIdToValue extends ToValue[DeviceId] {
    override def apply(value: DeviceId): String = value
  }

  implicit object DeviceIdFromValue extends FromValue[DeviceId] {
    override def apply(value: Any, field: Field): DeviceId = value.toString().taggedWith[DeviceIdTag]
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
