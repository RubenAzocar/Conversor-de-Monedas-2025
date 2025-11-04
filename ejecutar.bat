@echo off
echo Ejecutando Conversor de Monedas...
echo.

REM Verificar que exista la carpeta out
if not exist "out" (
    echo × Error: No se encontraron los archivos compilados.
    echo Por favor ejecute primero compilar.bat
    pause
    exit /b 1
)

REM Ejecutar la aplicacion
java -cp "out;lib\gson-2.10.1.jar" com.example.conversor.Principal

pause
