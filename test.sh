#!/bin/bash

echo "🧪 بدء اختبار مشروع AdvanceTech..."

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

# تنظيف المشروع
echo "🧹 تنظيف المشروع..."
mvn clean

# تشغيل الاختبارات
echo "🧪 تشغيل الاختبارات..."
mvn test

if [ $? -eq 0 ]; then
    echo "✅ تم تشغيل جميع الاختبارات بنجاح!"
    echo "🎉 جميع الاختبارات نجحت!"
else
    echo "❌ فشل في بعض الاختبارات"
    exit 1
fi
