grammar junk;

tokens: .* EOF;

IDENTIFIER: [a-zA-Z][a-zA-Z0-9]*;

INTLITERAL: [0-9]+;

FLOATLITERAL: [0-9]*'.'[0-9]+;

STRINGLITERAL: '"'[^"]+'"';

COMMENT: '--'.*?'\n'*;

WS : [\r\n\t]+;
