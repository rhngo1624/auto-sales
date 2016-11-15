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
    private HashMap<FinancialApplication, Car> completed;
    private Car appInProgress;

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

            Iterator it = completed.entrySet().iterator();
            while(it.hasNext()){

                Map.Entry pair = (Map.Entry) it.next();

                if(pair.getValue().equals(item)){
                    it.remove();
                }

            }
        }
    }

    public ArrayList<Car> getRequirements(){
        return requirements;
    }

    private void addRequirement(Car car){
        requirements.add(car);
    }

    private void removeRequirement(Car car){
        requirements.remove(car);
    }

    public HashMap<FinancialApplication, Car> getCompletedApps(){
        return completed;
    }

    public void setAppInProgress(Car car){
        if(appInProgress == null){
            appInProgress = car;
        }
    }

    public void addCompletedApp(FinancialApplication application){
        completed.put(application, appInProgress);
        removeRequirement(appInProgress);
        appInProgress = null;
    }

}
