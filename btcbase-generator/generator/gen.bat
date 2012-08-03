@echo off
echo [INFO] Generate entity from database to %cd%\output dir.
call gradle genEntity compileJava jar genBizCode prepareGenPage pages

pause