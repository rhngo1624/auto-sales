package app.ui.items;


public class SelectedItemPane {

    private static StoreItemPane selectedPane = null;

    public static StoreItemPane get(){
        if(selectedPane == null){
            return null;
        }else{
            return selectedPane;
        }
    }

    public static void set(StoreItemPane pane){
        selectedPane = pane;
    }

    public static void reset(){
        selectedPane = null;
    }

}
