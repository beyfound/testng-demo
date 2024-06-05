package org.example.proxy;

public class ProxySing implements ISay{
    private People people;
    public ProxySing(People people)
    {
        this.people = people;
    }
    @Override
    public void say() {
        people.say();
        sing();
    }

    public void sing(){
        System.out.println("sing somethng");
    }
}
