package view;

import infrastructure.application.ViewBase;

/**
 * Класс, описывающий завершеющее представление.
 */
public class ExitView extends ViewBase {

    /**
     * Метод вывода сообщения.
     * @param message Сообщение.
     */
    public void print(String message) {
        System.out.println(message);
    }
}
