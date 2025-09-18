public class Dog extends Animal implements Playable {
    public Dog(String name) {
        super(name);
    }

    @Override
    public void makeSound() {
        System.out.println(name + " : ワンワン！");
    }

    @Override
    public void play() {
        System.out.println(name + " はボールで遊びます！");
    }
}
