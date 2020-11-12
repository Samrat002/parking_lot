organization := "com.gojek.parking"

name := "parking-lot"

version := "1.0.0"

scalaVersion := "2.12.0"

resolvers += Classpaths.typesafeReleases

// https://mvnrepository.com/artifact/org.scalatest/scalatest
libraryDependencies += "org.scalatest" %% "scalatest" % "3.1.1" % "test"

assemblyOutputPath in assembly := baseDirectory.value / "distribution" / s"${name.value}-assembly-${version.value}.jar"


