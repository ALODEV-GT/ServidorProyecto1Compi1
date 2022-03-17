package midik.jflex;
import java_cup.runtime.Symbol;
import midik.cup.sym;

%%
%class AnalizadorLexico
%type Symbol
%unicode
%public
%cup
%line
%column
%state COMENTARIO_BLOQUE

%{

    private Symbol symbol(int type) {
        return new Symbol(type, yycolumn+1, yyline+1);
    }

    private Symbol symbol(int type, Object value) {
        return new Symbol(type, yycolumn+1, yyline+1, value);
    }

%}

//Palabras reservadas
IMPORT = "import"
PUBLIC = "public"
PRIVATED = "privated"
PROTECTED = "protected"
CLASS = "class"
INT = "int"
BOOLEAN = "boolean"
STRING = "String"
DOUBLE = "double"
CHAR = "char"
IF = "if"
ELSE = "else"
FOR = "for"
WHILE = "while"
DO = "do"
SWITCH = "switch"
FINAL = "final"
BREAK = "break"
RETURN = "return"
CASE = "case"
DEFAULT = "default"
VOID = "void"

//Signos de puntuacion
P_COMA = ";"
PUNTO = "."
COMA = ","
DOS_PUNTOS = ":"

//Signos de agrupacion
LLAVE_A = "{"
LLAVE_C = "}"
PARENTESIS_A = "("
PARENTESIS_C = ")"

//Operadores aritmeticos
MAS = "+"
MENOS = "-"
POR = "*"
DIVISION = "/"

//Operadores relacionales
IGUAL = "=="
MAYOR_IGUAL = ">="
MENOR_IGUAL = "<="
ASIGNACION = "="
MAYOR_QUE = ">"
MENOR_QUE = "<"
DIFERENTE = "!="

//Operadores logicos
AND = "&&"
OR = "||"
NOT = "!"

//Operadores incremento/decremento
INCREMENTO = "++"
DECREMENTO = "--"

//Partes (Solo seran utiles para formar otras estructuras)
LETRA = [a-zA-Z]
NUMERO = [0-9]
GUION_BAJO = "_"
FIN_LINEA = \r|\n|\r\n|\u2028|\u2029|\u000B|\u000C|\u0085
ESPACIO = " " | "\t"
CARACTERES = [^\r\n]

//Comentarios
COMENTARIO_LINEA = "//"~{FIN_LINEA}
INICIO_COMENTARIO_BLOQUE = "/*"
FIN_COMENTARIO_BLOQUE = "*/"

//Valores
ID = ({GUION_BAJO} | {LETRA})({GUION_BAJO} | {LETRA} | {NUMERO})*
ENTERO = {NUMERO}{NUMERO}*
DECIMAL = {NUMERO}{NUMERO}*{PUNTO}{NUMERO}{NUMERO}*
CADENA = "\""~"\""
TRUE = "true"
FALSE = "false"
CARACTER = "'"{CARACTERES}"'"
NULL = "null"

%%
<YYINITIAL> {TRUE}                              {return symbol(sym.TRUE, yytext());}
<YYINITIAL> {FALSE}                             {return symbol(sym.FALSE, yytext());}
<YYINITIAL> {NULL}                              {return symbol(sym.NULL, yytext());}
<YYINITIAL> {CADENA}                            {return symbol(sym.CADENA, yytext());}
<YYINITIAL> {CARACTER}                          {return symbol(sym.CARACTER, yytext());}
<YYINITIAL> {IMPORT}                            {return symbol(sym.IMPORT, yytext());}          
<YYINITIAL> {PUBLIC}                            {return symbol(sym.PUBLIC, yytext());}          
<YYINITIAL> {PRIVATED}                          {return symbol(sym.PRIVATED, yytext());}            
<YYINITIAL> {PROTECTED}                         {return symbol(sym.PROTECTED, yytext());}           
<YYINITIAL> {CLASS}                             {return symbol(sym.CLASS, yytext());}           
<YYINITIAL> {INT}                               {return symbol(sym.INT, yytext());}         
<YYINITIAL> {BOOLEAN}                           {return symbol(sym.BOOLEAN, yytext());}         
<YYINITIAL> {STRING}                            {return symbol(sym.STRING, yytext());}          
<YYINITIAL> {DOUBLE}                            {return symbol(sym.DOUBLE, yytext());}          
<YYINITIAL> {CHAR}                              {return symbol(sym.CHAR, yytext());}                    
<YYINITIAL> {IF}                                {return symbol(sym.IF, yytext());}          
<YYINITIAL> {ELSE}                              {return symbol(sym.ELSE, yytext());}            
<YYINITIAL> {FOR}                               {return symbol(sym.FOR, yytext());}         
<YYINITIAL> {WHILE}                             {return symbol(sym.WHILE, yytext());}           
<YYINITIAL> {DO}                                {return symbol(sym.DO, yytext());}            
<YYINITIAL> {SWITCH}                            {return symbol(sym.SWITCH, yytext());}          
<YYINITIAL> {FINAL}                             {return symbol(sym.FINAL, yytext());}           
<YYINITIAL> {BREAK}                             {return symbol(sym.BREAK, yytext());}           
<YYINITIAL> {RETURN}                            {return symbol(sym.RETURN, yytext());}
<YYINITIAL> {CASE}                              {return symbol(sym.CASE, yytext());}
<YYINITIAL> {DEFAULT}                           {return symbol(sym.DEFAULT, yytext());}
<YYINITIAL> {VOID}                              {return symbol(sym.VOID, yytext());}       
<YYINITIAL> {P_COMA}                            {return symbol(sym.P_COMA, yytext());}
<YYINITIAL> {PUNTO}                             {return symbol(sym.PUNTO, yytext());}
<YYINITIAL> {COMA}                              {return symbol(sym.COMA, yytext());}
<YYINITIAL> {DOS_PUNTOS}                        {return symbol(sym.DOS_PUNTOS, yytext());}
<YYINITIAL> {LLAVE_A}                           {return symbol(sym.LLAVE_A, yytext());}
<YYINITIAL> {LLAVE_C}                           {return symbol(sym.LLAVE_C, yytext());}
<YYINITIAL> {PARENTESIS_A}                      {return symbol(sym.PARENTESIS_A, yytext());}
<YYINITIAL> {PARENTESIS_C}                      {return symbol(sym.PARENTESIS_C, yytext());}
<YYINITIAL> {MAS}                               {return symbol(sym.MAS, yytext());}
<YYINITIAL> {MENOS}                             {return symbol(sym.MENOS, yytext());}
<YYINITIAL> {POR}                               {return symbol(sym.POR, yytext());}
<YYINITIAL> {INICIO_COMENTARIO_BLOQUE}          {yybegin(COMENTARIO_BLOQUE);}
<YYINITIAL> {COMENTARIO_LINEA}                  {}
<YYINITIAL> {DIVISION}                          {return symbol(sym.DIVISION, yytext());}
<YYINITIAL> {IGUAL}                             {return symbol(sym.IGUAL, yytext());}
<YYINITIAL> {MAYOR_IGUAL}                       {return symbol(sym.MAYOR_IGUAL, yytext());}
<YYINITIAL> {MENOR_IGUAL}                       {return symbol(sym.MENOR_IGUAL, yytext());}
<YYINITIAL> {ASIGNACION}                        {return symbol(sym.ASIGNACION, yytext());}
<YYINITIAL> {MAYOR_QUE}                         {return symbol(sym.MAYOR_QUE, yytext());}
<YYINITIAL> {MENOR_QUE}                         {return symbol(sym.MENOR_QUE, yytext());}
<YYINITIAL> {DIFERENTE}                         {return symbol(sym.DIFERENTE, yytext());}
<YYINITIAL> {AND}                               {return symbol(sym.AND, yytext());}
<YYINITIAL> {OR}                                {return symbol(sym.OR, yytext());}
<YYINITIAL> {NOT}                               {return symbol(sym.NOT, yytext());}
<YYINITIAL> {INCREMENTO}                        {return symbol(sym.INCREMENTO, yytext());}
<YYINITIAL> {DECREMENTO}                        {return symbol(sym.DECREMENTO, yytext());}
<YYINITIAL> {ID}                                {return symbol(sym.ID, yytext());}
<YYINITIAL> {ENTERO}                            {return symbol(sym.ENTERO, yytext());}
<YYINITIAL> {DECIMAL}                           {return symbol(sym.DECIMAL, yytext());}
<YYINITIAL> {FIN_LINEA}                         {}
<YYINITIAL> {ESPACIO}                           {}

<COMENTARIO_BLOQUE> {
    {FIN_COMENTARIO_BLOQUE}                     {yybegin(YYINITIAL);}
    [^]                                         {}
}

<<EOF>>                                         { return symbol(sym.EOF,"FIN"); }
<YYINITIAL> .                                   {System.out.println("El simbolo " + yytext() + " no existe en el lenguaje." + " Linea:" + (yyline+1) + " Columna:" + yycolumn);}