public abstract class Animal {
    protected String name;

    public Animal(String name) {
        this.name = name;
    }

    public void introduce() {
        System.out.println("こんにちは、私は " + name + " です。");
    }

    public abstract void makeSound();
}
