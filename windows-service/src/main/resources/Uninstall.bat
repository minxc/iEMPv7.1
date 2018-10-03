@echo off

@echo .
@echo . Uninstall.bat - uninstall JavaService executable using JavaWindowsService.jar
@echo .


setlocal
@rem note that if JVM not found, service 'does not report an error' when startup fails, although event logged
rem if "%JAVA_HOME%" == "" set JAVA_HOME=d:\j2sdk1.4.2_10\jre

set JAVA_HOME=%CD%\jdk1.8.0_172
echo %JAVA_HOME%
set JVMDIR=%JAVA_HOME%\jre\bin\server
echo %JVMDIR%
set JSBINDIR=%CD%
set JSEXE=%JSBINDIR%\JavaService.exe
set SSBINDIR=%JSBINDIR%


@echo . Using following version of JavaService executable:
@echo .
%JSEXE% -version
@echo .



@echo Stopping JavaWindowServiceMinxc service... Press Control-C to abort
@pause
@echo .
net stop JavaWindowServiceMinxc
@echo .


@echo Un-installing JavaWindowServiceMinxc service... Press Control-C to abort
@pause
@echo .
%JSEXE% -uninstall JavaWindowServiceMinxc
@echo .


@echo End of script
@pause