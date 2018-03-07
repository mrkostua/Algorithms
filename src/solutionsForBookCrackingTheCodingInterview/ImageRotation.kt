package solutionsForBookCrackingTheCodingInterview

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
 *
 * 1)to test it we need to create matrix our matrix or just use some image.
 * 3)do the rotation of matrix (matrix 3x3 -(initial 1x3 value saved in x) -> 1x1 going to 1x3 index -> 1x3 moves to 1x1 index
 * -> and so on for 1x,2x,3x .....(that mean we can do it in place with one initial array and additional primitive variable)
 */

/**
 * So to do it in place I think we need to use only few variable no arrays, collections .TODO hard to find some algorithm for all sizes of matrix (more time)
 * In other ways with using additional data structure more easier
 */

class ImageRotation {
    private val defaultByteArray = byteArrayOf(0, 0, 0, 0)

    fun rotateTwoDimensionMatrix(imageArray: Array<Array<ByteArray>>, rotationAngle: Int): Array<Array<ByteArray>> {
        return if (imageArray.size != imageArray[0].size) {
            rotateMatrixNotInPlaceAlgorithm(imageArray)
        } else {
            if (imageArray.size % 2 == 0) {
                rotatePairedSizeMatrix(imageArray)
            } else {
                rotateNonPairedSizeMatrix(imageArray)
            }
        }


    }

    /**
     * first of all it is impossible to do in place if our matrix (xDimension.length != yDimension.length) because we need
     * to change the size of our arrays(which mean creating new array) can't do it in one place.
     * We also can do it recursively.
     */
    private fun rotateNonPairedSizeMatrix(imageArray: Array<Array<ByteArray>>): Array<Array<ByteArray>> {
        println("rotateNonPairedSizeMatrix")
        var firstIndex = 0
        var startIndex = 0
        var matrixSize = imageArray.size
        var additionalPixel: ByteArray
        var decreaseIndex: Int
        var lastIndex: Int

        var whileSize = imageArray.size

        while (whileSize > 1) {
            //5x5
            lastIndex = matrixSize - 1
            decreaseIndex = matrixSize - 1
            for (incrementalIndex in startIndex until matrixSize - 1) {
                println("   incremental index = " + incrementalIndex)
                println("   decrease index = " + decreaseIndex)

                additionalPixel = imageArray[firstIndex][incrementalIndex]

                //4 move to 1
                println("move index[" + incrementalIndex + "][" + lastIndex + "] into index[" + firstIndex + "][" + incrementalIndex + "]")
                imageArray[firstIndex][incrementalIndex] = imageArray[incrementalIndex][lastIndex]

                //3 move to 4
                println("move index[" + lastIndex + "][" + decreaseIndex + "] into index[" + incrementalIndex + "][" + lastIndex + "]")
                imageArray[incrementalIndex][lastIndex] = imageArray[lastIndex][decreaseIndex]

                //2 move 3
                println("move index[" + decreaseIndex + "][" + firstIndex + "] into index[" + lastIndex + "][" + decreaseIndex + "]")
                imageArray[lastIndex][decreaseIndex] = imageArray[decreaseIndex][firstIndex]

                //1 move to 2
                println("move index[" + firstIndex + "][" + incrementalIndex + "] into index[" + decreaseIndex + "][" + firstIndex + "]")
                imageArray[decreaseIndex][firstIndex] = additionalPixel


                decreaseIndex--

            }
            matrixSize--
            whileSize -= 2
            firstIndex++
            startIndex++

            println("\n\nROTATION")
            for (xArray in imageArray) {
                for (yArray in xArray) {
                    print(Arrays.toString(yArray) + "  ")
                }
                print("\n")
            }
            println("END OF ROTATION \n\n")


        }
        return imageArray

    }

    private fun rotatePairedSizeMatrix(imageArray: Array<Array<ByteArray>>): Array<Array<ByteArray>> {
        println("rotatePairedSizeMatrix")
        return imageArray
    }

    private fun rotateMatrixNotInPlaceAlgorithm(imageArray: Array<Array<ByteArray>>): Array<Array<ByteArray>> {
        println("rotateMatrixNotInPlaceAlgorithm")
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
    println("After rotation : \n\n\n ")
    val resultArray = ImageRotation().rotateTwoDimensionMatrix(imageArray, 89)

    for (xArray in resultArray) {
        for (yArray in xArray) {
            print(Arrays.toString(yArray) + "  ")
        }
        print("\n")
    }


}


