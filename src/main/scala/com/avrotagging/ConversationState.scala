package com.avrotagging

import com.avrotagging.domain.{ConversationId, Timestamp, UserId}

case class User(userId: UserId, timestamp: Timestamp)

case class Conversation(name: String, conversationId: ConversationId, deliveredTo: List[User])

case class Conversations(conversations: Map[String, Conversation] = Map.empty)

case class ConversationState(test: String, previousConversations: Conversations)

////new version

case class User2(userId: UserId, timestamp: Timestamp, newField: String = "default value")

case class Conversation2(name: String, conversationId: ConversationId, deliveredTo: List[User2])

case class Conversations2(conversations: Map[String, Conversation2])

case class ConversationState2(test: String, previousConversations: Conversations2)
