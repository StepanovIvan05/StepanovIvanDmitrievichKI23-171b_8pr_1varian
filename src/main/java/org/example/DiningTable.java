package org.example;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Класс, представляющий стол в столовой.
 */
public class DiningTable {
  private final Fork[] forks;
  private final Lock[] forkLocks;
  private final int numOfPhilosophers;

  /**
   * Конструктор класса DiningTable.
   * @param numOfPhilosophers количество философов
   */
  public DiningTable(int numOfPhilosophers) {
    this.numOfPhilosophers = numOfPhilosophers;
    forks = new Fork[numOfPhilosophers];
    forkLocks = new Lock[numOfPhilosophers];
    for (int i = 0; i < numOfPhilosophers; i++) {
      forks[i] = new Fork(i);
      forkLocks[i] = new ReentrantLock();
    }
  }

  /**
   * Метод для взятия вилок философом.
   * @param philosopherId идентификатор философа
   * @throws InterruptedException если поток прерван
   */
  public void takeForks(int philosopherId) throws InterruptedException {
    int leftForkIndex = philosopherId;
    int rightForkIndex = (philosopherId + 1) % numOfPhilosophers;

    forkLocks[leftForkIndex].lock();
    try {
      forks[leftForkIndex].pickUp();
      System.out.println("Философ " + philosopherId + " взял левую вилку.");
      forkLocks[rightForkIndex].lock();
      try {
        forks[rightForkIndex].pickUp();
        System.out.println("Философ " + philosopherId + " взял правую вилку.");
      } finally {
        if (!forks[rightForkIndex].isInUse()) {
          forkLocks[rightForkIndex].unlock();
        }
      }
    } finally {
      if (!forks[leftForkIndex].isInUse()) {
        forkLocks[leftForkIndex].unlock();
      }
    }
  }

  /**
   * Метод для освобождения вилок философом.
   * @param philosopherId идентификатор философа
   */
  public void releaseForks(int philosopherId) {
    int leftForkIndex = philosopherId;
    int rightForkIndex = (philosopherId + 1) % numOfPhilosophers;

    forks[leftForkIndex].putDown();
    System.out.println("Философ " + philosopherId + " положил левую вилку.");
    forks[rightForkIndex].putDown();
    System.out.println("Философ " + philosopherId + " положил правую вилку.");

    forkLocks[leftForkIndex].unlock();
    forkLocks[rightForkIndex].unlock();
  }
}
