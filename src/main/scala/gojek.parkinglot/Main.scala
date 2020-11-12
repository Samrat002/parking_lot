package gojek.parkinglot

import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.InputStreamReader
import gojek.parkinglot.exception.ErrorCode
import gojek.parkinglot.exception.ParkingException
import gojek.parkinglot.processor.RequestProcessor
import gojek.parkinglot.service.implementation.ParkingServiceImplementation

object Main {

  def main(args: Array[String]): Unit = {
    val processor = new RequestProcessor(new ParkingServiceImplementation)
    args.size match {
      case 0 => {
        while (true) try {
          val bufferReader = new BufferedReader(new InputStreamReader(System.in))
          val input = bufferReader.readLine()
          if (processor.validate(input))
            try processor.execute(input.trim) catch {
              case e: Exception => println(e.getMessage())
            }

        } catch {
          case e: Exception => throw new ParkingException(ErrorCode.INVALID_REQUEST.toString)
        }

      }
      case 1 => { // File input/output
        val inputFile = new File(args(0))
        try {
          val bufferReader = new BufferedReader(new FileReader(inputFile))

          var inputs = bufferReader.readLine()
          while (inputs != null) {
            val input = inputs.trim
            if (processor.validate(input))
              try processor.execute(input)
              catch {
                case e: Exception => println(e.getMessage())
              }
            inputs = bufferReader.readLine()
          }
        } catch {
          case e: Exception => {
            System.exit(0)
          }
          //throw new ParkingException(ErrorCode.INVALID_REQUEST.toString)
        }

      }
      case _ => println("Invalid input.")
    }
  }
}
