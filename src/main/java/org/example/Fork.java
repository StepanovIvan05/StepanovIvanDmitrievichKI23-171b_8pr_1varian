package org.example;

/** Класс, представляющий вилку. */
public class Fork {
  private final int id;
  private boolean inUse;

  /**
   * Конструктор класса Fork.
   *
   * @param id уникальный идентификатор вилки
   */
  public Fork(int id) {
    this.id = id;
    this.inUse = false;
  }

  /** Метод для захвата вилки. */
  public synchronized void pickUp() {
    while (inUse) {
      try {
        wait();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }
    inUse = true;
  }

  /** Метод для освобождения вилки. */
  public synchronized void putDown() {
    inUse = false;
    notify();
  }

  /**
   * Метод для проверки, используется ли вилка.
   *
   * @return true, если вилка используется, иначе false
   */
  public synchronized boolean isInUse() {
    return inUse;
  }
}
