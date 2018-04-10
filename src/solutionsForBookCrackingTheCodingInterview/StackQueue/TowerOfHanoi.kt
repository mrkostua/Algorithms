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
//TODO IMPORTANT there can be a lot of errors in methods like tryToMoveDiskFromRodToRod() after try to implement methods with names as actions(and inside do tryToMoveDiskFromRodToRod(x,y) )
//TODO will help a lot in the future searching for errors and bugs
class TowerOfHanoi(private val firstRodDisks: ArrayStack<Int>, private val secondRodDisks: ArrayStack<Int>,
                   private val thirdRodDisks: ArrayStack<Int>) {
    private val minAmountOfMoves = Math.pow(firstRodDisks.size.toDouble(), 3.0).toInt()

    /**
     * Initial assumption  :
     * all disks in first rod is different and 2rod,3rod are empty stacks
     */
    fun moveDisksToLoastRod_UpdateMethod() {
        //we can try to write this method in TDD way first Test after writing solution to pass it.

        while (isDone()) {
            doMove()


        }
    }

    private fun doMove() {
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

    private fun moveFromThirdToFirst() {
        if (tryToMoveDiskFromRodToRod(thirdRodDisks, secondRodDisks)) {
            if (tryToMoveDiskFromRodToRod(secondRodDisks, firstRodDisks)) {
                //good element was moved without any difficulties
                return

            } else {
                //in this step top disk in 1rod is bigger than top disk in 2rod so we can without problems -> move top disk in 1rod to 3rod

                tryToMoveDiskFromRodToRod(firstRodDisks, secondRodDisks)
                tryToMoveDiskFromRodToRod(secondRodDisks, thirdRodDisks)
                if (secondRodDisks.peek() < thirdRodDisks.peek()) {
                    //good we can move our element and finish this method
                    firstRodDisks.push(secondRodDisks.pop())
                } else {

                    //looks like we need to same actions again recursive method ? until we can move our element
                }

            }
        }

        //TODO here we need to move disks from 3rod to 1rod until (top disk in 3rod is < than top [fist and also top disk in 2rod] )
    }

    private fun recursiveSecondRodDiskToFirstRodDisk() {
        if (secondRodDisks.peek() > firstRodDisks.peek()) {
            //good we can move our element and finish this method
            firstRodDisks.push(secondRodDisks.pop())
            return

        } else {
            tryToMoveDiskFromRodToRod(firstRodDisks, secondRodDisks)
            if (secondRodDisks.peek() > thirdRodDisks.peek()) {
                tryToMoveDiskFromRodToRod(secondRodDisks, thirdRodDisks)
                recursiveSecondRodDiskToFirstRodDisk()

            } else {
                tryToMoveDiskFromRodToRod(firstRodDisks, secondRodDisks)
                //in this case we need to use this fun once more but as recursive form 2rod to 3rod
                recursiveSecondRodDiskToThirdRodDisk() //this will move our element from 2rod to 3rod
                //and back to recursive
                recursiveSecondRodDiskToFirstRodDisk()
            }

        }
    }

    private fun recursiveSecondRodDiskToThirdRodDisk() {
        TODO("not implemented")
        //here is the same recursive method as recursiveSecondRodDiskToFirstRodDisk
        //but different direction of moving and in the end different
        //TODO create one general method(rodTo,rodFrom) and use in this in those recursive methods.
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

    private lateinit var fromRod: ArrayStack<Int>
    private lateinit var toRod: ArrayStack<Int>


    /**
     * TODO work on this method and test it before using in algorithm
     * This method is only for moving disk from 2rod to 1rod or 3rod!
     */
    private fun recursiveMoveDiskFromRodToRod(fromRodNumber: Int, toRodNumber: Int) {
        fromRod = getRodByNumber(fromRodNumber)
        toRod = getRodByNumber(toRodNumber)
        if (fromRod.peek() > toRod.peek()) {
            //good we can move our element and finish this method
            toRod.push(fromRod.pop())
            return

        } else {
            tryToMoveDiskFromRodToRod(toRod, fromRod)
            //here is a problem
            when (toRodNumber) {
                getNumberByRod(firstRodDisks) -> {
                    if (fromRod.peek() > getRodByNumber(toRodNumber + 1).peek()) {
                        tryToMoveDiskFromRodToRod(fromRod, getRodByNumber(toRodNumber + 1))
                        recursiveMoveDiskFromRodToRod(fromRodNumber, toRodNumber)

                    } else {
                        //TODO continue implementation recursiveSecondRodDiskToFirstRodDisk() method
                    }
                }
                getNumberByRod(thirdRodDisks) -> {
                    //TODO implement this case
                }
                else -> throw UnsupportedOperationException("this method moves disk only to 2rod or 3rod")
            }

        }

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

//TODO previous approach check and delete
    /* fun moveDisksToLastRod() {
         for (i in 0 until firstRodDisks.size) {
             move()

         }
     }

     //moves one disk from first to last rod -> inserting it in the bottom of the last rod.
     private fun move() {
         */
    /**
     * our task is one block to the last rod one iteration is moving one disk to last rod (+having all smaller disks on top of moved disk)
     *  so in result :
     *  firstRog have size-1 element,
     *  second rod is empty,
     *  third rod contains all previous disks + 1 big rod in the bottom of stack.
     *//*
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

    }*/


}


fun main(args: Array<String>) {
    //3 stacks -> rods
    // one is fill with disks ascending order from the top

}