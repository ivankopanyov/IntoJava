package GenealogicalTree;

import java.util.*;

/**
 * Класс, описывающий человека из генеалогического дерева.
 */
public class Person {

    /**
     * Объект генеалогического дерева, к которому принадлежит экземпляр класса человека.
     */
    private final GenealogicalTree genealogicalTree;

    /**
     * Идентификатор человека.
     */
    private final int id;

    /**
     * Имя человека.
     */
    private final String name;

    /**
     * Фамилия человека.
     */
    private final String surname;

    /**
     * Пол человека.
     */
    private final Gender gender;

    /**
     * Мать.
     */
    private Person mother;

    /**
     * Отец.
     */
    private Person father;

    /**
     * Множество детей человека.
     */
    private final Set<Person> children;

    /**
     * Инициализация объекта человека.
     * @param genealogicalTree Объект генеалогического дерева, к которому принадлежит экземпляр класса человека.
     * @param id Идентификатор человека.
     * @param name Имя человека.
     * @param surname Фамилия человека.
     * @param gender Пол человека.
     */
    Person(GenealogicalTree genealogicalTree, int id, String name, String surname, Gender gender) {
        this.genealogicalTree = genealogicalTree;
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        children = new HashSet<>();
    }

    /**
     * Инициализация объекта человека.
     * @param genealogicalTree Объект генеалогического дерева, к которому принадлежит экземпляр класса человека.
     * @param id Идентификатор человека.
     * @param name Имя человека.
     * @param surname Фамилия человека.
     * @param gender Пол человека.
     * @param mother Мать.
     * @param father Отец.
     */
    Person(GenealogicalTree genealogicalTree, int id, String name, String surname, Gender gender, Person mother, Person father) {
        this(genealogicalTree, id, name, surname, gender);
        this.mother = mother;
        this.father = father;
    }

    /**
     * Метод, возвращающий идентификатор.
     */
    public int getId() {
        return id;
    }


    /**
     * Метод, возвращающий объект генеалогического дерева,
     * к которому принадлежит объект.
     */
    public GenealogicalTree getGenealogicalTree() {
        return genealogicalTree;
    }


    /**
     * Метод, возвращающий фамилию.
     */
    public String getSurname() {
        return surname;
    }


    /**
     * Метод, возвращающий пол.
     */
    public Gender getGender() {
        return gender;
    }


    /**
     * Метод, возвращающий объет матери.
     */
    public Person getMother() {
        return mother;
    }


    /**
     * Метод, возвращающий объект отца.
     */
    public Person getFather() {
        return father;
    }


    /**
     * Метод, возвращающий множество детей человека.
     */
    public ArrayList<Person> getChildren() {
        return new ArrayList<Person>(children);
    }


    /**
     * Метод, добавляющий ребенка.
     */
    void addChild(Person person) {
        children.add(person);
    }


    /**
     * Переопределение метода преобразования объекта в строку.
     */
    @Override
    public String toString() {
        return name + ' ' + surname;
    }
}
