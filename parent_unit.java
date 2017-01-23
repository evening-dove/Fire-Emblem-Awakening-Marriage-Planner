
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.util.*;


public abstract class parent_unit extends character_unit
{
    //A unit that is also a parent. Parents can have children if they marry.
    
    
    unit_class[] classes_availible;
    
    int[] stat_modifier;
    
    child parent_of=null;
    
    ArrayList<parent_unit> marry_options=new ArrayList<parent_unit>();
    
    Char_Attached_JButton match_button=new Char_Attached_JButton(this, "match");
    
    parent_unit matched_with;
    
    
    //Initiates parent_unit()
    //Takes as peramaters: a String refering to what this parent_unit will be called, a boolean that is true if this 
    //parent_unit is female, an array of class_units that this parent_unit has access to, and a list of ints refering to
    //what this parent_unit's stat modifiers are (how they will differ from others when assigned a class.)
    public parent_unit(String name, boolean is_female, unit_class[] classes_availible, int[] stat_modifier)
    {
        super(name, is_female, true);
        
        this.classes_availible=classes_availible;
        this.stat_modifier=stat_modifier;
        
        //Sets up the UI associated with this object
        info_button=new Char_Attached_JButton(this, name);
        info_button.addActionListener(new InfoButtonListener());
        
        match_button.addActionListener(new MatchButtonListener());
        
        info_button=new Char_Attached_JButton(this, name);
        info_button.addActionListener(new InfoButtonListener());
        
        on_team_info_button=new Char_Attached_JButton(this, name);
        on_team_info_button.addActionListener(new InfoButtonListener());
        
        buttons_panel.add(info_button, BorderLayout.CENTER);
        buttons_panel.add(match_button, BorderLayout.SOUTH);
        
        info_frame=new parent_info_frame(this, true);
    }
    
    public void clear_unit(){
        matched_with=null;
        
        finished=false;
        info_frame.classes_to_become.clear();
        info_frame.skills_to_get.clear();
        
        info_frame.married_to_label.setText("");
        ((parent_info_frame)info_frame).child2_label.setText("");
        info_frame.picked_classes_panel.removeAll();
        info_frame.picked_stats_panel.removeAll();
        info_frame.picked_skills_panel.removeAll();
        info_frame.pack();
        
        main_code.units_on_team.remove(this);
        finished_frame.add_to_team_button.setText("Add to Team");
        main_code.update_team();
        
        main_code.update_team();
    }
    
    
    
    //The listener for the button which matches 2 parent_units
    private class MatchButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            
            Char_Attached_JButton source=(Char_Attached_JButton)event.getSource();
            
            
            //If this matching will disallow a child that his been finalised
            if (source.attached_to_parent.parent_of!=null && source.attached_to_parent.parent_of.finished==true){
                int confirm_match=JOptionPane.showConfirmDialog(main_code.match_frame, "Doing this will clear your progress\non the chracter "+source.attached_to_parent.parent_of.name+".\nAre you sure?");
                if (confirm_match==JOptionPane.NO_OPTION || confirm_match==JOptionPane.CANCEL_OPTION || confirm_match==-1){
                    return;
                }
            }
            if (source.attached_to_parent.matched_with!=null && source.attached_to_parent.matched_with.parent_of!=null && source.attached_to_parent.matched_with.parent_of.finished==true){
                int confirm_match=JOptionPane.showConfirmDialog(main_code.match_frame, "Doing this will clear your progress\non the chracter "+source.attached_to_parent.matched_with.parent_of.name+".\nAre you sure?");
                if (confirm_match==JOptionPane.NO_OPTION || confirm_match==JOptionPane.CANCEL_OPTION || confirm_match==-1){
                    return;
                }
            }
            
            //If either parent_unit is already married, un-marry them.
            if (source.attached_to_parent.matched_with!=null){
                
                if (source.attached_to_parent.matched_with.parent_of!=null){
                    source.attached_to_parent.matched_with.parent_of.remove_parent2();
                    
                }
                source.attached_to_parent.matched_with.info_frame.married_to_label.setText("Married to: None");
                ((parent_info_frame)source.attached_to_parent.matched_with.info_frame).child1_label.setText("");
                source.attached_to_parent.matched_with.matched_with=null;
                source.attached_to_parent.matched_with.finished_frame.update_frame();
                
            }
            
            source.attached_to_parent.info_frame.married_to_label.setText("Married to: None");
            source.attached_to_parent.matched_with=null;
            source.attached_to_parent.finished_frame.update_frame();
            
            if (source.attached_to_parent.parent_of!=null){
                 source.attached_to_parent.parent_of.remove_parent2();
            }
            
            
            //If this is the first parent_unit the user clicked on, or the user ties to match two parent_units who
            //cannot be matched, display who this newly clicked-on parent_unit can be matched with
            if (main_code.clicked_match==null || source.getText()=="X"){
                main_code.clicked_match=source.attached_to_parent;
                
                for (int i=0; i<mother.all_mothers.size(); i++){
                    mother.all_mothers.get(i).match_button.setText("X");
                    
                }
                
                for (int i=0; i<father.all_fathers.size(); i++){
                    father.all_fathers.get(i).match_button.setText("X");
                    
                }
                
                for (int i=0; i<marry_options.size(); i++){
                    marry_options.get(i).match_button.setText("match");
                }
                
                source.setText("Matching");
            
                return;
            }
            
            //Handles if the user tries to match a parent_unit with themself. 
            if (main_code.clicked_match.name==source.attached_to_parent.name){
                main_code.clicked_match=null;
                
                for (int i=0; i<mother.all_mothers.size(); i++){
                    mother.all_mothers.get(i).match_button.setText("match");
                    
                }
                
                for (int i=0; i<father.all_fathers.size(); i++){
                    father.all_fathers.get(i).match_button.setText("match");
                    
                }
                
                return;
            }
            
            
            //If the user matches two parent_units
            if (source.getText()=="match"){
                source.attached_to_parent.matched_with=main_code.clicked_match;
                main_code.clicked_match.matched_with=source.attached_to_parent;
                
                source.attached_to_parent.info_frame.married_to_label.setText("Married to: "+main_code.clicked_match.name);
                main_code.clicked_match.info_frame.married_to_label.setText("Married to: "+source.attached_to_parent.name);
                
                if (source.attached_to_parent.parent_of!=null){
                    source.attached_to_parent.parent_of.add_parent2(main_code.clicked_match);
                    source.attached_to_parent.parent_of.flexable_parent_label.setText("<html>Parent No.<br>1: "+source.attached_to_parent.name+"<br>2: "+main_code.clicked_match.name+"</html>");
                }
                
                if (main_code.clicked_match.parent_of!=null){
                    main_code.clicked_match.parent_of.add_parent2(source.attached_to_parent);
                    main_code.clicked_match.parent_of.flexable_parent_label.setText("<html>Parent No.<br>1: "+main_code.clicked_match.name+"<br>2: "+source.attached_to_parent.name+"</html>");
                }
                
                
                for (int i=0; i<mother.all_mothers.size(); i++){
                    mother.all_mothers.get(i).match_button.setText("match");
                }
                
                for (int i=0; i<father.all_fathers.size(); i++){
                    father.all_fathers.get(i).match_button.setText("match");
                }
                
                source.attached_to_parent.finished_frame.update_frame();
                main_code.clicked_match.finished_frame.update_frame();
                
                
                main_code.clicked_match=null;
                
                return;
            }
        }
    }
}
