package gojek.parkinglot.core.implementation

import gojek.parkinglot.constants.Constants
import gojek.parkinglot.core.ParkingDataManager
import gojek.parkinglot.exception.ErrorCode
import gojek.parkinglot.model.Car
import gojek.parkinglot.model.strategy.NearestFirstParkingStrategy

import scala.collection.mutable
import scala.util.Try


class ParkingManager(val capacity:Int,
                     val parkingStrategy: NearestFirstParkingStrategy) extends ParkingDataManager[Car]{

  // defining available slots
  // initially equal to the capacity
  private var availability = capacity

  private val parkingLotMap  = new mutable.HashMap[Int,Car]()

  // initializing the parking lot map with null denoting no cars present
  (0 until capacity).map(i =>{
    // adding free slot in the parking strategy
    parkingStrategy.add(i)
    parkingLotMap += (i -> null)
  })

  // getter for the capacity
  override def getCapacity: Int = capacity

  override def parkCar(c: Car): Int = {
    if(availability == 0)
      ErrorCode.NOT_AVAILABLE
    else{
      val slot = parkingStrategy.getSlot()
      parkingLotMap.put(slot, c)
      availability = availability - 1
      if(parkingStrategy.removeSlot(slot))
        slot
      else
        ErrorCode.VEHICLE_ALREADY_EXIST
    }

  }

  override def leaveCar(slotNum: Int): Boolean = {
    Try{
      availability = availability+1
      parkingStrategy.add(slotNum)
      parkingLotMap.put(slotNum, null)
      true
    } getOrElse{
      availability = availability-1
      false
    }

  }

  override def getStatus():String = {
    val init = "Slot No.    Registration No    Colour\n"
    init+(0 until capacity).filter(parkingLotMap(_)!= null).map(i=>{
      val car:Car =  parkingLotMap(i)
      s"${i+1}           ${car.registrationNo}      ${car.colour}"
    }).mkString("\n")

  }

  override def getRegNumberForColour(colour: String): String = {

    val regNo = (0 until capacity).filter(parkingLotMap(_)!= null).map(i => {
      val car = parkingLotMap(i)
      if(colour.equalsIgnoreCase(car.colour)) car.registrationNo
    }).toStream.filter(_.isInstanceOf[String])
    regNo.mkString(", ")
  }

  override def getSlotNumbersFromColour(colour: String): String = {
    val slotList: Seq[AnyVal] = (0 until capacity).filter(parkingLotMap(_)!=null).map(i => {
      val car = parkingLotMap(i)
      if(colour.equals(car.colour)) i+1 else ErrorCode.NOT_AVAILABLE
    }).filter(_!= ErrorCode.NOT_AVAILABLE).toList
    slotList.mkString(", ")
  }

  override def getSlotNoFromRegistrationNo(registrationNo: String): Int = {
    val slotwithreg = (0 until capacity).filter(parkingLotMap(_)!=null).map(s => {
      val regn = parkingLotMap(s).registrationNo
      if(regn.equalsIgnoreCase(registrationNo)) s
      else null
    }).filter(_ != null)
    if (slotwithreg.size == 1) slotwithreg(0).toString.toInt
    else ErrorCode.NOT_FOUND
  }

  override def getAvailableSlotsCount: Int = availability


}