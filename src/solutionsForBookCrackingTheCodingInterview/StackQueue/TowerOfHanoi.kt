package solutionsForBookCrackingTheCodingInterview.StackQueue

/**
 * @author Kostiantyn Prysiazhnyi
 * @created on 4/9/2018
 * "Cracking the Coding Interview" task 3.4
 */

/**
 * TASK :
 * (A) Only one disk can be moved at a time.
 * (B) A disk is slid off the top of one rod onto the next rod.
 * (C) A disk can only be placed on top of a larger disk.
 * Write a program to move the disks from the first rod to the last using Stacks.
 */
/**
 * so min amount of moves to move all disks from first rod to last (3 rods) is (N-> amount of disks) N^3 (3 is amount of rods)
 *
 */
class TowerOfHanoi(private val firstRodDisks: ArrayStack<Int>, private val secondRodDisks: ArrayStack<Int>,
                   private val thirdRodDisks: ArrayStack<Int>) {
    private val minAmountOfMoves = Math.pow(firstRodDisks.size.toDouble(), 3.0).toInt()


    fun moveDisksToLastRod() {
        for (i in 0 until firstRodDisks.size) {
            move()

        }
    }
    //moves one disk from first to last rog -> inserting it in the bottom of the last rog.
    private fun move() {
        /**
         * our task is one block to the last rod one iteration is moving one disk to last rog (+having all smaller disks on top of moved disk)
         *  so in result :
         *  firstRog have size-1 element,
         *  second rog is empty,
         *  third rog contains all previous disks + 1 big rog in the bottom of stack.
         */
        val diskToMove = firstRodDisks.pop()
        if (secondRodDisks.isEmpty()) {
            secondRodDisks.push(diskToMove)

        } else {
            throw UnsupportedOperationException("during beginning of move() second rod must be empty")

        }

        //true only for first iteration of whole alg
        if (thirdRodDisks.isEmpty()) {
            thirdRodDisks.push(secondRodDisks.pop())

        } else {
            val lastRogSize = thirdRodDisks.size
            moveAllDisksFromLastToFirstRod()
            if (thirdRodDisks.isEmpty() && secondRodDisks.size == 1) {
                thirdRodDisks.push(secondRodDisks.pop())
                moveDisksBackToLastFromFirst(lastRogSize)

            } else {
                throw UnsupportedOperationException("moveAllDisksFromLastToFirstRod() not working correctly")

            }
        }

    }


    private fun moveAllDisksFromLastToFirstRod() {
        TODO("not implemented ")
    }

    private fun moveDisksBackToLastFromFirst(amountOfSmallDisks: Int) {
        TODO("not implemented ")

    }

    fun isDone(): Boolean {
        TODO("not implemented ")
    }
}


fun main(args: Array<String>) {
    //3 stacks -> rods
    // one is fill with disks ascending order from the top

}