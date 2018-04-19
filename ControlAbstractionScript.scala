
//REDUCING CODE DUPLICATION using higher order functions
//Lets create an API that allows user to search for files matching some criterion
//1st: add facility to search for files whose names end
//in a particular string

//we create a singleton object
object FileMatcher {
  private def filesHere = (new java.io.File(".")).listFiles()

  def filesEnding(query: String) =
  //don't need semi colon because its 1 conditional
    for (file <- filesHere if file.getName.endsWith(query))
      yield file

  //what if user cannot remember wha tthe ynamed file?
  //lets add functionality so that if user types in a string that could appear
  //anywhere in the string will show up
  def filesContaining(query: String) =
    for (file <- filesHere if file.getName.contains(query))
      yield file

  //and what if later, you want to implement a function with regexs?
  //so list all files with "pdf" in them E.G.
  def filesregex(query: String) =
    for (file <- filesHere if file.getName.matches(query))
      yield file
  //NOTICE THE REPETITION!!!!!


  //we keep having to refactor our function due to user requests
  //lets use a function value that calls a method that helps us
  //this will check a file name against a query
  def filesMatching(query: String, matcher: (String, String) => Boolean) = {
    //this check, matcher(string, string) takes 2 args and returns a boolean
    for (file <- filesHere; if matcher(file.getName, query))
      yield file
  }
  //using this helper method, we can now simplify the 3 search methods to these:
  def filesEnding2(query: String) = filesMatching(query, _.endsWith(_))

  def filesContaining2(query: String) = filesMatching(query, _.contains(_))

  def filesRegex(query: String) = filesMatching(query, _.matches(_)) //_ means takes 2 strings

}
//even more simplified version ,holy crap
object FileMatcher2 {
  private def filesHere = (new java.io.File(".")).listFiles


  private def filesMatching(matcher: String => Boolean) =
    for (file <- filesHere if matcher(file.getName))
      yield file

  def filesEnding(query: String) =
    filesMatching(_.endsWith(query))

  def filesContaining(query: String) =
    filesMatching(_.contains(query))

  def filesRegex(query: String) =
    filesMatching(_.matches(query))

//SIMPLIFYING CLIENT CODE
  //this is very bad
  def containsNeg(nums: List[Int]): Boolean = {
    var exists = false
    for (num <- nums)
      if (num < 0)
        exists = true
    exists
  }
  //most concise way to define methoths by using higher-order functions, like .exists as so:
  def containsNeg2(nums: List[Int]) = nums.exists(_ < 0)
  containsNeg(List(0, -1, -2))
  containsNeg2(List(0, -1, -2))

  //this represents a control abstraction, a special purpose looping construct
  //provided by the Scala library,
  //rather than being built into the Scala language like while or for.

  def containsOdd(nums: List[Int]): Boolean = {
    var exists = false
    for (num <- nums)
      if (num % 2 == 1)
        exists = true
    exists
  }
  //notice how containsOdd and containsNeg are exactly the same except the if conditional
  //the code duplication it reduces is client code of the Scala API in action

  //so use .exists, and rewrite containsOdd like so
  //UTILIZING SPECIAL PORUPOSE LOOPING CONSTRUCTS provided by the Scala library
  //rather than being built into language
  def containsOdd2(num: List[Int]) = num.exists(_ % 2 == 1)




}


//a control structure that performs an op 2x and
//returns a result
def twice(op: Double => Double, x: Double) =
  op(op(x))

println()

println(twice(_ + 1, 5))

//CAPTURE IN A CONTROL ABSTRACTION
//USING A METHOD LIKE THIS
/*def withPrintWriter(file: File, op: PrintWriter => Unit) {
  val writer = new PrintWriter(file)
  try {
    op(writer)
  } finally {
    writer.close()
  }
}*/

//here is a way to make client code look more like a built in control structure, by using
//curly braces instead of parentheses to surround an argument list.
println("Hello, World!")
println{"Hello, World!"}

//but this won't work
val g = "Hello, worrld!"
//does not work g.substring{7,9}
//yes works.
g.substring(7,9)
















