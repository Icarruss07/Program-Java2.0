@CHCP 65001 > NUL

@ECHO off

@SET nombre=Programa Clientes

ECHO Introduce el nombre de salida del JAR

@SET jar=Programa_Clientes
@SET main=Control
@SET nombrepaquete=jcolonia.daw2020.programas
@SET rutapaquete=jcolonia\daw2020\programas

@SET PATH=c:java\jdk11\bin;%PATH%
@SET PATH=C:\java\Java8\bin;%%
@SET CLASSPATH=bin
@SET JAVA_TOOL_OPTIONS=-Dfile.encoding=UTF-8

ECHO ---------------
ECHO.

jar cvf %jar%.jar -C bin .

PAUSE