class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        System.out.println(this.toString());
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "name=" + name + ", age=" + age;
    }

    public static void main(String[] args) {
        Person person = new Person("Test Dummy", -4);
    }
}