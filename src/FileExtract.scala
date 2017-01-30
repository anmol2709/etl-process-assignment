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
  override val fileNameInput = "./resources/inputs/"

}

//Output to Directory
class OutputFile extends Output {
  override val fileNameOutput = "./resources/outputs/"

}

class TransformToUpper extends Transform {

  //returns string in lower case
  override def transformation(inputData: String): String = {
    inputData.toUpperCase()
  }
}


object FileExtract extends App {
  try {

    val listOfFiles = (for {
      file <- new File((new InputFile).fileNameInput).listFiles

      if file.isFile

    } yield file).toList

    val fileNames = for {
      files <- listOfFiles
    } yield files.getName

    val listOfLines = for {
      files <- listOfFiles
    } yield Source.fromFile(files).getLines.mkString("\n")

    val outputFiles = for {
      i <- fileNames

    } yield (new OutputFile).fileNameOutput + i

    for (i <- fileNames.indices) {
      val writer = new PrintWriter(outputFiles(i))
      writer.write((new TransformToUpper).transformation(listOfLines(i)))
      writer.close
    }

  }


  catch {
    case ex: FileNotFoundException => {
      println("Missing file exception")
    }

    case ex: IOException => {
      println("IO Exception")
    }
      case ex: Exception => {
      println("Generic Exception")
    }
  }
}
