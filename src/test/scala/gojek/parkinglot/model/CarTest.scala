package gojek.parkinglot.model
import org.scalatest.flatspec.AnyFlatSpec
class CarTest extends AnyFlatSpec {
  "Car model test " should "give correct car detail" in {
    val car = Car("KA-01-HH-1234", "Red")
    assert(car.toString === """[registrationNo=KA-01-HH-1234, color=Red]""")
  }
}
