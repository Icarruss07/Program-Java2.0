@CHCP 65001 > NUL

@ECHO off

SET nombre=Programa Clientes
SET jar=Programa_Clientes

SET PATH=c:\java\Java8\bin;%PATH%
SET JAVA_TOOL_OPTIONS=-Dfile.encoding=UTF-8

ECHO Ejecutando %jar%.jar
ECHO.

java -jar %jar%.jar

PAUSE