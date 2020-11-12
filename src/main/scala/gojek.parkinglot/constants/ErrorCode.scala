package gojek.parkinglot.exception

object ErrorCode extends Enumeration{
  type ErrorCode = Value
  val PARKING_ALREADY_EXIST = Value("Sorry Parking already created, It can not be again recreated.")
  val PARKING_NOT_EXIST_ERROR = Value("Sorry, Car Parking Does not Exist")
  val INVALID_VALUE = Value("value is incorrect")
  val INVALID_FILE = Value("Invalid File")
  val PROCESSING_ERROR = Value("Processing Error")
  val INVALID_REQUEST = Value("Invalid Request")
  val NOT_AVAILABLE = -1
  val VEHICLE_ALREADY_EXIST = -2
  val NOT_FOUND = -1

}



