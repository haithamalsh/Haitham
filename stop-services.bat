@echo off
chcp 65001 >nul
echo 🛑 إيقاف جميع خدمات AdvanceTech...

REM إيقاف التطبيق Spring Boot
echo 🔄 البحث عن عمليات Spring Boot...
taskkill /f /im java.exe >nul 2>&1

REM إيقاف Docker Compose
echo 🐳 إيقاف Docker Compose...
docker-compose down

REM إيقاف الحاويات المتبقية
echo 🧹 تنظيف الحاويات المتبقية...
for /f "tokens=*" %%i in ('docker ps -q --filter "name=advancetech_*" 2^>nul') do docker stop %%i >nul 2>&1
for /f "tokens=*" %%i in ('docker ps -aq --filter "name=advancetech_*" 2^>nul') do docker rm %%i >nul 2>&1

REM إيقاف الشبكات
echo 🌐 إيقاف الشبكات...
docker network rm advancetech_advancetech_network >nul 2>&1

echo ✅ تم إيقاف جميع الخدمات بنجاح
echo 💡 يمكنك الآن إعادة تشغيل الخدمات باستخدام: run-dev.bat

pause
