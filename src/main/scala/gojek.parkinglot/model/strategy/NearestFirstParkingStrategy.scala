package gojek.parkinglot.model.strategy

import scala.collection.mutable.TreeSet

class NearestFirstParkingStrategy extends ParkingStrategy {
  private val freeSlots = new TreeSet[Int]()

  override def add(i: Int): Unit = {
    freeSlots.add(i)
  }

  override def getSlot(): Int = freeSlots.firstKey

  override def removeSlot(slot: Int): Boolean = freeSlots.remove(slot)
}
