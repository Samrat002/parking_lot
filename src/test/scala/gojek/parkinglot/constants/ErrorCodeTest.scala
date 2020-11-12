package gojek.parkinglot.constants

import gojek.parkinglot.exception.ErrorCode
import org.scalatest.flatspec.AnyFlatSpec

class ErrorCodeTest extends AnyFlatSpec {
  val PARKING_ALREADY_EXIST = "Sorry Parking already created, It can not be again recreated."
  val PARKING_NOT_EXIST_ERROR = "Sorry, Car Parking Does not Exist"
  val INVALID_VALUE = "value is incorrect"
  val INVALID_FILE = "Invalid File"
  val PROCESSING_ERROR = "Processing Error"
  val INVALID_REQUEST = "Invalid Request"
  assert(ErrorCode.PARKING_ALREADY_EXIST.toString === PARKING_ALREADY_EXIST)
  assert(ErrorCode.PARKING_NOT_EXIST_ERROR.toString === PARKING_NOT_EXIST_ERROR)
  assert(ErrorCode.INVALID_VALUE.toString === INVALID_VALUE)
  assert(ErrorCode.INVALID_FILE.toString === INVALID_FILE)
  assert(ErrorCode.PROCESSING_ERROR.toString === PROCESSING_ERROR)
  assert(ErrorCode.INVALID_REQUEST.toString === INVALID_REQUEST)
}
