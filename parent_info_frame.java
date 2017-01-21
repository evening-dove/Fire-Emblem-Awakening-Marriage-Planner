import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.util.*;


public class parent_info_frame extends character_info_frame
{
    //A frame the displays information about a parent_unit. Displays thier skills, classes, and who they are related too.
    
    parent_unit based_on;
    parent_info_frame this_frame=this;
    
    
    JLabel child1_label;
    JLabel child2_label;
    
    
    //Initiates parent_info_frame()
    //Takes a boolean as a peramater, which is true if this is the first info_frame made for a parent_unit, or if this is a 
    //copy of the innitial frame
    public parent_info_frame(parent_unit based_on, boolean is_original)
    {
        super(based_on, is_original);
        
        this.based_on=based_on;
        
        is_parent=true;
        
        //UI informing the user who this parent_unit is related to.
        name_label=new JLabel("Name: "+based_on.name, SwingConstants.CENTER);
        married_to_label=new JLabel("Married to: None", SwingConstants.CENTER);
        child1_label=new JLabel("", SwingConstants.CENTER);
        child2_label=new JLabel("", SwingConstants.CENTER);
        
        family_panel.add(name_label);
        family_panel.add(married_to_label);
        
        
        
        
        if (based_on.is_female==true || based_on.name=="Chrom"){
            family_panel.add(child1_label);
            family_panel.add(child2_label);
        }
        
        if (based_on.is_female==false && based_on.name!="Chrom"){
            family_panel.add(child2_label);
            family_panel.add(child1_label);
        }
        
        reset_classes();
        
        pack();
    }
    
    //Resets the classes the parent_unit has access to.
    public void reset_classes(){
        super.reset_classes(new ArrayList<unit_class>(Arrays.asList(based_on.classes_availible)));
    }
}