package Lesson_1.Marathon;

public class Team {
    String name;
    Competitor[] competitors;

    public Team(String name, Competitor[] competitors) {
        this.name = name;
        this.competitors = competitors;
    }

    public void showResults() {
        System.out.println("Участники команды " + this.name + ", прошедшие дистанцию:");
        for (Competitor c: this.competitors) {
            if(c.isOnDistance()) System.out.println(c.getName());
        }
    }

    public void teamInfo() {
        System.out.println("Состав команды " + this.name + ":");
        for (Competitor c: this.competitors) {
            System.out.println(c.getName());
        }
    }
}
