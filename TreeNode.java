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
    public static void main(String[] args){
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
    }
}
