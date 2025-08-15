import sbt._

object Dependencies {

  val compile: Seq[ModuleID] = Seq(
    "uk.gov.hmrc" %% "performance-test-runner"   % "6.2.0"
  )
}
