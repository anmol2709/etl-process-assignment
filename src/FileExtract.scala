/**
  * First Part
  * Read files from a directory
  * For each file read contents of the file
  * Capitalize the contents of the file
  * Then write the capitalized content into another output file with the same name in a different directory

  */


import java.io._

import scala.io.Source

//Input from Directory
class InputFile extends Input {
  override val fileNameInput = "./resources/inputs/demo-file.txt"

}

//Output to Directory
class OutputFile extends Output {
  override val fileNameOutput = "./resources/outputs/demo-file.txt"

}

class TransformToUpper extends Transform {

  //returns string in lower case
  override def transformation(inputData: String): String = {
    inputData.toUpperCase()
  }
}


object FileExtract extends App {
  try {
    val lines = Source.fromFile((new InputFile).fileNameInput).getLines()
    val writer = new PrintWriter((new OutputFile).fileNameOutput)
    for (line <- lines) writer.write(((new TransformToUpper).transformation(line)) + "\n")
    writer.close()
    println("File Successfully Copied")
  }
  catch {
    case ex: FileNotFoundException => {
      println("Missing file exception")
    }

    case ex: IOException => {
      println("IO Exception")
    }
  }
}
