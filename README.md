# 🛍️ Shopping Cart Promotions Engine

This Java project implements a flexible and extensible **Shopping Cart System** that supports **multiple promotions**, **custom product sorting strategies**, and follows key **Object-Oriented Programming (OOP)** principles including **SOLID design**, **Command Pattern**, and **Strategy Pattern**.

## 📌 Features

- 🛒 **Shopping Cart** with support for multiple products
- 🎯 **Promotion Engine** with various promotional strategies:
  - Percentage discount above a threshold
  - Free product giveaways
  - Buy-2-get-1-free logic
  - Single product discount coupon
- 🧮 **Promotion Optimizer** that evaluates all permutations of promotions to apply the best combination
- ✅ **Test Suite** written using JUnit (AAA pattern)
- 🔀 **Sorting Strategies**:
  - Price descending
  - Name alphabetical
  - Combined (price + name)

---

## 📂 Project Structure

```
src/
├── cart/
│   ├── ShoppingCart.java
│   ├── Promotion.java (interface)
│   └── promotion/
│       ├── PercentageDiscountPromotion.java
│       ├── FreeMugPromotion.java
│       ├── BuyTwoGetOneFreePromotion.java
│       ├── SingleProductCouponPromotion.java
│       └── PromotionOptimizer.java
├── command/
│   ├── Command.java (interface)
│   ├── PromotionCommand.java
│   └── PromotionInvoker.java
├── model/
│   └── Product.java
├── sorting/
│   ├── ProductSorter.java
│   └── strategies/
│       ├── PriceDescendingSorter.java
│       ├── NameAlphabeticalSorter.java
│       └── CombinedSorter.java
├── util/
│   └── ProductUtils.java
└── Main.java
|
├── src
│   └── test
│       └── java
│           └── org.example
│               ├── ProductUtilsTest.java
│               ├── PromotionTest.java
|               ├── SorterTest.java
│               ├── ShoppingCartTest.java
```

---

## 🚀 How It Works

1. **Products** are added to the `ShoppingCart`.
2. The cart contents are **sorted** using a chosen strategy (e.g., price descending).
3. A set of **Promotions** is defined.
4. The `PromotionOptimizer` evaluates all permutations of these promotions to find the **optimal final price**.
5. The best combination of promotions is applied, and the final total is displayed.

---

## ✅ Example Output

```
Najlepsza kombinacja promocji:
Laptop - 1710.0zł
Mouse - 50.0zł
Keyboard - 70.0zł
USB Hub - 0.0zł
JavaMarkt Mug - 0.0zł
Finalna cena: 1830.00 zł
```

---

## 🧪 Testing

The project includes a full suite of **unit tests** in:

- `PromotionTest.java`
- `ShoppingCartTest.java`

Tests follow the **AAA (Arrange–Act–Assert)** structure.

To run tests using Maven:

```bash
mvn test
```

(or use your IDE’s built-in test runner)

---

## ⚒️ Technologies

- Java 23
- JUnit 5
- Maven

---

## 🧠 Design Patterns & SOLID Principles

### ✅ Design Patterns

- **Strategy Pattern**
  - Used for implementing **flexible product sorting strategies**.
  - Enables dynamic selection of sorting logic at runtime without altering the core code.

- **Command Pattern**
  - Applied to **encapsulate promotions** as command objects.
  - Decouples promotion logic from the shopping cart, making it easy to add, remove, or queue promotions.

---

### 🧱 SOLID Principles

- **Single Responsibility Principle (SRP)**
  - Each class or component has **one specific responsibility**.
  - Example: `ShoppingCart`, `PromotionOptimizer`, and each concrete `Promotion` class focus on distinct tasks.

- **Open/Closed Principle (OCP)**
  - The system is **open for extension but closed for modification**.
  - Easily extendable with new promotions or sorting strategies **without changing existing code**.

- **Liskov Substitution Principle (LSP)**
  - All classes implementing the `Promotion` interface can be **used interchangeably** without altering system behavior.
  - Promotes seamless substitution of different promotion strategies.

- **Interface Segregation Principle (ISP)**
  - Interfaces like `Promotion` and `Command` are **small and focused**, avoiding bloated interfaces.
  - Components only depend on **what they actually use**.

- **Dependency Inversion Principle (DIP)**
  - High-level modules depend on **abstractions**, not concrete implementations.
  - Promotes **flexibility**, **testability**, and **maintainability** of the codebase.

---

> 💡 This architecture ensures clean, scalable, and maintainable code—fully aligned with modern software engineering practices.

---

## ✍ Author

Developed by [@qlpak](https://github.com/qlpak)

