package ua.edu.ucu.apps.task1;

public class MilkDecorator extends AbstractDecorator {
    public MilkDecorator(Coffee decoratedCoffee) {
        super(decoratedCoffee);
    }
    
    @Override
    public String getDescription() {
        return decoratedCoffee.getDescription() + " with Milk";
    }
    
    @Override
    public double getCost() {
        return decoratedCoffee.getCost() + 0.5;
    }
}