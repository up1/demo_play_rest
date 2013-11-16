name := "demo01"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  "com.jayway.restassured" % "rest-assured" % "1.8.0"
)     

play.Project.playJavaSettings
