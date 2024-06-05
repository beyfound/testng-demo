package org.example.observer;

import java.util.List;

public class WeatherSubject implements Subject{
    private List<Observer> observers;
    private int state;
    public void setState(int state) {
        this.state = state;
        notifyObservers();
    }
    public int getState() {
        return state;
    }
    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers){
            observer.update();
        }
    }
}
