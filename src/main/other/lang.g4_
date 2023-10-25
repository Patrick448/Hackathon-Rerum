grammar lang;

@parser::header
{
//package parser;

import ast.*;
}

@lexer::header
{
//package parser;
}

/* Regras da gramática */



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

dataList returns [DataList ast]:
  (d=data {
    if($ast == null){$ast = new DataList($d.ast.getLine(), $d.ast.getCol(), $d.ast); }
    else{$ast.addNode($d.ast);}
  })*
;

data returns [Data ast]:
  kw='data' TYPE '{' declList '}' { $ast = new Data($kw.line, $kw.pos, new Type($TYPE.line, $TYPE.pos, $TYPE.text), $declList.ast);}
  ;

funcList returns [FuncList ast]:
  (f=func {
    if($ast == null){$ast = new FuncList($f.ast.getLine(), $f.ast.getCol(), $f.ast); }
    else{$ast.addNode($f.ast);}
  })*
;

func returns [Func ast]:
  {boolean hasTypes = false;
  boolean hasParams = false;}

  ID '(' (params{hasParams = true;})? ')' (':' types {hasTypes=true;})? '{'cmdList'}'{
    $ast = new Func(
      $ID.line, $ID.pos, 
      new ID($ID.line, $ID.pos, $ID.text), 
      hasParams? $params.ast:null, 
      hasTypes? $types.ast : null, 
      $cmdList.ast);
    }
;

declList returns [DeclList ast]:
   (d=decl {
    if($ast == null){$ast = new DeclList($d.ast.getLine(), $d.ast.getCol(), $d.ast); }
    else{$ast.addNode($d.ast);}
  })*
;

decl returns [Decl ast]:
  ID '::' type ';' {$ast = new Decl($ID.line, $ID.pos, new ID($ID.line, $ID.pos, $ID.text), $type.ast);}
  ;

params returns[ParamsList ast]:
  p=param {$ast = new ParamsList($p.ast.getLine(), $p.ast.getCol(), $p.ast); } 
  (','p2=param {$ast.addNode($p2.ast); })* 

  /*(p=param {
    if($ast == null){$ast = new ParamsList($p.ast.getLine(), $p.ast.getCol(), $p.ast); }
    else{$ast.addNode($p.ast);}
  })**/
;

param returns [Param ast]:
  ID '::' t=type {
    $ast = new Param($ID.line, $ID.pos, new ID($ID.line, $ID.pos, $ID.text), $type.ast);}
;

types returns [TypeList ast]:
  t=type{$ast = new TypeList($t.ast.getLine(), $t.ast.getCol(), $t.ast); } 
  (','t2=type {$ast.addNode($t2.ast); })* 
;

type returns [Type ast]:
  
  t2=type{$ast = new Type($t2.ast.getLine(), $t2.ast.getCol(), $t2.ast.getName()); int i=0;} 
  ('['']' {$ast.addDimension();})+ 
  |
  ( t='Int'  | t='Char'  | t='Float'  | t='Bool'  | t=TYPE  ) {$ast = new Type($t.line, $t.pos, $t.text);}
;

cmdList returns [CmdList ast]:
  (c=cmd {
    
    if($ast == null){$ast = new CmdList($c.ast.getLine(), $c.ast.getCol(), $c.ast); }
    else{$ast.addNode($c.ast);}
  })*
;


cmd returns [Node ast]:
 '{' cmdList '}' { $ast = $cmdList.ast;}
 |
 'print' exp ';' {$ast = new Print($exp.ast.getLine(), $exp.ast.getCol(), $exp.ast);}
 |
 'read' lvalue ';' {$ast = new Read($lvalue.ast.getLine(), $lvalue.ast.getCol(), $lvalue.ast);}
 |
 'iterate' '(' exp ')' cmd {$ast = new Iterate($exp.ast.getLine(), $exp.ast.getCol(), $exp.ast, $cmd.ast);}
 |
 lvalue '=' exp ';' {$ast = new Attr($lvalue.ast.getLine(), $lvalue.ast.getCol(), $lvalue.ast, $exp.ast);}
 |
 f='if' '(' exp ')' cmd  {$ast = new If($f.line, $f.pos, $exp.ast, $cmd.ast, null);} 
 |
 f='if' '(' exp ')' c1=cmd 'else' c2=cmd  {$ast = new If($f.line, $f.pos, $exp.ast, $c1.ast, $c2.ast);} 
  |
 'return' e=exps ';' {$ast = new ReturnCMD($e.ast.getLine(), $e.ast.getCol(), $e.ast);}
 |
 {boolean hasLvalues = false;
  boolean hasExps = false;}
 ID '(' (exps {hasExps=true;})? ')' ('<' lvalues '>' {hasLvalues=true;})? ';' {$ast = new CallFunction($ID.line, $ID.pos, new ID($ID.line, $ID.pos, $ID.text), hasExps? $exps.ast:null, hasLvalues? $lvalues.ast:null);}
;



/*returnElement returns [Expr ast]:
  e=exp ','? {$ast = new Return($e.ast.getLine(), $e.ast.getCol(), $e.ast);}
;*/

exps returns [ExprList ast]:
    e=exp{$ast = new ExprList($e.ast.getLine(), $e.ast.getCol(), $e.ast); } 
  (','e2=exp {$ast.addNode($e2.ast); })* 
  ;



exp returns [Expr ast]:
  a1=exp '&&' a2=exp {$ast = new And($a1.ast.getLine(), $a1.ast.getCol(), $a1.ast, $a2.ast);} 
  |
  rexp{$ast=$rexp.ast;}
;

rexp returns [Expr ast]:
  ll=aexp o='<' rl=aexp {$ast = new LessThan($o.line, $o.pos, $ll.ast, $rl.ast);} 
  |
  lg=aexp o='>' rg=aexp {$ast = new GreaterThan($o.line, $o.pos, $lg.ast, $rg.ast);} 
  |
  le=rexp o='==' re=aexp {$ast = new Eq($o.line, $o.pos, $le.ast, $re.ast);} 
  | 
  ld=rexp o='!=' rd=aexp {$ast = new Diff($o.line, $o.pos, $ld.ast, $rd.ast);} 
  |
  aexp{$ast=$aexp.ast;}
;

aexp returns [Expr ast]:
  a1=aexp '+' a2=mexp{$ast = new Add($a1.ast.getLine(), $a1.ast.getCol(), $a1.ast, $a2.ast);}
  |
  a1=aexp '-' a2=mexp{$ast = new Sub($a1.ast.getLine(), $a1.ast.getCol(), $a1.ast, $a2.ast);}
  |
  mexp{$ast=$mexp.ast;}  
;

mexp returns [Expr ast]:
  a1=mexp '*' a2=sexp{$ast = new Mul($a1.ast.getLine(), $a1.ast.getCol(), $a1.ast, $a2.ast);}
  |
  a1=mexp '/' a2=sexp{$ast = new Div($a1.ast.getLine(), $a1.ast.getCol(), $a1.ast, $a2.ast);}
  |
  a1=mexp '%' a2=sexp{$ast = new Rest($a1.ast.getLine(), $a1.ast.getCol(), $a1.ast, $a2.ast);}
  |
  sexp{$ast=$sexp.ast;}
;

sexp returns [Expr ast]:
  n='!' sexp {$ast = new Neg($n.line, $n.pos, $sexp.ast);}
  |
  su='-' sexp {$ast = new SubUni($su.line, $su.pos, $sexp.ast);}
  | 
  (b='true' | b='false'){$ast = new Bool($b.line, $b.pos, Boolean.parseBoolean($b.text));}
  |
  n='null'{$ast = new Null($n.line, $n.pos);}
  |
  INT {$ast = new Int($INT.line, $INT.pos, Integer.parseInt($INT.text));}
  |
  FLOAT {$ast = new FloatAst($FLOAT.line, $FLOAT.pos, Float.parseFloat($FLOAT.text));}
  |
  CHAR {$ast = new Char($CHAR.line, $CHAR.pos, $CHAR.text);}
  | 
  pexp {$ast=$pexp.ast;}
;

pexp returns [Expr ast]:
  lvalue{$ast=$lvalue.ast;}
  | 
  '(' exp ')'{$ast=$exp.ast;}
  |

  {boolean hasExp = false;}
  'new' type ('[' exp ']'{hasExp=true;})? {$ast = new New($type.ast.getLine(), $type.ast.getCol(), $type.ast, hasExp? $exp.ast:null);
    if(hasExp){$type.ast.addDimension();}}
  |
  {boolean hasExps = false;}
  ID '(' (exps {hasExps=true;})? ')' '[' exp ']' {$ast = new CallFunctionVet($ID.line, $ID.pos, new ID($ID.line, $ID.pos, $ID.text),  hasExps? $exps.ast:null, $exp.ast);}
;

lvalues returns [LValueList ast]:

    l=lvalue{$ast = new LValueList($l.ast.getLine(), $l.ast.getCol(), $l.ast); } 
  (','l2=lvalue {$ast.addNode($l2.ast); })* 
  ;

lvalue returns [LValue ast]:
  ID {$ast = new LValue($ID.line, $ID.pos, new ID($ID.line, $ID.pos, $ID.text), null, null);}
  |
  l=lvalue '[' exp ']' {$ast = new LValue($l.ast.getLine(), $l.ast.getCol(), null, $l.ast, $exp.ast);}
  |
  l=lvalue '.' ID {$ast = new LValue($l.ast.getLine(), $l.ast.getCol(), new ID($ID.line, $ID.pos, $ID.text), $l.ast, null);}
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
LINE_COMMENT: '--' ~('\r' | '\n')* NEWLINE -> skip;
COMMENT: '{-' (.)* '-}' -> skip;
BLANK: (' ' | '\f' | '\t') -> skip;