package com.giampaolotrapasso

import com.giampaolotrapasso.avrotagging._
import com.sksamuel.avro4s.AvroSchema
import org.scalacheck.Gen._


class AvroTaggingSpec extends SpecBase {
  "ConversationCreatedEvent" should {
    "be serialized and deserialized" in {

      val l = listOfN(10, genMessage).sample.value

      val state = l.foldLeft(ConversationState.Undefined)((r, c) => r.copy(messages = r.messages.add(c)))

      val s1 = AvroSchema[UserAction]
      val s2  = AvroSchema[Message]
      val s3 = AvroSchema[MessageStore]
      val s4 = AvroSchema[ConversationState]

      println("UserAction ->" + s1)
      println("Message->" + s2)
      println("MessageStore->" + s3)
      println("ConversationState->" + s4)

      val serializer = new AkkaAvroSerializer[ConversationState]()

      // when
      val binary       = serializer.toBinary(state)
      val deserialized = serializer.fromBinary(binary, state.getClass.getName).asInstanceOf[ConversationState]

      // then
      deserialized should be(state)

      true

    }
  }
}
