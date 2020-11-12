package gojek.parkinglot.constants

import gojek.parkinglot.exception.ErrorCode
import org.scalatest.flatspec.AnyFlatSpec

class ConstantsTest extends AnyFlatSpec {
  "Verifying constants " should "provide equal constant count" in {
    val CREATE_PARKING_LOT = "create_parking_lot"
    val PARK = "park"
    val LEAVE = "leave"
    val STATUS = "status"
    val REG_NUMBER_FOR_CARS_WITH_COLOUR = "registration_numbers_for_cars_with_colour"
    val SLOTS_NUMBER_FOR_CARS_WITH_COLOUR = "slot_numbers_for_cars_with_colour"
    val SLOTS_NUMBER_FOR_REG_NUMBER = "slot_number_for_registration_number"
    val NOT_AVAILABLE = -1
    val VEHICLE_ALREADY_EXIST = -2
    val NOT_FOUND = -1
    assert(ErrorCode.NOT_FOUND === NOT_FOUND)
    assert(Constants.CREATE_PARKING_LOT === CREATE_PARKING_LOT)
    assert(Constants.PARK === PARK)
    assert(Constants.LEAVE === LEAVE)
    assert(Constants.STATUS === STATUS)
    assert(Constants.REG_NUMBER_FOR_CARS_WITH_COLOUR === REG_NUMBER_FOR_CARS_WITH_COLOUR)
    assert(Constants.SLOTS_NUMBER_FOR_CARS_WITH_COLOUR === SLOTS_NUMBER_FOR_CARS_WITH_COLOUR)
    assert(Constants.SLOTS_NUMBER_FOR_REG_NUMBER === SLOTS_NUMBER_FOR_REG_NUMBER)
    assert(ErrorCode.NOT_AVAILABLE === NOT_AVAILABLE)
    assert(ErrorCode.VEHICLE_ALREADY_EXIST === VEHICLE_ALREADY_EXIST)
  }
}
