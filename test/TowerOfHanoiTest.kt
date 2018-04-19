import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import solutionsForBookCrackingTheCodingInterview.StackQueue.ArrayStack
import solutionsForBookCrackingTheCodingInterview.StackQueue.TowerOfHanoi
import java.util.*
import org.junit.Assert.*


/**
 * @author Kostiantyn Prysiazhnyi
 * @created on 4/19/2018
 */
@RunWith(Parameterized::class)
public class TowerOfHanoiTest(private val rod1: ArrayStack<Int>, private val rod2: ArrayStack<Int>,
                              private val rod3: ArrayStack<Int>) {
    private val towerOfHanoi: TowerOfHanoi = TowerOfHanoi(rod1, rod2, rod3)
    private var diskToMove: Int = rod2.peek()

    @Before
    fun initialize() {
        print("initial data : ")
        towerOfHanoi.printAllRods()
        diskToMove = rod2.peek()
    }

    @Test
    fun testMoveDiskFromRod2ToRod3() {
        println("testMoveDiskFromRod2ToRod3()")
        if (rod2.isEmpty()) {
            println("rod2 is empty can't execute the moving")
            return

        } else {
            println("move from rod2 to rod3")
            towerOfHanoi.MovingTools().moveDiskFromRodToRod(rod2, rod3)
            towerOfHanoi.printAllRods()
            assertEquals("Error : $diskToMove disk wasn't moved to rod3", diskToMove, rod3.peek())

        }
    }

    @Test
    fun testMoveDiskFromRod2ToRod1() {
        println("testMoveDiskFromRod2ToRod1()")
        if (rod2.isEmpty()) {
            println("rod2 is empty can't execute the moving")
            return

        } else {
            println("move from rod2 to rod1")
            towerOfHanoi.MovingTools().moveDiskFromRodToRod(rod2, rod1)
            towerOfHanoi.printAllRods()
            assertEquals("Error : $diskToMove disk wasn't moved to rod1", diskToMove, rod1.peek())

        }
    }


    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun getTestDataSet(): List<Array<ArrayStack<Int>>> {
            val testData = ArrayList<Array<ArrayStack<Int>>>()
            testData.add(getTestDataItem(arrayOf(0), arrayOf(3, 1), arrayOf(2)))
            testData.add(getTestDataItem(arrayOf(3), arrayOf(0, 1), arrayOf(2)))
            return testData

        }

        private fun getTestDataItem(rod1Disks: Array<Int>, rod2Disks: Array<Int>,
                                    rod3Disks: Array<Int>): Array<ArrayStack<Int>> {
            if (rod1Disks.size + rod2Disks.size + rod3Disks.size !in 4 downTo 1) {
                throw UnsupportedOperationException("getTestDataItem : max amount of disks is 4 (temporary)")

            }
            val rod1 = ArrayStack<Int>(4, 0)
            val rod2 = ArrayStack<Int>(4, 1)
            val rod3 = ArrayStack<Int>(4, 2)
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
