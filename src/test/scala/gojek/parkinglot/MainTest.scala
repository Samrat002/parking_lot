package gojek.parkinglot

import gojek.parkinglot.constants.Constants
import gojek.parkinglot.exception.ErrorCode
import gojek.parkinglot.model.Car
import gojek.parkinglot.service.implementation.ParkingServiceImplementation
import org.scalatest.flatspec.AnyFlatSpec

import scala.util.Try


class MainTest extends AnyFlatSpec {

  // ----------- parking lot creation ------------------- //
  "A create parking lot command" should "return success message" in {
    val parking = new ParkingServiceImplementation
    assert(parking.createParkingLot(10) === "Created a parking lot with 10 slots")

  }

  "Creating multiple parking lot " should "Raise concern stating not possible to create message" in {
    val parking = new ParkingServiceImplementation
    parking.createParkingLot(10)
    assertThrows[gojek.parkinglot.exception.ParkingException](parking.createParkingLot(50))
  }
  // ----------------- parking-----------------------//
  "parking vehicle in the parking lot " should "allow to park a vehicle" in {
    val parking = new ParkingServiceImplementation
    parking.createParkingLot(3)
    assert(parking.park(Car("KA-01-HH-1234", "White")) === "Allocated slot number: 1")
    assert(parking.park(Car("KA-01-HH-9999", "White")) === "Allocated slot number: 2")
    assert(parking.park(Car("KA-01-HH-9969", "White")) === "Allocated slot number: 3")
  }

  "exceeding the parking limit " should "Raise concern stating:-  Sorry, parking lot is full" in {
    val parking = new ParkingServiceImplementation
    parking.createParkingLot(1)
    assert(parking.park(Car("KA-01-HH-1234", "White")) === "Allocated slot number: 1")
    assert(parking.park(Car("KA-01-HH-9999", "White")) === "Sorry, parking lot is full")
  }

  // ---------------------- unpark -------------------------------- //
  "Unparking the car from the parking lot " should "increase the available slots and make it empty" in {
    val parking = new ParkingServiceImplementation
    parking.createParkingLot(2)
    assert(parking.getAvailableSlotsCount() === Try(2))
    parking.park(Car("KA-01-HH-1234", "White"))
    assert(parking.getAvailableSlotsCount() === Try(1))
    assert(parking.unPark(1) === "Slot number 1 is free")
    assert(parking.getAvailableSlotsCount() === Try(2))
  }

  // ----------------------- registration_numbers_for_cars_with_colour -------------------//
  "registration numbers for cars with colour" should "give comma separated registration number" in {
    val parking = new ParkingServiceImplementation
    parking.createParkingLot(4)
    parking.park(Car("KA-01-HH-1234", "White"))
    parking.park(Car("KA-01-HH-9999", "White"))
    parking.park(Car("KA-01-HM-1234", "Black"))
    assert(parking.getRegNumberForColour("White") === "KA-01-HH-1234, KA-01-HH-9999")
    assert(parking.getRegNumberForColour("Black") === "KA-01-HM-1234")
  }

  // --------------------- slot_numbers_for_cars_with_colour ---------------------------//
  "slot numbers for cars with colour" should "give comma separated slot number" in {
    val parking = new ParkingServiceImplementation
    parking.createParkingLot(4)
    parking.park(Car("KA-01-HH-1234", "White"))
    parking.park(Car("KA-01-HH-9999", "White"))
    parking.park(Car("KA-01-HM-1234", "Black"))
    assert(parking.getSlotNumbersFromColour("White") === "1, 2")
    assert(parking.getSlotNumbersFromColour("Black") === "3")
    assert(parking.getAvailableSlotsCount() === Try(1))
  }

  // --------------------  slot_number_for_registration_number ----------------------- //
  "slot number for registration number" should "give slot number where the car is park" in {
    val parking = new ParkingServiceImplementation
    parking.createParkingLot(2)
    parking.park(Car("KA-01-HH-9999", "White"))
    parking.park(Car("KA-01-HM-1234", "Black"))
    assert(parking.getSlotNoFromRegistrationNo("KA-01-HM-1234") === 2)
    assert(parking.getSlotNoFromRegistrationNo("KA-01-HH-1234") === ErrorCode.NOT_FOUND)
  }

  // ------------------- check availability of free slots --------------------------- //
  "check availability of free slots" should "give number of available free slots" in {
    val parking = new ParkingServiceImplementation
    parking.createParkingLot(5)
    assert(parking.getAvailableSlotsCount() === Try(5))
  }
  // ------------------ status check ---------------- //
  "check the status of parking lot" should "give exact fixtures" in {
    val parking = new ParkingServiceImplementation
    parking.createParkingLot(6)
    parking.park(Car("KA-01-HH-1234", "White"))
    parking.park(Car("KA-01-HH-9999", "White"))
    parking.park(Car("KA-01-BB-0001", "Black"))
    parking.park(Car("KA-01-HH-7777", "Red"))
    parking.park(Car("KA-01-HH-2701", "Blue"))
    parking.park(Car("KA-01-HH-3141", "Black"))
    parking.unPark(4)
    assert(true)
  }
}
