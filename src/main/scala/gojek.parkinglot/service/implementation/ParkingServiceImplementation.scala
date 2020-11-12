package gojek.parkinglot.service.implementation

import scala.util.Try

import gojek.parkinglot.core.ParkingDataManager
import gojek.parkinglot.core.implementation.ParkingManager
import gojek.parkinglot.exception.{ErrorCode, ParkingException}
import gojek.parkinglot.model.Car
import gojek.parkinglot.model.strategy.NearestFirstParkingStrategy
import gojek.parkinglot.service.ParkingService


class ParkingServiceImplementation extends ParkingService {

  private var dataManager: ParkingDataManager[Car] = null

  def getDataManager: ParkingDataManager[Car] = dataManager
  /**
   * actions
   */
  override def createParkingLot(capacity: Int): String = {
    if (dataManager != null) throw new ParkingException(ErrorCode.PARKING_ALREADY_EXIST.toString)
    val parkingStrategy = new NearestFirstParkingStrategy
    dataManager = new ParkingManager(capacity, parkingStrategy)
    s"Created a parking lot with $capacity slots"
  }

  override def park(vehicle: Car): String = {
    validateParkingLot()
    val allocatedSlotNo = Try(dataManager.parkCar(vehicle))
    if (allocatedSlotNo.isSuccess && allocatedSlotNo.get >= 0)
      s"Allocated slot number: ${allocatedSlotNo.get + 1}"
    else
      "Sorry, parking lot is full"
  }

  def validateParkingLot(): Unit = if (dataManager == null)
    throw new ParkingException(ErrorCode.PARKING_NOT_EXIST_ERROR.toString)

  override def unPark(slotNumber: Int): String = {
    val slot = slotNumber - 1
    validateParkingLot()
    try {
      if (slot >= dataManager.getCapacity)
        s"Slot number not available"
      else {
        dataManager.leaveCar(slot)
        s"Slot number $slotNumber is free"
      }

    } catch {
      case _: Exception =>
        throw new ParkingException(ErrorCode.INVALID_VALUE.toString)
    }
  }

  override def getStatus(): String = {
    validateParkingLot()
    try {
      val statusList = dataManager.getStatus
      if (statusList.isEmpty) "Sorry, parking lot is empty."
      else
        statusList
    } catch {
      case _: Exception => throw new ParkingException(ErrorCode.PROCESSING_ERROR.toString)
    }
  }

  override def getAvailableSlotsCount(): Try[Int] = Try {
      validateParkingLot()
      dataManager.getAvailableSlotsCount
    }

  override def getRegNumberForColour(colour: String): String =
    try {
      validateParkingLot()
      val registrationList = dataManager.getRegNumberForColour(colour)
      if (registrationList.isEmpty) "Not found"
      else registrationList
    } catch {
      case _: Exception => throw new ParkingException(ErrorCode.PROCESSING_ERROR.toString)
    }

  override def getSlotNumbersFromColour(colour: String): String = {
    validateParkingLot()
    dataManager.getSlotNumbersFromColour(colour)
  }

  override def getSlotNoFromRegistrationNo(registrationNo: String): Int =
    Try {
      validateParkingLot()
      val value = dataManager.getSlotNoFromRegistrationNo(registrationNo)
      if (value != -1) value + 1 else ErrorCode.NOT_FOUND
    } getOrElse ErrorCode.PROCESSING_ERROR.id

}
