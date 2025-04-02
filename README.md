# SkillCinema

## ✨ **Описание проекта**
SkillCinema — это мобильное приложение, разработанное на Kotlin с использованием Jetpack Compose. Оно предоставляет удобный интерфейс для взаимодействия с киноиндустрией (например, каталог фильмов, аналитика или рекомендации). Проект ориентирован на современные подходы разработки Android-приложений.

---

## 🔧 **Технологии и архитектура**

### **Основные технологии:**
- **Язык программирования:** Kotlin
- **UI Framework:** Jetpack Compose — декларативный подход к созданию пользовательского интерфейса
- **Jetpack Components:** ViewModel, Navigation, Room/DataStore
- **Асинхронная обработка:** Coroutines, Flow
- **Сетевые запросы:** Retrofit или Ktor
- **Внедрение зависимостей (DI):** Hilt или Koin

### **Архитектурный подход:**
- **MVVM (Model-View-ViewModel):**
  - **Model:** управление данными (например, данные о фильмах из API или локальной базы данных).
  - **View:** интерфейс, разработанный с помощью Jetpack Compose.
  - **ViewModel:** управление состоянием интерфейса и бизнес-логикой.

---

## 🎨 **Основные особенности проекта**
1. **Современный UI:**
   - Реализован с использованием Jetpack Compose.
   - Управление состоянием через State и StateFlow.

2. **Асинхронная обработка данных:**
   - Корутины и Flow для обработки сетевых запросов и работы с базой данных.

3. **Модульная структура:**
   - Чёткое разделение слоёв приложения (Model, ViewModel, View).
   - Использование Dependency Injection для удобного управления зависимостями.

4. **Навигация:**
   - Используется Jetpack Navigation или Compose Navigation для работы с многокомпонентным интерфейсом.

5. **Тестирование:**
   - Покрытие Unit-тестами ViewModel и бизнес-логики.
   - UI-тесты с помощью Compose Test или Espresso.

---

## 🔎 **Как запустить проект?**

### 1. Склонируйте репозиторий:
```bash
git clone https://github.com/D-Arshad-Dazai-Dimash/SkillCinema.git
```

### 2. Откройте проект в Android Studio:
- Убедитесь, что у вас установлена последняя версия Android Studio.
- Импортируйте проект.

### 3. Запустите приложение:
- Выберите конфигурацию запуска (эмулятор или подключённое устройство).
- Нажмите **Run**.

---

## 📊 **Автор и вклад**
- Основной разработчик: Dimash Yeskendir, Adilkhan Yerbolat, Nurbakyt Anuarov, Bakyzhan Saukhanbek
---
