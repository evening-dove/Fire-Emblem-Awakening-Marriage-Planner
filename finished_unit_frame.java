

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


import java.util.*;


public class finished_unit_frame extends JFrame
{
    //A frame for if the user is finished working on a unit
    
    private finished_unit_frame this_frame=this;
    
    private character_unit based_on;
    
    JPanel main_panel=new JPanel(new BorderLayout());
    
    JPanel title_panel=new JPanel(new BorderLayout());
    JPanel family_panel=new JPanel(new FlowLayout());
    JPanel family_titles_panel=new JPanel(new GridLayout(0, 1));
    JPanel family_names_panel=new JPanel(new GridLayout(0, 1));
    
    JPanel build_panel=new JPanel(new GridLayout(0, 1));
    JPanel options_panel=new JPanel(new FlowLayout());
    
    JLabel name_label;
    
    JLabel class_label=new JLabel("", SwingConstants.CENTER);
    
    JPanel stat_names_panel=new JPanel(new GridLayout(1,0));
    JPanel stat_nums_panel=new JPanel(new GridLayout(1,0));
    
    JPanel skills_panel=new JPanel(new FlowLayout());
    
    JLabel comment_label=new JLabel("", SwingConstants.CENTER);
    
    JButton add_to_team_button=new JButton("Add to Team");
    JButton edit_button=new JButton("Edit");
    
    
    //initiates finished_unit_frame(character_unit)
    //takes a character_unit as a peramater, refering to whom this frame describes.
    public finished_unit_frame(character_unit based_on)
    {
        super();
        
        this.based_on=based_on;
        
        //Builds the text at the top decribing family.
        name_label=new JLabel(based_on.name, SwingConstants.CENTER);
        name_label.setFont(new Font("Serif", Font.PLAIN, 20));
        title_panel.add(name_label, BorderLayout.NORTH);
        
        if (based_on.is_parent==false){
            if (based_on.name!="Lucina"){
                family_titles_panel.add(new JLabel("Mother:"));
                family_titles_panel.add(new JLabel("Father:"));
            }
            if (based_on.name=="Lucina"){
                family_titles_panel.add(new JLabel("Father:"));
                family_titles_panel.add(new JLabel("Mother:"));
            }
        }
        
        family_panel.add(family_titles_panel);
        family_panel.add(family_names_panel);
        
        title_panel.add(family_panel, BorderLayout.CENTER);
        
        main_panel.add(title_panel, BorderLayout.NORTH);
        
        //Builds the panel describing this unit's chosen build
        build_panel.add(class_label);
        build_panel.add(stat_names_panel);
        build_panel.add(stat_nums_panel);
        build_panel.add(skills_panel);
        build_panel.add(comment_label);
        
        for (int i=0; i<9; i++){
            stat_names_panel.add(new JLabel(main_code.stat_names[i], SwingConstants.CENTER));
        }
        
        main_panel.add(build_panel, BorderLayout.CENTER);
        
        
        //Button for adding or removing this unit from the user's team
        options_panel.add(add_to_team_button);
        add_to_team_button.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent event){
                    
                    JButton source=(JButton)event.getSource();
                    if (source.getText()=="Add to Team"){
                        main_code.units_on_team.add(this_frame.based_on);
                        source.setText("Remove from Team");
                    }
                    else{
                        main_code.units_on_team.remove(this_frame.based_on);
                        source.setText("Add to Team");
                    }
                    
                    main_code.update_team();
            }
        }
        );
        
        //button for editing the current unit
        options_panel.add(edit_button);
        edit_button.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent event){
                    
                    if (this_frame.add_to_team_button.getText()=="Remove from Team"){
                        main_code.units_on_team.remove(this_frame.based_on);
                        main_code.update_team();
                        this_frame.add_to_team_button.setText("Add to Team");
                    }
                    
                    this_frame.based_on.finished=false;
                    this_frame.setVisible(false);
                    this_frame.based_on.info_frame.setVisible(true);
            }
        }
        );
        
        main_panel.add(options_panel, BorderLayout.SOUTH);
        
        add(main_panel);
        
        setPreferredSize(new Dimension(500, 250));
        pack();
        setVisible(false);
    }
    
    //fills this frame with revelent content.
    public void update_frame(){
        
        if (based_on.finished==false){
            return;
        }
        
        //Adds info relevent to this_unit's family
        family_names_panel.removeAll();
        
        if (based_on.is_parent==true){
            family_titles_panel.removeAll();
            
            JLabel temp_child_label=new JLabel("Child:"); //So "Child:" is only added once, even if based_on has 2 children
            
            if (((parent_unit)based_on).matched_with!=null){
                family_titles_panel.add(new JLabel("Married to:"));
                family_names_panel.add(new JLabel(((parent_unit)based_on).matched_with.name));
            }
            
            if (((parent_unit)based_on).parent_of!=null){
                family_titles_panel.add(temp_child_label);
                family_names_panel.add(new JLabel(((parent_unit)based_on).parent_of.name));
            }
            if (((parent_unit)based_on).matched_with!=null && ((parent_unit)based_on).matched_with.parent_of!=null){
                family_titles_panel.add(temp_child_label);
                family_names_panel.add(new JLabel(((parent_unit)based_on).matched_with.parent_of.name));
            }
            
            //Formats children correctly
            if ((((parent_unit)based_on).parent_of!=null) && (((parent_unit)based_on).matched_with!=null && ((parent_unit)based_on).matched_with.parent_of!=null)){
                family_titles_panel.add(new JLabel(""));
            }
        }
        
        if (based_on.is_parent==false){
            family_names_panel.add(new JLabel(((child)based_on).constant_parent.name));
            family_names_panel.add(new JLabel(((child)based_on).flexable_parent.name));
        }
        
        
        //Adds info relevent to this_unit's build
        unit_class class_picked=based_on.info_frame.classes_to_become.get(0);
        class_label.setText(class_picked.name);
        
        stat_nums_panel.removeAll();
        
        stat_nums_panel.add(new JLabel(""+class_picked.stats[0], SwingConstants.CENTER));
        for (int i=0; i<7; i++){
                    
            if (based_on.is_parent==true){
                stat_nums_panel.add(new JLabel(""+(class_picked.stats[i+1]+((parent_unit)based_on).stat_modifier[i]), SwingConstants.CENTER));
            }
            
            if (based_on.is_parent==false){
                    
                if (((child)based_on).flexable_parent==null){
                    stat_nums_panel.add(new JLabel(""+(class_picked.stats[i+1]+((child)based_on).constant_parent.stat_modifier[i]+1), SwingConstants.CENTER));
                }
                        
                if (((child)based_on).flexable_parent!=null){
                    stat_nums_panel.add(new JLabel(""+(class_picked.stats[i+1]+((child)based_on).constant_parent.stat_modifier[i]+((child)based_on).flexable_parent.stat_modifier[i]+1), SwingConstants.CENTER));
                }
            }
        }
        stat_nums_panel.add(new JLabel(""+class_picked.stats[8], SwingConstants.CENTER));
        
        skills_panel.removeAll();
        
        for (int i=0; i<based_on.info_frame.skills_to_get.size(); i++){
            skills_panel.add(new JLabel("    "+based_on.info_frame.skills_to_get.get(i)+"    "));
        }
        
        
        
        pack();
    }

    
}
