@echo off
rem /**
rem  * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
rem  *
rem  * Author: ThinkGem@163.com
rem  */
title %cd%
echo.
echo  [信息] 使用Jetty插件运行工程。
echo.
rem pause
rem echo.

cd %~dp0
cd..

set MAVEN_OPTS=%MAVEN_OPTS% -Xms256m -Xmx512m -XX:PermSize=128m -XX:MaxPermSize=256m
set MAVEN_OPTS=%MAVEN_OPTS% -noverify -javaagent:lib/jrebel.jar
set MAVEN_OPTS=%MAVEN_OPTS% -Drebel.spring_plugin=true
set MAVEN_OPTS=%MAVEN_OPTS% -Drebel.dirs=src/main/webapp/WEB-INF/classes

call mvn jetty:run  

cd bin
pause