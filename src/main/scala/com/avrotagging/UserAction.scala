package com.avrotagging

import com.avrotagging.domain.{MessageId, Timestamp, UserId}

case class UserAction(userId: UserId, timestamp: Timestamp)
