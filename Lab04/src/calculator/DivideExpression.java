package calculator;

class DivideExpression extends BinaryExpression{

   public DivideExpression(final Expression lft, final Expression rht) {
      super(lft,rht, "/");
   }
}

