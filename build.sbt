name := "profile"
 
version := "1.0" 
      
lazy val `profile` = (project in file(".")).enablePlugins(PlayScala)

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"
      
resolvers += "Akka Snapshot Repository" at "http://repo.akka.io/snapshots/"
      
scalaVersion := "2.12.2"

val springVersion = "4.3.1.RELEASE"

libraryDependencies ++= Seq(
  jdbc ,
  ehcache ,
  ws ,
  specs2 % Test ,
  guice,
  "com.typesafe.play" %% "play-slick" % "3.0.0",
  "mysql" % "mysql-connector-java" % "5.1.21",
  "com.zaxxer"  % "HikariCP" % "2.7.6",
  "org.springframework.guice" % "spring-guice" % "1.0.0",
  "org.springframework" % "spring-core" % springVersion,
  "org.springframework" % "spring-context" % springVersion,
  "org.springframework" % "spring-beans" % springVersion,
  "org.springframework" % "spring-context-support" % springVersion,
  "org.springframework" % "spring-test" % springVersion % "test"
)

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )  

      