lexer grammar txjLexer;

IntegerConstant: (([1-9][0-9]*)|('0'[0-7]*)|('0'[xX][0-9a-fA-F]*))([uU]?('ll'|'LL')?|('ll'|'LL')?[uU]?|[uU]?[Ll]?|[Ll]?[Uu]?);
CharConstant: ([cLuU]?)('\''.?'\'');
FloatConstant: DECIFLOAT
             | HEXAFLOAT
             ;
fragment
DECIFLOAT : ([0-9]+'.'?[0-9]*)([eE][+-]?[0-9]+)?([flFL])?;

fragment
HEXAFLOAT : ('0'[xX][0-9a-fA-F]+'.'?[0-9a-fA-F]*)([pP][+-]?[0-9]+)?([flFL])?;

StringLiteral : (([uUL]?)|('u8'))('"'(.*)'"');

Identifier: ([_a-zA-Z][_a-zA-Z0-9]*);