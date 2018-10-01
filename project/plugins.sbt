resolvers += "Typesafe Repository" at "https://repo.typesafe.com/typesafe/releases/"

addSbtPlugin("org.scala-js"       % "sbt-scalajs"              % "0.6.23")
addSbtPlugin("org.portable-scala" % "sbt-scalajs-crossproject" % "0.4.0")
addSbtPlugin("com.github.gseitz"  % "sbt-release"              % "1.0.9")
addSbtPlugin("org.scoverage"      % "sbt-scoverage"            % "1.5.1")
addSbtPlugin("com.codacy"         % "sbt-codacy-coverage"      % "1.3.11")
addSbtPlugin("com.jsuereth"       % "sbt-pgp"                  % "1.1.2")
addSbtPlugin("pl.project13.scala" % "sbt-jmh"                  % "0.3.4")
addSbtPlugin("org.xerial.sbt"     % "sbt-sonatype"             % "2.0")
addSbtPlugin("com.47deg"          % "sbt-microsites"           % "0.7.18")
addSbtPlugin("com.eed3si9n"       % "sbt-unidoc"               % "0.4.1")
addSbtPlugin("com.typesafe"       % "sbt-mima-plugin"          % "0.1.18")
addSbtPlugin("de.heikoseeberger"  % "sbt-header"               % "4.0.0")
addSbtPlugin("com.lucidchart"     % "sbt-scalafmt"             % "1.15")
addSbtPlugin("com.dwijnand"       % "sbt-travisci"             % "1.1.3")
