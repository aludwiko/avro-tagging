package com.giampaolotrapasso.avrotagging

import java.util.UUID

import com.softwaremill.tagging._

trait UserIdTag
trait MessageIdTag
trait DeviceIdTag
trait MediaIdTag
trait ConversationIdTag
trait RequestIdTag
trait TimestampTag
trait SessionIdTag

package object domain {

  val UninitializedId = "-1".taggedWith[UserIdTag]

  type UserId         = String @@ UserIdTag
  type DeviceId       = String @@ DeviceIdTag
  type MessageId      = String @@ MessageIdTag
  type MediaId        = String @@ MediaIdTag
  type ConversationId = String @@ ConversationIdTag
  type RequestId      = String @@ RequestIdTag
  type Timestamp      = Long @@ TimestampTag
  type SessionId      = String @@ SessionIdTag

  val uninitializedDeviceId = "device.none".taggedWith[DeviceIdTag]

  def randomMessageId: MessageId = {
    UUID.randomUUID().toString.taggedWith[MessageIdTag]
  }

  implicit def toUserId(userId: String): UserId = userId.taggedWith[UserIdTag]

  implicit def toDeviceId(deviceId: String): DeviceId = deviceId.taggedWith[DeviceIdTag]

  implicit def toRequestId(requestId: String): RequestId = requestId.taggedWith[RequestIdTag]

  implicit def toConversationId(conversationId: String): ConversationId = conversationId.taggedWith[ConversationIdTag]

  implicit def toSessionId(sessionId: String): SessionId = sessionId.taggedWith[SessionIdTag]

  implicit def toTimestamp(timestamp: Long): Timestamp = timestamp.taggedWith[TimestampTag]

}
