import sbt._

object Version {
  final val Scala      = "2.11.8"
  final val FastParse  = "0.3.7"
  final val ScalaCheck = "1.13.0"
}

object Library {
  val scalaCheck = "org.scalacheck" %% "scalacheck" % Version.ScalaCheck
  val fastParse = "com.lihaoyi" %% "fastparse" % Version.FastParse
}
