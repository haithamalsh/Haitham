@echo off
chcp 65001 >nul
echo 🗄️ بدء تهيئة قاعدة بيانات AdvanceTech...

REM إنشاء مجلد init-scripts إذا لم يكن موجوداً
if not exist init-scripts mkdir init-scripts

REM إنشاء ملف SQL لتهيئة قاعدة البيانات
echo 📝 إنشاء ملف تهيئة قاعدة البيانات...

echo -- تهيئة قاعدة بيانات AdvanceTech > init-scripts\01-init.sql
echo -- إنشاء المستخدم الإداري الافتراضي >> init-scripts\01-init.sql
echo. >> init-scripts\01-init.sql
echo CREATE TABLE IF NOT EXISTS users ( >> init-scripts\01-init.sql
echo     id BIGSERIAL PRIMARY KEY, >> init-scripts\01-init.sql
echo     username VARCHAR(50) UNIQUE NOT NULL, >> init-scripts\01-init.sql
echo     email VARCHAR(100) UNIQUE NOT NULL, >> init-scripts\01-init.sql
echo     password VARCHAR(255) NOT NULL, >> init-scripts\01-init.sql
echo     enabled BOOLEAN DEFAULT true >> init-scripts\01-init.sql
echo ); >> init-scripts\01-init.sql

echo ✅ تم إنشاء ملف تهيئة قاعدة البيانات
echo 🔑 بيانات الدخول الافتراضية:
echo    - اسم المستخدم: admin
echo    - كلمة المرور: admin123
echo.
echo 🚀 يمكنك الآن تشغيل الخدمات باستخدام: run-dev.bat

pause
