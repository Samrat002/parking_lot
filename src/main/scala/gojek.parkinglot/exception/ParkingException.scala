package gojek.parkinglot.exception

/**
 * Custom Exception class to raise exception specific to the parking Lot
 */
class ParkingException (
                         val message: String
                       )extends Exception{
}
