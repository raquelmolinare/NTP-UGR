name := "recursividad"

version := "0.1"

scalaVersion := "2.13.8"

// para usar Scalameter
resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"
libraryDependencies += "com.storm-enroute" %% "scalameter-core" % "0.21"
testFrameworks += new TestFramework("org.scalameter.ScalaMeterFramework")
parallelExecution := false

// para usar ScalaCheck
libraryDependencies += "org.scalacheck" %% "scalacheck" % "1.15.4" % "test"

// para usar ScalaTest + JUnit
libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.11"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.11" % "test"
libraryDependencies += "org.scalatestplus" %% "junit-4-13" % "3.2.11.0" % "test"
resolvers += "Artima Maven Repository" at "https://repo.artima.com/releases"
