package com.ohz.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DropdownOptionMapper {

    private static final HashMap<String, ArrayList<String>> newModelsFromMake = new HashMap<>();

    private static final ArrayList<String> newAcuraModels = new ArrayList<>(List.of("All models","ILX","Integra","MDX","NSX","RDX","TLX"));
    private static final ArrayList<String> newBuickModels = new ArrayList<>(List.of("All models","Enclave","Encore","Encore GX","Envision","Envista","Skylark"));


    public static ArrayList<String> getNewModelsForMake(String make){
        setNewModelFromMakeMap();
        return newModelsFromMake.get(make);
    }

    private static void setNewModelFromMakeMap(){
        if(newModelsFromMake.isEmpty()){
            newModelsFromMake.put("Acura", newAcuraModels);
            newModelsFromMake.put("Buick", newBuickModels);
        }
    }
}
