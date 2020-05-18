@mode con cp select=65001 > NUL
@ECHO off

ECHO Compilando Programa Clientes
ECHO ====================
ECHO.

@SET PATH=c:\java\jdk-11\bin;%PATH%
@SET CLASSPATH=.\bin
@SET JAVA_TOOL_OPTIONS=-Dfile.encoding=UTF-8

javac -sourcepath src -d bin src\jcolonia\daw2020\programas\Control.java

PAUSE