
name := "Demo"

version := "0.1"

scalaVersion := "2.11.2"

scalacOptions := Seq("-encoding", "utf8",
                   "-target:jvm-1.7",
                   "-feature",
                   "-language:implicitConversions",
                   "-language:postfixOps",
                   "-unchecked",
                   "-deprecation",
                   "-Xlog-reflective-calls"
                  )

mainClass := Some("flca.demo.Demo")

unmanagedSourceDirectories in Compile <++= baseDirectory { base =>
  Seq(
    base / "src-gen",
    base / "src-gen-test",
    base / "src/main/resources"
  )
}

resolvers ++= Seq("Sonatype Releases"   at "http://oss.sonatype.org/content/repositories/releases",
                "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/",
                "Spray Repository"    at "http://repo.spray.io/",
                "Base64 Repo"         at "http://dl.bintray.com/content/softprops/maven"           )

libraryDependencies ++= {
  val akkaVersion  = "2.3.8"
  Seq(
    "com.typesafe.akka"       %%  "akka-actor"           % akkaVersion,
    "com.typesafe.akka"       %%  "akka-slf4j"           % akkaVersion,
    "com.typesafe.akka"       %%  "akka-testkit"           % akkaVersion % "test",
    "com.typesafe.akka" % "akka-stream-experimental_2.11" % "1.0-M2",
    "com.typesafe.akka" % "akka-http-experimental_2.11" % "1.0-M2",
    "com.typesafe.akka" % "akka-http-core-experimental_2.11" % "1.0-M2",
    "com.typesafe.slick"     %% "slick"                       % "2.1.0",
    "com.fasterxml.jackson.core" % "jackson-databind" % "2.4.3",
    "com.fasterxml.jackson.module" % "jackson-module-scala_2.11" % "2.4.3",
    "com.github.nscala-time"  %  "nscala-time_2.11"            % "1.2.0",
    "org.scalatest"         %% "scalatest"               % "2.1.7",
    "junit"               % "junit"                       % "4.10",    
    "ch.qos.logback"          %   "logback-classic"        % "1.0.12",
    "org.slf4j"           % "slf4j-nop"                   % "1.6.4",
    "org.apache.commons" % "commons-lang3" % "3.1",
    "com.h2database"         % "h2"                         % "1.4.182",
    "com.jolbox"           % "bonecp"                   % "0.8.0.RELEASE",
    "me.lessis" % "base64_2.11" % "0.1.1",
    "org.specs2"  %% "specs2"  % "2.3.12"
  )
 }
