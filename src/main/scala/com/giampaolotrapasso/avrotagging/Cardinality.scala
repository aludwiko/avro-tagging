package com.giampaolotrapasso.avrotagging

sealed trait Cardinality

object Cardinality {
  def from(value: String): Cardinality = {
    value.toString match {
      case "Group"  => Cardinality.Group
      case "Single" => Cardinality.Single
      case c        => sys.error("cardinality not found: " + c)
    }
  }

  case object Single extends Cardinality
  case object Group  extends Cardinality
}
