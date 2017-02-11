lazy val app = (project in file(".")).enablePlugins(ScalaJSPlugin).settings(
  name := "scala-js-react-sample",
  version := "1.0",
  scalaVersion := "2.12.1",
  libraryDependencies += "com.github.japgolly.scalajs-react" %%% "core" % "0.11.3",
  jsDependencies ++= Seq(
    "org.webjars.bower" % "react" % "15.3.2"
      / "react-with-addons.js"
      minified "react-with-addons.min.js"
      commonJSName "React",

    "org.webjars.bower" % "react" % "15.3.2"
      / "react-dom.js"
      minified "react-dom.min.js"
      dependsOn "react-with-addons.js"
      commonJSName "ReactDOM",

    "org.webjars.bower" % "react" % "15.3.2"
      / "react-dom-server.js"
      minified "react-dom-server.min.js"
      dependsOn "react-dom.js"
      commonJSName "ReactDOMServer")
)

