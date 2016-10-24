package com.giampaolotrapasso

import com.avrotagging.{AkkaAvroSerializer, ConversationState}
import com.avrotagging._
import com.sksamuel.avro4s.{AvroInputStream, AvroSchema}
import org.scalacheck.Gen._
import com.avrotagging.domain.toConversationId
import com.avrotagging.ConversationIdCustomMapping._
import com.avrotagging.UserIdCustomMapping._
import com.avrotagging.TimestampCustomMapping._
import play.api.libs.json.Json


class AvroTaggingSpec extends SpecBase {

  val avroSerializer = new AvroSerializer
  val serializer = new AkkaAvroSerializer(avroSerializer)

  "ConversationCreatedEvent" should {
    "deserialize 2 version" in {
      val previousConversations = Conversations(listOfN(10, genConversations).sample.value.map(c => c.conversationId -> c).toMap)
      val state = ConversationState("na", previousConversations)
//      val state = ConversationState("na")

      val schema1 = AvroSchema[ConversationState]
      val schema2 = AvroSchema[ConversationState2]
      println("ConversationState1->" + Json.prettyPrint(Json.parse(schema1.toString)))
      println("ConversationState2->" + Json.prettyPrint(Json.parse(schema2.toString)))

      val binary       = serializer.toBinary(state)
      val deserialized = avroSerializer.deserialize[ConversationState2](binary)

      println(state)
      println(deserialized)
    }

    "be serialized and deserialized" in {

//      val l = listOfN(10, genMessage).sample.value
      val conversationId = genConversationIds.sample.value
      val conversation = genConversations.sample.value

//      val state = l.foldLeft(ConversationState.Undefined)((r, c) => r.copy(messages = r.messages.add(c)))
//      val previousConversations = Conversations(listOfN(10, genConversations).sample.value)
      val previousConversations = Conversations(listOfN(10, genConversations).sample.value.map(c => c.conversationId -> c).toMap)
      val state = ConversationState("na", previousConversations)

//      val s1 = AvroSchema[UserAction]
//      val s2  = AvroSchema[Message]
//      val s3 = AvroSchema[MessageStore]
      val schema = AvroSchema[ConversationState]

      val jsValue = Json.parse(schema.toString)
      // JsValue = {"foo":{"bar":{"baz":"T"}}}

      println(Json.prettyPrint(jsValue))

//      println("UserAction ->" + s1)
//      println("Message->" + s2)
//      println("MessageStore->" + s3)
      println("ConversationState->" + schema)

      // when
      val binary       = serializer.toBinary(state)
      val deserialized = serializer.fromBinary(binary, state.getClass.getName).asInstanceOf[ConversationState]

      // then
      deserialized should be(state)
      println(state)
      println(deserialized)

//      val s1 = """
//        {"type":"record","name":"ConversationState","namespace":"com.giampaolotrapasso.avrotagging","fields":[{"name":"conversationId","type":"string"},{"name":"name","type":"string"},{"name":"owner","type":"string"},{"name":"users","type":{"type":"array","items":"string"}},{"name":"messages","type":{"type":"record","name":"MessageStore","fields":[{"name":"messages","type":{"type":"map","values":{"type":"record","name":"Message","fields":[{"name":"uuid","type":"string"},{"name":"position","type":"int"},{"name":"deliveredTo","type":{"type":"array","items":{"type":"record","name":"UserAction","fields":[{"name":"userId","type":"string"},{"name":"timestamp","type":"long"}]}}},{"name":"readBy","type":{"type":"map","values":"long"}},{"name":"sender","type":"string"},{"name":"text","type":["null","string"]},{"name":"mediaId","type":["null","string"]},{"name":"removed","type":"boolean"},{"name":"createdOn","type":"long"}]}},"default":{}},{"name":"index","type":{"type":"map","values":"int"},"default":{}}]},"default":"MessageStore(Map(),Map())"},{"name":"mediaId","type":["null","string"]},{"name":"creationTimestamp","type":"long"},{"name":"cardinality","type":{"type":"record","name":"Cardinality","fields":[]},"default":"Single"},{"name":"visibility","type":{"type":"record","name":"Visibility","fields":[]},"default":"Public"},{"name":"conversationKey","type":["null","bytes"],"default":null},{"name":"initializationVector","type":["null","bytes"],"default":null}]}
//      """.stripMargin
//      val s2 =
//        """
//          |{"type":"record","name":"ConversationState","namespace":"com.giampaolotrapasso.avrotagging","fields":[{"name":"conversationId","type":"string"},{"name":"name","type":"string"},{"name":"owner","type":"string"},{"name":"users","type":{"type":"array","items":"string"}},{"name":"messages","type":{"type":"record","name":"MessageStore","fields":[{"name":"messages","type":{"type":"record","name":"Map","namespace":"scala.collection.immutable","fields":[]},"default":{}},{"name":"index","type":{"type":"map","values":"int"},"default":{}}]},"default":"MessageStore(Map(),Map())"},{"name":"mediaId","type":["null","string"]},{"name":"creationTimestamp","type":"long"},{"name":"cardinality","type":{"type":"record","name":"Cardinality","fields":[]},"default":"Single"},{"name":"visibility","type":{"type":"record","name":"Visibility","fields":[]},"default":"Public"},{"name":"conversationKey","type":["null","bytes"],"default":null},{"name":"initializationVector","type":["null","bytes"],"default":null}]}
//        """.stripMargin
//
//      println(Json.prettyPrint(Json.parse(s1)))
//      println(Json.prettyPrint(Json.parse(s2)))
    }
  }
}
