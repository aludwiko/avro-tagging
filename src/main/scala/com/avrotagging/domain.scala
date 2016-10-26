package com.avrotagging

import java.util.UUID

import com.softwaremill.tagging._

trait UserIdTag

trait ConversationIdTag

trait TimestampTag

package object domain {

  val UninitializedId = "-1".taggedWith[UserIdTag]

  type UserId         = String @@ UserIdTag
  type ConversationId = String @@ ConversationIdTag
  type Timestamp      = Long @@ TimestampTag

  implicit def toUserId(userId: String): UserId = userId.taggedWith[UserIdTag]

  implicit def toConversationId(conversationId: String): ConversationId = conversationId.taggedWith[ConversationIdTag]

  implicit def toTimestamp(timestamp: Long): Timestamp = timestamp.taggedWith[TimestampTag]

}
