package Lesson_1.Marathon;

public class Human implements Competitor {
    String name;

    int maxRunDistance;
    int maxSwimDistance;
    int maxJumpHeight;

    boolean onDistance;

    public Human(String name, int maxRunDistance, int maxSwimDistance, int maxJumpHeight) {
        this.name = name;
        this.maxRunDistance = maxRunDistance;
        this.maxSwimDistance = maxSwimDistance;
        this.maxJumpHeight = maxJumpHeight;
        this.onDistance = true;
    }

    @Override
    public void run(int dist) {
        if(dist <= maxRunDistance) {
            System.out.println("Человек " + name + " справился с кроссом");
        } else {
            System.out.println("Человек " + " " + name + " не справился с кроссом");
            onDistance = false;
        }
    }

    @Override
    public void swim(int dist) {
        if(dist <= maxSwimDistance) {
            System.out.println("Человек " + " " + name + " справился с заплывом");
        } else {
            System.out.println("Человек " + " " + name + " не справился с заплывом");
            onDistance = false;
        }
    }

    @Override
    public void jump(int height) {
        if(height <= maxJumpHeight) {
            System.out.println("Человек " + " " + name + " справился с прыжком");
        } else {
            System.out.println("Человек " + " " + name + " не справился с прыжком");
            onDistance = false;
        }
    }

    @Override
    public boolean isOnDistance() {
        return onDistance;
    }

    @Override
    public void info() {
        System.out.println("Человек " + " " + name + " " + onDistance);
    }

    @Override
    public String getName() {
        return "Человек " + name;
    }
}
