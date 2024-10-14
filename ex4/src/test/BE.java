package test;

import java.util.concurrent.ForkJoinTask;

public class BE extends MyRecursiveTask<Double>  implements  Expression {

    private static final long serialVersionUID = 1L;

    public char operator;
    public Expression left;
    public Expression right;

  public BE(char operator , Expression x , Expression y)
  {
     this.operator = operator;
     this.left = x;
     this.right = y;
  }

    @Override
    public Double calculate() {
    return compute();
    }

    @Override
    protected Double compute() {

        if( left instanceof Num && right instanceof Num )
        {
            if(operator == '-')
                return left.calculate() - right.calculate();
            if(operator == '+')
                return left.calculate() + right.calculate();
            if(operator == '*')
                return left.calculate() * right.calculate();
            if(operator == '/')
                return left.calculate() / right.calculate();
        }

           if(right instanceof BE)
            ((BE) (right)).testFork();
             double r = 0;
           if(right instanceof BE)
             r = ((BE) (right)).join();
             double res = left.calculate();

            switch(operator){
            case '+': res+= r; break;
            case '-': res-= r; break;
            case '*': res*= r; break;
            case '/': res/= r; break;
            }

        return res;


    }
}
