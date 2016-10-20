package com.giampaolotrapasso

import cats.scalatest.XorMatchers
import com.giampaolotrapasso.avrotagging._
import com.giampaolotrapasso.avrotagging.domain._
import com.softwaremill.tagging.{@@, Tagger}
import com.typesafe.scalalogging.LazyLogging
import org.scalacheck.Arbitrary._
import org.scalacheck.Gen._
import org.scalacheck.{Arbitrary, Gen}
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.prop.Checkers
import org.scalatest.{Matchers, OptionValues, WordSpecLike}
import play.api.libs.json.{JsObject, JsString}


trait SpecBase
    extends WordSpecLike
    with Matchers
    with ScalaFutures
    with OptionValues
    with Checkers
    with XorMatchers
   // with AvroModule
    with LazyLogging {

  lazy val genUserIds: Gen[UserId] = for {
    uuid <- Gen.uuid
  } yield uuid.toString.taggedWith[UserIdTag]

  lazy val genMessageIds: Gen[MessageId] = for {
    uuid <- Gen.uuid
  } yield uuid.toString.taggedWith[MessageIdTag]

  lazy val genMediaIds: Gen[MediaId] = for {
    uuid <- Gen.uuid
  } yield uuid.toString.taggedWith[MediaIdTag]


  lazy val getUserTimeStamp: Gen[(String, Timestamp)] =
    for {
      userId    <- genUserIds
      timestamp <- choose(1000000L, 100000000L)
    } yield (userId.toString, timestamp.taggedWith[TimestampTag])

  lazy val getUserTimeStampUA: Gen[UserAction] =
    for {
      userId    <- genUserIds
      timestamp <- choose(1000000L, 100000000L)
    } yield UserAction(userId, timestamp.taggedWith[TimestampTag])

  lazy val genMessage: Gen[Message] = for {
    position    <- choose(1, 100)
    deliveredTo <- Gen.listOfN(2, getUserTimeStampUA)
    readBy      <- Gen.mapOfN(2, getUserTimeStamp)
    sender      <- genUserIds
    text    = Gen.alphaStr.sample
    mediaId = genMediaIds.sample
    timestamp              <- arbitrary[Int]
    destinationCountryCode <- choose(1, 100).map(n => s"+$n")
    destinationPhoneNumber <- choose(1000000, 100000000).map(_.toString)
    uuid                   <- genMessageIds
  } yield Message(uuid, position, deliveredTo, readBy, sender, text, mediaId, false, timestamp)
}
