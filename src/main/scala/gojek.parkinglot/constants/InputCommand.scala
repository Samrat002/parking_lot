package gojek.parkinglot.constants
import Constants._

object InputCommand  {
  val commandParameterMap = Map[String, Int](
    // number of parameter for the command
    CREATE_PARKING_LOT -> 1,
    PARK -> 2,
    LEAVE -> 1,
    STATUS -> 0,
    REG_NUMBER_FOR_CARS_WITH_COLOUR -> 1,
    SLOTS_NUMBER_FOR_CARS_WITH_COLOUR -> 1,
    SLOTS_NUMBER_FOR_REG_NUMBER -> 1
  )

  def getCommandParameterMap: Map[String, Int] = commandParameterMap


}
