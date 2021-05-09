public class RBTree {



    Node root=null;

    public void insert(String data){
        Node newNode= new Node();
        newNode.data=data;
        newNode.left=null;
        newNode.right=null;
        newNode.parent=null;
        newNode.color=newNode.RED;
        root=insertFunction(root,newNode);
        fixInsert(newNode);
    }

    public Node insertFunction(Node root,Node newNode){

        if (root== null){
            return newNode;
        }
        else if(newNode.data.compareToIgnoreCase(root.data)>0 ) {
            root.right=insertFunction(root.right,newNode);//right
            root.right.parent=root;

        }

        else if ( newNode.data.compareToIgnoreCase(root.data)<0 ){
            root.left=insertFunction(root.left,newNode);
            root.left.parent=root;
        }
        return root;

    }

    public boolean searchWord(Node root,String data){


        if(root==null){
            return false;
        }
        if(data.compareToIgnoreCase(root.data)==0 ){
            return true;
        }
        else if(data.compareToIgnoreCase(root.data)>0 ) {
          return  searchWord(root.right,data);
        }
        else if (data.compareToIgnoreCase(root.data)<0 ){
          return searchWord(root.left,data);
        }
          return false;
    }

    public  void leftRotate( Node x ) {
        Node y;
        y = x.right;
        x.right = y.left;
        if ( y.left != null)
        {
            y.left.parent = x;
        }
        y.parent = x.parent;
        if ( x.parent == null )
        {
            root = y;
        }
        else if ( x == x.parent.left ) {

            x.parent.left = y;
        }
        else{
            x.parent.right = y;
        }
        y.left = x;
        x.parent = y;
    }
    public void rightRotate(Node x) {

        Node y ;
        y=x.left;
        x.left = y.right;
        if(y.right != null)
        {
            y.right.parent = x;
        }
        y.parent = x.parent;
        if(x.parent == null)
        {
            root = y;
        }
        else if(x == x.parent.right)
        {
            x.parent.right = y;
        }
        else
        {
            x.parent.left = y;
        }
        y.right = x;
        x.parent = y;
    }

    public int height(Node root)
    {
        if (root == null) {
            return 0;
        }
        return 1+ Math.max(height(root.left), height(root.right));
    }

    public int size(Node root){
        return root==null? 0 : 1 + size(root.left) + size(root.right);
    }

    public void fixInsert(Node newNode) {


        Node uncle = null;
        // Node grandFather;

        if (root == newNode) {
            root.color = root.BLACK;
        }
        else if (newNode.parent.color != newNode.BLACK) {

            if (newNode.parent.parent != null) {
                // grandFather = newNode.parent.parent;
                if (newNode.parent.parent.left == newNode.parent) {
                    uncle = newNode.parent.parent.right;
                } else
                    uncle = newNode.parent.parent.left;
            }
            if (newNode.color == newNode.parent.color) { // parent and child red

                if (uncle != null) { // so case 1 aor 2 or 3

                    if (newNode.color == uncle.color) { //case 1 uncle is red
                        newNode.parent.color = newNode.BLACK;
                        uncle.color = newNode.BLACK;
                        newNode.parent.parent.color = newNode.RED;
                        fixInsert(newNode.parent.parent);
                    } else {
                        if(newNode.parent == newNode.parent.parent.left) {

                            if (newNode == newNode.parent.right)// case 2 (left Right) to be left-left
                            {
                                newNode = newNode.parent;
                                leftRotate(newNode);
                            }
                            // case 3 left left // recolor parent and grandparent then rotate right
                            newNode.parent.color = newNode.BLACK; //made parent black
                            newNode.parent.parent.color = newNode.RED; //made parent red
                            rightRotate(newNode.parent.parent);
                        }
                        else if (newNode.parent == newNode.parent.parent.right){ // case 2 (right left) so rotate right to be right-right
                            if ( newNode == newNode.parent.left ) {
                                newNode = newNode.parent;
                                rightRotate(newNode);
                            }

                            // case 3 right right// recolor parent and grandparent then rotate left
                            newNode.parent.color = newNode.BLACK; //made parent black
                            newNode.parent.parent.color = newNode.RED; //made parent red
                            leftRotate(newNode.parent.parent);
                        }
                    }

                } else {//uncle is null  black case 2 or 3
                    if ((newNode.parent == newNode.parent.parent.left))// case 2 (left Right) to be left-left
                    {
                        if(newNode == newNode.parent.right){
                            newNode = newNode.parent;
                            leftRotate(newNode);
                        }

                        // case 3 left left // recolor parent and grandparent then rotate right
                        newNode.parent.color = newNode.BLACK; //made parent black
                        newNode.parent.parent.color = newNode.RED; //made parent red
                        rightRotate(newNode.parent.parent);
                    } else if(newNode.parent == newNode.parent.parent.right){ // case 2 (right left) so rotate right to be right-right

                        if ( newNode == newNode.parent.left ) {
                            newNode = newNode.parent;
                            rightRotate(newNode);
                        }
                        // case 3 right right// recolor parent and grandparent then rotate left
                        newNode.parent.color = newNode.BLACK; //made parent black
                        newNode.parent.parent.color = newNode.RED; //made parent red
                        leftRotate(newNode.parent.parent);
                    }
                }
            }
        }
        root.color = newNode.BLACK;
    }
}