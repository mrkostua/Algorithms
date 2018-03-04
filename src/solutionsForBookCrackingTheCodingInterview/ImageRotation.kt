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
 * 1)to test it we need to create matrix our matrix or just use some image.
 * 3)do the rotation of matrix (matrix 3x3 -(initial 1x3 value saved in x) -> 1x1 going to 1x3 index -> 1x3 moves to 1x1 index
 * -> and so on for 1x,2x,3x .....(that mean we can do it in place with one initial array and additional primitive variable)
 */

/**
 * So to do it in place I think we need to use only few variable no arrays, collections .TODO hard to find some algorithm for all sizes of matrix (more time)
 * In other ways with using additional data structure more easier
 */

fun rotateTwoDimensionMatrix(imageArray: Array<Array<ByteArray>>, rotationAngle: Int): Array<Array<ByteArray>> {
    val rotationAngle = getRotationAngel(rotationAngle)
    var additionalPixel: ByteArray
    if (imageArray.size != imageArray[0].size) {
        /**
         * first of all it is impossible to do in place if our matrix (xDimension.length != yDimension.length) because we need to change the size of our arrays(which mean creating new array)
         * can't do it in one place
         */

    }
    val defaultByteArray = byteArrayOf(0, 0, 0, 0)
    val resultImageArray = Array(imageArray.size, { Array(imageArray[0].size, { defaultByteArray }) })
    var rotationDimension: Array<ByteArray>

    for (xIndex in 0 until imageArray.size) {
        rotationDimension = imageArray[xIndex]
        for (yIndex in 0 until imageArray[0].size) {
            resultImageArray[yIndex][xIndex] = rotationDimension[yIndex]
        }
    }




    return resultImageArray


}


fun main(args: Array<String>) {
    val positiveArray = byteArrayOf(1, 1, 1, 1)
    val negativeArray = byteArrayOf(0, 0, 0, 0)

    val xDimension = 2
    val yDimension = 2

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
    val resultArray = rotateTwoDimensionMatrix(imageArray, 78)
    for (xArray in resultArray) {
        for (yArray in xArray) {
            print(Arrays.toString(yArray) + "  ")
        }
        print("\n")
    }


}

private fun getRotationAngel(angle: Int): Int {
    return when {
        angle <= 90 -> 90
        angle in 91..180 -> 180
        angle in 181..270 -> 270
        else -> 90
    }
}

