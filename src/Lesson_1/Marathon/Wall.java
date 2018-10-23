package Lesson_1.Marathon;

public class Wall extends Obstacle {
    int height;

    public Wall(int height) {
        this.height = height;
    }

    @Override
    public void doit(Competitor competitor) {
        competitor.jump(height);
    }
}
