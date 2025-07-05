public class TreeNode {
    int data;
    TreeNode left;
    TreeNode right;
    public TreeNode(int data){
        this.data = data;
        this.left = null;
        this.right = null;
    }
    public TreeNode(){
    }

    //preorder: root-left-right
    public static void preorder(TreeNode root){
        TreeNode node = root;
        if(node != null){
            System.out.print(node.data + " ");
            preorder(node.left);
            preorder(node.right);
        }
    }

    //inorder: left-root-right
    public static void inorder(TreeNode root){
        TreeNode node = root;
        if(node != null){
            inorder(node.left);
            System.out.print(node.data + " ");
            inorder(node.right);
        }
    }

    //postorder: left-right-root
    public static void postorder(TreeNode root){
        TreeNode node = root;
        if(node != null){
            postorder(node.left);
            postorder(node.right);
            System.out.print(node.data + " ");
        }
    }

    //maxDepth: find the maximum depth of given tree
    public static int maxDepth(TreeNode root){
        if(root == null) return 0;
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return Math.max(left,right) + 1;
    }

    //isBalanced: judge whether tree is balanced(maxDepth(left) - maxDepth(right) <= 1)
    public static boolean isBalanced(TreeNode root){
        if(root == null) return true;
        return Math.abs(maxDepth(root.left) - maxDepth(root.right)) <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }

    //isBST: judge whether tree is binary search tree(left < root <= right)
    static int paramBST = Integer.MIN_VALUE;
    public static boolean isBST(TreeNode root){
        if(root == null) return true;
        //if(root.left == null && root.right == null) return true;
        //make left recursion
        if(!isBST(root.left)){
            return false;
        }
        //judge current node
        if(root.data <= paramBST){
            return false;
        }
        //updates parameter
        paramBST = root.data;
        //make right recursion
        return isBST(root.right);
    }

    //arrayToBST: transfer sorting array to binary search tree
    public static TreeNode arrayToBST(int[] arr){
        if(arr.length == 0) return null;
        return auxOfArrToBST(arr,0,arr.length-1);
    }
    public static TreeNode auxOfArrToBST(int[] arr, int left, int right){
        //terminal
        if(left > right) return null;
        //create root
        int mid = (left+right)/2;
        TreeNode root = new TreeNode(arr[mid]);
        //create left and right by recursion
        root.left = auxOfArrToBST(arr,left,mid-1);
        root.right = auxOfArrToBST(arr,mid+1,right);
        return root;
    }

    //mergeTree: add value of node(same place but not same tree)
    public static TreeNode mergeTree(TreeNode t1, TreeNode t2){
        if(t1 == null) return t2;
        if(t2 == null) return t1;
        //create root
        TreeNode root = new TreeNode(t1.data + t2.data);
        //get left and right by recursion
        root.left = mergeTree(t1.left,t2.left);
        root.right = mergeTree(t1.right,t2.right);
        return root;
    }

    //isSymmetric: judge whether tree is symmetric
    public static boolean isSymmetric(TreeNode root){
        if(root == null) return true;
        if(root.left == null && root.right == null) return true;
        return auxIsSymmetric(root.left,root.right);
    }
    public static boolean auxIsSymmetric(TreeNode t1, TreeNode t2){
        if(t1 == null && t2 == null) return true;
        if(t1 == null || t2 == null) return false;
        boolean current = t1.data == t2.data;
        boolean outer = auxIsSymmetric(t1.left,t2.right);
        boolean inner = auxIsSymmetric(t1.right,t2.left);
        return current && outer && inner;
    }

    //flipTree: mirror flip tree
    public static TreeNode flipTree(TreeNode root){
        if(root == null) return null;
        if(root.left == null && root.right == null) return root;
        //recursion
        TreeNode left = flipTree(root.left);
        TreeNode right = flipTree(root.right);
        //connect but reverse
        root.left = right;
        root.right = left;
        return root;
    }
    public static void main(String[] args){

    }
}
