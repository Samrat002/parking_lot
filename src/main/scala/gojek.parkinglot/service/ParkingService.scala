package gojek.parkinglot.service

import gojek.parkinglot.model.Car
import scala.util.Try

trait ParkingService extends Service {
  /**
   * actions
   */
  def createParkingLot( capacity:Int):String
  def park( vehicle:Car ):String
  def unPark( slotNumber:Int):String
  def  getStatus():String
  def getAvailableSlotsCount(): Try[Int]
  def getRegNumberForColour(color: String ):String
  def getSlotNumbersFromColour(colour:String):String
  def getSlotNoFromRegistrationNo(registrationNo: String ):Int
}
