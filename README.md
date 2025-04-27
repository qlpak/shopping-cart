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
│               ├── PerformanceTest.java
│               ├── PromotionTest.java
|               ├── SorterTest.java
│               └── ShoppingCartTest.java
```

---

## 🚀 How It Works

1. **Products** are added to the `ShoppingCart`.
2. The cart contents are **sorted** using a chosen strategy (e.g., price descending).
3. A set of **Promotions** is defined.
4. The `PromotionOptimizer` evaluates all permutations of these promotions to find the **optimal final price**.
5. The best combination of promotions is applied, and the final total is displayed.

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

## 🧠 Design Patterns & SOLID Principles & Algorithmic Optimization

### ✅ Design Patterns

#### Strategy Pattern
- Used to implement **flexible product sorting strategies**.
- Allows dynamic selection of sorting logic at runtime without altering core business logic.

#### Command Pattern
- Applied to **encapsulate promotions as command objects**.
- Decouples promotion logic from the shopping cart, enabling dynamic queuing, applying, or removal of promotions.

#### (Inspired) Event Sourcing Pattern
- Promotions are recorded as a sequence of **command events**, allowing deferred execution and reconstruction of the cart state.
- Although simplified, this approach decouples events from the final state computation.

---

### ⚡ Algorithmic Optimization

#### Depth-First Search (DFS) with Branch Pruning
- Originally, the optimizer generated all permutations of promotions, which resulted in performance issues (e.g., `OutOfMemoryError` for 100 promotions).
- After optimization, the project uses a **Depth-First Search (DFS)** approach combined with **branch pruning** (Branch & Bound technique).
- This drastically reduces the search space by cutting off non-promising paths early, achieving **optimal promotion application** in a **fraction of the time**.
- Thanks to pruning, 100 promotions can now be processed efficiently without causing memory overflow or significant delays.

---

### 🧱 SOLID Principles

#### Single Responsibility Principle (SRP)
- Each class has **one clear responsibility** (e.g., `ShoppingCart`, `PromotionOptimizer`, `Promotion` implementations).

#### Open/Closed Principle (OCP)
- The system is **open for extension** (new promotions, new sorters) **without modifying existing code**.

#### Liskov Substitution Principle (LSP)
- All classes implementing the `Promotion` interface can be **substituted** without altering system correctness.

#### Interface Segregation Principle (ISP)
- Interfaces like `Promotion` and `Command` are **small, focused, and precise**.

#### Dependency Inversion Principle (DIP)
- High-level modules depend on **abstractions, not implementations**.
- Promotes **clean architecture and unit testing**.

---

> 💡 Thanks to algorithmic improvements and a solid architecture, this project achieves **both optimal performance and clean design**, fully aligned with modern software engineering practices.

---

## ✍ Author

Developed by [@qlpak](https://github.com/qlpak)

