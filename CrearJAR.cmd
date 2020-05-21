@CHCP 65001 > NUL

@ECHO off

@SET nombre=Programa Clientes

ECHO Introduce el nombre de salida del JAR

SET /p jar="JAR: "

@SET main=Control
@SET nombrepaquete=jcolonia.daw2020.programas
@SET rutapaquete=jcolonia\daw2020\programas

@SET PATH=c:java\jdk11\bin;%PATH%
@SET CLASSPATH=bin
@SET JAVA_TOOL_OPTIONS=-Dfile.encoding=UTF-8

ECHO ---------------
ECHO.

jar --create --file %jar%.jar --main-class=%nombrepaquete%.%main% -C bin .

PAUSE