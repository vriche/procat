@echo off
rem /**
rem  * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
rem  *
rem  * Author: ThinkGem@163.com
rem  */
title %cd%
echo.
echo [��Ϣ] ʹ��Jetty������й��̡�
echo.
rem pause
rem echo.

cd %~dp0
cd..
rem procat/src/main/webapp/WEB-INF/classes
set MAVEN_OPTS=%MAVEN_OPTS% -Xms256m -Xmx512m -XX:PermSize=128m -XX:MaxPermSize=256m

set MAVEN_OPTS=%MAVEN_OPTS% -noverify -javaagent:D:/java/java_tools/javarebel-2.0/jrebel.jar
rem set MAVEN_OPTS=%MAVEN_OPTS% -noverify -javaagent:D:/java/java_tools/jrebel-5.5/jrebel.jar

set MAVEN_OPTS=%MAVEN_OPTS% -Drebel.spring_plugin=true
set MAVEN_OPTS=%MAVEN_OPTS% -Drebel.dirs=D:/java/work/procat/src/main/webapp/WEB-INF/classes

rem set JAVA_OPTS=%JAVA_OPTS% -noverify -javaagent:D:/java/java_tools/javarebel-2.0/jrebel.jar  -Drebel.spring_plugin=false
rem set JAVA_OPTS=%JAVA_OPTS% -Drebel.dirs=D:/java/work/procat/src/main/webapp/WEB-INF/classes

call mvn jetty:run  

cd bin
pause