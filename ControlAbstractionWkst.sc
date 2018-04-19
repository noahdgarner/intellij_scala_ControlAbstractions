//bad
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


val aList = List(0, 2, -2)
val aList2 = List(0, 1, -2)


containsNeg2(aList)
//concise way to use Scala collections API, the .exist
//to get rid of explicit checking
def containsOdd(num: List[Int]) = num.exists(_ % 2 == 1)
containsOdd(aList)
//CURRYING!!!!!!
//a curried function is applied to multple argument lists
//regular function
def plainOldSum(x: Int, y: Int) = x + y
plainOldSum(1, 2)

//curried function
def curriedSum(x: Int)(y: Int) = x + y
curriedSum(1)(2)

//basicaly this is happening
def first(x: Int) = (y: Int) => x + y
val second = first(1)
second(2)
//think like this
// it becomes (y: Int) => 1 + y
// which becomes second(2) => 1 + 2

//may also apply curriyng to partially applied functions
val onePlus = curriedSum(1)_
onePlus(2) //prints 3
//a control structure that performs an op 2x and
//returns a result
def twice(op: Double => Double, x: Double) =
  op(op(x))
//okay this is freaking crazy
twice(_ + 1, 5)

//consider a widely used coding pattern
//opening a resource, operating on it,
//and then closing the resource

//CAPTURE IN A CONTROL ABSTRACTION
//USING A METHOD LIKE THIS
def withPrintWriter(file: File, op: PrintWriter =>Unit) {
  val writer = new PrintWriter(file)
  try{
    op(writer)
  }
  finally {
    writer.close()
  }
}










