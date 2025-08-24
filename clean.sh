#!/bin/bash

echo "🧹 بدء تنظيف مشروع AdvanceTech..."

# إيقاف الخدمات
echo "🛑 إيقاف الخدمات..."
docker-compose down 2>/dev/null

# تنظيف Maven
echo "🧹 تنظيف Maven..."
mvn clean 2>/dev/null

# حذف المجلدات المبنية
echo "🗑️ حذف المجلدات المبنية..."
find . -name "target" -type d -exec rm -rf {} + 2>/dev/null
find . -name "*.jar" -type f -delete 2>/dev/null
find . -name "*.war" -type f -delete 2>/dev/null

# حذف ملفات IDE
echo "🗑️ حذف ملفات IDE..."
find . -name ".idea" -type d -exec rm -rf {} + 2>/dev/null
find . -name "*.iml" -type f -delete 2>/dev/null
find . -name ".project" -type f -delete 2>/dev/null
find . -name ".classpath" -type f -delete 2>/dev/null

# حذف ملفات النظام
echo "🗑️ حذف ملفات النظام..."
find . -name ".DS_Store" -type f -delete 2>/dev/null
find . -name "Thumbs.db" -type f -delete 2>/dev/null

# حذف ملفات التحميل
echo "🗑️ حذف ملفات التحميل..."
rm -rf uploads/ 2>/dev/null
rm -rf logs/ 2>/dev/null

# تنظيف Docker
echo "🐳 تنظيف Docker..."
docker system prune -f 2>/dev/null
docker volume prune -f 2>/dev/null

echo "✅ تم تنظيف المشروع بنجاح!"
echo "💡 يمكنك الآن إعادة بناء المشروع باستخدام: ./build.sh"
