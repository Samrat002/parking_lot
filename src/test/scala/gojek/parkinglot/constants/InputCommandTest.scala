package gojek.parkinglot.constants

import org.scalatest.flatspec.AnyFlatSpec

class InputCommandTest extends AnyFlatSpec {

  "Check the input command map" should "provide all functionality command" in {
    val commandMap = InputCommand.commandParameterMap
    assert(InputCommand.getCommandParameterMap === commandMap)
  }
}
