@echo off
chcp 65001 >nul
echo 🚀 إنشاء مشروع AdvanceTech من البداية...

REM إنشاء هيكل المشروع
echo 📁 إنشاء هيكل المشروع...

REM إنشاء المجلدات الرئيسية
if not exist advance-tech-core\src\main\java\com\advancetech\core mkdir advance-tech-core\src\main\java\com\advancetech\core
if not exist advance-tech-domain\src\main\java\com\advancetech\domain mkdir advance-tech-domain\src\main\java\com\advancetech\domain
if not exist advance-tech-persistence\src\main\java\com\advancetech\persistence mkdir advance-tech-persistence\src\main\java\com\advancetech\persistence
if not exist advance-tech-service\src\main\java\com\advancetech\service mkdir advance-tech-service\src\main\java\com\advancetech\service
if not exist advance-tech-web\src\main\java\com\advancetech mkdir advance-tech-web\src\main\java\com\advancetech
if not exist advance-tech-api\src\main\java\com\advancetech\api mkdir advance-tech-api\src\main\java\com\advancetech\api

REM إنشاء المجلدات الإضافية
if not exist uploads mkdir uploads
if not exist logs mkdir logs
if not exist init-scripts mkdir init-scripts
if not exist docs mkdir docs
if not exist scripts mkdir scripts

echo ✅ تم إنشاء هيكل المشروع

echo 🎉 تم إنشاء مشروع AdvanceTech بنجاح!
echo 🚀 الخطوات التالية:
echo    1. تثبيت المتطلبات الأساسية
echo    2. تشغيل: setup-project.bat

pause
