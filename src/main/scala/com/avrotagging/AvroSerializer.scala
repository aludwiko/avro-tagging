package com.avrotagging

import java.io.ByteArrayOutputStream

import cats.data.Xor
import com.sksamuel.avro4s._

class AvroSerializer {

  def serialize[T: SchemaFor: ToRecord](obj: T): Array[Byte] = {
    val output = new ByteArrayOutputStream
    val avro   = AvroOutputStream.data[T](output)
    avro.write(obj)
    avro.close()
    output.toByteArray
  }

  def deserialize[T: SchemaFor: FromRecord](bytes: Array[Byte]): T = {
    val inputStream = AvroInputStream.data[T](bytes)
    try {
      inputStream.iterator().toSet.headOption match {
        case Some(value) => value
        case None =>
          throw new IllegalArgumentException("Too short array")
      }
    } finally {
      inputStream.close()
    }
  }

  def deserializeToXor[T: SchemaFor: FromRecord](byteArray: Array[Byte]): Xor[Throwable, T] = {
    Xor.catchNonFatal {
      deserialize(byteArray)
    }
  }
}
