package GenealogicalTree;

import java.util.Random;

/**
 * Класс, описывающий фабрику для создания генеалогического дерева.
 */
public class GenealogicalTreeBuilder {

    /**
     * Массив, содержащий мужские имена.
     */
    private static final String[] MALE_NAMES = new String[] { "Александр", "Дмитрий", "Максим", "Сергей", "Андрей", "Алексей", "Артём",
            "Илья", "Кирилл", "Михаил", "Никита", "Матвей", "Роман", "Егор", "Арсений", "Иван", "Денис", "Евгений",
            "Тимофей", "Владислав", "Игорь", "Владимир", "Павел", "Руслан", "Марк", "Константин", "Тимур", "Олег",
            "Ярослав", "Антон", "Николай", "Данил" };

    /**
     * Массив, содержащий женские имена.
     */
    private static final String[] FEMALE_NAMES = new String[] { "Анастасия", "Мария", "Анна", "Виктория", "Екатерина", "Наталья",
            "Марина", "Полина", "София", "Дарья", "Алиса", "Ксения", "Александра", "Елена" };

    /**
     * Массив, содержащий фамилии.
     */
    private static final String[] SURNAMES = new String[] {"Смирнов", "Иванов", "Кузнецов", "Соколов", "Попов", "Лебедев", "Козлов",
            "Новиков", "Морозов", "Петров", "Волков", "Соловьёв", "Васильев", "Зайцев", "Павлов", "Семёнов",
            "Голубев", "Виноградов", "Богданов", "Воробьёв", "Фёдоров", "Михайлов", "Беляев", "Тарасов", "Белов",
            "Комаров", "Орлов", "Киселёв", "Макаров", "Андреев", "Ковалёв", "Ильин", "Гусев", "Титов", "Кузьмин",
            "Кудрявцев", "Баранов", "Куликов", "Алексеев", "Степанов", "Яковлев", "Сорокин", "Сергеев", "Романов",
            "Захаров", "Борисов", "Королёв", "Герасимов", "Пономарёв", "Григорьев", "Лазарев", "Медведев", "Ершов",
            "Никитин", "Соболев", "Рябов", "Поляков", "Цветков", "Данилов", "Жуков", "Фролов", "Журавлёв",
            "Николаев", "Крылов", "Максимов", "Сидоров", "Осипов", "Белоусов", "Федотов", "Дорофеев", "Егоров",
            "Матвеев", "Бобров", "Дмитриев", "Калинин", "Анисимов", "Петухов", "Антонов", "Тимофеев", "Никифоров",
            "Веселов", "Филиппов", "Марков", "Большаков", "Суханов", "Миронов", "Ширяев", "Александров",
            "Коновалов", "Шестаков", "Казаков", "Ефимов", "Денисов", "Громов", "Фомин", "Давыдов", "Мельников",
            "Щербаков", "Блинов", "Колесников", "Карпов", "Афанасьев" };

    /**
     * Приватный конструктор.
     */
    private GenealogicalTreeBuilder() { }

    /**
     * Статический метод генерирования генеалогического дерева.
     * @param minChildren Минимальное колличество детей у человека.
     * @param maxChildren Максимальное колличество детей у человека.
     * @param generationsNumber Колличество поколений в дереве.
     * @return Сгенерированный объект генеалогического дерева.
     */
    public static GenealogicalTree generateGeneologicalTree(int minChildren, int maxChildren, int generationsNumber) {

        if (minChildren < 0)
            minChildren = 0;

        if (maxChildren < minChildren)
            maxChildren = minChildren;

        if (generationsNumber < 1)
            generationsNumber = 1;

        Random random = new Random();
        Gender gender = random.nextInt(0, 2) == 0 ? Gender.male : Gender.female;
        String name = gender == Gender.male ? MALE_NAMES[random.nextInt(MALE_NAMES.length)]
                : FEMALE_NAMES[random.nextInt(FEMALE_NAMES.length)];
        String surname = SURNAMES[random.nextInt(SURNAMES.length)];
        if (gender == Gender.female)
            surname += 'а';
        GenealogicalTree tree = new GenealogicalTree(name, surname, gender);
        addGeneration(tree, tree.getHead(), 1, generationsNumber, minChildren, maxChildren);

        return tree;
    }

    /**
     * Рекурсивный метод заполнения генеалогического дерева.
     * @param tree Объект генеалогического дерева.
     * @param person Объект для добавления в генеалогическое дерево.
     * @param currentGeneration Номер текущего поколения.
     * @param endGeneration Номер последнего поколения.
     * @param minChildren Минимальное колличество детей.
     * @param maxChildren Максимальное колличество детей.
     */
    private static void addGeneration(GenealogicalTree tree, Person person, int currentGeneration, int endGeneration,
                                      int minChildren, int maxChildren) {

        if (currentGeneration == endGeneration)
            return;

        Random random = new Random();

        int childrenCount = random.nextInt(minChildren, maxChildren + 1);
        if (childrenCount == 0)
            return;

        Gender secondParentGender = person.getGender() == Gender.female ? Gender.male : Gender.female;
        String secondParentName = secondParentGender == Gender.male ? MALE_NAMES[random.nextInt(MALE_NAMES.length)]
                : FEMALE_NAMES[random.nextInt(FEMALE_NAMES.length)];
        String secondParentSurname = SURNAMES[random.nextInt(SURNAMES.length)];
        if (secondParentGender == Gender.female)
            secondParentSurname += 'а';
        String childrenSurname = person.getGender() == Gender.male ? person.getSurname() : secondParentSurname;
        Person secondParent = null;

        for (int i = 0; i < random.nextInt(minChildren, maxChildren + 1); i++) {
            Gender childGender = random.nextInt(0, 2) == 0 ? Gender.male : Gender.female;
            String name = childGender == Gender.male ? MALE_NAMES[random.nextInt(MALE_NAMES.length)]
                    : FEMALE_NAMES[random.nextInt(FEMALE_NAMES.length)];
            String surname = childrenSurname;
            if (childGender == Gender.female)
                surname += 'а';
            Person child = null;
            if (secondParent == null) {
                child = tree.addPerson(person, secondParentName, secondParentSurname, name, surname, childGender);
                secondParent = person.getGender() == Gender.male ? child.getMother() : child.getFather();
            } else
                child = tree.addPerson(person, secondParent, name, surname, childGender);
            addGeneration(tree, child, currentGeneration + 1, endGeneration, minChildren, maxChildren);
        }
    }
}
