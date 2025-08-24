# AdvanceTech - منصة التكنولوجيا المتقدمة

## نظرة عامة

AdvanceTech هي منصة متكاملة لتقديم الخدمات التقنية، مبينة باستخدام أحدث التقنيات والمعايير في هندسة البرمجيات.

## التقنيات المستخدمة

### Backend
- Java 17
- Spring Boot 3.2
- Spring Security 6
- Spring Data JPA
- PostgreSQL
- Redis
- JWT

### Frontend (قيد التطوير)
- Qwik
- TypeScript
- Tailwind CSS
- Vite

## هيكل المشروع

```
AdvanceTech/
├── advance-tech-parent (POM Parent)
│   ├── advance-tech-core (المكتبات الأساسية)
│   ├── advance-tech-domain (النماذج والكيانات)
│   ├── advance-tech-persistence (JPA Repositories)
│   ├── advance-tech-service (Business Logic)
│   ├── advance-tech-web (REST Controllers)
│   └── advance-tech-api (DTOs + API docs)
```

## التثبيت والتشغيل

### 1. تشغيل الخدمات الأساسية
```bash
docker-compose up -d postgres redis
```

### 2. بناء المشروع
```bash
mvn clean install
```

### 3. تشغيل التطبيق
```bash
cd advance-tech-web
mvn spring-boot:run
```

### 4. الوصول للتطبيق
- التطبيق: http://localhost:8080
- قاعدة البيانات: http://localhost:8081
- Redis: http://localhost:8082

## الميزات

- نظام مستخدمين متكامل
- إدارة الخدمات والتصنيفات
- طلبات عروض الأسعار
- المواعيد والمشاريع
- نظام أمان متقدم
- دعم كامل للغة العربية

## المساهمة

يرجى اتباع معايير التطوير وكتابة اختبارات شاملة.

## الترخيص

MIT License
