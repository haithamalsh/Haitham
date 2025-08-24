#!/bin/bash

echo "🚀 إعداد مشروع AdvanceTech من البداية..."

# التحقق من المتطلبات الأساسية
echo "🔍 التحقق من المتطلبات الأساسية..."

# التحقق من وجود Docker
if ! command -v docker &> /dev/null; then
    echo "❌ Docker غير مثبت. يرجى تثبيت Docker أولاً."
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

echo "✅ جميع المتطلبات متوفرة"

# إنشاء مجلدات المشروع
echo "📁 إنشاء مجلدات المشروع..."
mkdir -p uploads logs init-scripts

# تهيئة قاعدة البيانات
echo "🗄️ تهيئة قاعدة البيانات..."
./init-db.sh

# بناء المشروع
echo "🔨 بناء المشروع..."
./build.sh

# تشغيل الخدمات
echo "🚀 تشغيل الخدمات..."
./run-dev.sh

echo "🎉 تم إعداد مشروع AdvanceTech بنجاح!"
