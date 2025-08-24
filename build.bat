@echo off
chcp 65001 >nul
echo 🔨 بدء بناء مشروع AdvanceTech...

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
echo 📋 معلومات البناء:

REM تنظيف المشروع
echo 🧹 تنظيف المشروع...
mvn clean

REM تحميل التبعيات
echo 📦 تحميل التبعيات...
mvn dependency:resolve

REM بناء المشروع
echo 🔨 بناء المشروع...
mvn install -DskipTests

if %errorlevel% equ 0 (
    echo ✅ تم بناء المشروع بنجاح!
    echo 📁 الملفات المبنية:
    dir /s /b *.jar | findstr "target"
    
    echo.
    echo 🚀 يمكنك الآن تشغيل التطبيق باستخدام:
    echo    cd advance-tech-web
    echo    mvn spring-boot:run
) else (
    echo ❌ فشل في بناء المشروع
    pause
    exit /b 1
)

pause
