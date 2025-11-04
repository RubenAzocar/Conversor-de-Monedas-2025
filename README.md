# Conversor de Monedas - Consola (Java)

Proyecto de consola en Java que consulta la API de Exchange Rate para convertir entre monedas.

## Características principales

- Menú en bucle con 12 conversiones predefinidas y opción de salida
- Uso de HttpClient (Java 11+) para consumir la API
- Parsing JSON con Gson
- Validación de entrada del usuario
- Manejo robusto de errores de red y formato
- **Sin dependencias de Maven** - compilación directa con javac

## Conversiones disponibles

1. Dólar (USD) → Peso Argentino (ARS)
2. Peso Argentino (ARS) → Dólar (USD)
3. Dólar (USD) → Real Brasileño (BRL)
4. Real Brasileño (BRL) → Dólar (USD)
5. Dólar (USD) → Peso Colombiano (COP)
6. Peso Colombiano (COP) → Dólar (USD)
7. Peso Chileno (CLP) → Dólar (USD)
8. Dólar (USD) → Peso Chileno (CLP)
9. Peso Chileno (CLP) → Peso Filipino (PHP)
10. Peso Filipino (PHP) → Peso Chileno (CLP)
11. Peso Filipino (PHP) → Dólar (USD)
12. Dólar (USD) → Peso Filipino (PHP)

## Requisitos previos

- **Java 11 o superior** instalado
- Conexión a Internet para consultar la API
- La biblioteca Gson (incluida en la carpeta `lib/`)

## Configuración de la API Key

### Paso 1: Obtener tu API Key

1. Visita [ExchangeRate-API](https://www.exchangerate-api.com/)
2. Regístrate para obtener una clave API gratuita
3. Copia tu API key

### Paso 2: Configurar la API Key en el proyecto

1. Abre el archivo: `src/main/java/com/example/conversor/ConsultaTasa.java`
2. Busca la línea 20 que contiene:

   ```java
   public static final String API_KEY = "32f74fd97093ccf7488d0952";
   ```

3. Reemplaza el valor con tu propia API key:

   ```java
   public static final String API_KEY = "TU_API_KEY_AQUI";
   ```

4. Guarda el archivo

## Ejecución de la aplicación

### Método 1: Usando los scripts incluidos (Recomendado)

**En Windows (CMD o PowerShell):**

1. Haz doble clic en `compilar.bat` para compilar el proyecto
2. Haz doble clic en `ejecutar.bat` para ejecutar la aplicación

**O desde la terminal:**

```cmd
compilar.bat
ejecutar.bat
```

**En PowerShell:**

```powershell
.\compilar.ps1
.\ejecutar.ps1
```

### Método 2: Compilación y ejecución manual

1. Abre una terminal en la carpeta del proyecto

2. Compila el proyecto:

   ```cmd
   javac -cp "lib\gson-2.10.1.jar" -d out src\main\java\com\example\conversor\*.java
   ```

3. Ejecuta la aplicación:

   ```cmd
   java -cp "out;lib\gson-2.10.1.jar" com.example.conversor.Principal
   ```

   > **Nota para Linux/Mac:** Usa `:` en lugar de `;` en el classpath:
   > ```bash
   > java -cp "out:lib/gson-2.10.1.jar" com.example.conversor.Principal
   > ```

## Uso de la aplicación

1. Al iniciar, verás el menú con las 13 opciones
2. Ingresa el número de la opción deseada (1-13)
3. Si eliges una conversión (1-12), se te pedirá ingresar la cantidad a convertir
4. El programa mostrará el resultado de la conversión
5. El menú se mostrará nuevamente hasta que elijas la opción 13 (Salir)

### Ejemplo de uso

```text
======================================
     Conversor de Monedas - Menú
======================================
1)  Dólar (USD) a Peso Argentino (ARS)
2)  Peso Argentino (ARS) a Dólar (USD)
3)  Dólar (USD) a Real Brasileño (BRL)
4)  Real Brasileño (BRL) a Dólar (USD)
5)  Dólar (USD) a Peso Colombiano (COP)
6)  Peso Colombiano (COP) a Dólar (USD)
7)  Peso Chileno (CLP) a Dólar (USD)
8)  Dólar (USD) a Peso Chileno (CLP)
9)  Peso Chileno (CLP) a Peso Filipino (PHP)
10) Peso Filipino (PHP) a Peso Chileno (CLP)
11) Peso Filipino (PHP) a Dólar (USD)
12) Dólar (USD) a Peso Filipino (PHP)
13) Salir
Seleccione una opción (1-13): 1
Ingrese la cantidad a convertir: 100
100.00 USD = 35050.00 ARS
```

## Estructura del proyecto

```text
Proyecto Conversor de Monedas/
├── README.md                        # Este archivo
├── compilar.bat                     # Script de compilación (Windows CMD)
├── compilar.ps1                     # Script de compilación (PowerShell)
├── ejecutar.bat                     # Script de ejecución (Windows CMD)
├── ejecutar.ps1                     # Script de ejecución (PowerShell)
├── lib/                            # Dependencias externas
│   └── gson-2.10.1.jar             # Biblioteca Gson para JSON
├── out/                            # Archivos compilados (.class)
└── src/
    └── main/
        └── java/
            └── com/example/conversor/
                ├── Principal.java        # Clase principal con menú
                ├── ConsultaTasa.java     # Lógica de API
                ├── TasaCambio.java       # Record para JSON
                └── Moneda.java           # Enum de monedas
```

## Solución de problemas

### Error: "API key no configurada"

- Verifica que hayas reemplazado el placeholder con tu API key real en `ConsultaTasa.java`

### Error: "javac no se reconoce como comando"

- Java no está instalado o no está en el PATH del sistema
- Descarga e instala Java JDK 11 o superior desde [oracle.com](https://www.oracle.com/java/technologies/downloads/)

### Error de compilación: "package com.google.gson does not exist"

- El archivo `gson-2.10.1.jar` no está en la carpeta `lib/`
- Descárgalo manualmente o ejecuta:
  ```cmd
  Invoke-WebRequest -Uri "https://repo1.maven.org/maven2/com/google/code/gson/gson/2.10.1/gson-2.10.1.jar" -OutFile "lib\gson-2.10.1.jar"
  ```

### Error de red al consultar la API

- Verifica tu conexión a Internet
- Verifica que tu API key sea válida
- Asegúrate de no haber excedido el límite de solicitudes de tu plan

## Notas técnicas

- **API utilizada**: ExchangeRate-API v6 (`https://v6.exchangerate-api.com/v6/`)
- **Versión de Java**: Requiere Java 11 o superior (usa HttpClient nativo)
- **Biblioteca JSON**: Gson 2.10.1
- **Arquitectura**: Programación Orientada a Objetos con separación de responsabilidades
- **Manejo de errores**: Try-catch para IOException, InterruptedException y NumberFormatException
- La dependencia Gson se usa para parsear la respuesta JSON de la API
- Todas las excepciones de red e IO son manejadas apropiadamente
