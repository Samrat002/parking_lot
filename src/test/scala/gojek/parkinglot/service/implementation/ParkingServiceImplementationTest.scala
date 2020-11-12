package gojek.parkinglot.service.implementation
import gojek.parkinglot.exception.ParkingException
import gojek.parkinglot.model.Car
import org.scalatest.flatspec.AnyFlatSpec
class ParkingServiceImplementationTest extends AnyFlatSpec {

  "datamanager" should "be only one" in {
    val parking = new ParkingServiceImplementation
    assert(parking.getDataManager === null)
  }

  "creating the parking lot" should "create one parking lot " in {
    val parking = new ParkingServiceImplementation
    assert(parking.createParkingLot(3) === "Created a parking lot with 3 slots")
    assertThrows[ParkingException](parking.createParkingLot(4))
  }

  "Park a car " should "only number of available slot" in {
    val parking = new ParkingServiceImplementation
    parking.createParkingLot(1)
    assert(parking.park(Car("KA-MM-1234", "Red")) === "Allocated slot number: 1")
    assert(parking.park(Car("KA-MM-1224", "Red")) === "Sorry, parking lot is full")
  }

  "Unpark a car" should "empty the slot in parking lot" in {
    val parking = new ParkingServiceImplementation
    parking.createParkingLot(1)
    parking.park(Car("KA-MM-1234", "Red"))
    assert(parking.unPark(1) === "Slot number 1 is free")
  }
}
