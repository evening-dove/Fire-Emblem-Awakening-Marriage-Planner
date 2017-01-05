import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.util.*;

public class mother extends parent_unit
{
    //A mother is a type of parent_unit that is assigned a child. No mater who a mather marries, they will always have that
    //same child. Mothers pass down their class and and skill options to their child.
    
    static ArrayList<mother> all_mothers=new ArrayList<mother>();
    
    //Initiates mother()
    //Takes as peramaters: a String refering to what this mother will be called, an array of class_units that this mother
    //has access to, and a list of ints, refering to what this mother's stat modifiers are (how they will differ from others
    //when assigned a class.)
    public mother(String name, unit_class[] classes_availible, int[] stat_modifier)
    {
        super(name, true, classes_availible, stat_modifier);
        matched_with=null;
        all_mothers.add(this);
    }
    
    //Takes an array of parent_units as a peramater. 
    //Enables a mother to marry any parent_units in that array.
    public void set_up_partners(parent_unit[] can_marry){
        
        for (int i=0; i<can_marry.length; i++){
            marry_options.add(can_marry[i]);
            can_marry[i].marry_options.add(this);
        }
        
        return;
    }
}