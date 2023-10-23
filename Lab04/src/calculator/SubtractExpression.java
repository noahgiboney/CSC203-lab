package calculator;

class SubtractExpression extends BinaryExpression {

   public SubtractExpression(final Expression lft, final Expression rht)
   {
      super(lft, rht, "-");
   }

}

