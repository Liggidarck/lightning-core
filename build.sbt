name := "lightning-server"

version := "0.1"

scalaVersion := "2.13.6"

lazy val akkaVersion = "2.6.8"
lazy val akkaHttpVersion = "10.1.11"


libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor-typed"             % akkaVersion,
  "com.typesafe.akka" %% "akka-http"                    % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-http-jackson"            % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-stream"                  % akkaVersion,

  "ch.qos.logback" % "logback-classic"                  % "1.2.3",

  "commons-lang"       % "commons-lang"                 % "2.6",

  "com.typesafe.akka" %% "akka-testkit"                 % akkaVersion     % Test,
  "com.typesafe.akka" %% "akka-http-testkit"            % akkaHttpVersion % Test,
  "com.typesafe.akka" %% "akka-actor-testkit-typed"     % akkaVersion     % Test,
  "junit"              % "junit"                        % "4.12"          % Test,
  "com.novocode"       % "junit-interface"              % "0.10"          % Test,
  "org.mockito"        % "mockito-core"                 % "5.11.0"        % Test,
  "org.mock-server"    % "mockserver-netty"             % "5.11.1"        % Test,
)

