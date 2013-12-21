package dataStructures;                                         

class AVLNode<K,V> extends BSTNode<K,V>
{                                                                   

    static final long serialVersionUID = 0L;


    // The balance factor of the tree rooted at the node,
    // which is:
    // 'E'  iff  height( node.getLeft() ) = height( node.getRight() );
    // 'L'  iff  height( node.getLeft() ) = height( node.getRight() ) + 1;
    // 'R'  iff  height( node.getLeft() ) = height( node.getRight() ) - 1.
    private char balanceFactor;


    public AVLNode( K key, V value, char balance, 
        AVLNode<K,V> left, AVLNode<K,V> right )
    {                                                                
        super(key, value, left, right);
        balanceFactor = balance;                                      
    }


    public AVLNode( K key, V value )
    {    
        this(key, value, 'E', null, null);
    }


    public char getBalance( )                           
    {   
        return balanceFactor;
    }


    public void setBalance( char newBalance )
    {    
        balanceFactor = newBalance;
    }


}






