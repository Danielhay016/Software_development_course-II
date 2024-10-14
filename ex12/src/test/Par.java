package test;

import java.util.concurrent.RecursiveTask;

public class Par extends RecursiveTask<Integer>
{

    BinTree tree;
    public Par(BinTree tree)
    {
        this.tree=tree;
    }

    @Override
    protected Integer compute() {
        int sum = 0 ;
        if(tree.left == null && tree.right ==null)//stop condition
            return tree.get();

            sum = tree.get();
            Par left = new Par(tree.left);
            Par right = new Par(tree.right);
            left.fork();

            return (sum + right.compute()+left.join());
        }

    }
