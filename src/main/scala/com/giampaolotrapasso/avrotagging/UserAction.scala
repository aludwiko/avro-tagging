package com.giampaolotrapasso.avrotagging

import com.giampaolotrapasso.avrotagging.domain.{MessageId, Timestamp, UserId}

case class UserAction(userId: UserId, timestamp: Timestamp)
