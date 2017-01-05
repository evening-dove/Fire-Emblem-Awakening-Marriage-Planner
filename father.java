
import java.util.*;



public class father extends parent_unit
{
    //A father. Can be matched with one mother and have one child. Fathers pass down their class and and skill options 
    //to their child. Unlike mothers, children are not assigned to father.
    //object chrom in main_code is the only father that does have a child assigned to them.
    
    static ArrayList<father> all_fathers=new ArrayList<father>();
    
    //Initiates father()
    //Takes as peramaters: a String refering to what this father will be called, an array of class_units that this father
    //has access to, and a list of ints, refering to what this father's stat modifiers are (how they will differ from others
    //when assigned a class.)
    public father(String name, unit_class[] classes_availible, int[] stat_modifier)
    {
        super(name, false, classes_availible, stat_modifier);
        
        matched_with=null;
        all_fathers.add(this);
    }
}

