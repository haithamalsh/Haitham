#!/bin/bash

echo "🔨 بدء بناء مشروع AdvanceTech..."

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
echo "📋 معلومات البناء:"
echo "   - Java Version: $(java -version 2>&1 | head -n 1)"
echo "   - Maven Version: $(mvn --version | head -n 1)"
echo "   - Working Directory: $(pwd)"

# تنظيف المشروع
echo "🧹 تنظيف المشروع..."
mvn clean

# تحميل التبعيات
echo "📦 تحميل التبعيات..."
mvn dependency:resolve

# بناء المشروع
echo "🔨 بناء المشروع..."
mvn install -DskipTests

if [ $? -eq 0 ]; then
    echo "✅ تم بناء المشروع بنجاح!"
    echo "📁 الملفات المبنية:"
    find . -name "*.jar" -type f | head -10
    
    echo ""
    echo "🚀 يمكنك الآن تشغيل التطبيق باستخدام:"
    echo "   cd advance-tech-web"
    echo "   mvn spring-boot:run"
else
    echo "❌ فشل في بناء المشروع"
    exit 1
fi
