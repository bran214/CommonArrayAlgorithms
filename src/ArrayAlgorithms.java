import java.util.Random;

/**
 * @author Brandon Winters
 */
public class ArrayAlgorithms {

    public static void main(String[] args) {
        int[] randomIntegers = new int[100];
        for (int i = 0; i < randomIntegers.length; i++) {
            Random random = new Random();
            randomIntegers[i] = random.nextInt(199) - 99;
        }

        System.out.println("Array: " + convertToString(randomIntegers, ", "));
        System.out.println("Sum: " + sum(randomIntegers));
        System.out.println("Average: " + average(randomIntegers));
        System.out.println("Minimum: " + minimum(randomIntegers));
        System.out.println("Maximum: " + maximum(randomIntegers));
        System.out.println("First occurrence of 0: " + indexOf(0, randomIntegers));
        System.out.println("Array with the first 0 removed: " + convertToString(remove(0, randomIntegers), ", "));
        System.out.println("Array with 0 added to the end: " + convertToString(append(0, randomIntegers), ", "));
        System.out.println("Array with the first -1 and first 1 swapped: " + convertToString(swap(-1, 1, randomIntegers), ", "));
        System.out.println("Copied version of the array: " + convertToString(copy(randomIntegers), ", " ));
    }

    /**
     * Returns the sum of all elements in a given array.
     * @param array the array to be summed up
     * @return the sum
     */
    public static int sum(int[] array) {
        int sum = 0;
        for (int j : array) {
            sum += j;
        }
        return sum;
    }

    /**
     * Returns the average of all elements in a given array.
     * @param array the array to find the average of
     * @return the average
     */
    public static double average(int[] array) {
        return (double) sum(array) / array.length;
    }

    /**
     * Returns the minimum of the elements in a given array. If the array is empty, returns 0.
     * @param array the array to find the minimum in
     * @return the minimum
     */
    public static int minimum(int[] array) {
        int minimum = 0;
        for (int i = 0; i < array.length; i++) {
            if (i == 0 || array[i] < minimum) {
                minimum = array[i];
            }
        }
        return minimum;
    }

    /**
     * Returns the maximum of the elements in a given array. If the array is empty, returns 0.
     * @param array the array to find the maximum in
     * @return the maximum
     */
    public static int maximum(int[] array) {
        int maximum = 0;
        for (int i = 0; i < array.length; i++) {
            if (i == 0 || array[i] > maximum) {
                maximum = array[i];
            }
        }
        return maximum;
    }

    public static String convertToString(int[] array) {
        return convertToString(array, " ");
    }

    /**
     * Converts a given array to a String, with separators in between the elements
     * @param array the array to be converted
     * @param separator the separators to be used between elements
     * @return the converted array
     */
    public static String convertToString(int[] array, String separator) {
        String convertedArray = "";
        for (int i = 0; i < array.length; i++) {
            convertedArray = convertedArray + array[i];
            if (i != array.length - 1) {
                convertedArray = convertedArray + separator;
            }
        }
        return convertedArray;
    }

    /**
     * Finds the index of a value in an array. Returns -1 if the value can't be found.
     * @param value the value to look for
     * @param array the array to look in
     * @return the index of the value in the array
     */
    public static int indexOf(int value, int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                return i; // returns the index where the value was found
            }
        }
        return -1; // returns -1 if the value isn't found
    }

    /**
     * Returns a new array with the given value removed. If the value can't be found, returns the original array.
     * @param value the value to remove
     * @param array the array to remove the value from
     * @return the new array with the value removed
     */
    public static int[] remove(int value, int[] array) {
        int removedIndex = indexOf(value, array);
        if (removedIndex != -1) {
            int[] newArray;
            if (removedIndex != 0 && removedIndex != array.length - 1) {
                newArray = merge(copy(0, removedIndex, array), copy(removedIndex + 1, array.length, array));
            } else if (removedIndex == 0) {
                newArray = copy(1, array.length, array);
            } else {
                newArray = copy(0, array.length - 1, array);
            }
            return newArray;
        } else {
            return array;
        }
    }

    /**
     * Returns a new array with the provided value appended to the end.
     * @param value the value to append
     * @param array the array to append the value to
     * @return the new array with the appended value
     */
    public static int[] append(int value, int[] array) {
        return merge(array, new int[] {value});
    }

    /**
     * Returns a new array with the two values swapped. If either value can't be found, returns the original array.
     * @param value1 the first value to be swapped
     * @param value2 the second value to be swapped
     * @param array the array in which to swap values
     * @return the new array with the swapped values
     */
    public static int[] swap(int value1, int value2, int[] array) {
        int[] newArray = copy(0, array.length, array);
        int index1 = indexOf(value1, newArray);
        int index2 = indexOf(value2, newArray);
        if (index1 != -1 && index2 != -1) {
            newArray[index1] = value2;
            newArray[index2] = value1;
        }
        return newArray;
    }

    public static int[] copy(int[] array) {
        return copy(0, array.length, array);
    }

    /**
     * Copies an array from the startingIndex to the endingIndex (inclusive and exclusive respectively)
     * @param startingIndex the start of where to copy (inclusive)
     * @param endingIndex the end of where to copy (exclusive)
     * @param array the array to copy from
     * @return the copied portion of the given array
     */
    public static int[] copy(int startingIndex, int endingIndex, int[] array) {
        int[] newArray = new int[endingIndex - startingIndex];
        for (int i = startingIndex, j = 0; i < endingIndex; i++, j++) {
            newArray[j] = array[i];
        }
        return newArray;
    }

    /**
     * Returns a new array which has been merged from two other arrays.
     * @param array1 the first array to be merged
     * @param array2 the second array to be merged
     * @return the new array merged from the other two arrays.
     */
    public static int[] merge(int[] array1, int[] array2) {
        int[] newArray = new int[array1.length + array2.length];
        for (int i = 0; i < array1.length; i++) {
            newArray[i] = array1[i];
        }
        for (int i = array1.length; i < newArray.length; i++) {
            newArray[i] = array2[i - array1.length];
        }
        return newArray;
    }
}
