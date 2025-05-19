@echo off
echo Compiling all Java files...
javac -cp ../biuoop-1.4.jar -d ../out Ass5Game.java gamesetup\*.java shapes\*.java interfaces\*.java hitlisteners\*.java
if %errorlevel% neq 0 (
    echo.
    echo ❌ Compilation failed!
) else (
    echo.
    echo ✅ Compilation successful!
)
pause
