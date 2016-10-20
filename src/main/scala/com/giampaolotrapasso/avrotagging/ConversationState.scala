package com.giampaolotrapasso.avrotagging

import com.giampaolotrapasso.avrotagging.Cardinality.Single
import com.giampaolotrapasso.avrotagging.Visibility.Public

case class ConversationState(conversationId: String,
                             name: String,
                             owner: String,
                             users: Set[String],
                             messages: MessageStore = MessageStore.empty,
                             mediaId: Option[String],
                             creationTimestamp: Long,
                             cardinality: Cardinality = Single,
                             visibility: Visibility = Public,
                             conversationKey: Option[Array[Byte]] = None,
                             initializationVector: Option[Array[Byte]] = None)

object ConversationState {

  val Undefined = ConversationState(conversationId = "",
                                    name = "",
                                    owner = "-1",
                                    users = Set(),
                                    mediaId = None,
                                    creationTimestamp = 0L)
}
