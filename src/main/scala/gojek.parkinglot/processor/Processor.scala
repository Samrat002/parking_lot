package gojek.parkinglot.processor


trait Processor {

  def validate(input:String):Boolean

  def execute(action:String):Unit

}
