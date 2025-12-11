// Your entire BST class is kept exactly the same.
// Only the new required methods are added at the bottom.

public class BST<E> implements Tree<E> {
    protected TreeNode<E> root;
    protected int size = 0;
    protected java.util.Comparator<E> c;

    public BST() {
        this.c = (e1, e2) -> ((Comparable<E>)e1).compareTo(e2);
    }

    public BST(java.util.Comparator<E> c) {
        this.c = c;
    }

    public BST(E[] objects) {
        this.c = (e1, e2) -> ((Comparable<E>)e1).compareTo(e2);
        for (int i = 0; i < objects.length; i++)
            add(objects[i]);
    }

    @Override
    public boolean search(E e) {
        TreeNode<E> current = root;
        while (current != null) {
            if (c.compare(e, current.element) < 0)
                current = current.left;
            else if (c.compare(e, current.element) > 0)
                current = current.right;
            else
                return true;
        }
        return false;
    }

    @Override
    public boolean insert(E e) {
        if (root == null)
            root = createNewNode(e);
        else {
            TreeNode<E> parent = null;
            TreeNode<E> current = root;
            while (current != null)
                if (c.compare(e, current.element) < 0) {
                    parent = current;
                    current = current.left;
                } else if (c.compare(e, current.element) > 0) {
                    parent = current;
                    current = current.right;
                } else
                    return false;

            if (c.compare(e, parent.element) < 0)
                parent.left = createNewNode(e);
            else
                parent.right = createNewNode(e);
        }

        size++;
        return true;
    }

    protected TreeNode<E> createNewNode(E e) {
        return new TreeNode<>(e);
    }

    @Override
    public void inorder() {
        inorder(root);
    }

    protected void inorder(TreeNode<E> root) {
        if (root == null) return;
        inorder(root.left);
        System.out.print(root.element + " ");
        inorder(root.right);
    }

    @Override
    public void postorder() {
        postorder(root);
    }

    protected void postorder(TreeNode<E> root) {
        if (root == null) return;
        postorder(root.left);
        postorder(root.right);
        System.out.print(root.element + " ");
    }

    @Override
    public void preorder() {
        preorder(root);
    }

    protected void preorder(TreeNode<E> root) {
        if (root == null) return;
        System.out.print(root.element + " ");
        preorder(root.left);
        preorder(root.right);
    }

    public static class TreeNode<E> {
        protected E element;
        protected TreeNode<E> left;
        protected TreeNode<E> right;

        public TreeNode(E e) {
            element = e;
        }
    }

    @Override
    public int getSize() {
        return size;
    }

    public TreeNode<E> getRoot() {
        return root;
    }

    public java.util.ArrayList<TreeNode<E>> path(E e) {
        java.util.ArrayList<TreeNode<E>> list = new java.util.ArrayList<>();
        TreeNode<E> current = root;

        while (current != null) {
            list.add(current);
            if (c.compare(e, current.element) < 0)
                current = current.left;
            else if (c.compare(e, current.element) > 0)
                current = current.right;
            else
                break;
        }

        return list;
    }

    @Override
    public boolean delete(E e) {
        TreeNode<E> parent = null;
        TreeNode<E> current = root;
        while (current != null) {
            if (c.compare(e, current.element) < 0) {
                parent = current;
                current = current.left;
            } else if (c.compare(e, current.element) > 0) {
                parent = current;
                current = current.right;
            } else break;
        }

        if (current == null)
            return false;

        if (current.left == null) {
            if (parent == null)
                root = current.right;
            else if (c.compare(e, parent.element) < 0)
                parent.left = current.right;
            else
                parent.right = current.right;
        } else {
            TreeNode<E> parentOfRightMost = current;
            TreeNode<E> rightMost = current.left;

            while (rightMost.right != null) {
                parentOfRightMost = rightMost;
                rightMost = rightMost.right;
            }

            current.element = rightMost.element;

            if (parentOfRightMost.right == rightMost)
                parentOfRightMost.right = rightMost.left;
            else
                parentOfRightMost.left = rightMost.left;
        }

        size--;
        return true;
    }

    @Override
    public java.util.Iterator<E> iterator() {
        return new InorderIterator();
    }

    private class InorderIterator implements java.util.Iterator<E> {
        private java.util.ArrayList<E> list = new java.util.ArrayList<>();
        private int current = 0;

        public InorderIterator() {
            inorder(root);
        }

        private void inorder(TreeNode<E> root) {
            if (root == null) return;
            inorder(root.left);
            list.add(root.element);
            inorder(root.right);
        }

        @Override
        public boolean hasNext() {
            return current < list.size();
        }

        @Override
        public E next() {
            return list.get(current++);
        }

        @Override
        public void remove() {
            if (current == 0)
                throw new IllegalStateException();
            delete(list.get(--current));
            list.clear();
            inorder(root);
        }
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    // -----------------------------
    // PART B – NEW METHODS BELOW
    // -----------------------------

    // Task 6 – Tree Height (Recursive)
    public int height() {
        return height(root);
    }

    private int height(TreeNode<E> node) {
        if (node == null) return -1;
        return 1 + Math.max(height(node.left), height(node.right));
    }

    // Task 7 – Count Leaves
    public int countLeaves() {
        return countLeaves(root);
    }

    private int countLeaves(TreeNode<E> node) {
        if (node == null) return 0;
        if (node.left == null && node.right == null) return 1;
        return countLeaves(node.left) + countLeaves(node.right);
    }

    // Task 8 – Min and Max
    public E min() {
        if (root == null) return null;
        TreeNode<E> cur = root;
        while (cur.left != null) cur = cur.left;
        return cur.element;
    }

    public E max() {
        if (root == null) return null;
        TreeNode<E> cur = root;
        while (cur.right != null) cur = cur.right;
        return cur.element;
    }

    // Task 9 – Convert to Sorted List
    public java.util.List<E> toSortedList() {
        java.util.List<E> result = new java.util.ArrayList<>();
        toSortedList(root, result);
        return result;
    }

    private void toSortedList(TreeNode<E> node, java.util.List<E> result) {
        if (node == null) return;
        toSortedList(node.left, result);
        result.add(node.element);
        toSortedList(node.right, result);
    }

    // Task 10 – Check Balanced
    public boolean isBalanced() {
        return heightOrUnbalanced(root) != -2;
    }

    private int heightOrUnbalanced(TreeNode<E> node) {
        if (node == null) return -1;

        int left = heightOrUnbalanced(node.left);
        if (left == -2) return -2;

        int right = heightOrUnbalanced(node.right);
        if (right == -2) return -2;

        if (Math.abs(left - right) > 1) return -2;

        return 1 + Math.max(left, right);
    }
}
