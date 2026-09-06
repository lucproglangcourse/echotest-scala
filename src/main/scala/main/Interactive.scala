package edu.luc.cs.cs371.echo
package main

import edu.luc.cs.cs371.echo.impl.{DoubleEcho, SimpleEcho}
import mainargs.{Flag, ParserForMethods, arg, main}

object Interactive:

  val promptString = "Enter a message to echo (EOF to exit) >"

  // internal main method with arguments annotated for parsing
  @main
  def run(
      @arg(short = 'd', doc = "double echo") doubleEcho: Flag,
      @arg(short = 'p', doc = "enable input prompts") prompt: Flag
  ) =
    // prompt by default if reading from stdin
    val doPrompt = prompt.value || isInputFromTerminal

    val echo = if doubleEcho.value then new DoubleEcho else new SimpleEcho
    if doPrompt then
      print(promptString)
    var line = Option(scala.io.StdIn.readLine())
    while line.isDefined do
      println(echo.echo(line.get))
      // terminate on I/O error such as SIGPIPE
      if scala.sys.process.stdout.checkError() then
        sys.exit(1)
      if doPrompt then
        print(promptString)
      line = Option(scala.io.StdIn.readLine())

  // external entry point into Scala application
  def main(args: Array[String]): Unit =
    ParserForMethods(this).runOrExit(args.toIndexedSeq)
    ()

  private def isInputFromTerminal: Boolean =
    System.console() != null ||
      System.getProperty("os.name").nn.toLowerCase.nn.contains("windows") &&
        sys.process.stdin.available() == 0

end Interactive
