package week11;

public class CustomizeException {
    public static void main(String[] args) {
        try {
            int[] arr = new int[4];
            int i = arr[4]; // Ini akan error index out of bounds
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}