#!/bin/bash

echo "📊 مراقبة حالة خدمات AdvanceTech..."
echo "=================================="

# مراقبة Docker Compose
echo "🐳 حالة Docker Compose:"
if docker-compose ps | grep -q "Up"; then
    echo "✅ الخدمات تعمل"
    docker-compose ps --format "table {{.Name}}\t{{.Status}}\t{{.Ports}}"
else
    echo "❌ الخدمات متوقفة"
fi

echo ""

# مراقبة قاعدة البيانات
echo "🐘 حالة PostgreSQL:"
if docker ps | grep -q "advancetech_postgres"; then
    echo "✅ PostgreSQL يعمل"
    # محاولة الاتصال بقاعدة البيانات
    if docker exec advancetech_postgres pg_isready -U postgres >/dev/null 2>&1; then
        echo "✅ الاتصال بقاعدة البيانات ناجح"
    else
        echo "⚠️ قاعدة البيانات تعمل لكن الاتصال فشل"
    fi
else
    echo "❌ PostgreSQL متوقف"
fi

echo ""

# مراقبة Redis
echo "🔴 حالة Redis:"
if docker ps | grep -q "advancetech_redis"; then
    echo "✅ Redis يعمل"
    # محاولة الاتصال بـ Redis
    if docker exec advancetech_redis redis-cli ping >/dev/null 2>&1; then
        echo "✅ الاتصال بـ Redis ناجح"
    else
        echo "⚠️ Redis يعمل لكن الاتصال فشل"
    fi
else
    echo "❌ Redis متوقف"
fi

echo ""

# مراقبة التطبيق Spring Boot
echo "🚀 حالة التطبيق Spring Boot:"
if pgrep -f "spring-boot:run\|AdvanceTechApplication" >/dev/null; then
    echo "✅ التطبيق يعمل"
    # عرض معلومات العملية
    ps aux | grep -E "(spring-boot:run|AdvanceTechApplication)" | grep -v grep
else
    echo "❌ التطبيق متوقف"
fi

echo ""

# مراقبة الموارد
echo "💻 استخدام الموارد:"
echo "   - CPU: $(top -bn1 | grep "Cpu(s)" | awk '{print $2}' | cut -d'%' -f1)%"
echo "   - Memory: $(free -m | awk 'NR==2{printf "%.1f%%", $3*100/$2}')"
echo "   - Disk: $(df -h / | awk 'NR==2{print $5}')"

echo ""

# مراقبة الشبكة
echo "🌐 حالة الشبكة:"
echo "   - Port 8080 (App): $(netstat -tlnp 2>/dev/null | grep :8080 | wc -l) connections"
echo "   - Port 5432 (DB): $(netstat -tlnp 2>/dev/null | grep :5432 | wc -l) connections"
echo "   - Port 6379 (Redis): $(netstat -tlnp 2>/dev/null | grep :6379 | wc -l) connections"

echo ""
echo "💡 أوامر مفيدة:"
echo "   - تشغيل الخدمات: ./run-dev.sh"
echo "   - إيقاف الخدمات: ./stop-services.sh"
echo "   - إعادة تشغيل: docker-compose restart"
echo "   - عرض السجلات: docker-compose logs -f"
