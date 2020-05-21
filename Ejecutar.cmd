@CHCP 65001 > NUL
@ECHO off

ECHO Ejecutando Programa Clientes
ECHO =====================
ECHO.

@SET PATH=c:\java\jdk-11\bin;%PATH%
@SET CLASSPATH=.\bin
@SET JAVA_TOOL_OPTIONS=-Dfile.encoding=UTF-8

java jcolonia.daw2020.programas.Control

PAUSE