# Script de compilacion para PowerShell
Write-Host "Compilando Conversor de Monedas..." -ForegroundColor Cyan
Write-Host ""

# Crear carpeta de salida si no existe
if (-not (Test-Path "out")) {
    New-Item -ItemType Directory -Path "out" | Out-Null
}

# Compilar todas las clases Java
javac -cp "lib\gson-2.10.1.jar" -d out src\main\java\com\example\conversor\*.java

if ($LASTEXITCODE -eq 0) {
    Write-Host ""
    Write-Host "✓ Compilacion exitosa!" -ForegroundColor Green
    Write-Host "Los archivos .class estan en la carpeta 'out'" -ForegroundColor Gray
} else {
    Write-Host ""
    Write-Host "× Error en la compilacion" -ForegroundColor Red
}

Write-Host ""
Read-Host "Presione Enter para continuar"
