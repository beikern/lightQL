import sbt._

object Version {
  final val ScalaVersions       = Seq("2.11.11", "2.12.3")
  final val ScalaTest           = "3.0.4"
  final val FastParse           = "1.0.0"
  final val Elastic4s           = "5.5.5"
  final val NscalaTime          = "2.16.0"
  final val Log4j               = "2.8.2"
  final val Scalafmt            = "1.3.0"
  final val Dsl                 = "0.5.0"
  final val ElasticMaterializer = "5.5.0"
}

object Library {
  val fastParse        = "com.lihaoyi"              %% "fastparse"         % Version.FastParse
  val elastic4s        = "com.sksamuel.elastic4s"   %% "elastic4s-core"    % Version.Elastic4s
  val elastic4sTcp     = "com.sksamuel.elastic4s"   %% "elastic4s-tcp"     % Version.Elastic4s
  val elastic4sHttp    = "com.sksamuel.elastic4s"   %% "elastic4s-http"    % Version.Elastic4s
  val elastic4sTestkit = "com.sksamuel.elastic4s"   %% "elastic4s-testkit" % Version.Elastic4s
  val nscalaTime       = "com.github.nscala-time"   %% "nscala-time"       % Version.NscalaTime
  val scalaTest        = "org.scalatest"            %% "scalatest"         % Version.ScalaTest
  val log4jCore        = "org.apache.logging.log4j" % "log4j-core"         % Version.Log4j
  val log4jSlfj4Impl   = "org.apache.logging.log4j" % "log4j-slf4j-impl"   % Version.Log4j
}
