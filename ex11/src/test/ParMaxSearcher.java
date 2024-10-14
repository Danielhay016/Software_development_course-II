package test;

import java.util.concurrent.RecursiveTask;

public class ParMaxSearcher extends RecursiveTask<Integer> {

	private static final long serialVersionUID = 1L;

	BinTree tree;

	public ParMaxSearcher(BinTree tree) {
	this.tree = tree;
	}



	@Override
	protected Integer compute() {
		if(tree.left == null && tree.right ==null)
			return tree.get();

		ParMaxSearcher left = new ParMaxSearcher(tree.getLeft());
		ParMaxSearcher right = new ParMaxSearcher(tree.getRight());

		left.fork();
		return Integer.max(tree.v,Integer.max(right.compute(), left.join()));

	}	

}
