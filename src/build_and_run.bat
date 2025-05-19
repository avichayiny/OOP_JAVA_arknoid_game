@echo off
echo 🔧 Compiling all Java files...
javac -cp ../biuoop-1.4.jar -d ../out Ass5Game.java gamesetup\*.java shapes\*.java interfaces\*.java hitlisteners\*.java
if %errorlevel% neq 0 (
    echo ❌ Compilation failed!
    pause
    exit /b
)
echo ✅ Compilation successful!

echo 🚀 Running Ass5Game...
java -cp ../out;../biuoop-1.4.jar Ass5Game
pause
