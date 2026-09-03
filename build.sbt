name := "echotest-scala"

version := "0.4"

libraryDependencies ++= Seq(
  "com.lihaoyi"            %% "mainargs"          % "0.7.8",
  "org.scalatest"          %% "scalatest"         % "3.2.20"   % Test,
  "com.github.sbt.junit"    % "jupiter-interface" % JupiterKeys.jupiterVersion.value % Test,
  "org.scalacheck"         %% "scalacheck"        % "1.20.0"   % Test,
  "org.scalatestplus"      %% "scalacheck-1-19"   % "3.2.20.0" % Test
)

enablePlugins(JavaAppPackaging)
