#ifndef MYSCANNER_H
#define MYSCANNER_H




typedef enum{
    T_over,T_keyword,T_identifier,T_interger,T_float,T_char,T_string,T_symbol,T_empty
    
}TokenType;

const char* token_strs[]={
    "EOF","keyword","Identifier","const_interger","const_float","const_char","const_string","..."
};


#endif
