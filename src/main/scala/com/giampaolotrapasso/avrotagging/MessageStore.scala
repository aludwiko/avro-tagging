package com.giampaolotrapasso.avrotagging

import com.giampaolotrapasso.avrotagging.domain.MessageId

case class MessageStore(val messages: Map[String, Message] = Map.empty[String, Message],
                        val index: Map[String, Int] = Map.empty[String, Int]) {

  //def this() = this(Map.empty[String, Message], Map.empty[String, Int])

  def size: Int = index.size

  def add(message: Message) = {
    new MessageStore(messages = messages + (message.position.toString -> message),
                     index = index + (message.uuid                    -> message.position))
  }

  private def positionOf(uuid: String) = {
    println("position of " + uuid + " is " + index(uuid))
    index(uuid).toString
  }

  def apply(messageId: MessageId): Message = {
    messages(positionOf(messageId))
  }

  def apply(position: Int): Message = {
    messages(position.toString)
  }

  def update(message: Message): MessageStore = {
    new MessageStore(
      messages = messages + (message.position.toString -> message),
      index
    )
  }

  def nextPosition: Int = messages.keys.size + 1

  def lastMessage: Message = messages(messages.keys.size.toLong.toString)

  def contains(messageId: MessageId) = index.contains(messageId)

  def getMessages(range: Range.Inclusive): List[Message] = {
    range.map(i => messages.get(i.toString)).filter(_.isDefined).flatten.toList.reverse
  }

  def updateMessageText(messageId: MessageId, newText: String): MessageStore = {
    println("*** messages " + messages)
    println("*** index " + index)
    val oldMessage    = messages(positionOf(messageId))
    val editedMessage = oldMessage.copy(text = Some(newText))
    update(editedMessage)
  }

}

object MessageStore {

  def empty = {
    new MessageStore()
  }
}
