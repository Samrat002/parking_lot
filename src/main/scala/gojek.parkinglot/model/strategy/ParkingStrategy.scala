package gojek.parkinglot.model.strategy

trait ParkingStrategy {

  def add(i:Int):Unit

  def getSlot():Int

  def removeSlot(slot:Int):Boolean
}
