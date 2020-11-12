package gojek.parkinglot.core

import gojek.parkinglot.model.Car

trait ParkingDataManager[Vehicle] {

  // return the slot number alloted for the car
  def parkCar(vehicle: Car): Int

  // returns success or failure
  def leaveCar(slotNumber: Int): Boolean

  // shows the complete parking lot preview on current state
  def getStatus: String

  // returns all the cars reg. numbers whose color matches to the given color in params
  def getRegNumberForColour(color: String): String

  // returns all the slots in parking lot that is occupied by the given color cars
  def getSlotNumbersFromColour(colour: String): String

  // find the slot number in which the car is parked
  def getSlotNoFromRegistrationNo(registrationNo: String): Int

  def getAvailableSlotsCount: Int

  def getCapacity:Int


}
