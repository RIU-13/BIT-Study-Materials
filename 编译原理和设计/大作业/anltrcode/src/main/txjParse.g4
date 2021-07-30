grammar txjParse;
import txjLexer;

program:function;
function:IntegerConstant
        | CharConstant
        | FloatConstant
        | StringLiteral
        | Identifier
        ;
unaryExpression: postfixExpression
                 | '++' unaryExpression
                 | '--' unaryExpression
                 | unaryExpression castExpression
                 ;


unaryTypename: 'sizeof' unaryExpression
              |'sizeof' '(' typeName ')';