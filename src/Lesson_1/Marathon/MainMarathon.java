package Lesson_1.Marathon;

public class MainMarathon {
    public static void main(String[] args) {
        Course c = new Course("Полоса препятствий для ГТО", new Cross(80), new Wall(2), new Water(10)); // Создаем полосу препятствий
        Team team = new Team("Спартак", new Competitor[] {new Cat("Барсик"), new Dog("Бобик"), new Man("Вася")}); // Создаем команду
        c.doIt(team); // Просим команду пройти полосу
        team.showResults(); // Показываем результаты
    }
}
