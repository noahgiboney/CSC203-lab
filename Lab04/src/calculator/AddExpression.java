package calculator;

class AddExpression extends BinaryExpression{

   public AddExpression(Expression lft, Expression rht) {
      super(lft, rht, "+");
   }
}
