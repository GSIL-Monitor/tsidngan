@echo off
pause
cd %~dp0
echo ----------------------------package start------------------------
call mvn clean package
echo ----------------------------package finished------------------------
pause