@echo off
echo Compilando Conversor de Monedas...
echo.

REM Crear carpeta de salida si no existe
if not exist "out" mkdir out

REM Compilar todas las clases Java
javac -cp "lib\gson-2.10.1.jar" -d out src\main\java\com\example\conversor\*.java

if %errorlevel% equ 0 (
    echo.
    echo ✓ Compilacion exitosa!
    echo Los archivos .class estan en la carpeta 'out'
) else (
    echo.
    echo × Error en la compilacion
)

pause
