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
        recursiveMethods.recursiveMoveDiskFromRodToRod(getNumberByRod(secondRodDisks), getNumberByRod(firstRodDisks))
    }

    private fun recursiveSecondRodDiskToThirdRodDisk() {
        recursiveMethods.recursiveMoveDiskFromRodToRod(getNumberByRod(secondRodDisks), getNumberByRod(thirdRodDisks))

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
        return if (fromRod.peek() > toRod.peek()) {
            toRod.push(fromRod.pop())
            true
        } else {
            false
        }
    }

    private fun getRodByNumber(rodNumber: Int): ArrayStack<Int> {
        return when (rodNumber) {
            0 -> firstRodDisks
            1 -> secondRodDisks
            2 -> thirdRodDisks
            else -> throw UnsupportedOperationException("max number of rods is 3 (0,1,2) ")
        }
    }

    private fun getNumberByRod(rod: ArrayStack<Int>): Int {
        return when (rod) {
            firstRodDisks -> 0
            secondRodDisks -> 1
            thirdRodDisks -> 2
            else -> throw UnsupportedOperationException("max number of rods is 3 (0,1,2) ")
        }
    }

    // TODO work on this method and test it before using in algorithm
    inner class MovingTools {
        private lateinit var fromRod: ArrayStack<Int>
        private lateinit var toRod: ArrayStack<Int>

        /**
         * This method is only for moving disk from 2rod to 1rod or 3rod!
         */
        fun recursiveMoveDiskFromRodToRod(fromRodNumber: Int, toRodNumber: Int) {
            fromRod = getRodByNumber(fromRodNumber)
            toRod = getRodByNumber(toRodNumber)
            if (fromRod.peek() > toRod.peek()) {
                //good we can move our element and finish this method
                toRod.push(fromRod.pop())

            } else {
                moveSmallDiskAndContinueRefactoring(fromRodNumber, toRodNumber)

            }
        }

        /**
         * in this case the move is done from rod(toRod) to fromRod
         * and after from (fromRod) to (firstRod or ThirdRod)
         */
        //TODO after testing this method can be smaller
        private fun moveSmallDiskAndContinueRefactoring(fromRodNumber: Int, toRodNumber: Int) {
            tryToMoveDiskFromRodToRod(toRod, fromRod)
            when (toRodNumber) {
                getNumberByRod(firstRodDisks) -> {
                    if (fromRod.peek() > getRodByNumber(fromRodNumber + 1).peek()) {
                        tryToMoveDiskFromRodToRod(fromRod, getRodByNumber(fromRodNumber + 1))
                        //we have successfully moved from 1rod to 3rod and now doing recursive call to try to move once more disk from 2rod to 1rod
                        recursiveMoveDiskFromRodToRod(fromRodNumber, toRodNumber)

                    } else {
                        //in this step we already moved disk from 1rod to 2rod but we can't move it to 3rod so recursive call is done with parameters as 2rod to 3rod
                        recursiveMoveDiskFromRodToRod(fromRodNumber, fromRodNumber + 1)
                        //after recursive call we theoretically have moved this disk
                        //so now we need to try to move our main disk once more
                        recursiveMoveDiskFromRodToRod(fromRodNumber, toRodNumber)

                    }
                }
                getNumberByRod(thirdRodDisks) -> {
                    if (fromRod.peek() > getRodByNumber(fromRodNumber - 1).peek()) {
                        tryToMoveDiskFromRodToRod(fromRod, getRodByNumber(fromRodNumber - 1))
                        //we have successfully moved from 3rod to 1rod and now doing recursive call to try to move once more disk from 2rod to 3rod
                        recursiveMoveDiskFromRodToRod(fromRodNumber, toRodNumber)
                        //after recursive call we theoretically have moved this disk
                        //so now we need to try to move our main disk once more
                        recursiveMoveDiskFromRodToRod(fromRodNumber, toRodNumber)

                    } else {
                        //in this step we already moved disk from 3rod to 2rod but we can't move it to 1rod so recursive call is done with parameters as 2rod to 1rod
                        recursiveMoveDiskFromRodToRod(fromRodNumber, fromRodNumber - 1)

                    }
                }
                else -> throw UnsupportedOperationException("this method moves disk only to 2rod or 3rod")

            }
        }

    }

}


fun main(args: Array<String>) {
    //3 stacks -> rods
    // one is fill with disks ascending order from the top

}