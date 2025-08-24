#!/bin/bash

echo "🚀 بدء تشغيل AdvanceTech في بيئة التطوير..."

# التحقق من وجود Docker
if ! command -v docker &> /dev/null; then
    echo "❌ Docker غير مثبت. يرجى تثبيت Docker أولاً."
    exit 1
fi

if ! command -v docker-compose &> /dev/null; then
    echo "❌ Docker Compose غير مثبت. يرجى تثبيت Docker Compose أولاً."
    exit 1
fi

# التحقق من وجود Maven
if ! command -v mvn &> /dev/null; then
    echo "❌ Maven غير مثبت. يرجى تثبيت Maven أولاً."
    exit 1
fi

# التحقق من وجود Java 17
if ! command -v java &> /dev/null; then
    echo "❌ Java غير مثبت. يرجى تثبيت Java 17 أولاً."
    exit 1
fi

JAVA_VERSION=$(java -version 2>&1 | head -n 1 | cut -d'"' -f2 | cut -d'.' -f1)
if [ "$JAVA_VERSION" -lt 17 ]; then
    echo "❌ Java 17 مطلوب. الإصدار الحالي: $JAVA_VERSION"
    exit 1
fi

echo "✅ جميع المتطلبات متوفرة"

# إيقاف الخدمات الموجودة
echo "🛑 إيقاف الخدمات الموجودة..."
docker-compose down

# تشغيل الخدمات الأساسية
echo "🐘 تشغيل PostgreSQL..."
echo "🔴 تشغيل Redis..."
docker-compose up -d postgres redis

# انتظار حتى تكون الخدمات جاهزة
echo "⏳ انتظار حتى تكون الخدمات جاهزة..."
sleep 10

# التحقق من حالة الخدمات
if ! docker-compose ps | grep -q "Up"; then
    echo "❌ فشل في تشغيل الخدمات الأساسية"
    docker-compose logs
    exit 1
fi

echo "✅ الخدمات الأساسية تعمل بنجاح"

# بناء المشروع
echo "🔨 بناء المشروع..."
mvn clean install -DskipTests

if [ $? -ne 0 ]; then
    echo "❌ فشل في بناء المشروع"
    exit 1
fi

echo "✅ تم بناء المشروع بنجاح"

# تشغيل التطبيق
echo "🚀 تشغيل التطبيق..."
cd advance-tech-web
mvn spring-boot:run

echo "🎉 تم تشغيل AdvanceTech بنجاح!"
echo "📱 التطبيق: http://localhost:8080"
echo "🗄️ قاعدة البيانات: http://localhost:8081"
echo "🔴 Redis: http://localhost:8082"
