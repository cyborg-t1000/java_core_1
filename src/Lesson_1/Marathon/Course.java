package Lesson_1.Marathon;

public class Course {
    String name;
    Obstacle[] obstacles;

    public Course(String name, Obstacle... obstacles) {
        this.name = name;
        this.obstacles = obstacles;
    }

    public void doIt(Team team) {
        for (Competitor c: team.competitors) {
            for (Obstacle o: this.obstacles) {
                if(!c.isOnDistance()) {
                    break;
                }
                o.doit(c);
            }
        }
    }
}
