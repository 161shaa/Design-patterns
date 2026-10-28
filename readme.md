# Java Design Patterns

A practical, hands-on repository for learning and implementing **Design Patterns in Java**, with simple explanations, real-world examples, and runnable code.

## 🎯 Objective

The goal of this repository is to understand:

- Why design patterns are needed.
- Which problems each pattern solves.
- How to implement patterns using plain Java.
- When to use a pattern and when to avoid it.
- How design patterns improve code maintainability, flexibility, and reusability.

## 📚 What Are Design Patterns?

Design patterns are reusable approaches to commonly occurring software design problems. They are not ready-made code; they are proven ways to structure classes and objects.

Design patterns are commonly grouped into three categories.

### 1. Creational Patterns

These patterns deal with object creation.

| Pattern | Purpose | Status |
|---|---|---|
| Singleton | Ensure a single shared instance when appropriate | 🟡 Learning |
| Factory Method | Delegate object creation to subclasses | 🟡 Learning |
| Abstract Factory | Create families of related objects | ⬜ Planned |
| Builder | Construct complex objects step by step | 🟡 Learning |
| Prototype | Create objects by copying existing instances | ⬜ Planned |

### 2. Structural Patterns

These patterns deal with how classes and objects are composed.

| Pattern | Purpose | Status |
|---|---|---|
| Adapter | Make incompatible interfaces work together | ✅ Practiced |
| Bridge | Separate abstractions from their implementations | ⬜ Planned |
| Composite | Treat individual objects and groups uniformly | 📖 Learning |
| Decorator | Add behavior to objects dynamically | ⬜ Planned |
| Facade | Provide a simple interface to a complex subsystem | ✅ Practiced |
| Flyweight | Share common data to reduce memory usage | ⬜ Planned |
| Proxy | Control access to another object | ⬜ Planned |

### 3. Behavioral Patterns

These patterns deal with communication and responsibilities between objects.

| Pattern | Purpose | Status |
|---|---|---|
| Chain of Responsibility | Pass requests through a chain of handlers | ⬜ Planned |
| Command | Encapsulate a request as an object | ⬜ Planned |
| Iterator | Traverse a collection without exposing its internals | ⬜ Planned |
| Mediator | Centralize communication between objects | ⬜ Planned |
| Memento | Capture and restore an object's state | ⬜ Planned |
| Observer | Notify subscribers when an object's state changes | ⬜ Planned |
| State | Change behavior based on an object's state | ⬜ Planned |
| Strategy | Make algorithms interchangeable | ⬜ Planned |
| Template Method | Define an algorithm's structure in a base class | ⬜ Planned |
| Visitor | Add operations to object structures without changing their classes | ⬜ Planned |

*Status legend:* ✅ Practiced · 📖 Learning · 🟡 Introduced · ⬜ Planned

## 🛠️ Technology Stack

- **Language:** Java
- **Concepts:** OOP, interfaces, inheritance, composition, polymorphism, SOLID principles
- **Tools:** JDK, Git, GitHub
- **Frameworks:** None required — examples use plain Java.

## 📂 Repository Structure

```text
design-patterns/
├── README.md
├── creational/
│   ├── singleton/
│   ├── factory-method/
│   ├── abstract-factory/
│   ├── builder/
│   └── prototype/
├── structural/
│   ├── adapter/
│   ├── bridge/
│   ├── composite/
│   ├── decorator/
│   ├── facade/
│   ├── flyweight/
│   └── proxy/
└── behavioral/
    ├── chain-of-responsibility/
    ├── command/
    ├── iterator/
    ├── mediator/
    ├── memento/
    ├── observer/
    ├── state/
    ├── strategy/
    ├── template-method/
    └── visitor/
```

## 🧠 Learning Approach

For every design pattern, the goal is to document:

1. **Problem:** What problem are we trying to solve?
2. **Without the pattern:** What limitations exist in a straightforward implementation?
3. **Solution:** How does the pattern address the problem?
4. **Implementation:** A working Java example.
5. **Class relationships:** The role of each class and interface.
6. **Real-world use cases:** Where the pattern is useful.
7. **Trade-offs:** Limitations, complexity, and when not to use it.
8. **Key takeaways:** Interview-ready definitions and revision notes.

## 🚀 Progress

This repository is a work in progress. Patterns are added as they are studied, implemented, tested, and documented.

The focus is not just memorizing pattern definitions, but understanding the underlying design decisions and knowing when a pattern is genuinely useful.

## 📖 References

- [Refactoring.Guru — Design Patterns](https://refactoring.guru/design-patterns)
- [Head First Design Patterns](https://www.oreilly.com/library/view/head-first-design/0596007124/)

---

**Learning by building. Understanding by implementing. Improving through practice.**
