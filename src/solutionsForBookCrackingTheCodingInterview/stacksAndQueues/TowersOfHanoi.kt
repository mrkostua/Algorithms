package solutionsForBookCrackingTheCodingInterview.stacksAndQueues

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
class TowersOfHanoi(private val firstRodDisks: ArrayStack<Int>, private val secondRodDisks: ArrayStack<Int>,
                    private val thirdRodDisks: ArrayStack<Int>) {
     val expectedRod: MutableList<Int> = ArrayList(firstRodDisks.size + secondRodDisks.size + thirdRodDisks.size)

    init {
        expectedRod.addAll(firstRodDisks.getAllElements())
        expectedRod.addAll(secondRodDisks.getAllElements())
        expectedRod.addAll(thirdRodDisks.getAllElements())
        expectedRod.sortDescending()
    }

    /**
     * Initial assumption :
     * all disks in first rod is different and 2rod,3rod are empty stacks
     */
    fun moveAllDisksToLastRod() {
        println("moveAllDisksToLastRod()")
        var algIteration = 0
        while (!isDone()) {
            println("alg iteration is : " + algIteration++)
            if (tryToMoveDiskFromRodToRod(firstRodDisks, secondRodDisks)) {
                if (tryToMoveDiskFromRodToRod(secondRodDisks, thirdRodDisks)) {
                    continue

                } else {
                    MovingTools().moveDiskFromRodToRod(secondRodDisks, thirdRodDisks)

                }
            } else {
                throw UnsupportedOperationException("scenario with blocked move from rod1 to rod2 is unsupported")

            }
        }
    }

    private fun isDone() = firstRodDisks.isEmpty() && secondRodDisks.isEmpty() && thirdRodDisks.containsAll(expectedRod)

    private fun tryToMoveDiskFromRodToRod(fromRod: ArrayStack<Int>, toRod: ArrayStack<Int>): Boolean {
        println("tryToMoveDiskFromRodToRod rodNumbers( " + fromRod.stackNumber + " , " + toRod.stackNumber + " )")
        if (fromRod.isEmpty()) {
            throw UnsupportedOperationException(" fromRod stack can't be empty")

        }
        return if (toRod.isEmpty() || fromRod.peek() < toRod.peek()) {
            toRod.push(fromRod.pop())
            println("tryToMoveDiskFromRodToRod() moved successfully")
            true
        } else {
            println("tryToMoveDiskFromRodToRod() not moved")
            false
        }
    }

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
                else -> throw UnsupportedOperationException("this method moves disk only to 1rod or 3rod")
            }
            println("recursiveMoveDiskFromRodToRod() try to move main disk after changes")
            printAllRods(firstRodDisks, secondRodDisks, thirdRodDisks)
            moveDiskFromRodToRod(fromRod, toRod)//and now doing recursive call to try to move once more disk from 2rod to 3rod

        }

        //TODO rename this method
        private fun moveSmallDiskFirstRod(fromRod: ArrayStack<Int>, toRod: ArrayStack<Int>) {
            println("moveSmallDiskFirstRod( " + fromRod.stackNumber + " , " + toRod.stackNumber + " )")
            if (tryToMoveDiskFromRodToRod(fromRod, toRod)) {
                return //we have successfully moved from fromRod to toRod

            } else {
                moveDiskFromRodToRod(fromRod, toRod)//can't move it so trying to move away one disk from toRod

            }
        }

    }

    fun printAllRods(rod1: ArrayStack<Int> = firstRodDisks, rod2: ArrayStack<Int> = secondRodDisks,
                     rod3: ArrayStack<Int> = thirdRodDisks) {
        print("\nrod1 : ")
        rod1.printStack()
        print("\nrod2 : ")
        rod2.printStack()
        print("\nrod3 : ")
        rod3.printStack()
        println()
    }
}