package com.giampaolotrapasso.avrotagging

import com.giampaolotrapasso.avrotagging.domain.{ConversationId, DeviceId, UserId}
import com.giampaolotrapasso.avrotagging.domain._

case class DeviceState(userId: UserId = UninitializedId,
                       userName: Option[String] = None,
                       deviceId: DeviceId = uninitializedDeviceId,
                       // undeliveredMessages: List[OutMessage] = List.empty,
                       encodedPublicKey: Option[String] = None,
                       mutedConversations: Set[ConversationId] = Set.empty)
