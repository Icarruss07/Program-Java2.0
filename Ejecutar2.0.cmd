@CHCP 65001 > NUL

@ECHO off

@SET nombre=Programa Clientes
@SET jar=Programa_Clientes
@SET args=
@SET main=Control
@SET nombrepaquete=jcolonia.daw2020.programas
@SET rutapaquete=jcolonia\daw2020\programas

@SET PATH=c:java\jdk11\bin;%PATH%
@SET CLASSPATH=bin
@SET JAVA_TOOL_OPTIONS=-Dfile.encoding=UTF-8

ECHO %nombre%

ECHO ---------------
ECHO.

java -jar %jar%.jar %args%