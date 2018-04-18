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
    private val recursiveMethods = MovingTools()
    /**
     * Initial assumption  :
     * all disks in first rod is different and 2rod,3rod are empty stacks
     */
    fun moveDisksToLoastRod() {
        //we can try to write this method in TDD way first Test after writing solution to pass it.
        while (isDone()) {
            fullyMoveOneDiskToThirdRod()


        }
    }

    private fun fullyMoveOneDiskToThirdRod() {
        if (moveDiskFromFirstToSecondRod()) {
            if (moveDiskFromSecondToThirdRod()) {
                //first alg iteration with pop() top element from the stack
                //good go back to top disk in 1rod
                return
            } else {
                while (secondRodDisks.peek() < thirdRodDisks.peek()) {
                    moveFromThirdToFirst()

                }
                thirdRodDisks.push(secondRodDisks.pop())
                //good disk from the 1rod was moved to 3rod
                //but all disks which was moved before is once more on 1rod
                //so we will continue with top (smallest disk in 1rod) -> it is the cost of task constrains
                return
            }
        } else {
            doSecondMove()
        }
    }

    private fun doSecondMove() {
        if (moveDiskFromSecondToThirdRod()) {

        } else {
            moveFromThirdToFirst()
        }
    }

    /**
     * this fun must move one disk from third to first ROD
     */
    private fun moveFromThirdToFirst() {
        if (tryToMoveDiskFromRodToRod(thirdRodDisks, secondRodDisks)) {
            if (tryToMoveDiskFromRodToRod(secondRodDisks, firstRodDisks)) {
                //good element was moved without any difficulties
                return

            } else {
                //in this step top disk in 1rod is bigger than top disk in 2rod so we can without problems -> move top disk from 1rod to 3rod
                tryToMoveDiskFromRodToRod(firstRodDisks, secondRodDisks)
                tryToMoveDiskFromRodToRod(secondRodDisks, thirdRodDisks)

                if (secondRodDisks.peek() < thirdRodDisks.peek()) {
                    //good we can move our element and finish this method
                    firstRodDisks.push(secondRodDisks.pop())
                    return
                } else {

                    //looks like we need to same actions again recursive method ? until we can move our element
                }

            }
        }

        //TODO here we need to move disks from 3rod to 1rod until (top disk in 3rod is < than top [fist and also top disk in 2rod] )
    }

    private fun recursiveSecondRodDiskToFirstRodDisk() {
        recursiveMethods.moveDiskFromRodToRod(secondRodDisks, firstRodDisks)
    }

    private fun recursiveSecondRodDiskToThirdRodDisk() {
        recursiveMethods.moveDiskFromRodToRod(secondRodDisks, thirdRodDisks)

    }

    private fun moveDiskFromSecondToThirdRod(): Boolean {
        return tryToMoveDiskFromRodToRod(secondRodDisks, thirdRodDisks)

    }

    private fun moveDiskFromFirstToSecondRod(): Boolean {
        return tryToMoveDiskFromRodToRod(firstRodDisks, secondRodDisks)

    }

    private fun isDone(): Boolean {
        TODO("not implemented ")
    }

    private fun tryToMoveDiskFromRodToRod(fromRod: ArrayStack<Int>, toRod: ArrayStack<Int>): Boolean {
        println("tryToMoveDiskFromRodToRod rodNumbers( " + fromRod.stackNumber + " , " + toRod.stackNumber + " )")
        return if (fromRod.peek() < toRod.peek()) {
            toRod.push(fromRod.pop())
            println("tryToMoveDiskFromRodToRod() moved successfully")
            true
        } else {
            println("tryToMoveDiskFromRodToRod() not moved")
            false
        }
    }

    // TODO add tasks to trello : (1 write few unit tests to check how it works with different input) try different input, etc)
    //TODO but this method will be used inside algorithm so less chances to use wrong input values)
    inner class MovingTools {
        /**
         * This method is only for moving disk from 2rod to 1rod or 3rod!
         */
        fun moveDiskFromRodToRod(fromRod: ArrayStack<Int>, toRod: ArrayStack<Int>) {
            println("moveDiskFromRodToRod( " + fromRod.stackNumber + " , " + toRod.stackNumber + " )")
            if (tryToMoveDiskFromRodToRod(fromRod, toRod)) {
                //good we can move our element and finish this method
                println("moveDiskFromRodToRod() was successful without recursion")

            } else {
                recursiveMoveDiskFromRodToRod(fromRod, toRod)

            }
        }

        /**
         * in this case the move is done from rod(toRod) to fromRod
         * and after from (fromRod) to (firstRod or ThirdRod)
         */
        private fun recursiveMoveDiskFromRodToRod(fromRod: ArrayStack<Int>, toRod: ArrayStack<Int>) {
            println("recursiveMoveDiskFromRodToRod( " + fromRod.stackNumber + " , " + toRod.stackNumber + " )")
            tryToMoveDiskFromRodToRod(toRod, fromRod) // we can this disk because fromRod > toRod
            printAllRods(firstRodDisks, secondRodDisks, thirdRodDisks)//testing
            when (toRod.stackNumber) {
                firstRodDisks.stackNumber -> {
                    moveSmallDiskFirstRod(fromRod, thirdRodDisks)

                }
                thirdRodDisks.stackNumber -> {
                    moveSmallDiskFirstRod(fromRod, firstRodDisks)

                }
                else -> throw UnsupportedOperationException("this method moves disk only to 2rod or 3rod")
            }
            println("recursiveMoveDiskFromRodToRod() try to move()main disk after changes")
            printAllRods(firstRodDisks, secondRodDisks, thirdRodDisks)
            moveDiskFromRodToRod(fromRod, toRod)//and now doing recursive call to try to move once more disk from 2rod to 3rod

        }

        //TODO rename this method
        private fun moveSmallDiskFirstRod(fromRod: ArrayStack<Int>, toRod: ArrayStack<Int>) {
            println("moveSmallDiskFirstRod()")
            if (tryToMoveDiskFromRodToRod(fromRod, toRod)) {
                return //we have successfully moved from fromRod to toRod

            } else {
                moveDiskFromRodToRod(fromRod, toRod)//can't move it so trying to move away one disk from toRod

            }
        }

    }

}


fun main(args: Array<String>) {
    //3 stacks -> rods
    // one is fill with disks ascending order from the top
    val rod1 = ArrayStack<Int>(4, 0)
    val rod2 = ArrayStack<Int>(4, 1)
    val rod3 = ArrayStack<Int>(4, 2)
    rod1.push(4)
    rod2.push(3)
    rod3.push(2)
    rod3.push(1)
    printAllRods(rod1, rod2, rod3)

    val towerOfHanoi = TowerOfHanoi(rod1, rod2, rod3)
    towerOfHanoi.MovingTools().moveDiskFromRodToRod(rod2, rod3)
    printAllRods(rod1, rod2, rod3)
}

fun printAllRods(rod1: ArrayStack<Int>, rod2: ArrayStack<Int>,
                 rod3: ArrayStack<Int>) {
    print("\nrod1 : ")
    rod1.printStack()
    print("\nrod2 : ")
    rod2.printStack()
    print("\nrod3 : ")
    rod3.printStack()
    println()


}