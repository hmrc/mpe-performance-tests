import sbt._

object Dependencies {

  val compile: Seq[ModuleID] = Seq(
    "org.playframework" %% "play-ahc-ws-standalone" % "3.0.10",
    "uk.gov.hmrc" %% "performance-test-runner"   % "6.3.0",
    "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.20.1",
    "org.playframework" %% "play-ws-standalone-json" % "3.0.10"
  )
}
