@echo off
REM Build and run helper (Windows).
REM   run.bat all        run every lab in order
REM   run.bat lab05      run one lab
REM   run.bat test       run the automated test-suite
REM   run.bat library       Library Management System (Lab 11 mini-project), interactive menu
REM   run.bat library-demo  scripted demo of the mini-project (persistence + overdue)
REM   run.bat clean      delete compiled classes and generated output
REM Add -i after a lab name for interactive input (labs 7 and 10), e.g.  run.bat lab07 -i
setlocal enabledelayedexpansion
cd /d "%~dp0"
set "CMD=%~1"
if "%CMD%"=="" set "CMD=all"

if /i "%CMD%"=="clean" (
  if exist out rmdir /s /q out
  del /q output\* 2>nul
  echo cleaned
  exit /b 0
)

if not exist out mkdir out
if not exist output mkdir output
if exist sources.txt del sources.txt
for /r src %%f in (*.java) do (set "P=%%f" & echo "!P:\=/!">>sources.txt)
for /r tests %%f in (*.java) do (set "P=%%f" & echo "!P:\=/!">>sources.txt)
javac -Xlint:all -d out @sources.txt
if errorlevel 1 ( del sources.txt & exit /b 1 )
del sources.txt

if /i "%CMD%"=="test" ( java -cp out AllTests & exit /b %errorlevel% )

if /i "%CMD%"=="library" ( java -cp out lab11.library.LibraryApp %2 & exit /b %errorlevel% )
if /i "%CMD%"=="library-demo" ( java -cp out lab11.library.LibraryDemo & exit /b %errorlevel% )

if /i "%CMD%"=="all" (
  for %%i in (01 02 03 04 05 06 07 08 09 10 11) do (
    echo.
    echo ################  LAB %%i  ################
    java -cp out lab%%i.Lab%%iRunner
  )
  echo.
  echo ################  LAB 11 MINI-PROJECT DEMO  ################
  java -cp out lab11.library.LibraryDemo
  exit /b 0
)

set "N=%CMD:lab=%"
if "%N:~1,1%"=="" set "N=0%N%"
echo.
echo ################  LAB %N%  ################
java -cp out lab%N%.Lab%N%Runner %2
