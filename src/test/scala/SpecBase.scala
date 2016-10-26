package com.giampaolotrapasso

import cats.scalatest.XorMatchers
import com.avrotagging._
import com.avrotagging.tags.{ConversationId, Timestamp, UserId}
import com.softwaremill.tagging.Tagger
import com.typesafe.scalalogging.LazyLogging
import org.scalacheck.Gen
import org.scalacheck.Gen._
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.prop.Checkers
import org.scalatest.{Matchers, OptionValues, WordSpecLike}

trait SpecBase
    extends WordSpecLike
    with Matchers
    with ScalaFutures
    with OptionValues
    with Checkers
    with XorMatchers
    with LazyLogging {

  lazy val genUserIds: Gen[UserId] = for {
    uuid <- choose(1, 100).map(_.toString)
  } yield uuid.toString.taggedWith[UserIdTag]

  lazy val getTimeStamp: Gen[(Timestamp)] =
    for {
      timestamp <- choose(1000000L, 100000000L)
    } yield timestamp.taggedWith[TimestampTag]

  lazy val getUser: Gen[User] =
    for {
      userId    <- genUserIds
      timestamp <- getTimeStamp
    } yield User(userId, timestamp)

  lazy val genConversationIds: Gen[ConversationId] = for {
    uuid <- choose(1, 100).map(_.toString)
  } yield uuid.toString.taggedWith[ConversationIdTag]

  lazy val genConversations: Gen[Conversation] = for {
    conversationId <- genConversationIds
  } yield Conversation("name", conversationId, listOfN(3, getUser).sample.value)
}
