/**
  * Second Part:
  * Read all the unique words from the file
  * For each word calculate the word count
  * For eg: for a file containing the content
  * “Hello world, hello”
  * In this case the summary would be
  * hello -> 2  world -> 1
  * For simplicity cases should be ignored – Hello and hello mean the same word
  * This summary should then be added to an output file with the same name in a different directory
  */


import java.io._

import scala.io.Source

//Input from Directory
class InputFromDir extends Input {
  override val fileNameInput = "./resources/inputs/demo-file.txt"

}

//Output to Directory
class OutputToDir extends Output {
  override val fileNameOutput = "./resources/outputs/FrequencyChar.txt"

}

class TransformToLower extends Transform {

  //returns string in lower case
  override def transformation(inputData: String): String = {
    inputData.toLowerCase()
  }
}

object UniqueWords extends App {
  try {
    val data = Source.fromFile((new InputFromDir).fileNameInput).mkString
    val tempData = (((new TransformToLower).transformation(data)).split("\\W+")).toList
    val setOfWords = tempData.toSet
    val output = setOfWords.map(x => {
      val count = for (y <- tempData if (x == y)) yield 1
      (x, count.size)
    }).toMap

    val writer = new PrintWriter((new OutputToDir).fileNameOutput)
    for (line <- output) writer.write(line._1 + " -> " + line._2 + "\n")
    writer.close()
    println("File Successfully Edited")
  }

  catch {
    case ex: FileNotFoundException => {
      println("Missing file exception")
    }

    case ex: IOException => {
      println("IO Exception")
    }

    case _: Exception => {
      println("Generic Exception")
    }
  }

}
