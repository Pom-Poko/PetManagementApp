public class Cat extends Animal implements Playable {
    public Cat(String name) {
        super(name);
    }

    @Override
    public void makeSound() {
        System.out.println(name + " : ニャー！");
    }

    @Override
    public void play() {
        System.out.println(name + " はねこじゃらしで遊びます！");
    }
}
