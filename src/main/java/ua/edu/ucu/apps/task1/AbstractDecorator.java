package ua.edu.ucu.apps.task1;

abstract class AbstractDecorator implements Coffee {
    protected Coffee decoratedCoffee;
    
    public AbstractDecorator(Coffee decoratedCoffee) {
        this.decoratedCoffee = decoratedCoffee;
    }
    
    @Override
    public String getDescription() {
        return decoratedCoffee.getDescription();
    }
    
    @Override
    public double getCost() {
        return decoratedCoffee.getCost();
    }
}