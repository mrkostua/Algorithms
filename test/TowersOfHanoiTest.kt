import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import solutionsForBookCrackingTheCodingInterview.StackQueue.ArrayStack
import solutionsForBookCrackingTheCodingInterview.StackQueue.TowersOfHanoi
import java.util.*
import org.junit.Assert.*
import org.junit.Ignore


/**
 * @author Kostiantyn Prysiazhnyi
 * @created on 4/19/2018
 */
@RunWith(Parameterized::class)
class TowersOfHanoiTest(private val rod1: ArrayStack<Int>, private val rod2: ArrayStack<Int>,
                        private val rod3: ArrayStack<Int>) {
    private val towersOfHanoi: TowersOfHanoi = TowersOfHanoi(rod1, rod2, rod3)
    private var diskToMove: Int = -1

    @Before
    fun initialize() {
        print("initial data : ")
        towersOfHanoi.printAllRods()

    }

    @Test
    fun testMoveAllDisksToLastRod() {
        val expected = towersOfHanoi.expectedRod.toIntArray()
        towersOfHanoi.moveAllDisksToLastRod()
        val result = IntArray(expected.size, { 0 })
        rod3.arrayBuffer.forEachIndexed { index, elem -> result[index] = elem ?: -1 }
        assertArrayEquals("all disks wasn't moved to last rod", result, expected)
    }

    @Ignore
    @Test
    fun testMoveDiskFromRod2ToRod3() {
        println("testMoveDiskFromRod2ToRod3()")
        if (rod2.isEmpty()) {
            throw UnsupportedOperationException("wrong TestDataSet can't perform the moving, Stack rod2 is empty")

        }
        diskToMove = rod2.peek()
        towersOfHanoi.MovingTools().moveDiskFromRodToRod(rod2, rod3)
        towersOfHanoi.printAllRods()
        assertEquals("Error : $diskToMove disk wasn't moved to rod3", diskToMove, rod3.peek())

    }

    @Ignore
    @Test
    fun testMoveDiskFromRod2ToRod1() {
        println("testMoveDiskFromRod2ToRod1()")
        if (rod2.isEmpty()) {
            throw UnsupportedOperationException("wrong TestDataSet can't perform the moving, Stack rod2 is empty")

        }
        diskToMove = rod2.peek()
        towersOfHanoi.MovingTools().moveDiskFromRodToRod(rod2, rod1)
        towersOfHanoi.printAllRods()
        assertEquals("Error : $diskToMove disk wasn't moved to rod1", diskToMove, rod1.peek())

    }

    //static method with testData
    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun getTestDataSet(): List<Array<ArrayStack<Int>>> {
            val testData = ArrayList<Array<ArrayStack<Int>>>()
            testData.add(getTestDataItem(arrayOf(3, 2, 1, 0), arrayOf(), arrayOf(), 4))
            testData.add(getTestDataItem(arrayOf(1, 0), arrayOf(), arrayOf(), 2))
            testData.add(getTestDataItem(arrayOf(5, 4, 3, 2, 1, 0), arrayOf(), arrayOf(), 6))
            return testData

        }

        private fun getTestDataItem(rod1Disks: Array<Int>, rod2Disks: Array<Int>,
                                    rod3Disks: Array<Int>, maxRodSize: Int): Array<ArrayStack<Int>> {
            if (rod1Disks.size + rod2Disks.size + rod3Disks.size !in 10 downTo 1) {
                throw UnsupportedOperationException("getTestDataItem : max amount of disks is 4 (temporary)")

            }
            val rod1 = ArrayStack<Int>(maxRodSize, 0)
            val rod2 = ArrayStack<Int>(maxRodSize, 1)
            val rod3 = ArrayStack<Int>(maxRodSize, 2)
            for (i in rod1Disks)
                rod1.push(i)
            for (i in rod2Disks)
                rod2.push(i)
            for (i in rod3Disks)
                rod3.push(i)

            return arrayOf(rod1, rod2, rod3)
        }

    }

}
