grammar odl;

@parser::header
{
//package parser;

import odlAst.*;
}

@lexer::header
{
//package parser;
}

/* Regras da gramática */



odlprog returns [ODLProg ast]:
  c=classList{
     $ast = new ODLProg($c.ast.getLine(), $c.ast.getCol(), $c.ast);
}

;
/*
prog returns [Prog ast]:
  d=dataList f=funcList {

  if($d.ast != null){
     $ast = new Prog($d.ast.getLine(), $d.ast.getCol(), $d.ast, $f.ast);
    }
  else if($f.ast != null){
    $ast = new Prog($f.ast.getLine(), $f.ast.getCol(), $d.ast, $f.ast);
    }
  else{
    $ast = new Prog(0, 0, $d.ast, $f.ast);
  }
  }
;
*/
classList returns [ClassList ast]:
  (c=class {
    if($ast == null){$ast = new ClassList($c.ast.getLine(), $c.ast.getCol(), $c.ast); }
    else{$ast.addNode($c.ast);}
  })*
;


class returns [ClassAst ast]:
  kw='class' TYPE '{' declList '};' { $ast = new ClassAst($kw.line, $kw.pos, new Type($TYPE.line, $TYPE.pos, $TYPE.text), $declList.ast);}
;


declList returns [DeclList ast]:
   (d=decl {
    if($ast == null){$ast = new DeclList($d.ast.getLine(), $d.ast.getCol(), $d.ast); }
    else{$ast.addNode($d.ast);}
  })*
;

decl returns [Decl ast]:
  'attribute'? type ID';' {$ast = new Decl($ID.line, $ID.pos, new ID($ID.line, $ID.pos, $ID.text), $type.ast);}
  ;



type returns [Type ast]:

  t2=type{$ast = new Type($t2.ast.getLine(), $t2.ast.getCol(), $t2.ast.getName()); int i=0;}
  ('['']' {$ast.addDimension();})+
  |
  ( t='byte'  | t='char'  | t='float'  | t='short' | t='int' | t='long' | t='double' |  t='oid' | t='enum'    | t=TYPE  ) {$ast = new Type($t.line, $t.pos, $t.text);}
;



ID : [a-z] ([a-z]|[A-Z]|[0-9]|'_')*;
INT : [0-9]+;
FLOAT : [0-9]+ '.' [0-9]+ | '.' [0-9]+;
CHAR : ('\'' '\\n' '\'')
    | ('\'' '\\t' '\'')
    | ('\'' '\\b' '\'')
    | ('\'' '\\r' '\'')
    | ('\'' '\\\\' '\'')
    | ('\'' '\\' '\'')
    | ('\'' ~('\''|'\\') '\'')
    ;
TYPE: [A-Z]([a-z]|[A-Z]|[0-9]|'_')*;
NEWLINE: '\r'? '\n' -> skip;
LINE_COMMENT: '//' ~('\r' | '\n')* NEWLINE -> skip;
COMMENT: '/*' (.)* '*/' -> skip;
BLANK: (' ' | '\f' | '\t') -> skip;