# Script de ejecucion para PowerShell
Write-Host "Ejecutando Conversor de Monedas..." -ForegroundColor Cyan
Write-Host ""

# Verificar que exista la carpeta out
if (-not (Test-Path "out")) {
    Write-Host "× Error: No se encontraron los archivos compilados." -ForegroundColor Red
    Write-Host "Por favor ejecute primero compilar.ps1 o compilar.bat" -ForegroundColor Yellow
    Write-Host ""
    Read-Host "Presione Enter para continuar"
    exit 1
}

# Ejecutar la aplicacion
java -cp "out;lib\gson-2.10.1.jar" com.example.conversor.Principal

Write-Host ""
Read-Host "Presione Enter para continuar"
