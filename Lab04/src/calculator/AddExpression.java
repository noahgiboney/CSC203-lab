package calculator;

class AddExpression extends BinaryExpression{

   public AddExpression(Expression lft, Expression rht) {
      super(lft, rht, "+");
   }

   @Override
   protected double _applyOperator(Double lft, Double rht) {
      return lft + rht;
   }
}
