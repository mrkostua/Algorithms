package solutionsForBookCrackingTheCodingInterview.StackQueue

/**
 * @author Kostiantyn Prysiazhnyi
 * @created on 3/21/2018
 *
 */

/**
 * Task
 * Describe how you could use a single array to implement three stacks.
 */

/**
 * Solution :
 * First questions:
 * Fixed-size stack
 * If it is fixed-size stack, by creating array and dividing into 3 parts with first and last element(problem is that by deleting form one stack we will crash other stacks indexes)
 * Other way (easier) store elements of stack on after next like s1,s2,s3 first element If it is deleted it is set as null and pointer (top) just point to a previous element.
 *
 * Dynamic stack (size growing)
 * wrong array size can't be dynamically increased. Solution is to 3 stacks growing independently one of each others and some way to free up array memory after pop().
 */


