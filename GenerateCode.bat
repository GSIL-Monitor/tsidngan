@echo off
pause
cd %~dp0
echo ----------------------------start------------------------
call mvn mybatis-generator:generate
echo ----------------------------finished------------------------
pause