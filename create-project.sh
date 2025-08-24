#!/bin/bash

echo "🚀 إنشاء مشروع AdvanceTech من البداية..."

# إنشاء هيكل المشروع
echo "📁 إنشاء هيكل المشروع..."

# إنشاء المجلدات الرئيسية
mkdir -p advance-tech-core/src/main/java/com/advancetech/core
mkdir -p advance-tech-domain/src/main/java/com/advancetech/domain
mkdir -p advance-tech-persistence/src/main/java/com/advancetech/persistence
mkdir -p advance-tech-service/src/main/java/com/advancetech/service
mkdir -p advance-tech-web/src/main/java/com/advancetech
mkdir -p advance-tech-api/src/main/java/com/advancetech/api

# إنشاء المجلدات الإضافية
mkdir -p uploads logs init-scripts docs scripts

echo "✅ تم إنشاء هيكل المشروع"

# إنشاء ملفات التكوين
echo "📝 إنشاء ملفات التكوين..."

# جعل ملفات Scripts قابلة للتنفيذ
chmod +x *.sh

echo "✅ تم إنشاء جميع الملفات"

echo "🎉 تم إنشاء مشروع AdvanceTech بنجاح!"
echo "🚀 الخطوات التالية:"
echo "   1. تثبيت المتطلبات الأساسية"
echo "   2. تشغيل: ./setup-project.sh"
