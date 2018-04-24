package stacksQueues

import org.junit.Assert.*
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import org.junit.jupiter.api.RepeatedTest
import solutionsForBookCrackingTheCodingInterview.stacksAndQueues.TwoStacksQueue

/**
 * @author Kostiantyn Prysiazhnyi
 * @created on 4/24/2018
 */
class TwoStacksQueueTest {
    private var queueSize = 0

    companion object {
        private val queue = TwoStacksQueue<Int>()
        @JvmStatic
        @BeforeClass
        fun allInitialize() {
            println("allInitialize")
            for (i in 0..10) {
                queue.tailStack.push(i)
            }
        }
    }

    @Test
    fun testEnqueue() {
        queueSize = queue.size
        println("testEnqueue : queueSize : ${queueSize}")
        queue.enqueue(Integer.MIN_VALUE)
        assertEquals("size of queue is wrong after dequeue", queueSize + 1, queue.size)
    }
    @Test
    fun doTenTimes() {
        for (i in 0..4) {
            testDequeue()
        }
    }
//TODO check why @RepeatTest is not working???
    fun testDequeue() {
        queueSize = queue.size
        println("testDequeue queueSize : ${queueSize}")
        queue.dequeue()
        assertEquals("size of queue is wrong after dequeue", queueSize - 1, queue.size)

    }
}