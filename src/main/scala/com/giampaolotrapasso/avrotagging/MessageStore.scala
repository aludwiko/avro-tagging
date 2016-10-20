package com.giampaolotrapasso.avrotagging

import com.giampaolotrapasso.avrotagging.domain.MessageId

case class MessageStore(val mapOfMessages: Map[String, Message] = Map.empty[String, Message],
                        val index: Map[String, Int] = Map.empty[String, Int]) {

  //def this() = this(Map.empty[String, Message], Map.empty[String, Int])

  def size: Int = index.size

  def add(message: Message) = {
    new MessageStore(mapOfMessages = mapOfMessages + (message.position.toString -> message),
                     index = index + (message.uuid                              -> message.position))
  }

  private def positionOf(uuid: String) = {
    println("position of " + uuid + " is " + index(uuid))
    index(uuid).toString
  }

  def apply(messageId: MessageId): Message = {
    mapOfMessages(positionOf(messageId))
  }

  def apply(position: Int): Message = {
    mapOfMessages(position.toString)
  }

  def update(message: Message): MessageStore = {
    new MessageStore(
      mapOfMessages = mapOfMessages + (message.position.toString -> message),
      index
    )
  }

  def nextPosition: Int = mapOfMessages.keys.size + 1

  def lastMessage: Message = mapOfMessages(mapOfMessages.keys.size.toLong.toString)

  def contains(messageId: MessageId) = index.contains(messageId)

  def getMessages(range: Range.Inclusive): List[Message] = {
    range.map(i => mapOfMessages.get(i.toString)).filter(_.isDefined).flatten.toList.reverse
  }

  def updateMessageText(messageId: MessageId, newText: String): MessageStore = {
    println("*** messages " + mapOfMessages)
    println("*** index " + index)
    val oldMessage    = mapOfMessages(positionOf(messageId))
    val editedMessage = oldMessage.copy(text = Some(newText))
    update(editedMessage)
  }

}

object MessageStore {

  def empty = {
    new MessageStore()
  }
}
