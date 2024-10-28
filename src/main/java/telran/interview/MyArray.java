package telran.interview;
// All methods must have complexity O[1]

public class MyArray<T> {

    private Object[] array;  // Main array to hold values
    private Object setAllValue;  // The value set by the last setAll call
    private int[] setTimes;  // Tracks the time (order) when set() was called for each element
    private int globalSetTimer;  // Tracks the last time setAll() was called

    // Constructor
    public MyArray(int size) {
        array = new Object[size];
        setAllValue = null;  // Initially, there's no global value
        setTimes = new int[size];  // To track the individual set times for each index
        globalSetTimer = -1;  // Indicates no global set has happened yet
    }

    // Sets all elements of the array to the given value in O[1]
    public void setAll(T value) {
        setAllValue = value;
        globalSetTimer++;  // Update the global set time to mark the new setAll call
    }

    // Sets new value at a given index
    // Throws ArrayIndexOutOfBoundsException for incorrect index
    public void set(int index, T value) {
        ifIndexOutOfBoundsThrowExeption(index);
        array[index] = value;
        setTimes[index] = globalSetTimer + 1;  // Mark this element as individually set after the last setAll
    }

    // Returns a value at a given index
    // Throws ArrayIndexOutOfBoundsException for incorrect index
    @SuppressWarnings("unchecked")
    public T get(int index) {
        ifIndexOutOfBoundsThrowExeption(index);
        T res;
        // Check if this element has been individually set after the last setAll
        if (setTimes[index] <= globalSetTimer) {
            // If not, return the global setAll value
            res = (T) setAllValue;
        } else {
            // Otherwise, return the individually set value
            res = (T) array[index];
        }
        return res;
    }

    // Helper method to throw an exeption
    // It's unnecessary
    private void ifIndexOutOfBoundsThrowExeption(int index) {
        if (index < 0 || index >= array.length) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

}
