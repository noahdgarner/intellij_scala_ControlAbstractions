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



















