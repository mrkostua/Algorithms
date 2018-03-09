package solutionsForBookCrackingTheCodingInterview.ArraysAndStrings

import java.util.*

/**
 * @author Kostiantyn Prysiazhnyi
 * @created on 3/2/2018
 * "Cracking the Coding Interview" task 1.6
 */

/**
 * Given an image represented by an NxN matrix, where each pixel in the image is 4
 * bytes, write a method to rotate the image by 90 degrees. Can you do this in place?
 *
 * In computer science, an in-place algorithm is an algorithm which transforms input using no auxiliary data structure.
 * However a small amount of extra storage space is allowed for auxiliary variables. The input is usually overwritten by the output as the algorithm executes.
 * In-place algorithm updates input sequence only through replacement or swapping of elements. An algorithm which is not
 * in-place is sometimes called not-in-place or out-of-place.
 */

class ImageRotation {
    private val defaultByteArray = byteArrayOf(0, 0, 0, 0)

    /**
     * Rotate matrix for 90 degree
     */
    fun rotateTwoDimensionMatrix(imageArray: Array<Array<ByteArray>>, rotationAngle: Int): Array<Array<ByteArray>> {
        return if (imageArray.size != imageArray[0].size) {
            rotateMatrixNotInPlaceAlgorithm(imageArray)
        } else {
            /**
             * first of all it is impossible to do in place if our matrix (xDimension.length != yDimension.length) because we need
             * to change the size of our arrays(which mean creating new array) can't do it in one place.
             * We also can do it recursively.
             */
            rotateMatrixInPlaceAlgorithm(imageArray)

        }

    }

    /**
     * Rotate matrix in place, by changing initial given matrix and using few primitive variables.
     */
    private fun rotateMatrixInPlaceAlgorithm(imageArray: Array<Array<ByteArray>>): Array<Array<ByteArray>> {
        println("\n     rotateMatrixInPlaceAlgorithm\n")
        val repetitionTimes: Int = if (imageArray.size % 2 == 0) {
            -1
        } else {
            1
        }
        var startIndex = 0
        var matrixSize = imageArray.size
        var additionalPixel: ByteArray

        var firstElementIndex = 0
        var decreaseIndex: Int
        var lastElementIndex: Int
        var afterRotationMatrixSize = imageArray.size


        while (afterRotationMatrixSize > repetitionTimes) {
            println("ROTATION")
            lastElementIndex = matrixSize - 1
            decreaseIndex = matrixSize - 1
            for (incrementalIndex in startIndex until matrixSize - 1) {
                additionalPixel = imageArray[firstElementIndex][incrementalIndex]

                //4 move to 1
                println("move index[" + incrementalIndex + "][" + lastElementIndex + "] into index[" + firstElementIndex + "][" + incrementalIndex + "]")
                imageArray[firstElementIndex][incrementalIndex] = imageArray[incrementalIndex][lastElementIndex]

                //3 move to 4
                println("move index[" + lastElementIndex + "][" + decreaseIndex + "] into index[" + incrementalIndex + "][" + lastElementIndex + "]")
                imageArray[incrementalIndex][lastElementIndex] = imageArray[lastElementIndex][decreaseIndex]

                //2 move 3
                println("move index[" + decreaseIndex + "][" + firstElementIndex + "] into index[" + lastElementIndex + "][" + decreaseIndex + "]")
                imageArray[lastElementIndex][decreaseIndex] = imageArray[decreaseIndex][firstElementIndex]

                //1 move to 2
                println("move index[" + firstElementIndex + "][" + incrementalIndex + "] into index[" + decreaseIndex + "][" + firstElementIndex + "]")
                imageArray[decreaseIndex][firstElementIndex] = additionalPixel

                decreaseIndex--

            }
            matrixSize--
            afterRotationMatrixSize -= 2
            firstElementIndex++
            startIndex++
            println("\n result of rotation")
            for (xArray in imageArray) {
                for (yArray in xArray) {
                    print(Arrays.toString(yArray) + "  ")
                }
                print("\n")
            }
            println("END OF ROTATION \n")


        }
        return imageArray

    }

    private fun rotateMatrixNotInPlaceAlgorithm(imageArray: Array<Array<ByteArray>>): Array<Array<ByteArray>> {
        println("\n         rotateMatrixNotInPlaceAlgorithm\n")
        var rotationOfYArray: Array<ByteArray>
        val resultImageArray = Array(imageArray[0].size, { Array(imageArray.size, { defaultByteArray }) })
        for (xIndex in 0 until imageArray.size) {
            rotationOfYArray = imageArray[xIndex]
            for (yIndex in 0 until imageArray[0].size) {
                resultImageArray[yIndex][xIndex] = rotationOfYArray[yIndex]
            }
        }
        return resultImageArray
    }

    private fun getRotationAngel(angle: Int): Int {
        return when {
            angle <= 90 -> 90
            angle in 91..180 -> 180
            angle in 181..270 -> 270
            else -> 90
        }
    }

}


fun main(args: Array<String>) {
    val positiveArray = byteArrayOf(1, 1, 1, 1)
    val negativeArray = byteArrayOf(0, 0, 0, 0)

    val xDimension = 5
    val yDimension = 5

    val imageArray = Array(xDimension, { Array(yDimension, { negativeArray }) })

    for (columnIndex in 0 until imageArray.size) {
        if (columnIndex % 2 == 0) {
            imageArray[columnIndex] = Array(yDimension, { positiveArray })
        } else {
            imageArray[columnIndex] = Array(yDimension, { negativeArray })
        }

    }

    println("initial data :" + xDimension + " x " + yDimension + "\n")
    for (xArray in imageArray) {
        for (yArray in xArray) {
            print(Arrays.toString(yArray) + "  ")
        }
        print("\n")
    }
    val resultArray = ImageRotation().rotateTwoDimensionMatrix(imageArray, 89)

    println("________________________________RESULT after running algorithm:_____________________________ \n ")
    for (xArray in resultArray) {
        for (yArray in xArray) {
            print(Arrays.toString(yArray) + "  ")
        }
        print("\n")
    }


}


