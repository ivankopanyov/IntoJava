package services;

import model.Model;
import services.exceptions.MapperException;

/**
 * Маппер.
 */
public interface Mapper {

    /**
     * Метод преобразования строки в экземпляр модели.
     * @param source Строка для преобразования.
     * @return Результат преобразования.
     * @throws MapperException Невозможно преобразовать строку в экземпляр модели.
     */
    Model map(String source) throws MapperException;
}
