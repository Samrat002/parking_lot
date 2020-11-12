package gojek.parkinglot.processor
import gojek.parkinglot.model.Car
import gojek.parkinglot.service.ParkingService
import gojek.parkinglot.constants.{Constants, InputCommand}
import gojek.parkinglot.exception.ErrorCode
import gojek.parkinglot.exception.ParkingException

import scala.util.Try

class RequestProcessor(val parkingService: ParkingService) extends Processor{

  override def validate(input: String): Boolean = Try {
    val inputs = input.split(" ")
    val params = InputCommand.getCommandParameterMap.get(inputs(0))
    val isValid = inputs.size match  {
      case 1 => if (params.get != 0) false else true // e.g status -> inputs = 1
      case 2 => if (params.get != 1) false else true // create_parking_lot 6 -> inputs = 2
      case 3 => if (params.get != 2)  false else true // park KA-01-P-333 White -> inputs = 3
      case _ => true
    }
    isValid
  } getOrElse false

  override def execute(action: String): Unit = {
    // assuming only one level in the parking lot
    val inputs = action.split(" ")
    val key = inputs(0)

    key match  {
      case Constants.CREATE_PARKING_LOT => Try {
        val capacity = inputs(1).toInt
        println(parkingService.createParkingLot(capacity))
      } getOrElse {throw new ParkingException(ErrorCode.PARKING_ALREADY_EXIST.toString)}

      case Constants.PARK => {
        println(parkingService.park(
          Car(registrationNo = inputs(1),
            colour = inputs(2))
        ))
      }

      case Constants.LEAVE => Try {
        val slotNumber = inputs(1).toInt
        println(parkingService.unPark(slotNumber))
      }getOrElse(throw new ParkingException(ErrorCode.PROCESSING_ERROR.toString))

      case Constants.STATUS => println(parkingService.getStatus())

      case Constants.REG_NUMBER_FOR_CARS_WITH_COLOUR => println(parkingService.getRegNumberForColour(inputs(1)))

      case Constants.SLOTS_NUMBER_FOR_CARS_WITH_COLOUR => println(parkingService.getSlotNumbersFromColour(inputs(1)))

      case Constants.SLOTS_NUMBER_FOR_REG_NUMBER => {
        val d = parkingService.getSlotNoFromRegistrationNo( inputs(1))
        if (d ==  ErrorCode.NOT_FOUND) println("Not found") else println(d)
      }

      case _ => None
    }
  }
}
