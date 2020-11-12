package gojek.parkinglot.model

case class Car(
                registrationNo: String,
                colour: String
              ) extends Vehicle {
  override def toString: String = "[registrationNo=" + registrationNo + ", color=" + colour + "]"

}
