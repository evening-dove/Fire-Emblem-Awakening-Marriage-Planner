

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


import java.util.*;



public abstract class character_info_frame extends JFrame
{
    //A frame the displays information about a character_unit. Displays thier skills, classes, and who they are related too.
    
    
    private character_unit based_on;
    
    boolean is_parent;
    
    
    JPanel main_panel=new JPanel(new BorderLayout());
    
    JPanel full_build_panel=new JPanel(new BorderLayout());
    
    
    JPanel family_panel=new JPanel(new GridLayout(0,1));
    JLabel name_label;
    JLabel married_to_label;
    
    
    JPanel classes_panel=new JPanel(new GridLayout(0,1,5,5));
    ArrayList<Class_Name_JButton> class_options=new ArrayList<Class_Name_JButton>();
    
    JPanel selected_build_panel=new JPanel(new BorderLayout());
    
    JPanel picked_classes_panel=new JPanel(new GridLayout(0,1));
    JPanel picked_stats_panel=new JPanel(new GridLayout(0,1));
    JPanel picked_skills_panel=new JPanel(new GridLayout(0,1));
    JPanel passed_down_skill=new JPanel(new GridLayout(0,1));
    
    JPanel skills_panel=new JPanel(new GridLayout(0,2,5,5));
    ArrayList<JPanel> skill_options=new ArrayList<JPanel>();
    
    
    JPanel build_extras_panel=new JPanel(new BorderLayout());
    
    JPanel info_panel=new JPanel(new GridLayout(1,0));
    
    private character_info_frame this_frame=this;
    
    
    ArrayList<unit_class> classes_to_become=new ArrayList<unit_class>();
    ArrayList<String> skills_to_get=new ArrayList<String>();
    
    
    
    
    JPanel extra_options_panel=new JPanel(new FlowLayout());
    
    JButton create_frame_copy_button=new JButton("Create A Copy Frame");
    ArrayList<character_info_frame> frame_copies=new ArrayList<character_info_frame>();
    boolean is_original_frame;
    
    
    
    
    //initiates character_info_frame()
    //as peramaters, this takes an character_unit refering to who this character_info_frame is for, and a boolean that is true
    //if this is the initial character_info_frame created for the given character_unit, or if it is a copy.
    public character_info_frame(character_unit based_on, boolean is_original)
    {
        super();
        
        
        this.based_on=based_on;
        this.is_original_frame=is_original;
        
        
        
        selected_build_panel.add(picked_classes_panel, BorderLayout.WEST);
        selected_build_panel.add(picked_stats_panel, BorderLayout.CENTER);
        selected_build_panel.add(picked_skills_panel, BorderLayout.EAST);
        
        
        full_build_panel.add(family_panel, BorderLayout.NORTH);
        full_build_panel.add(classes_panel, BorderLayout.WEST);
        full_build_panel.add(selected_build_panel, BorderLayout.CENTER);
        full_build_panel.add(skills_panel, BorderLayout.EAST);
        full_build_panel.add(build_extras_panel, BorderLayout.SOUTH);
        
        
        main_panel.add(full_build_panel, BorderLayout.CENTER);
        
        //If this frame is the first of its kind, allow for it to be copied
        if (is_original==true){
            
            //An anonymous action listener for a button that creates a copy of this frame.
            create_frame_copy_button.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent event){
                    
                    //Copies the UI from the original frame
                    if (this_frame.based_on.is_parent==true){
                        
                        if (this_frame.based_on.is_female==true){
                            this_frame.frame_copies.add(new parent_info_frame((mother)this_frame.based_on, false));
                        }
                        
                        if (this_frame.based_on.is_female==false){
                            this_frame.frame_copies.add(new parent_info_frame((father)this_frame.based_on, false));
                        }
                        
                    }
                    
                    if (this_frame.based_on.is_parent==false){
                        this_frame.frame_copies.add(new child_info_frame((child)this_frame.based_on, false));
                    }
                    
                    character_info_frame new_frame=this_frame.frame_copies.get(this_frame.frame_copies.size()-1);
                    
                    new_frame.classes_to_become.addAll(this_frame.classes_to_become);
                    new_frame.skills_to_get.addAll(this_frame.skills_to_get);
                    
                    //Copies the class options, skill options, classes being considered, and skills being considered
                    //from the original frame
                    for (int i=0; i<classes_to_become.size(); i++){
                        new_frame.picked_classes_panel.add(new JLabel(this_frame.classes_to_become.get(i).name, SwingConstants.CENTER));
                        
                        int[] stats_to_use=this_frame.classes_to_become.get(i).stats;
                        
                        if (this_frame.classes_to_become.get(i).name=="great lord" && this_frame.based_on.name=="Lucina"){
                            stats_to_use=main_code.great_lord_f_stats;
                        }
                        
                        String[] stat_names=new String[] {" Str: ", " Mag: ", " Skl: ", " Spd: ", " Lck: ", " Def: ", " Res: "};
                        String stats_text="HP: "+stats_to_use[0];
                        
                        for (int ii=0; ii<7; ii++){
                            stats_text=stats_text+stat_names[ii];
                            
                            if (is_parent==true){
                                stats_text=stats_text+(stats_to_use[ii+1]+((parent_unit)this_frame.based_on).stat_modifier[ii]);
                            }
                            
                            if (is_parent==false){
                            
                                if (((child)this_frame.based_on).flexable_parent==null){
                                    stats_text=stats_text+(stats_to_use[ii+1]+((child)this_frame.based_on).constant_parent.stat_modifier[ii]+1);
                                }
                                
                                if (((child)this_frame.based_on).flexable_parent!=null){
                                    stats_text=stats_text+(stats_to_use[ii+1]+((child)this_frame.based_on).constant_parent.stat_modifier[ii]+((child)this_frame.based_on).flexable_parent.stat_modifier[ii]+1);
                                }
                            }
                            
                        }
                        
                        new_frame.picked_stats_panel.add(new JLabel(stats_text, SwingConstants.CENTER));
                    }
                    
                    for (int i=0; i<this_frame.skills_to_get.size(); i+=1){
                        new_frame.picked_skills_panel.add(new JLabel(this_frame.skills_to_get.get(i), SwingConstants.CENTER));
                    }
                    
                    new_frame.setVisible(true);
                    new_frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                }
            }
            );
        }
        
        main_panel.add(extra_options_panel, BorderLayout.SOUTH);
        
        add(main_panel);
        
        setPreferredSize(new Dimension(1000, 800));
        
        pack();
        
        setVisible(false);
    }
    
    
    
    
    //Re-evaluates what classes and skills a unit has access to.
    public void reset_classes(ArrayList<unit_class> all_classes_dup){
        
        
        this_frame.picked_classes_panel.removeAll();
        classes_to_become.clear();
        
        this_frame.picked_stats_panel.removeAll();
        
        this_frame.picked_skills_panel.removeAll();
        skills_to_get.clear();
        
        
        class_options.clear();
        skill_options.clear();
        
        ArrayList<unit_class> all_classes=new ArrayList<unit_class>();
        
        for (int i=0; i<all_classes_dup.size(); i++){
            if (all_classes.contains(all_classes_dup.get(i))==false){
                all_classes.add(all_classes_dup.get(i));
            }
        }
        
        
        //Creates the buttons for all their available skills and classes.
        for (int i=0; i<all_classes.size(); i++){
            
            
            Class_Name_JButton class_button=new Class_Name_JButton(all_classes.get(i), all_classes.get(i).name);
            class_button.addActionListener(new AddClassButtonListener());
            
            class_options.add(class_button);
            
            
            JPanel temp_skill1_panel=new JPanel(new BorderLayout());
            JButton skill1_name_button=new JButton(all_classes.get(i).skill1);
            skill1_name_button.addActionListener(new AddSkillButtonListener());
            Skill_Info_JButton skill1_info_button=new Skill_Info_JButton(all_classes.get(i).skill1_info, "Info");
            skill1_info_button.addActionListener(new SkillInfoButtonListener());
            temp_skill1_panel.add(skill1_name_button, BorderLayout.CENTER);
            temp_skill1_panel.add(skill1_info_button, BorderLayout.EAST);
            
            JPanel temp_skill2_panel=new JPanel(new BorderLayout(1,0));
            JButton skill2_name_button=new JButton(all_classes.get(i).skill2);
            skill2_name_button.addActionListener(new AddSkillButtonListener());
            Skill_Info_JButton skill2_info_button=new Skill_Info_JButton(all_classes.get(i).skill2_info, "Info");
            skill2_info_button.addActionListener(new SkillInfoButtonListener());
            temp_skill2_panel.add(skill2_name_button, BorderLayout.CENTER);
            temp_skill2_panel.add(skill2_info_button, BorderLayout.EAST);
            
            
            skill_options.add(temp_skill1_panel);
            skill_options.add(temp_skill2_panel);
        }
        
        classes_panel.removeAll();
        skills_panel.removeAll();
        
        
        for (int i=0; i<class_options.size(); i++){
            classes_panel.add(class_options.get(i));
        }
        
        for (int i=0; i<skill_options.size(); i++){
            skills_panel.add(skill_options.get(i));
        }
        
    }
    
    
    
    
    //The button that labels a class as being considered, or no longer considered
    public static class Class_Name_JButton extends JButton{
        
        unit_class based_on;
        
        public Class_Name_JButton(unit_class based_on){
            super();
            this.based_on=based_on;
        }
        
        public Class_Name_JButton(unit_class based_on, String text){
            super(text);
            this.based_on=based_on;
        }
    }
    
    
    //Listener for Class_Name_JButton, a button that labels a class as being considered, or no longer considered
    private class AddClassButtonListener implements ActionListener{
        
        
        public void actionPerformed(ActionEvent event){
            
            Class_Name_JButton source=(Class_Name_JButton)event.getSource();
            
            if (classes_to_become.contains(source.based_on)==false){
                classes_to_become.add(source.based_on);
            }
            
            else{
                classes_to_become.remove(source.based_on);
            }
            
            this_frame.picked_stats_panel.removeAll();
            this_frame.picked_classes_panel.removeAll();
            
            //finds out what stats go with the selected class
            for (int i=0; i<classes_to_become.size(); i++){
                this_frame.picked_classes_panel.add(new JLabel(classes_to_become.get(i).name, SwingConstants.CENTER));
                
                int[] stats_to_use=classes_to_become.get(i).stats;
                
                
                if (classes_to_become.get(i).name=="great lord" && this_frame.based_on.name=="Lucina"){
                    stats_to_use=main_code.great_lord_f_stats;
                }
                
                String[] stat_names=new String[] {" Str: ", " Mag: ", " Skl: ", " Spd: ", " Lck: ", " Def: ", " Res: "};
                String stats_text="HP: "+stats_to_use[0];
                
                for (int ii=0; ii<7; ii++){
                    stats_text=stats_text+stat_names[ii];
                    
                    if (is_parent==true){
                        stats_text=stats_text+(stats_to_use[ii+1]+((parent_unit)this_frame.based_on).stat_modifier[ii]);
                    }
                    
                    if (is_parent==false){
                    
                        if (((child)this_frame.based_on).flexable_parent==null){
                            stats_text=stats_text+(stats_to_use[ii+1]+((child)this_frame.based_on).constant_parent.stat_modifier[ii]+1);
                        }
                        
                        if (((child)this_frame.based_on).flexable_parent!=null){
                            stats_text=stats_text+(stats_to_use[ii+1]+((child)this_frame.based_on).constant_parent.stat_modifier[ii]+((child)this_frame.based_on).flexable_parent.stat_modifier[ii]+1);
                        }
                    }
                    
                }
                
                
                this_frame.picked_stats_panel.add(new JLabel(stats_text, SwingConstants.CENTER));
            }
            
            this_frame.pack();
        }
        
    }
    
    
    //A Button that displays more information about a skill
    public static class Skill_Info_JButton extends JButton{
        
        String description;
        
        public Skill_Info_JButton(String description){
            super();
            this.description=description;
        }
        
        public Skill_Info_JButton(String description, String text){
            super(text);
            this.description=description;
        }
    }
    
    
    //Listener for Skill_Info_JButton
    private class SkillInfoButtonListener implements ActionListener{
        
        public void actionPerformed(ActionEvent event){
            
            Skill_Info_JButton source=(Skill_Info_JButton)event.getSource();
            
            info_panel.removeAll();
            
            info_panel.add(new JLabel(source.description));
            
            this_frame.pack();
        }
        
    }
    
    
    //Listener for the button that labels a skill as being considered, or no longer considered
    private class AddSkillButtonListener implements ActionListener{
        
        public void actionPerformed(ActionEvent event){
            
            JButton source=(JButton)event.getSource();
            
            if (skills_to_get.contains(source.getText())==false){
                skills_to_get.add(source.getText());
            }
            
            else{
                skills_to_get.remove(source.getText());
            }
            
            this_frame.picked_skills_panel.removeAll();
            
            for (int i=0; i<skills_to_get.size(); i++){
                this_frame.picked_skills_panel.add(new JLabel(skills_to_get.get(i), SwingConstants.CENTER));
            }
            
            this_frame.pack();
        }
    }
    
    
}


