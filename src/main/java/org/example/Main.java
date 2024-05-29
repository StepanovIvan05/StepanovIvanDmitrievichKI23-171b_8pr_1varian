package org.example;

/**
 * Главный класс приложения, где создаются и запускаются потоки философов,
 * а также обрабатывается пользовательский ввод и сохраняются настройки.
 */
public class Main {
  public static void main(String[] args) {
    ConfigurationManager configManager = new ConfigurationManager();

    // Получение временного промежутка для поглощения пищи
    int eatingTime = configManager.loadSettingsFromXML();
    if(eatingTime == 0){
      eatingTime = configManager.getEatingTimeFromUser();
      // Сохранение настроек в XML-файл
      configManager.saveSettingsToXML(eatingTime);
    }
    // Создание столовой
    DiningTable diningTable = new DiningTable(5);

    // Создание и запуск потоков для каждого философа
    Philosopher[] philosophers = new Philosopher[5];
    for (int i = 0; i < philosophers.length; i++) {
      philosophers[i] = new Philosopher(i, diningTable, eatingTime);
      philosophers[i].start();
    }

    // Ожидание завершения всех потоков
    try {
      for (Philosopher philosopher : philosophers) {
        philosopher.join();
      }
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }
}
