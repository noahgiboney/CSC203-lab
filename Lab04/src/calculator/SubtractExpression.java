package calculator;

class SubtractExpression extends BinaryExpression {

   public SubtractExpression(final Expression lft, final Expression rht) {
      super(lft, rht, "-");
   }

   @Override
   protected double _applyOperator(Double lft, Double rht) {
      return lft - rht;
   }
}

