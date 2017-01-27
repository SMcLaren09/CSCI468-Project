grammar junk;

tokens: .* EOF;

COMMENT: '--'~('\n')*;
/*('\r\n'|'\n')*/

STRINGLITERAL: '"'(~")*'"';

IDENTIFIER: [a-zA-Z][a-zA-Z0-9]*;

INTLITERAL: [0-9]+;

FLOATLITERAL: [0-9]*'.'[0-9]+;

/*STRINGLITERAL: '"'[~"]+'"';*/

WS : [ \r\n\t]+;

