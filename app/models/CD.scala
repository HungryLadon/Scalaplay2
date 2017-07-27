package models

import play.api.data.Forms._
import play.api.data._

import scala.collection.mutable.ArrayBuffer

/**
  * Created by Alok1 on 26/07/2017.
  */
 case class CD(name:String,price:Int) {

}
object CD{

  val createCD=Form(
    mapping(
      "name"->nonEmptyText,
      "price"->number
    )(CD.apply)(CD.unapply)
  )

  val cds = ArrayBuffer(
    CD("ASD", 123),
    CD("FGH", 456),
    CD("JKL", 789)
  )
}
