@echo off
REM ======================================
REM REDEPLOY AUTOMÁTICO DE MICROSERVICIO
REM ======================================

set CONTAINER_NAME=micro-service-shopping-cart-cont
set IMAGE_NAME=micro-service-shopping-cart-img
set VOLUME_NAME=h2-data
set PORT=8080


echo Verificando el volumen Docker...
docker volume inspect %VOLUME_NAME% >nul 2>&1
IF ERRORLEVEL 1 (
    echo Creando volumen %VOLUME_NAME%...
    docker volume create %VOLUME_NAME%
)

set SKIP_MAVEN=true
if "%1"=="-cm" (
    set SKIP_MAVEN=false
)

IF "%SKIP_MAVEN%"=="false" (
    echo ...........1. Compilando el proyecto con Maven...........
    call mvn clean package -DskipTests
    IF %ERRORLEVEL% NEQ 0 (
        echo ❌ Error durante la compilación de Maven.
        pause
        exit /b 1
    )
)

echo ...........2. Generando la imagen Docker...........
docker build -t %IMAGE_NAME% .

echo ...........3. Eliminar contenedor anterior si existe...........
docker rm -f %CONTAINER_NAME% 2>nul

echo ...........4. Creando y ejecutando el nuevo contenedor...........
docker run -d ^
    -p %PORT%:%PORT% ^
    --name %CONTAINER_NAME% ^
    -v h2-data:/data ^
    %IMAGE_NAME%

echo Proceso completado.
pause