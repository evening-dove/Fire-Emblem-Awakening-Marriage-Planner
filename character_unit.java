
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.util.*;



public abstract class character_unit
{
    //Abstract class for a unit
    
    String name;
    boolean is_female;
    boolean is_parent;
    
    
    
    JPanel buttons_panel=new JPanel(new BorderLayout());
    
    character_info_frame info_frame;
    
    
    //Initiates character_unit()
    //Takes as peramaters: a String refering to what this character_unit will be called, a boolean that is true if this 
    //character_unit is female, and a boolean that is true if this character_unit is also a parent_unit.
    public character_unit(String name, boolean is_female, boolean is_parent)
    {
        this.name=name;
        this.is_female=is_female;
        this.is_parent=is_parent;
    }
    
    //Button that lets you open up a unit's info frame. (The buttons with names on them)
    public static class Char_Attached_JButton extends JButton{
        
        character_unit attached_to;
        parent_unit attached_to_parent;
        child attached_to_child;
        
        public Char_Attached_JButton(parent_unit attached_to){
            super();
            this.attached_to=attached_to;
            this.attached_to_parent=attached_to;
        }
        
        public Char_Attached_JButton(parent_unit attached_to, String text){
            super(text);
            this.attached_to=attached_to;
            this.attached_to_parent=attached_to;
        }
        
        public Char_Attached_JButton(child attached_to){
            super();
            this.attached_to=attached_to;
            this.attached_to_child=attached_to;
        }
        
        public Char_Attached_JButton(child attached_to, String text){
            super(text);
            this.attached_to=attached_to;
            this.attached_to_child=attached_to;
        }
    }
    
    
    //Listener for the button that lets you open up a unit's info frame. (The buttons with names on them)
    protected class InfoButtonListener implements ActionListener{
        
        public void actionPerformed(ActionEvent event){
            
            Char_Attached_JButton source=(Char_Attached_JButton)event.getSource();
            
            source.attached_to.info_frame.setVisible(true);
        }
    }
}
