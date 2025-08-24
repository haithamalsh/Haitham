#!/bin/bash

echo "🗄️ بدء تهيئة قاعدة بيانات AdvanceTech..."

# إنشاء مجلد init-scripts إذا لم يكن موجوداً
mkdir -p init-scripts

# إنشاء ملف SQL لتهيئة قاعدة البيانات
echo "📝 إنشاء ملف تهيئة قاعدة البيانات..."
cat > init-scripts/01-init.sql << 'EOF'
-- تهيئة قاعدة بيانات AdvanceTech
-- إنشاء المستخدم الإداري الافتراضي

-- إنشاء جدول المستخدمين إذا لم يكن موجوداً
CREATE TABLE IF NOT EXISTS users (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    phone VARCHAR(20),
    avatar_url VARCHAR(500),
    verification_status VARCHAR(20) DEFAULT 'PENDING',
    enabled BOOLEAN DEFAULT true,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    version BIGINT DEFAULT 0
);

-- إنشاء جدول أدوار المستخدمين
CREATE TABLE IF NOT EXISTS user_roles (
    user_id BIGINT NOT NULL,
    role VARCHAR(20) NOT NULL,
    PRIMARY KEY (user_id, role),
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- إنشاء المستخدم الإداري الافتراضي
INSERT INTO users (username, email, password, verification_status, enabled) 
VALUES ('admin', 'admin@advancetech.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDi', 'VERIFIED', true)
ON CONFLICT (username) DO NOTHING;

-- إضافة دور ADMIN للمستخدم الإداري
INSERT INTO user_roles (user_id, role)
SELECT id, 'ADMIN' FROM users WHERE username = 'admin'
ON CONFLICT DO NOTHING;

-- إنشاء فئات الخدمات الافتراضية
INSERT INTO service_categories (name_en, name_ar, description_en, description_ar, display_order, active, featured) VALUES
('Web Development', 'تطوير الويب', 'Professional web development services', 'خدمات تطوير الويب الاحترافية', 1, true, true),
('Mobile Development', 'تطوير التطبيقات', 'Mobile app development for iOS and Android', 'تطوير تطبيقات الهاتف المحمول', 2, true, true)
ON CONFLICT DO NOTHING;

echo "✅ تم إنشاء قاعدة البيانات بنجاح!"
echo "🔑 بيانات الدخول الافتراضية:"
echo "   - اسم المستخدم: admin"
echo "   - كلمة المرور: admin123"
EOF

echo "✅ تم إنشاء ملف تهيئة قاعدة البيانات"
echo "🚀 يمكنك الآن تشغيل الخدمات باستخدام: ./run-dev.sh"
