
name := "profile"
 
version := "1.0" 
      
lazy val `profile` = (project in file(".")).enablePlugins(PlayScala)
routesGenerator := InjectedRoutesGenerator

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"

resolvers += "artifactory" at "http://artifactory.ximalaya.com/artifactory/libs-releases/"

resolvers += "Akka Snapshot Repository" at "http://repo.akka.io/snapshots/"
      
scalaVersion := "2.12.2"



val springVersion = "4.3.1.RELEASE"

libraryDependencies ++= Seq(
  jdbc ,
  ws ,
  guice,
  ehcache,
  filters,
  specs2 % Test ,
  "com.typesafe.play" %% "play-slick" % "3.0.0",
  "mysql" % "mysql-connector-java" % "5.1.21",
  "com.zaxxer"  % "HikariCP" % "2.7.6",
  "org.springframework.guice" % "spring-guice" % "1.0.0",
  "org.springframework" % "spring-core" % springVersion,
  "org.springframework" % "spring-context" % springVersion,
  "org.springframework" % "spring-beans" % springVersion,
  "org.springframework" % "spring-context-support" % springVersion,
  "org.springframework" % "spring-test" % springVersion % "test",
  "redis.clients"       % "jedis"       %  "2.8.0",//connect to redis using java, redis java driver
  "org.springframework.data" % "spring-data-redis" % "1.5.2.RELEASE",
  "javax.servlet"%"javax.servlet-api"%"3.1.0"





)


      