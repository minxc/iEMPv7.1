@echo off

@echo .....
@echo . Install.bat -  JavaService executable using JavaWindowsService.jar
@echo .....


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
@echo ......
%JSEXE% -version
@echo ......


@echo Installing JavaWindowServiceMinxc service... Press Control-C to abort
@pause
@echo .
%JSEXE% -install JavaWindowServiceMinxc %JVMDIR%\jvm.dll -Djava.class.path=%SSBINDIR%\JavaWindowsService.jar -Xms16M -Xmx32M -start org.minxc.emp.windows.service.App -method startService -stop org.minxc.emp.windows.service.App -method stopService -out %JSBINDIR%\stdout.log -err %JSBINDIR%\stderr.log -current %JSBINDIR% -manual -description "Java Windows服务--南京今领信息技术有限公司"
@echo .


@echo Starting JavaWindowServiceMinxc service... Press Control-C to abort
@pause
@echo .
net start JavaWindowServiceMinxc
@echo .


@rem @echo Stopping sample service... Press Control-C to abort
@rem @pause
@rem @echo .
@rem net stop SampleService
@rem @echo .


@rem @echo Un-installing sample service... Press Control-C to abort
@rem @pause
@rem @echo .
@rem %JSEXE% -uninstall SampleService
@rem @echo .


@echo End of script
@pause