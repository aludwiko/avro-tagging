import com.avrotagging.ConversationState
import com.giampaolotrapasso.SpecBase
import com.sksamuel.avro4s.AvroSchema
import play.api.libs.json.Json

class ConversationStateSchemaSpec extends SpecBase {

  "ConversationState" should {
    "create schema" in {

      // when
      val schema = AvroSchema[ConversationState]

      // then
      val prettySchema = Json.prettyPrint(Json.parse(schema.toString))
      println("ConversationState->" + prettySchema)
      prettySchema should be("""{
                               |  "type" : "record",
                               |  "name" : "ConversationState",
                               |  "namespace" : "com.avrotagging",
                               |  "fields" : [ {
                               |    "name" : "test",
                               |    "type" : "string"
                               |  }, {
                               |    "name" : "previousConversations",
                               |    "type" : {
                               |      "type" : "record",
                               |      "name" : "Conversations",
                               |      "fields" : [ {
                               |        "name" : "conversations",
                               |        "type" : {
                               |          "type" : "record",
                               |          "name" : "Map",
                               |          "namespace" : "scala.collection.immutable",
                               |          "fields" : [ ]
                               |        }
                               |      } ]
                               |    }
                               |  } ]
                               |}""".stripMargin)
    }

    "create schema with proper imports" in {
      // given
      import com.avrotagging.ConversationIdCustomMapping._
      import com.avrotagging.UserIdCustomMapping._
      import com.avrotagging.TimestampCustomMapping._

      // when
      val schema = AvroSchema[ConversationState]

      // then
      val prettySchema = Json.prettyPrint(Json.parse(schema.toString))
      println("ConversationState->" + prettySchema)
      prettySchema should be("""{
                               |  "type" : "record",
                               |  "name" : "ConversationState",
                               |  "namespace" : "com.avrotagging",
                               |  "fields" : [ {
                               |    "name" : "test",
                               |    "type" : "string"
                               |  }, {
                               |    "name" : "previousConversations",
                               |    "type" : {
                               |      "type" : "record",
                               |      "name" : "Conversations",
                               |      "fields" : [ {
                               |        "name" : "conversations",
                               |        "type" : {
                               |          "type" : "map",
                               |          "values" : {
                               |            "type" : "record",
                               |            "name" : "Conversation",
                               |            "fields" : [ {
                               |              "name" : "name",
                               |              "type" : "string"
                               |            }, {
                               |              "name" : "conversationId",
                               |              "type" : "string"
                               |            }, {
                               |              "name" : "deliveredTo",
                               |              "type" : {
                               |                "type" : "array",
                               |                "items" : {
                               |                  "type" : "record",
                               |                  "name" : "User",
                               |                  "fields" : [ {
                               |                    "name" : "userId",
                               |                    "type" : "string"
                               |                  }, {
                               |                    "name" : "timestamp",
                               |                    "type" : "long"
                               |                  } ]
                               |                }
                               |              }
                               |            } ]
                               |          }
                               |        }
                               |      } ]
                               |    }
                               |  } ]
                               |}""".stripMargin)
    }
  }
}
