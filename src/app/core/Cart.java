package app.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import db.models.Car;
import db.models.FinancialApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class Cart {

    private ArrayList<StoreItem> items;
    private ArrayList<Car> requirements;
    private HashMap<Car, FinancialApplication> completed;

    public Cart(){
        items = new ArrayList<>();
        requirements = new ArrayList<>();
        completed = new HashMap<>();
    }

    public ArrayList<StoreItem> dump(){
        if(!items.isEmpty()){
            return items;
        }

        return null;
    }

    public ObservableList<StoreItem> dumpObservable(){
        ObservableList<StoreItem> itemObservableList = FXCollections.observableArrayList();
        itemObservableList.addAll(items);
        return itemObservableList;
    }

    public void addItem(StoreItem item){
        items.add(item);

        if(item.getClass().getSimpleName().equals("Car")){
            requirements.add((Car)item);
        }
    }

    public void removeItem(StoreItem item){
        items.remove(item);

        if(item.getClass().getSimpleName().equals("Car")){
            requirements.remove((Car)item);
        }
    }

    public ArrayList<Car> getRequirements(){
        return requirements;
    }

    private void addRequirement(Car car){
        requirements.add(car);
    }

    public void removeRequirement(Car car, FinancialApplication application){
        requirements.remove(car);
        completed.put(car, application);
    }

    public HashMap<Car, FinancialApplication> getCompletedApps(){
        return completed;
    }

}
