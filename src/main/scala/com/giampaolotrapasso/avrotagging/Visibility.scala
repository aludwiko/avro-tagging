package com.giampaolotrapasso.avrotagging

import scalaz.Equal

sealed trait Visibility

object Visibility {
  implicit val visibilityEqual: Equal[Visibility] = new Equal[Visibility] {
    override def equal(a1: Visibility, a2: Visibility): Boolean = a1 == a2
  }

  def from(value: String): Visibility = {
    value match {
      case "Public" => Visibility.Public
      case "Secret" => Visibility.Secret
      case v        => sys.error("Visibility not found: " + v)
    }
  }

  case object Secret extends Visibility
  case object Public extends Visibility
}
