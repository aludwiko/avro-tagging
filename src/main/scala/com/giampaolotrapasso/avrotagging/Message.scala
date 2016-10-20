package com.giampaolotrapasso.avrotagging

import com.giampaolotrapasso.avrotagging.domain.MessageId

case class Message(uuid: MessageId,
                   position: Int,
                   deliveredTo: List[UserAction],
                   readBy: Map[String, Long],
                   sender: String,
                   text: Option[String],
                   mediaId: Option[String],
                   removed: Boolean,
                   createdOn: Long)
