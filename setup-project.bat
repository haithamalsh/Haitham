@echo off
chcp 65001 >nul
echo 🚀 إعداد مشروع AdvanceTech من البداية...

REM التحقق من المتطلبات الأساسية
echo 🔍 التحقق من المتطلبات الأساسية...

REM التحقق من وجود Docker
docker --version >nul 2>&1
if %errorlevel% neq 0 (
    echo ❌ Docker غير مثبت. يرجى تثبيت Docker أولاً.
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

REM إنشاء مجلدات المشروع
echo 📁 إنشاء مجلدات المشروع...
if not exist uploads mkdir uploads
if not exist logs mkdir logs
if not exist init-scripts mkdir init-scripts

REM تهيئة قاعدة البيانات
echo 🗄️ تهيئة قاعدة البيانات...
call init-db.bat

REM بناء المشروع
echo 🔨 بناء المشروع...
call build.bat

REM تشغيل الخدمات
echo 🚀 تشغيل الخدمات...
call run-dev.bat

echo 🎉 تم إعداد مشروع AdvanceTech بنجاح!
pause
