@echo off
chcp 65001 >nul
echo 🧹 بدء تنظيف مشروع AdvanceTech...

REM إيقاف الخدمات
echo 🛑 إيقاف الخدمات...
docker-compose down >nul 2>&1

REM تنظيف Maven
echo 🧹 تنظيف Maven...
mvn clean >nul 2>&1

REM حذف المجلدات المبنية
echo 🗑️ حذف المجلدات المبنية...
for /d /r . %%d in (target) do @if exist "%%d" rd /s /q "%%d" 2>nul
del /s /q *.jar 2>nul
del /s /q *.war 2>nul

REM حذف ملفات IDE
echo 🗑️ حذف ملفات IDE...
for /d /r . %%d in (.idea) do @if exist "%%d" rd /s /q "%%d" 2>nul
del /s /q *.iml 2>nul
del /s /q .project 2>nul
del /s /q .classpath 2>nul

REM حذف ملفات النظام
echo 🗑️ حذف ملفات النظام...
del /s /q .DS_Store 2>nul
del /s /q Thumbs.db 2>nul

REM حذف ملفات التحميل
echo 🗑️ حذف ملفات التحميل...
if exist uploads rd /s /q uploads 2>nul
if exist logs rd /s /q logs 2>nul

REM تنظيف Docker
echo 🐳 تنظيف Docker...
docker system prune -f >nul 2>&1
docker volume prune -f >nul 2>&1

echo ✅ تم تنظيف المشروع بنجاح!
echo 💡 يمكنك الآن إعادة بناء المشروع باستخدام: build.bat

pause
