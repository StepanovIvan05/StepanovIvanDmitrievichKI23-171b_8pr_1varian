package org.example;

import java.io.InputStream;
import java.util.Objects;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/** Класс для управления настройками приложения и взаимодействия с пользователем через консоль. */
public class ConfigurationManager {
  private static final String FILE_NAME = "settings.xml";
  private final Scanner scanner;

  public ConfigurationManager() {
    scanner = new Scanner(System.in);
  }

  /**
   * Метод для получения временного промежутка для поглощения пищи от пользователя.
   *
   * @return временной промежуток для поглощения пищи
   */
  public int getEatingTimeFromUser() {
    System.out.print(
        "Введите временной промежуток для поглощения пищи (в миллисекундах: 1 - 1000000000)\n"
            + " или нажмите Enter для использования значения(10000) по умолчанию: ");
    while (true) {
      try {
        String str = scanner.nextLine();
        if (Objects.equals(str, "")) {
          return 10000;
        }
        int eatingTime = Integer.parseInt(str);
        if (eatingTime >= 1 && eatingTime <= 1e9) {
          return eatingTime;
        } else {
          throw new NumberFormatException();
        }
      } catch (NumberFormatException e) {
        System.out.println(
            "Ошибка: введите корректное число\n"
                + " или нажмите Enter для использования значения(10000) по умолчанию:");
      }
    }
  }

  /**
   * Метод для сохранения настроек в XML-файл.
   *
   * @param eatingTime временной промежуток для поглощения пищи
   */
  public void saveSettingsToXML(int eatingTime) {
    try {
      DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

      Document document = documentBuilder.newDocument();
      Element root = document.createElement("settings");
      document.appendChild(root);

      Element eatingTimeElement = document.createElement("eatingTime");
      eatingTimeElement.appendChild(document.createTextNode(String.valueOf(eatingTime)));
      root.appendChild(eatingTimeElement);

      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      Transformer transformer = transformerFactory.newTransformer();
      DOMSource domSource = new DOMSource(document);
      StreamResult streamResult = new StreamResult(FILE_NAME);

      transformer.transform(domSource, streamResult);

      System.out.println("Настройки сохранены в XML файл успешно.");
    } catch (ParserConfigurationException | TransformerException e) {
      System.out.println("Настройки не сохранены в XML файл.");
    }
  }

  /**
   * Метод для загрузки настроек из XML-файла.
   *
   * @return временной промежуток для поглощения пищи
   */
  public int loadSettingsFromXML() {
    int eatingTime = 0; // временное значение для возвращения
    try {
      ClassLoader classLoader = getClass().getClassLoader();
      InputStream inputStream = classLoader.getResourceAsStream(FILE_NAME);

      if (inputStream == null) {
        System.out.println("Файл настроек не существует.");
        return eatingTime;
      }

      DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

      Document document = documentBuilder.parse(inputStream);
      document.getDocumentElement().normalize();

      Element root = document.getDocumentElement();
      eatingTime =
          Integer.parseInt(root.getElementsByTagName("eatingTime").item(0).getTextContent());

      System.out.println("Настройки загружены из XML файла успешно.");
    } catch (Exception e) {
      System.out.println("Настройки не загружены из XML файла.");
    }
    return eatingTime;
  }
}
