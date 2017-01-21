import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.util.*;


public class child_info_frame extends character_info_frame
{
    //A frame the displays information about a child. Displays thier skills, classes, and who they are related too.
    
    JLabel parent1_label;
    JLabel parent2_label;
    JLabel child1_label;
    
    JPanel inherit_main_panel=new JPanel(new FlowLayout());
    JPanel inherit_mother_panel=new JPanel(new BorderLayout());
    JPanel inherit_mother_skills_panel=new JPanel(new GridLayout(2,0,5,0));
    JPanel inherit_father_panel=new JPanel(new BorderLayout());
    JPanel inherit_father_skills_panel=new JPanel(new GridLayout(2,0,5,0));
    
    child based_on;
    child_info_frame this_frame=this;
    
    //Initiates child_info_frame()
    //Takes a boolean as a peramater, which is true if this is the first info_frame made for a child, or if this is a 
    //copy of the innitial frame
    public child_info_frame(child based_on, boolean is_original)
    {
        super(based_on, is_original);
        
        
        is_parent=false;
        
        this.based_on=based_on;
        
        //UI showing who the child is related to.
        name_label=new JLabel("Name: "+based_on.name, SwingConstants.CENTER);
        parent1_label=new JLabel("Main Parent: "+based_on.constant_parent.name, SwingConstants.CENTER);
        parent2_label=new JLabel("Secondary Parent: None", SwingConstants.CENTER);
        child1_label=new JLabel("Secondary Parent: None", SwingConstants.CENTER);
        
        family_panel.add(name_label);
        family_panel.add(parent1_label);
        family_panel.add(parent2_label);
        
        //UI showing what the child can inherit.
        inherit_mother_panel.add(new JLabel("Mother's Skills", SwingConstants.CENTER), BorderLayout.NORTH);
        inherit_mother_panel.add(inherit_mother_skills_panel, BorderLayout.CENTER);
        
        inherit_father_panel.add(new JLabel("Father's Skills", SwingConstants.CENTER), BorderLayout.NORTH);
        inherit_father_panel.add(inherit_father_skills_panel, BorderLayout.CENTER);
        
        inherit_main_panel.add(inherit_mother_panel);
        inherit_main_panel.add(inherit_father_panel);
        
        //creates the misc. UI options catagory
        build_bottom_panel.add(inherit_main_panel, BorderLayout.NORTH);
        build_bottom_panel.add(info_panel, BorderLayout.SOUTH);
        
        
        reset_classes();
        
        
        pack();
    }
    
    
    
    
    //If the non-assigned parent_unit changes, reset_classes() updates the frame accordingly.
    public void reset_classes(){
        
        
        if (based_on.flexable_parent!=null){
            parent2_label.setText("Secondary Parent: "+based_on.flexable_parent.name);
        }
        
        if (based_on.flexable_parent==null){
            parent2_label.setText("Secondary Parent: None");
            based_on.flexable_parent_label.setText("<html>Parent No.<br>1: "+based_on.constant_parent.name+"<br>2: None</html>");
        }
        
        //add unit_class from assigned parent_unit
        ArrayList<unit_class> all_classes_dup=new ArrayList<unit_class>(Arrays.asList(based_on.constant_parent.classes_availible));
        
        //add unit_class from non-assigned parent_unit
        if (based_on.flexable_parent!=null){
            all_classes_dup.addAll(Arrays.asList(based_on.flexable_parent.classes_availible));
        }
        
        //handles any rules regarding if a parent_unit passes down a unit_class different to what they themselves have
        if (based_on.is_female==true){
        
            if (based_on.flexable_parent==main_code.vaike){
                all_classes_dup.remove(main_code.fighter);
                all_classes_dup.remove(main_code.warrior);
                all_classes_dup.remove(main_code.hero);
                
                all_classes_dup.add(main_code.knight);
                all_classes_dup.add(main_code.general);
                all_classes_dup.add(main_code.great_knight);
                
                all_classes_dup.remove(main_code.barbarian);
                all_classes_dup.remove(main_code.berserker);
                all_classes_dup.remove(main_code.warrior);
                
                all_classes_dup.add(main_code.mercenary);
                all_classes_dup.add(main_code.hero);
                all_classes_dup.add(main_code.bow_knight);
            }
        
            if (based_on.flexable_parent==main_code.gaius){
                all_classes_dup.remove(main_code.fighter);
                all_classes_dup.remove(main_code.warrior);
                all_classes_dup.remove(main_code.hero);
                
                all_classes_dup.add(main_code.pegasus_knight);
                all_classes_dup.add(main_code.dark_flier);
                all_classes_dup.add(main_code.falcon_knight);
            }
        
            if (based_on.flexable_parent==main_code.donnel){
                all_classes_dup.add(main_code.troubadour);
                all_classes_dup.add(main_code.valkyrie);
                all_classes_dup.add(main_code.war_monk);
                
                all_classes_dup.remove(main_code.fighter);
                all_classes_dup.remove(main_code.warrior);
                all_classes_dup.remove(main_code.hero);
                
                all_classes_dup.add(main_code.pegasus_knight);
                all_classes_dup.add(main_code.dark_flier);
                all_classes_dup.add(main_code.falcon_knight);
            }
        
            if (based_on.flexable_parent==main_code.gregor){
                all_classes_dup.remove(main_code.barbarian);
                all_classes_dup.remove(main_code.berserker);
                all_classes_dup.remove(main_code.warrior);
                
                all_classes_dup.add(main_code.troubadour);
                all_classes_dup.add(main_code.valkyrie);
                all_classes_dup.add(main_code.war_monk);
            }
        
            if (based_on.flexable_parent==main_code.henry){
                all_classes_dup.remove(main_code.barbarian);
                all_classes_dup.remove(main_code.berserker);
                all_classes_dup.remove(main_code.warrior);
                
                all_classes_dup.add(main_code.troubadour);
                all_classes_dup.add(main_code.valkyrie);
                all_classes_dup.add(main_code.war_monk);
            }
        }
        
        if (based_on.flexable_parent==main_code.donnel){
            all_classes_dup.remove(main_code.villager);
        }
        
        if (based_on.is_female==false){
        
            if (based_on.constant_parent==main_code.lissa){
                all_classes_dup.remove(main_code.pegasus_knight);
                all_classes_dup.remove(main_code.dark_flier);
                all_classes_dup.remove(main_code.falcon_knight);
                
                all_classes_dup.add(main_code.myrmidon);
                all_classes_dup.add(main_code.swordmaster);
                all_classes_dup.add(main_code.assassin);
                
                all_classes_dup.remove(main_code.troubadour);
                all_classes_dup.remove(main_code.valkyrie);
                
                all_classes_dup.add(main_code.barbarian);
                all_classes_dup.add(main_code.berserker);
                all_classes_dup.add(main_code.warrior);
            }
        
            if (based_on.constant_parent==main_code.miriel){
                all_classes_dup.remove(main_code.troubadour);
                all_classes_dup.remove(main_code.valkyrie);
                all_classes_dup.remove(main_code.war_monk);
                
                all_classes_dup.add(main_code.barbarian);
                all_classes_dup.add(main_code.berserker);
                all_classes_dup.add(main_code.warrior);
            }
        
            if (based_on.flexable_parent==main_code.maribelle || based_on.constant_parent==main_code.maribelle){
                all_classes_dup.remove(main_code.pegasus_knight);
                all_classes_dup.remove(main_code.dark_flier);
                all_classes_dup.remove(main_code.falcon_knight);
                
                all_classes_dup.add(main_code.cavalier);
                all_classes_dup.add(main_code.great_knight);
                all_classes_dup.add(main_code.paladin);
                
                all_classes_dup.remove(main_code.troubadour);
                all_classes_dup.remove(main_code.valkyrie);
                all_classes_dup.remove(main_code.war_monk);
                
                all_classes_dup.add(main_code.priest);
                all_classes_dup.add(main_code.sage);
                all_classes_dup.add(main_code.war_monk);
            }
        
            if (based_on.constant_parent==main_code.olivia || based_on.constant_parent==main_code.olivia){
                all_classes_dup.remove(main_code.dancer);
                
                all_classes_dup.add(main_code.mercenary);
                all_classes_dup.add(main_code.hero);
                all_classes_dup.add(main_code.bow_knight);
                
                all_classes_dup.remove(main_code.pegasus_knight);
                all_classes_dup.remove(main_code.dark_flier);
                all_classes_dup.remove(main_code.falcon_knight);
                
                all_classes_dup.add(main_code.barbarian);
                all_classes_dup.add(main_code.berserker);
                all_classes_dup.add(main_code.warrior);
            }
        
            if (based_on.constant_parent==main_code.panne){
                all_classes_dup.remove(main_code.wyvern_rider);
                all_classes_dup.remove(main_code.wyvern_lord);
                all_classes_dup.remove(main_code.griffon_rider);
                
                all_classes_dup.add(main_code.barbarian);
                all_classes_dup.add(main_code.berserker);
                all_classes_dup.add(main_code.warrior);
            }
        
            if (based_on.constant_parent==main_code.cherche){
                all_classes_dup.remove(main_code.troubadour);
                all_classes_dup.remove(main_code.valkyrie);
                all_classes_dup.remove(main_code.war_monk);
                
                all_classes_dup.add(main_code.fighter);
                all_classes_dup.add(main_code.warrior);
                all_classes_dup.add(main_code.hero);
            }
        }
        
        if (all_classes_dup.contains(main_code.lord)==true && based_on.name!="Lucina"){
            all_classes_dup.remove(main_code.lord);
            all_classes_dup.remove(main_code.great_lord);
        }
        
        if (based_on.name=="Lucina" && based_on.flexable_parent==main_code.olivia){
            all_classes_dup.remove(main_code.dancer);
        }
        
        if (based_on.name=="Kjelle"){
            all_classes_dup.add(main_code.knight);
            all_classes_dup.add(main_code.general);
        }
        
        //Updates the skills a child can have
        inherit_main_panel.removeAll();
        inherit_mother_skills_panel.removeAll();
        inherit_father_skills_panel.removeAll();
        
        
        //When recruted, a child will start with 1 skill from each parent_unit. This lets you decide which skills
        //they will be. the father chrom does not follow this rule.
        if (based_on.constant_parent!=main_code.chrom){
            
            boolean added_skills=false;
            
            for (int i=0; i<based_on.constant_parent.classes_availible.length; i++){
                
                if (all_classes_dup.contains(based_on.constant_parent.classes_availible[i])==false){
                
                    JPanel temp_skill1_panel=new JPanel(new BorderLayout());
                    JButton skill1_name_button=new JButton(based_on.constant_parent.classes_availible[i].skill1);
                    skill1_name_button.addActionListener(new AddSkillButtonListener());
                    Skill_Info_JButton skill1_info_button=new Skill_Info_JButton(based_on.constant_parent.classes_availible[i].skill1_info, "Info");
                    skill1_info_button.addActionListener(new SkillInfoButtonListener());
                    temp_skill1_panel.add(skill1_name_button, BorderLayout.CENTER);
                    temp_skill1_panel.add(skill1_info_button, BorderLayout.EAST);
                    
                    JPanel temp_skill2_panel=new JPanel(new BorderLayout(1,0));
                    JButton skill2_name_button=new JButton(based_on.constant_parent.classes_availible[i].skill2);
                    skill2_name_button.addActionListener(new AddSkillButtonListener());
                    Skill_Info_JButton skill2_info_button=new Skill_Info_JButton(based_on.constant_parent.classes_availible[i].skill2_info, "Info");
                    skill2_info_button.addActionListener(new SkillInfoButtonListener());
                    temp_skill2_panel.add(skill2_name_button, BorderLayout.CENTER);
                    temp_skill2_panel.add(skill2_info_button, BorderLayout.EAST);
                    
                    inherit_mother_skills_panel.add(temp_skill1_panel);
                    inherit_mother_skills_panel.add(temp_skill2_panel);
                    
                    added_skills=true;
                }
                
            }
            
            if (added_skills==true){
                inherit_main_panel.add(inherit_mother_panel);
            }
            
        }
        
        if (based_on.flexable_parent!=null && based_on.flexable_parent!=main_code.chrom){
            
            boolean added_skills=false;
            
            for (int i=0; i<based_on.flexable_parent.classes_availible.length; i++){
                
                if (all_classes_dup.contains(based_on.flexable_parent.classes_availible[i])==false){
                
                    JPanel temp_skill1_panel=new JPanel(new BorderLayout());
                    JButton skill1_name_button=new JButton(based_on.flexable_parent.classes_availible[i].skill1);
                    skill1_name_button.addActionListener(new AddSkillButtonListener());
                    Skill_Info_JButton skill1_info_button=new Skill_Info_JButton(based_on.flexable_parent.classes_availible[i].skill1_info, "Info");
                    skill1_info_button.addActionListener(new SkillInfoButtonListener());
                    temp_skill1_panel.add(skill1_name_button, BorderLayout.CENTER);
                    temp_skill1_panel.add(skill1_info_button, BorderLayout.EAST);
                    
                    JPanel temp_skill2_panel=new JPanel(new BorderLayout(1,0));
                    JButton skill2_name_button=new JButton(based_on.flexable_parent.classes_availible[i].skill2);
                    skill2_name_button.addActionListener(new AddSkillButtonListener());
                    Skill_Info_JButton skill2_info_button=new Skill_Info_JButton(based_on.flexable_parent.classes_availible[i].skill2_info, "Info");
                    skill2_info_button.addActionListener(new SkillInfoButtonListener());
                    temp_skill2_panel.add(skill2_name_button, BorderLayout.CENTER);
                    temp_skill2_panel.add(skill2_info_button, BorderLayout.EAST);
                    
                    if (based_on.constant_parent!=main_code.chrom){
                        inherit_father_skills_panel.add(temp_skill1_panel);
                        inherit_father_skills_panel.add(temp_skill2_panel);
                        added_skills=true;
                    }
                    
                    if (based_on.constant_parent==main_code.chrom){
                        inherit_mother_skills_panel.add(temp_skill1_panel);
                        inherit_mother_skills_panel.add(temp_skill2_panel);
                        inherit_main_panel.add(inherit_mother_panel);
                    }
                    
                }
                
            }
            
            if (added_skills==true){
                inherit_main_panel.add(inherit_father_panel);
            }
        }
        
        //the user is not allowed to choose which skill the father chrom passes down. It is always either Aether or 
        //Rightful King
        if (based_on.flexable_parent==main_code.chrom){
            
            JPanel temp_skill_panel=new JPanel(new BorderLayout());
            
            //passes down Aether
            if (based_on.is_female==true){
                
                JButton skill_name_button=new JButton(main_code.great_lord.skill1);
                skill_name_button.addActionListener(new AddSkillButtonListener());
                Skill_Info_JButton skill_info_button=new Skill_Info_JButton(main_code.great_lord.skill1_info, "Info");
                skill_info_button.addActionListener(new SkillInfoButtonListener());
                temp_skill_panel.add(skill_name_button, BorderLayout.CENTER);
                temp_skill_panel.add(skill_info_button, BorderLayout.EAST);
                
            }
            
            //passes down Rightful King
            if (based_on.is_female==false){
                
                JButton skill_name_button=new JButton(main_code.great_lord.skill2);
                skill_name_button.addActionListener(new AddSkillButtonListener());
                Skill_Info_JButton skill_info_button=new Skill_Info_JButton(main_code.great_lord.skill2_info, "Info");
                skill_info_button.addActionListener(new SkillInfoButtonListener());
                temp_skill_panel.add(skill_name_button, BorderLayout.CENTER);
                temp_skill_panel.add(skill_info_button, BorderLayout.EAST);
                
            }
            
            inherit_father_skills_panel.add(temp_skill_panel);
            
            inherit_main_panel.add(inherit_father_panel);
        }
        
        super.reset_classes(all_classes_dup);
    }
    
    
    //The listener for the button that displays additional info about a skill
    private class SkillInfoButtonListener implements ActionListener{
        
        public void actionPerformed(ActionEvent event){
            
            Skill_Info_JButton source=(Skill_Info_JButton)event.getSource();
            
            info_panel.removeAll();
            
            info_panel.add(new JLabel(source.description));
            
            this_frame.pack();
        }
        
    }
    
    //The listener for the button that marks a skill as being considered
    private class AddSkillButtonListener implements ActionListener{
        
        public void actionPerformed(ActionEvent event){
            
            JButton source=(JButton)event.getSource();
            
            if (skills_to_get.contains(source.getText())==false){
                skills_to_get.add(source.getText());
            }
            
            //If the skill is already being considered, remove it instead
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