package org.example;

/**
 * Класс, представляющий философа.
 */
public class Philosopher extends Thread {
  private final int id;
  private final DiningTable diningTable;
  private final long eatingTime; // Время, необходимое для поглощения пищи

  /**
   * Конструктор класса Philosopher.
   * @param id          уникальный идентификатор философа
   * @param diningTable ссылка на объект DiningTable, представляющий стол
   * @param eatingTime  время, необходимое для поглощения пищи
   */
  public Philosopher(int id, DiningTable diningTable, int eatingTime) {
    this.id = id;
    this.diningTable = diningTable;
    this.eatingTime = eatingTime;
  }

  /**
   * Метод, описывающий поведение философа при обеде.
   */
  @Override
  public void run() {
    try {
      while (true) {
        think();
        diningTable.takeForks(id);
        eat();
        diningTable.releaseForks(id);
      }
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }

  /**
   * Метод, описывающий размышления философа.
   * @throws InterruptedException если поток прерван
   */
  private void think() throws InterruptedException {
    System.out.println("Философ " + id + " размышляет...");
    Thread.sleep((long) (Math.random() * 1000)); // случайная задержка для размышлений
  }

  /**
   * Метод, описывающий процесс поглощения пищи философом.
   * @throws InterruptedException если поток прерван
   */
  private void eat() throws InterruptedException {
    System.out.println("Философ " + id + " ест...");
    Thread.sleep(eatingTime); // философ ест в течение eatingTime миллисекунд
  }
}
