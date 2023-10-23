package calculator;

class MultiplyExpression extends BinaryExpression {

   public MultiplyExpression(final Expression lft, final Expression rht) {
      super(lft,rht, "*");
   }
}

