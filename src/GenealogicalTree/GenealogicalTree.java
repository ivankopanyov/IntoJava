package GenealogicalTree;

import java.util.List;

/**
 * Класс, описывающий генеалогическое дерево.
 */
public class GenealogicalTree {
    /**
     * Счетчик идентификаторов.
     */
    private int idCounter = 0;

    /**
     * Самый дальний предок. Корень генеалогического дерева.
     */
    private final Person head;

    /**
     * Инициализация объекта генеалогического дерева.
     * @param headName Имя самого дальнего предка.
     * @param headSurname Фамилия самого дальнего предка.
     * @param headGender Пол самого дальнего предка.
     */
    public GenealogicalTree(String headName, String headSurname, Gender headGender) {
        head = new Person(this, ++idCounter, headName, headSurname, headGender);
    }

    /**
     * Метод, возвращающий объект самого дальнего предка.
     * @return Объект самого дальнего предка.
     */
    public Person getHead() {
        return head;
    }

    /**
     * Метод, возвращающий объект человека из генеалогического дерева.
     * @param id Идентификатор человека.
     * @return Объект человека с указанным идентификатором. Если не найден, вернет null.
     */
    public Person getPerson(int id) {
        if (id < 1 || id > idCounter)
            return null;

        return getPerson(id, head);
    }

    /**
     * Метод добавления человека в генеалогическое дерево.
     * @param parent Объект первого родителя.
     * @param secondParent Объект второго родителя.
     * @param name Имя.
     * @param surname Фамилия.
     * @param gender Пол.
     * @return Созданный объект человека.
     */
    public Person addPerson(Person parent, Person secondParent, String name, String surname, Gender gender) {
        if (parent == null || parent.getGenealogicalTree() != this
                || secondParent == null || secondParent.getGenealogicalTree() != this)
            return null;

        List<Person> children = parent.getChildren();
        if (children.size() == 0)
            return null;

        Person mother = null;
        Person father = null;

        for (Person child: children)
            if (parent.getGender() == Gender.male && child.getMother() == secondParent) {
                mother = secondParent;
                break;
            } else if (parent.getGender() == Gender.female && child.getFather() == secondParent) {
                father = secondParent;
                break;
            }

        if (mother == null && father == null)
            return null;

        if (parent.getGender() == Gender.male)
            father = parent;
        else
            mother = parent;

        Person child = new Person(this, ++idCounter, name, surname, gender, mother, father);
        mother.addChild(child);
        father.addChild(child);

        return child;
    }

    /**
     * Метод добавления человека в генеалогическое дерево с созданием второго родителя.
     * @param parent Объект первого родителя.
     * @param secondParentName Имя второго родителя.
     * @param secondParentSurname Фамилия второго родителя.
     * @param name Имя.
     * @param surname Фамилия.
     * @param gender Пол.
     * @return Созданный объект человека.
     */
    public Person addPerson(Person parent, String secondParentName, String secondParentSurname,
                            String name, String surname, Gender gender) {
        if (parent == null || parent.getGenealogicalTree() != this)
            return null;

        Person mother = null;
        Person father = null;

        if (parent.getGender() == Gender.male)
            father = parent;
        else
            mother = parent;

        List<Person> children = parent.getChildren();
        Person secondParent;
        if (children.size() == 0)
            secondParent = new Person(this, ++idCounter, secondParentName, secondParentSurname,
                    parent.getGender() == Gender.male ? Gender.female : Gender.male);
        else
            secondParent = parent.getGender() == Gender.male ? children.get(0).getMother() : children.get(0).getFather();

        if (secondParent.getGender() == Gender.male)
            father = secondParent;
        else
            mother = secondParent;

        Person child = new Person(this, ++idCounter, name, surname, gender, mother, father);
        mother.addChild(child);
        father.addChild(child);

        return child;
    }

    /**
     * Переопределение метода преобразования объекта генеалогического дерева в строку.
     * @return Строка, содержащая структуру генеалогического дерева.
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        getTree(head, builder, "", true);
        return builder.toString();
    }

    /**
     * Рекурсивный метод поиска объекта человека по идентификатору.
     * @param id Идентификатор человека для поиска.
     * @param person Корень для поиска.
     * @return Результат поиска.
     */
    private Person getPerson(int id, Person person) {
        if (person.getId() == id)
            return person;

        List<Person> children = person.getChildren();
        if (children.size() == 0)
            return null;

        Person spouse = person.getGender() == Gender.male ? children.get(0).getMother() : children.get(0).getFather();
        if (spouse.getId() == id)
            return spouse;

        for (Person child: children) {
            Person result = getPerson(id, child);
            if (result != null)
                return result;
        }

        return null;
    }

    /**
     * Рекурсивный метод преобразования генеалогического дерева в строку.
     * @param person Текущий объект человека.
     * @param builder Объект StringBuilder для записи.
     * @param indent Отступ.
     * @param last Является ли объект человека последним в множестве детей.
     */
    private void getTree(Person person, StringBuilder builder, String indent, boolean last) {
        builder.append(indent)
                .append(last ? '└' : '├')
                .append('─')
                .append(person)
                .append('(')
                .append(person.getId())
                .append(')');
        List<Person> children = person.getChildren();
        if (children.size() == 0)
            return;
        builder.append(" & ");
        Person spouse = person.getGender() == Gender.male ? children.get(0).getMother() : children.get(0).getFather();
        builder.append(spouse)
                .append('(')
                .append(spouse.getId())
                .append(')');
        for (Person child: children) {
            builder.append('\n');
            getTree(child, builder, indent + (last ? "  " : "│ "), child == children.get(children.size() - 1));
        }
    }
}
