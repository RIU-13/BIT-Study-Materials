grammar txj_parse;

WS:[ \t\n\r]+
   ->skip
   ;

BlockComment
    :   '/*' .*? '*/'
        -> skip
    ;

LineComment
    :   '//' ~[\r\n]*
        -> skip
    ;

//入口
program:translationUnit?EOF;
translationUnit: externalDeclaration+;
externalDeclaration:functionDefine
                    |declaration ';';

expr : expr op = ('++'|'--')                                       #Postfix_expression
     | expr '[' exprs ']'                                          #Array_access  //需要改正格式
     | op = ('++'|'--'|'sizeof'|Unary_operator) expr               #Unary_expression
     | op = 'sizeof' '(' typename ')'                              #Unary_typename //需要改正
     | '(' typename ')' expr                                       #Cast_expression  //需要改正格式
     | expr op = ('*'|'/'|'%') expr                                #Binary_expression
     | expr op = ('+'|'-')  expr                                   #Binary_expression
     | expr op = ('<<'|'>>') expr                                  #Binary_expression
     | expr op = ('>'|'<'|'<='|'>=') expr                          #Binary_expression
     | expr op = ('=='|'!=') expr                                  #Binary_expression
     | expr op = '&' expr                                          #Binary_expression
     | expr op = '^' expr                                          #Binary_expression
     | expr op = '|' expr                                          #Binary_expression
     | expr op = '&&' expr                                         #Binary_expression
     | expr op = '||' expr                                         #Binary_expression
     | expr '?' trueExpr=exprs ':' falseExpr=expr                  #Conditional_expression
     | expr op = Assignment_operator expr                          #Binary_expression
     | functionCall                                                #MyFunctionCall
    // | declaration                                                 #Mydec
     | IntegerConstant                                             #IntegerConstant
     | CharConstant                                                #CharConstant
     | FloatConstant                                               #FloatConstant
     | Identifier                                                  #Identifier
     | StringLiteral                                               #StringLiteral
     |'(' expr ')'                                                 #MyExpr
     ;

typename : specifiers? declarator?;
specifiers:Type
            |Type specifiers
            ;
//函数调用
functionCall: Identifier '(' argList? ')';
argList: expr
        |expr ',' argList;

Unary_operator :'&'|'*'|'+'|'-'|'~'|'!';

Assignment_operator : '='|'*='|'/='|'%='|'+='|'-='|'<<='|'>>='|'&='|'^='|'|=';



stat : breakStatement
      |continueStatement
      |gotoStatement
      |returnStatement
      |labeledStatement
      |compoundStatement
      |selectionStatement
      |expressionStatement
      |iterationStatement
      |iterationDecStatement
      |';'
      ;
breakStatement: 'break' ';';
continueStatement : 'continue' ';';
gotoStatement: 'goto' flag = Identifier ';';
returnStatement: 'return' exprs? ';';
labeledStatement: label = Identifier ':' stat ;
compoundStatement:'{' blockitems? '}';
blockitems : blockitem
            |blockitem blockitems
            ;
blockitem : stat
           | declaration ';' //补充
           ;
//
expressionStatement: exprs ';';
exprs: expr
    |expr ',' exprs;

selectionStatement:  'if' '(' exprs ')' then = stat
                    |'if' '(' exprs ')' then = stat 'else' otherwise=stat
                    ;
iterationStatement: 'while' '(' expr ')' stat
                    |'do"' stat 'while' '(' expr ')'';'
                    |'for' '(' init=exprs? ';' cond=exprs? ';' step=exprs? ')' stat
                    ;
iterationDecStatement:'for' '(' init2=declaration? ';' cond=exprs? ';' step=exprs? ')' stat;



//声明
declarator : Identifier                                             #VariableDeclarator
            |declarator '[' expr ']'                                #ArrayDeclarator
            |declarator '(' arguments? ')'                          #FunDeclarator
            |Type declarator                                        #ParamsDeclarator
            ;


initList: declarator (Assignment_operator '{'? exprs '}'?)?;
initLists : initList
            |initLists ',' initList
            ;
declaration:Type initLists ;

functionDefine:Type declarator '(' arguments? ')' body=compoundStatement;
arguments : fundeflist?;
fundeflist : declarator
            |declarator ',' fundeflist
            ;
//fundef : Type Identifier;
Type   :    'int'
            |'float'
            |'char'
            |'short'
            |'void'
            |'long'
            |'double'
            |'signed'
            |'unsigned'
            ;

IntegerConstant: (([1-9][0-9]*)|('0'[0-7]*)|('0'[xX][0-9a-fA-F]*))([uU]?('ll'|'LL')?|('ll'|'LL')?[uU]?|[uU]?[Ll]?|[Ll]?[Uu]?);
CharConstant: ([cLuU]?)('\''.?'\'');
FloatConstant: DECIFLOAT
             | HEXAFLOAT
             ;
fragment
DECIFLOAT : ([0-9]+'.'?[0-9]*)([eE][+-]?[0-9]+)?([flFL])?;

fragment
HEXAFLOAT : ('0'[xX][0-9a-fA-F]+'.'?[0-9a-fA-F]*)([pP][+-]?[0-9]+)?([flFL])?;

StringLiteral : (([uUL]?)|('u8'))('"'(.*?)'"');

Identifier: ([_a-zA-Z][_a-zA-Z0-9]*);