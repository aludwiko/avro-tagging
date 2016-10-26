package com.giampaolotrapasso

import com.avrotagging.{AkkaAvroSerializer, ConversationState}
import com.avrotagging._
import com.sksamuel.avro4s.AvroSchema
import org.scalacheck.Gen._
import com.avrotagging.ConversationIdCustomMapping._
import com.avrotagging.UserIdCustomMapping._
import com.avrotagging.TimestampCustomMapping._
import play.api.libs.json.Json


class ConversationStateSerializationSpec extends SpecBase {

  val avroSerializer = new AvroSerializer
  val serializer = new AkkaAvroSerializer(avroSerializer)

  "ConversationCreatedEvent" should {
    "deserialize 2 version" in {
      val previousConversations = Conversations(listOfN(10, genConversations).sample.value.map(c => c.conversationId -> c).toMap)
      val state = ConversationState("test", previousConversations)
//      val state = ConversationState("na")

      val schema1 = AvroSchema[ConversationState]
      val schema2 = AvroSchema[ConversationState2]
      println("ConversationState1->" + Json.prettyPrint(Json.parse(schema1.toString)))
      println("ConversationState2->" + Json.prettyPrint(Json.parse(schema2.toString)))

      val binary       = serializer.toBinary(state)
      val deserialized = avroSerializer.deserialize[ConversationState2](binary)

      val conversations = deserialized.previousConversations.conversations
      conversations should have size(previousConversations.conversations.size)
      conversations.values.head.deliveredTo(0).newField should be("default value")
    }

    "be serialized and deserialized" in {

      val previousConversations = Conversations(listOfN(10, genConversations).sample.value.map(c => c.conversationId -> c).toMap)
      val state = ConversationState("na", previousConversations)

      // when
      val binary       = serializer.toBinary(state)
      val deserialized = serializer.fromBinary(binary, state.getClass.getName).asInstanceOf[ConversationState]

      // then
      deserialized should be(state)
    }


    "be serialized and deserialized with empty map" in {

      val previousConversations = Conversations()
      val state = ConversationState("na", previousConversations)

      // when
      val binary       = serializer.toBinary(state)
      val deserialized = serializer.fromBinary(binary, state.getClass.getName).asInstanceOf[ConversationState]

      // then
      deserialized should be(state)
    }
  }
}
