@echo off
chcp 65001 >nul
echo 🧪 بدء اختبار مشروع AdvanceTech...

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

REM تنظيف المشروع
echo 🧹 تنظيف المشروع...
mvn clean

REM تشغيل الاختبارات
echo 🧪 تشغيل الاختبارات...
mvn test

if %errorlevel% equ 0 (
    echo ✅ تم تشغيل جميع الاختبارات بنجاح!
    echo 🎉 جميع الاختبارات نجحت!
) else (
    echo ❌ فشل في بعض الاختبارات
    pause
    exit /b 1
)

pause
