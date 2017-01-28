grammar junk;

tokens: .* EOF;

KEYWORD: ('PROGRAM'|'BEGIN'|'END'|'FUNCTION'|'READ'|'WRITE'|'IF'|'ELSE'|'ENDIF'|'WHILE'|'ENDWHILE'|'CONTINUE'|'BREAK'|'RETURN'|'INT'|'VOID'|'STRING'|'FLOAT');

STRINGLITERAL: '"'(.*?)'"';

IDENTIFIER: [a-zA-Z][a-zA-Z0-9]*;

INTLITERAL: [0-9]+;

FLOATLITERAL: [0-9]*'.'[0-9]+;

OPERATOR: (':='|'+'|'-'|'*'|'/'|'='|'!='|'<'|'>'|'('|')'|';'|','|'<='|'>=');

COMMENT: '--'~('\n')* -> skip ;

WS: [ \r\n\t]+ -> skip ;
