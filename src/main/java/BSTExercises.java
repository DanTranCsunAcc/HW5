public class BSTExercises {

    /**
     * Task 11 – Build a BST from an array.
     */
    public static BST<Integer> fromArray(int[] values) {
        if (values == null)
            throw new IllegalArgumentException("Input array cannot be null");

        BST<Integer> tree = new BST<>();

        for (int v : values) {
            tree.insert(v);
        }

        return tree;
    }
}
