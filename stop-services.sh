#!/bin/bash

echo "🛑 إيقاف جميع خدمات AdvanceTech..."

# إيقاف التطبيق Spring Boot
echo "🔄 البحث عن عمليات Spring Boot..."
pkill -f "spring-boot:run" 2>/dev/null
pkill -f "AdvanceTechApplication" 2>/dev/null

# إيقاف Docker Compose
echo "🐳 إيقاف Docker Compose..."
docker-compose down

# إيقاف الحاويات المتبقية
echo "🧹 تنظيف الحاويات المتبقية..."
docker stop $(docker ps -q --filter "name=advancetech_*") 2>/dev/null
docker rm $(docker ps -aq --filter "name=advancetech_*") 2>/dev/null

# إيقاف الشبكات
echo "🌐 إيقاف الشبكات..."
docker network rm advancetech_advancetech_network 2>/dev/null

echo "✅ تم إيقاف جميع الخدمات بنجاح"
echo "💡 يمكنك الآن إعادة تشغيل الخدمات باستخدام: ./run-dev.sh"
