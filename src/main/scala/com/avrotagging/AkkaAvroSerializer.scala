package com.avrotagging

import akka.serialization.SerializerWithStringManifest
// IDE will show following import as unused. Don't remove them, otherwise avro4s will not work.
import com.sksamuel.avro4s._
import UserIdCustomMapping._
import ConversationIdCustomMapping._
import TimestampCustomMapping._

class AkkaAvroSerializer(avroSerializer: AvroSerializer) extends SerializerWithStringManifest {

  def this() {
    this(new AvroSerializer)
  }

  val Identifier = 12345

  override def identifier: Int = Identifier

  override def manifest(o: AnyRef): String = o.getClass.getName

  override def toBinary(o: AnyRef): Array[Byte] = {
    o match {

      case state: ConversationState => avroSerializer.serialize[ConversationState](state)
//      case state: ConversationState2 => avroSerializer.serialize[ConversationState2](state)

      case _ =>
        throw new IllegalStateException(s"Serialization for $o not supported. Check toBinary in AkkaAvroSerializer.")
    }
  }

  override def fromBinary(bytes: Array[Byte], manifest: String): AnyRef = {

    if (manifest == classOf[ConversationState].getName) {
      avroSerializer.deserialize[ConversationState](bytes)

//    } else if (manifest == classOf[ConversationState2].getName) {
//      avroSerializer.deserialize[ConversationState2](bytes)

    } else {
      throw new IllegalStateException(
        s"Deserialization for $manifest not supported. Check fromBinary method in AkkaAvroSerializer class.")
    }
  }
}
