@echo off
chcp 65001 >nul
echo 🚀 بدء تشغيل AdvanceTech في بيئة التطوير...

REM التحقق من وجود Docker
docker --version >nul 2>&1
if %errorlevel% neq 0 (
    echo ❌ Docker غير مثبت. يرجى تثبيت Docker أولاً.
    pause
    exit /b 1
)

REM التحقق من وجود Docker Compose
docker-compose --version >nul 2>&1
if %errorlevel% neq 0 (
    echo ❌ Docker Compose غير مثبت. يرجى تثبيت Docker Compose أولاً.
    pause
    exit /b 1
)

REM التحقق من وجود Maven
mvn --version >nul 2>&1
if %errorlevel% neq 0 (
    echo ❌ Maven غير مثبت. يرجى تثبيت Maven أولاً.
    pause
    exit /b 1
)

REM التحقق من وجود Java 17
java -version >nul 2>&1
if %errorlevel% neq 0 (
    echo ❌ Java غير مثبت. يرجى تثبيت Java 17 أولاً.
    pause
    exit /b 1
)

echo ✅ جميع المتطلبات متوفرة

REM إيقاف الخدمات الموجودة
echo 🛑 إيقاف الخدمات الموجودة...
docker-compose down

REM تشغيل الخدمات الأساسية
echo 🐘 تشغيل PostgreSQL...
echo 🔴 تشغيل Redis...
docker-compose up -d postgres redis

REM انتظار حتى تكون الخدمات جاهزة
echo ⏳ انتظار حتى تكون الخدمات جاهزة...
timeout /t 10 /nobreak >nul

REM التحقق من حالة الخدمات
docker-compose ps | findstr "Up" >nul
if %errorlevel% neq 0 (
    echo ❌ فشل في تشغيل الخدمات الأساسية
    docker-compose logs
    pause
    exit /b 1
)

echo ✅ الخدمات الأساسية تعمل بنجاح

REM بناء المشروع
echo 🔨 بناء المشروع...
mvn clean install -DskipTests

if %errorlevel% neq 0 (
    echo ❌ فشل في بناء المشروع
    pause
    exit /b 1
)

echo ✅ تم بناء المشروع بنجاح

REM تشغيل التطبيق
echo 🚀 تشغيل التطبيق...
cd advance-tech-web
mvn spring-boot:run

echo 🎉 تم تشغيل AdvanceTech بنجاح!
echo 📱 التطبيق: http://localhost:8080
echo 🗄️ قاعدة البيانات: http://localhost:8081
echo 🔴 Redis: http://localhost:8082

pause
