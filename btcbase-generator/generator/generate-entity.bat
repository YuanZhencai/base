@echo off
echo [INFO] Generate entity from database to %cd%\output dir.
call gradle genEntity

pause