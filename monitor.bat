@echo off
chcp 65001 >nul
echo 📊 مراقبة حالة خدمات AdvanceTech...
echo ==================================

REM مراقبة Docker Compose
echo 🐳 حالة Docker Compose:
docker-compose ps | findstr "Up" >nul
if %errorlevel% equ 0 (
    echo ✅ الخدمات تعمل
    docker-compose ps --format "table {{.Name}}\t{{.Status}}\t{{.Ports}}"
) else (
    echo ❌ الخدمات متوقفة
)

echo.

REM مراقبة قاعدة البيانات
echo 🐘 حالة PostgreSQL:
docker ps | findstr "advancetech_postgres" >nul
if %errorlevel% equ 0 (
    echo ✅ PostgreSQL يعمل
    docker exec advancetech_postgres pg_isready -U postgres >nul 2>&1
    if %errorlevel% equ 0 (
        echo ✅ الاتصال بقاعدة البيانات ناجح
    ) else (
        echo ⚠️ قاعدة البيانات تعمل لكن الاتصال فشل
    )
) else (
    echo ❌ PostgreSQL متوقف
)

echo.

REM مراقبة Redis
echo 🔴 حالة Redis:
docker ps | findstr "advancetech_redis" >nul
if %errorlevel% equ 0 (
    echo ✅ Redis يعمل
    docker exec advancetech_redis redis-cli ping >nul 2>&1
    if %errorlevel% equ 0 (
        echo ✅ الاتصال بـ Redis ناجح
    ) else (
        echo ⚠️ Redis يعمل لكن الاتصال فشل
    )
) else (
    echo ❌ Redis متوقف
)

echo.

REM مراقبة التطبيق Spring Boot
echo 🚀 حالة التطبيق Spring Boot:
tasklist | findstr "java.exe" >nul
if %errorlevel% equ 0 (
    echo ✅ التطبيق يعمل
    tasklist | findstr "java.exe"
) else (
    echo ❌ التطبيق متوقف
)

echo.

REM مراقبة الموارد
echo 💻 استخدام الموارد:
wmic cpu get loadpercentage /value | findstr "LoadPercentage"
wmic OS get TotalVisibleMemorySize,FreePhysicalMemory /value | findstr "Memory"

echo.

REM مراقبة الشبكة
echo 🌐 حالة الشبكة:
netstat -an | findstr ":8080" | find /c "LISTENING"
netstat -an | findstr ":5432" | find /c "LISTENING"
netstat -an | findstr ":6379" | find /c "LISTENING"

echo.
echo 💡 أوامر مفيدة:
echo    - تشغيل الخدمات: run-dev.bat
echo    - إيقاف الخدمات: stop-services.bat
echo    - إعادة تشغيل: docker-compose restart
echo    - عرض السجلات: docker-compose logs -f

pause
