
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;


import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.List;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import java.util.*;

public class main_code extends JFrame
{
    
    private main_code this_frame=this;
    
    static JPanel main_panel=new JPanel(new FlowLayout());
    
    static JButton back_button=new JButton("Back");
    static String current_menu="main";
    
    static JButton open_plan_button=new JButton("Plan");
    static JButton open_review_button=new JButton("Review");
    
    static JButton new_team_button=new JButton("New Team");
    static JButton load_team_button=new JButton("Load Team");
    
    static JButton open_match_button=new JButton("Match");
    static JButton open_team_button=new JButton("Team");
    static JButton save_button=new JButton("Save");
    
    static JFrame match_frame=new JFrame();
    
    //Initiate all classes
    static String[] stat_names=new String[] {"HP: ", "Str: ", "Mag: ", "Skl: ", "Spd: ", "Lck: ", "Def: ", "Res: ", "Mov: "};
    
    static int[] great_lord_f_stats=new int[] {80, 40, 30, 42, 44, 45, 40, 40, 6};
    
    static unit_class great_lord=new unit_class("Great Lord", "Aether", "Attack twice consecutively, with the first strike having a Sol effect and the second strike having a Luna effect. Activation Rate = (Skill/2)%", "Rightful King", "Adds 10% to Skill activation rates", new int[] {80, 43, 30, 40, 41, 45, 42, 40, 6});
    static unit_class lord=new unit_class("Lord", "Dual Strike+", "Adds 10% to Dual Strike activation", "Charm", "Hit rate and Avoid +5 to all allies within a 3 tile radius", new int[] {60, 25, 20, 26, 28, 30, 25, 25, 5});
    static unit_class tactician=new unit_class("Tactician", "Veteran", "x1.5 experience gain when in Pair Up", "Solidarity", "Critical and Critical Avoid +10 to adjacent allies", new int[] {60, 25, 25, 25, 25, 30, 25, 25, 5});
    static unit_class cavalier=new unit_class("Cavalier", "Discipline", "Weapon Experience x2", "Outdoor Fighter", "Hit rate and Avoid +10 when fighting outdoors", new int[] {60, 26, 20, 25, 25, 30, 26, 26, 7});
    static unit_class knight=new unit_class("Knight", "Defense +2", "Defense +2", "Indoor Fighter", "Hit rate and Avoid +10 when fighting indoors", new int[] {60, 30, 20, 26, 23, 30, 30, 22, 4});
    static unit_class myrmidon=new unit_class("Myrmidon", "Avoid +10", "Avoid +10", "Vantage", "Strike first during an enemy attack when HP is under half", new int[] {60, 24, 22, 27, 28, 30, 22, 24, 5});
    static unit_class mercenary=new unit_class("Mercenary", "Armsthrift", "Weapon durability is not decreased when activated. Activation Rate = (Luck x2)%", "Patience", "Hit Rate and Avoid +10 during the enemy's Turn", new int[] {60, 26, 20, 28, 26, 30, 25, 23, 5});
    static unit_class fighter=new unit_class("Fighter", "HP +5", "Maximum HP +5", "Zeal", "Critical +5", new int[] {60, 29, 20, 26, 25, 30, 25, 23, 5});
    static unit_class barbarian=new unit_class("Barbarian", "Despoil", "Obtain a Bullion (S) from the enemy if the user defeats the enemy. Activation Rate = (Luck)%", "Gamble", "Hit rate -5, Critical +10", new int[] {60, 30, 20, 23, 27, 30, 22, 20, 5});
    static unit_class archer=new unit_class("Archer", "Skill +2", "Skill +2", "Prescience", "Hit rate and Avoid +15 during the user's Turn", new int[] {60, 26, 20, 29, 25, 30, 25, 21, 5});
    static unit_class thief=new unit_class("Thief", "Locktouch", "Unlock chests and doors without needing keys", "Movement +1", "Movement +1", new int[] {60, 22, 20, 30, 28, 30, 21, 20, 5});
    static unit_class pegasus_knight=new unit_class("Pegasus Knight", "Speed +2", "GSpeed +2", "Relief", "Recover 20% HP at the start of the user's Turn if there are no units within a 3 tile radius", new int[] {60, 24, 23, 28, 27, 30, 22, 25, 7});
    static unit_class wyvern_rider=new unit_class("Wyvern Rider", "Strength +2", "Strength +2", "Tantivy", "Hit rate and Avoid +10 if no allies within a 3 tile radius.", new int[] {60, 28, 20, 24, 24, 30, 28, 20, 7});
    static unit_class mage=new unit_class("Mage", "Magic +2", "Magic +2", "Focus", "Critical +10 when no allies are within a 3 tile radius", new int[] {60, 20, 28, 27, 26, 30, 21, 25, 5});
    static unit_class dark_mage=new unit_class("Dark Mage", "Hex", "Avoid -15 to all adjacent enemies", "Anathema", "Avoid and Critical Avoid -10 to all enemies within a 3 tile radius.", new int[] {60, 20, 27, 25, 25, 30, 25, 27, 5});
    static unit_class priest=new unit_class("Priest", "Miracle", "Character survives with 1 HP after receiving a fatal blow. (Must have at least 2 HP or more to activate.)  Activation Rate = (Luck)%", "Healtouch", "Restores an extra 5 HP when healing allies", new int[] {60, 22, 25, 24, 25, 30, 22, 27, 5});
    static unit_class troubadour=new unit_class("Troubadour", "Resistance +2", "Resistance +2", "Demoiselle", "Avoid and Critical Avoid +10 to all male allies within a 3 tile radius", new int[] {60, 20, 26, 24, 26, 30, 20, 28, 7});
    static unit_class grandmaster=new unit_class("Grandmaster", "Ignis", "Adds (Magic)/2 to Strength when dealing physical damage and (Strength)/2 to Magic when dealing magical damage. Activation Rate = (Skill)%", "Rally Spectrum", "All stats (except movement) +4 to all allies within a 3 tile radius for one Turn", new int[] {80, 40, 40, 40, 40, 45, 40, 40, 6});
    static unit_class paladin=new unit_class("Paladin", "Defender", "All Stats (except movement) +1 when in a pair up", "Aegis", "Halves damage from bows, tomes, and Dragonstones. Activation Rate = (Skill)%", new int[] {80, 42, 30, 40, 40, 45, 42, 42, 8});
    static unit_class great_knight=new unit_class("Great Knight", "Luna", "Ignores half of the enemy's Defense or Resistance when attacking. Activation Rate = (Skill)%", "Dual Guard+", "Adds 10% to Dual Guard rate", new int[] {80, 48, 20, 34, 37, 45, 48, 30, 7});
    static unit_class general=new unit_class("General", "Rally Defense", "Defense +4 to all allies within a 3 tile radius for one Turn", "Pavise", "Halves damage from swords, lances, axes (including their magical variants), and Beaststones. Activation Rate = (Skill)%", new int[] {80, 50, 30, 41, 35, 45, 50, 35, 5});
    static unit_class swordmaster=new unit_class("Swordmaster", "Astra", "Deals 5 consecutive hits at half damage. Activation Rate = (Skill/2)%", "Swordfaire", "Strength +5 when equipped with a sword. Magic +5 when equipped with a Levin Sword", new int[] {80, 38, 34, 44, 46, 45, 33, 38, 6});
    static unit_class hero=new unit_class("Hero", "Sol", "Recover HP equal to half the damage dealt to the enemy. Activation Rate = (Skill)%", "Axebreaker", "Hit rate and Avoid +50 when enemy is equipped with an axe", new int[] {80, 42, 30, 46, 42, 45, 40, 36, 6});
    static unit_class warrior=new unit_class("Warrior", "Rally Strength", "Strength +4 to all allies within a 3 tile radius for one turn", "Counter", "Returns damage when attacked by an adjacent enemy (does not return damage when user is KO'd)", new int[] {80, 48, 30, 42, 40, 45, 40, 35, 6});
    static unit_class berserker=new unit_class("Berserker", "Wrath", "Critical +20 when under half HP", "Axefaire", "Strength +5 when equipped with an axe. Magic +5 when equipped with a Bolt Axe", new int[] {80, 50, 30, 35, 44, 45, 34, 30, 6});
    static unit_class sniper=new unit_class("Sniper", "Hit Rate +20", "Hit Rate +20", "Bowfaire", "Strength +5 when equipped with a bow.", new int[] {80, 41, 30, 48, 40, 45, 40, 31, 6});
    static unit_class bow_knight=new unit_class("Bow Knight", "Rally Skill", "Skill +4 to all allies within a 3 tile radius for one Turn", "Bowbreaker", "Hit rate and Avoid +50 when the enemy is equipped with a bow.", new int[] {80, 40, 30, 43, 41, 45, 35, 30, 8});
    static unit_class assassin=new unit_class("Assassin", "Lethality", "Instantly defeats the enemy when attacking. Activation Rate = (Skill/4)%", "Pass", "User can move through tiles occupied by an enemy unit", new int[] {80, 40, 30, 48, 46, 45, 31, 30, 6});
    static unit_class trickster=new unit_class("Trickster", "Lucky Seven", "Hit rate and Avoid +20 for the first 7 Turns of a map.", "Acrobat", "All traversable terrain costs 1 movement point to cross", new int[] {80, 35, 38, 45, 43, 45, 30, 40, 6});
    static unit_class falcon_knight=new unit_class("Falcon Knight", "Rally Speed", "Speed +4 to all allies within a 3 tile radius for one Turn", "Lancefaire", "Strength +5 when equipped with a lance. Magic +5 when equipped with a Shockstick", new int[] {80, 38, 35, 45, 44, 45, 33, 40, 8});
    static unit_class dark_flier=new unit_class("Dark Flier", "Rally Movement", "Movement +1 to all allies within a 3 tile radius for one Turn", "Galeforce", "Allows the user another full action after they defeat an enemy during the user's Turn. Activates only once per Turn.", new int[] {80, 36, 42, 41, 42, 45, 32, 41, 8});
    static unit_class wyvern_lord=new unit_class("Wyvern Lord", "Quick Burn", "Hit rate and Avoid +15 at the start of a chapter. Effect decreases by 1 with each passing turn.", "Swordbreaker", "Hit rate and Avoid +50 when the enemy is equipped with a sword", new int[] {80, 46, 30, 38, 38, 45, 46, 30, 8});
    static unit_class griffon_rider=new unit_class("Griffon Rider", "Deliverer", "Movement +2 when in a Pair Up", "Lancebreaker", "Hit rate and Avoid +50 when the enemy is equipped with a lance", new int[] {80, 40, 30, 43, 41, 45, 40, 30, 8});
    static unit_class sage=new unit_class("Sage", "Rally Magic", "Magic +4 to all alies within a 3 tile radius for one Turn", "Tomefaire", "Magic +5 when equipped with a tome", new int[] {80, 30, 46, 43, 42, 45, 31, 40, 6});
    static unit_class sorcerer=new unit_class("Sorcerer", "Vengeance", "Adds half of the user's current accrued damage to user's attack. Activation Rate = (Skill x2)%", "Tomebreaker", "Hit rate and Avoid +50 when enemy is equipped with a tome", new int[] {80, 30, 44, 38, 40, 45, 41, 44, 6});
    static unit_class dark_knight=new unit_class("Dark Knight", "Slow Burn", "Hit rate and Avoid increases by 1 each Turn up to the 15th turn", "Lifetaker", "User recovers 50% Max HP after they defeat an enemy during the user's Turn.", new int[] {80, 38, 41, 40, 40, 45, 42, 38, 8});
    static unit_class war_monk=new unit_class("War Monk", "Rally Luck", "Luck +8 to all allies within a 3 tile radius for one Turn", "Renewal", "Recover 30% HP at the start of the user's Turn", new int[] {80, 40, 40, 38, 41, 45, 38, 43, 6});
    static unit_class valkyrie=new unit_class("Valkyrie", "Rally Resistance", "Resistance +4 to all allies within a 3 tile radius for one Turn", "Dual Support+", "Increases the Dual Support level of a unit by 4", new int[] {80, 30, 42, 38, 43, 45, 30, 45, 8});
    static unit_class villager=new unit_class("Villager", "Aptitude", "Adds 20% to all growth rates during Level Ups", "Underdog", "Hit rate and Avoid +15 when user's Level is lower than the enemy (promoted units count as Level +20)    ", new int[] {60, 20, 20, 20, 20, 30, 20, 20, 5});
    static unit_class dancer=new unit_class("Dancer", "Luck +4", "Luck +4", "Special Dance", "Strength, Magic, Defence and Resistance +2 for one Turn for the unit who receives the user's Dance", new int[] {80, 30, 30, 40, 40, 45, 30, 30, 5});
    static unit_class taguel=new unit_class("Taguel", "Even Rhythm", "Hit rate and Avoid +10 during even numbered Turns", "Beastbane", "Deals effective damage to Beast (beast *3) units when user is a Taguel", new int[] {80, 35, 30, 40, 40, 45, 35, 30, 6});
    static unit_class manakete=new unit_class("Manakete", "Odd Rhythm", "Hit rate and Avoid +10 during odd numbered Turns", "Wyrmsbane", "Deals effective damage to Dragon (dragon) units when user is a Manakete", new int[] {80, 40, 35, 35, 35, 45, 40, 40, 6});

    //Initiate all parents
    static father chrom=new father("Chrom", new unit_class[] {lord, great_lord, archer, sniper, bow_knight, cavalier, paladin, great_knight}, new int[] {1, 0, 1, 1, 1, -1, -1});
    static mother lissa=new mother("Lissa", new unit_class[] {troubadour, valkyrie, war_monk, priest, sage, pegasus_knight, falcon_knight, dark_flier}, new int[] {-2, 2, -1, 0, 2, -1, 1});
    static father frederick=new father("Frederick", new unit_class[] {knight, general, great_knight, wyvern_rider, griffon_rider, wyvern_lord, cavalier, paladin}, new int[] {2, -2, 2, -2, 0, 2, 0, 0});
    static mother sully=new mother("Sully", new unit_class[] {myrmidon, swordmaster, assassin, cavalier, paladin, great_knight, wyvern_rider, griffon_rider, wyvern_lord}, new int[] {-1, -1, 2, 2, 0, -1, 0});
    static father virion=new father("Virion", new unit_class[] {mage, sage, dark_knight, archer, sniper, bow_knight, wyvern_rider, griffon_rider, wyvern_lord}, new int[] {0, 0, 2, 2, -1, -2, 0});
    static father stahl=new father("Stahl", new unit_class[] {archer, sniper, bow_knight, cavalier, paladin, great_knight, myrmidon, swordmaster, assassin}, new int[] {2, -1, 1, 0, -2, 2, -1});
    static father vaike=new father("Vaike", new unit_class[] {thief, assassin, trickster, fighter, hero, warrior, barbarian, berserker, warrior}, new int[] {3, -2, 1, 1, -1, 0, -2});
    static mother miriel=new mother("Miriel", new unit_class[] {dark_mage, sorcerer, dark_knight, mage, sage, dark_knight, troubadour, war_monk, valkyrie}, new int[] {-2, 3, 1, 1, 0, -2, 0});
    static mother sumia=new mother("Sumia", new unit_class[] {priest, war_monk, sage, pegasus_knight, falcon_knight, dark_flier, knight, general, great_knight}, new int[] {-2, 0, 2, 3, 0, -2, 1});
    static father kellam=new father("Kellam", new unit_class[] {priest, sage, war_monk, knight, general, great_knight, thief, assassin, trickster}, new int[] {1, 0, 1, -2, -2, 3, 0});
    static father lonqu=new father("Lonqu", new unit_class[] {thief, assassin, trickster, myrmidon, swordmaster, assassin, wyvern_rider, griffon_rider, wyvern_lord}, new int[] {0, 0, 3, 3, 0, -2, -2});
    static father donnel=new father("Donnel", new unit_class[] {mercenary, hero, bow_knight, villager, fighter, warrior, hero}, new int[] {1, -1, -1, -1, 3, 1, -1});
    static father ricken=new father("Ricken", new unit_class[] {archer, sniper, bow_knight, mage, sage, dark_knight, cavalier, paladin, great_knight}, new int[] {-1, 2, 0, 0, 1, -1, 0});
    static mother maribelle=new mother("Maribelle", new unit_class[] {mage, sage, dark_knight, troubadour, war_monk, valkyrie, pegasus_knight, falcon_knight, dark_flier}, new int[] {-3, 2, 1, 0, 3, -3, 2});
    static mother panne=new mother("Panne", new unit_class[] {wyvern_rider, griffon_rider, wyvern_lord, taguel, thief, assassin, trickster}, new int[] {2, -1, 2, 3, -1, 1, -1});
    static father gaius=new father("Gaius", new unit_class[] {myrmidon, swordmaster, assassin, thief, assassin, trickster, fighter, warrior, hero}, new int[] {1, -1, 2, 2, -2, -1, 0});
    static mother cordelia=new mother("Cordelia", new unit_class[] {mercenary, hero, bow_knight, pegasus_knight, falcon_knight, dark_flier, dark_mage, sorcerer, dark_knight}, new int[] {1, -1, 2, 2, -1, 0, -1});
    static father gregor=new father("Gregor", new unit_class[] {barbarian, warrior, berserker, mercenary, hero, bow_knight, myrmidon, swordmaster, assassin}, new int[] {2, -1, 2, 0, -1, 1, -2});
    static mother nowi=new mother("Nowi", new unit_class[] {mage, sage, dark_knight, manakete, wyvern_rider, griffon_rider, wyvern_lord}, new int[] {1, 1, -1, -2, 1, 3, 2});
    static father libra=new father("Libra", new unit_class[] {priest, war_monk, mage, dark_mage, sage, dark_knight, sorcerer}, new int[] {0, 1, 1, 0, -1, 0, 1});
    static mother tharja=new mother("Tharja", new unit_class[] {knight, general, great_knight, dark_mage, sorcerer, dark_knight, archer, sniper, bow_knight}, new int[] {0, 3, -1, 1, -3, 1, 0});
    static mother olivia=new mother("Olivia", new unit_class[] {myrmidon, assassin, swordmaster, dancer, pegasus_knight, falcon_knight, dark_flier}, new int[] {0, 0, 1, 1, 0, -1, -1});
    static mother cherche=new mother("Cherche", new unit_class[] {priest, war_monk, sage, wyvern_rider, griffon_rider, wyvern_lord, troubadour, valkyrie, war_monk}, new int[] {3, 0, -1, -1, 0, 2, -2});
    static father henry=new father("Henry", new unit_class[] {barbarian, berserker, warrior, dark_mage, sorcerer, dark_knight, thief, assassin, trickster}, new int[] {1, 1, 2, 0, -2, 1, -1});

    //Initiate all children
    static child lucina=new child("Lucina", true, chrom);
    static child owain=new child("Owain", false, lissa);
    static child kjelle=new child("Kjelle", true, sully);
    static child laurent=new child("Laurent", false, miriel);
    static child cynthia=new child("Cynthia", true, sumia);
    static child brady=new child("Brady", false, maribelle);
    static child yarne=new child("Yarne", false, panne);
    static child severa=new child("Severa", true, cordelia);
    static child nah=new child("Nah", true, nowi);
    static child noire=new child("Noire", true, tharja);
    static child inigo=new child("Inigo", false, olivia);
    static child gerome=new child("Gerome", false, cherche);
    
    //Build the UI variables
    static JPanel main_match_panel=new JPanel(new BorderLayout());
    
    static JPanel parent_panel=new JPanel(new BorderLayout());
    
    static JPanel father_panel=new JPanel(new FlowLayout());
    static JPanel mother_panel=new JPanel(new FlowLayout());
    static JPanel child_panel=new JPanel(new FlowLayout());
    
    Matchable_Picture match_panel=new Matchable_Picture();
    
    public static parent_unit clicked_match;
    
    
    static JFrame team_frame=new JFrame();
    static JPanel team_panel=new JPanel(new BorderLayout());
    
    static JLabel team_progress_label=new JLabel("Current Team: (0/15)", SwingConstants.CENTER);
    static ArrayList<character_unit> units_on_team=new ArrayList<character_unit>();
    
    static JPanel team_list_panel=new JPanel(new GridBagLayout());
    
    static JFrame review_frame=new JFrame();
    static JPanel main_review_panel=new JPanel(new GridLayout(0,1,5,5));

    
    public static void main(){
        new main_code();
    }
    
    public main_code()
    {
        
        super();
        
        //anonymous listener for the back button
        back_button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                if (current_menu=="new or load"){
                    this_frame.current_menu="main";
                    this_frame.main_panel.removeAll();
                    this_frame.main_panel.add(open_plan_button);
                    this_frame.main_panel.add(open_review_button);
                    
                    SwingUtilities.updateComponentTreeUI(this_frame);
                    this_frame.pack();
                }
                if (current_menu=="planning"){
                    int confirm_back=JOptionPane.showConfirmDialog(this_frame, "Are you sure?\nAll unsaved progress on your current\nteam will be lost.");
                    if (confirm_back==JOptionPane.YES_OPTION){
                        for (int i=0; i<character_unit.all_units.size(); i++){
                            character_unit.all_units.get(i).clear_unit();
                        }
                        this_frame.current_menu="new or load";
                        this_frame.main_panel.removeAll();
                        this_frame.main_panel.add(new_team_button);
                        this_frame.main_panel.add(load_team_button);
                        this_frame.main_panel.add(back_button);
                        
                        SwingUtilities.updateComponentTreeUI(this_frame);
                        this_frame.pack();
                    }
                }
        }
        }
        );
        
        //anonymous listener for the new team button
        new_team_button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                current_menu="planning";
                this_frame.main_panel.removeAll();
                this_frame.main_panel.add(open_match_button);
                this_frame.main_panel.add(open_team_button);
                this_frame.main_panel.add(save_button);
                this_frame.main_panel.add(back_button);
                        
                SwingUtilities.updateComponentTreeUI(this_frame);
                this_frame.pack();
                
                match_frame.pack();
                
                update_team();
                team_frame.pack();
        }
        }
        );
        
        //anonymous listener for loading a team
        load_team_button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                JFileChooser team_selector=new JFileChooser();
                team_selector.setCurrentDirectory(new File((new File("saved_teams")).getAbsolutePath()));
                FileNameExtensionFilter file_filter = new FileNameExtensionFilter("Text files", "txt");
                team_selector.setFileFilter(file_filter);
                int file_picked=team_selector.showOpenDialog(this_frame);
                
                if (file_picked==0){
                    File file_to_load=team_selector.getSelectedFile();
                    
                    Charset text_file_charset=Charset.forName("ISO-8859-1");
                    
                    try{
                        //Units are saved in a txt as:
                        //Unit name
                        //Father or Mother or Spouse's name
                        //Class
                        //Skills
                        //Comment
                        //"true" if they are on the team, "false" otherwise.
                        //(Blank space)
                        
                        List<String> fileArray=Files.readAllLines(file_to_load.toPath(), text_file_charset);
                        
                        int character_checker_index=0;
                        character_unit unit_loading=null;
                        int character_info_line=0;
                        
                        for (int i=0; fileArray.size()>i; i++){
                            if (fileArray.get(i).compareTo("")==0){
                                unit_loading=null;
                                character_info_line=0;
                            }
                            else{
                                //Figure out who the unit being loaded in is.
                                while (unit_loading==null){
                                    if (character_unit.all_units.get(character_checker_index).name.compareTo(fileArray.get(i))==0){
                                        unit_loading=father.all_units.get(character_checker_index);
                                    }
                                    else{
                                        character_checker_index++;
                                    }
                                }
                                
                                if (character_info_line==0){
                                    unit_loading.finished=true;
                                }
                                
                                //Load Father or Mother or Spouse's name
                                if (character_info_line==1 && fileArray.get(i).compareTo("None")!=0){
                                    int family_checker_index=0;
                                    
                                    if (unit_loading.is_parent==true){
                                        while (((parent_unit)unit_loading).matched_with==null){
                                            if (((parent_unit)unit_loading).marry_options.get(family_checker_index).name.compareTo(fileArray.get(i))==0){
                                                ((parent_unit)unit_loading).matched_with=((parent_unit)unit_loading).marry_options.get(family_checker_index);
                                                ((parent_unit)unit_loading).matched_with.matched_with=(parent_unit)unit_loading;
                                                
                                                if (((parent_unit)unit_loading).parent_of!=null){
                                                    ((parent_unit)unit_loading).parent_of.add_parent2(((parent_unit)unit_loading).matched_with);
                                                }
                                                if (((parent_unit)unit_loading).matched_with.parent_of!=null){
                                                    ((parent_unit)unit_loading).matched_with.parent_of.add_parent2((parent_unit)unit_loading);
                                                }
                                            }
                                            family_checker_index++;
                                        }
                                    }
                                    if (unit_loading.is_parent!=true){
                                        while (((child)unit_loading).flexable_parent==null){
                                            if (((child)unit_loading).constant_parent.marry_options.get(family_checker_index).name.compareTo(fileArray.get(i))==0){
                                                ((child)unit_loading).constant_parent.matched_with=((child)unit_loading).constant_parent.marry_options.get(family_checker_index);
                                                ((child)unit_loading).constant_parent.matched_with.matched_with=((child)unit_loading).constant_parent;
                                                
                                                ((child)unit_loading).add_parent2(((child)unit_loading).constant_parent.matched_with);
                                                
                                                if (((child)unit_loading).constant_parent.matched_with.parent_of!=null){
                                                    ((child)unit_loading).constant_parent.matched_with.parent_of.add_parent2(((child)unit_loading).constant_parent);
                                                }
                                            }
                                            family_checker_index++;
                                        }
                                    }
                                }
                                
                                //load class
                                if (character_info_line==2){
                                    int class_checker_index=0;
                                    while (unit_loading.info_frame.classes_to_become.size()==0){
                                        if (unit_class.all_classes.get(class_checker_index).name.compareTo(fileArray.get(i))==0){
                                            unit_loading.info_frame.classes_to_become.add(unit_class.all_classes.get(class_checker_index));
                                        }
                                        class_checker_index++;
                                    }
                                }
                                
                                //load skills
                                if (character_info_line==3 && fileArray.get(i).compareTo("None")!=0){
                                    int skills_line_index=0;
                                    while (fileArray.get(i).substring(skills_line_index).contains("|")){
                                        unit_loading.info_frame.skills_to_get.add(fileArray.get(i).substring(skills_line_index).substring(0, fileArray.get(i).substring(skills_line_index).indexOf("|")));
                                        skills_line_index+=fileArray.get(i).substring(skills_line_index).indexOf("|")+1;
                                    }
                                    unit_loading.info_frame.skills_to_get.add(fileArray.get(i).substring(skills_line_index));
                                }
                                
                                //load comment
                                if (character_info_line==4 && fileArray.get(i).compareTo("None")!=0){
                                    unit_loading.finished_frame.comment_label.setText(fileArray.get(i));
                                }
                                
                                //load if they were on the team
                                if (character_info_line==5){
                                    if (fileArray.get(i).compareTo("true")==0){
                                        unit_loading.finished_frame.add_to_team_button.doClick();
                                    }
                                    
                                    unit_loading.info_frame.display_picked_classes();
                                    unit_loading.info_frame.display_picked_skills();
                                    
                                    unit_loading.info_frame.pack();
                                    unit_loading.finished_frame.update_frame();
                                }
                                
                                character_info_line++;
                            }
                        }
                        this_frame.current_menu="planning";
                        this_frame.main_panel.removeAll();
                        this_frame.main_panel.add(open_match_button);
                        this_frame.main_panel.add(open_team_button);
                        this_frame.main_panel.add(save_button);
                        this_frame.main_panel.add(back_button);
                        
                        SwingUtilities.updateComponentTreeUI(this_frame);
                        this_frame.pack();
                    }
                        
                    catch (IOException e) {
                        System.out.println(e);
                    }
                }
        }
        }
        );
        
        
        //anonymous listener for if the user opens up the planning menu
        open_plan_button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                this_frame.current_menu="new or load";
                this_frame.main_panel.removeAll();
                this_frame.main_panel.add(new_team_button);
                this_frame.main_panel.add(load_team_button);
                this_frame.main_panel.add(back_button);
                    
                SwingUtilities.updateComponentTreeUI(this_frame);
                this_frame.pack();
        }
        }
        );
        
        main_panel.add(open_plan_button);
        
        //anonymous listener for if the user opens up the review menu
        open_review_button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                if (review_frame.isVisible()==false){
                    review_frame.setVisible(true);
                }
        }
        }
        );
        
        main_panel.add(open_review_button);
        
        review_frame.setPreferredSize(new Dimension(1000, 1000));
        review_frame.pack();
        
        add(main_panel);
        
        
        //Creates everything for the matching menu
        
        //anonymous listener for if the user opens up the matching menu
        open_match_button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                if (match_frame.isVisible()==false){
                    match_frame.setVisible(true);
                }
        }
        }
        );
        
        match_frame.setPreferredSize(new Dimension(1400, 400));
        match_frame.pack();
        
        //Creates the UI for the matching menu
        for (int i=0; i<father.all_fathers.size(); i++){
            father_panel.add(father.all_fathers.get(i).buttons_panel);
        }
        
        for (int i=0; i<mother.all_mothers.size(); i++){
            mother_panel.add(mother.all_mothers.get(i).buttons_panel);
        }
        
        for (int i=0; i<child.all_children.size(); i++){
            child_panel.add(child.all_children.get(i).buttons_panel);
        }
        
        main_match_panel.add(parent_panel, BorderLayout.CENTER);
        
        parent_panel.add(father_panel, BorderLayout.NORTH);
        parent_panel.add(match_panel, BorderLayout.CENTER);
        parent_panel.add(mother_panel, BorderLayout.SOUTH);
        
        main_match_panel.add(child_panel, BorderLayout.SOUTH);
        
        match_frame.add(main_match_panel);
        
        //Enable legal marriages
        lissa.set_up_partners(new parent_unit[] {frederick, virion, vaike, stahl, kellam, lonqu, ricken, gaius, gregor, libra, henry, donnel});
        sully.set_up_partners(new parent_unit[] {chrom, frederick, virion, vaike, stahl, kellam, lonqu, ricken, gaius, gregor, libra, henry, donnel});
        miriel.set_up_partners(new parent_unit[] {frederick, virion, vaike, stahl, kellam, lonqu, ricken, gaius, gregor, libra, henry, donnel});
        sumia.set_up_partners(new parent_unit[] {chrom, frederick, gaius, henry});
        maribelle.set_up_partners(new parent_unit[] {chrom, frederick, virion, vaike, stahl, kellam, lonqu, ricken, gaius, gregor, libra, henry, donnel});
        panne.set_up_partners(new parent_unit[] {frederick, virion, vaike, stahl, kellam, lonqu, ricken, gaius, gregor, libra, henry, donnel});
        cordelia.set_up_partners(new parent_unit[] {frederick, virion, vaike, stahl, kellam, lonqu, ricken, gaius, gregor, libra, henry, donnel});
        nowi.set_up_partners(new parent_unit[] {frederick, virion, vaike, stahl, kellam, lonqu, ricken, gaius, gregor, libra, henry, donnel});
        tharja.set_up_partners(new parent_unit[] {frederick, virion, vaike, stahl, kellam, lonqu, ricken, gaius, gregor, libra, henry, donnel});
        olivia.set_up_partners(new parent_unit[] {chrom, frederick, virion, vaike, stahl, kellam, lonqu, ricken, gaius, gregor, libra, henry, donnel});
        cherche.set_up_partners(new parent_unit[] {frederick, virion, vaike, stahl, kellam, lonqu, ricken, gaius, gregor, libra, henry, donnel});
        
        //Creates the UI for the review menu
        review_frame.add(main_review_panel);
        
        for (int i=0; child.all_children.size()>i; i++){
            child.all_children.get(i).review_panel=new child_review_panel(child.all_children.get(i));
            main_review_panel.add(child.all_children.get(i).review_panel);
        }
        
        
        
        //Creates everything for the Team menu
        open_team_button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                if (team_frame.isVisible()==false){
                    team_frame.setVisible(true);
                }
        }
        }
        );
        
        
        //Listener for the save button
        save_button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                String team_save_name=JOptionPane.showInputDialog(this_frame, "Please enter your team name.");
                if (team_save_name.compareTo("")!=0 && team_save_name!=null){
                    try{
                        PrintWriter team_save_writer=new PrintWriter("saved_teams\\"+team_save_name+".txt", "UTF-8");
                        
                        
                        for (int i=0; i<character_unit.all_units.size(); i++){
                            if (character_unit.all_units.get(i).finished==true){
                                //Save Unit's name
                                team_save_writer.println(character_unit.all_units.get(i).name);
                                
                                //Save Unit's Father or Mother or Spouse
                                if (character_unit.all_units.get(i).is_parent==true){
                                    if (((parent_unit)character_unit.all_units.get(i)).matched_with!=null){
                                        team_save_writer.println(((parent_unit)character_unit.all_units.get(i)).matched_with.name);
                                    }
                                    else{
                                        team_save_writer.println("None");
                                    }
                                }
                                else{
                                    team_save_writer.println(((child)character_unit.all_units.get(i)).flexable_parent.name); //Finalized kids will always have a parent
                                }
                                
                                //Save Unit's class
                                team_save_writer.println(character_unit.all_units.get(i).info_frame.classes_to_become.get(0).name);
                                
                                //Save Unit's skills
                                if (character_unit.all_units.get(i).info_frame.skills_to_get.size()>0){
                                    String skills_text_save=character_unit.all_units.get(i).info_frame.skills_to_get.get(0);
                                    for (int ii=1; ii<character_unit.all_units.get(i).info_frame.skills_to_get.size(); ii++){
                                        skills_text_save+="|"+character_unit.all_units.get(i).info_frame.skills_to_get.get(ii);
                                    }
                                    team_save_writer.println(skills_text_save);
                                }
                                else{
                                    team_save_writer.println("None");
                                }
                                
                                //Save Unit's comment
                                if (character_unit.all_units.get(i).finished_frame.comment_label.getText()!=null && character_unit.all_units.get(i).finished_frame.comment_label.getText()!=""){
                                    team_save_writer.println(character_unit.all_units.get(i).finished_frame.comment_label.getText());
                                }
                                else{
                                    team_save_writer.println("None");
                                }
                                
                                //Save if Unit is on the team 
                                team_save_writer.println(Boolean.toString(units_on_team.contains(character_unit.all_units.get(i))));
                                
                                team_save_writer.println();
                            }
                        }
                        team_save_writer.close();
                    }
                    catch (IOException e) {
                        System.out.println(e);
                    }
                }
                
        }
        }
        );
        
        team_panel.add(team_progress_label, BorderLayout.NORTH);
        team_panel.add(team_list_panel, BorderLayout.CENTER);
        team_frame.add(team_panel);
        
        team_frame.setPreferredSize(new Dimension(400, 1000));
        team_frame.setLocation((int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth()-400, 0);
        team_frame.pack();
        
        
        setPreferredSize(new Dimension(180, 140));
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    //Updates the team panel
    public static void update_team(){
        
        team_list_panel.removeAll();
        
        GridBagConstraints panel_constants=new GridBagConstraints();
        
        for (int i=0; i<units_on_team.size(); i++){
            panel_constants.gridx=i-3*(int)(i/3);
            panel_constants.gridy=(int)(i/3);
            team_list_panel.add(units_on_team.get(i).on_team_info_button, panel_constants);
        }
        
        team_progress_label.setText("Current Team: ("+units_on_team.size()+"/15)");
        
        SwingUtilities.updateComponentTreeUI(team_frame);
        team_frame.pack();
        
    }
    
    
    //Creates the panel on the matching menu which displays who is matched with who, by drawing a line to connect them
    public class Matchable_Picture extends JPanel{
        
        public void paintComponent(Graphics graph){
            super.paintComponent(graph);
            
            for (int i=0; i<mother.all_mothers.size(); i++){
                if (mother.all_mothers.get(i).matched_with!=null){
                    
                    graph.setColor(Color.BLACK);
                    if (mother.all_mothers.get(i).parent_of.finished==true){
                        graph.setColor(Color.BLUE);
                    }
                    if (mother.all_mothers.get(i).matched_with.name=="Chrom" && main_code.lucina.finished==true){
                        graph.setColor(Color.BLUE);
                    }
                    
                    graph.drawLine(mother.all_mothers.get(i).matched_with.buttons_panel.getLocation().x+mother.all_mothers.get(i).matched_with.buttons_panel.getSize().width/2,
                    0,
                    mother.all_mothers.get(i).buttons_panel.getLocation().x+mother.all_mothers.get(i).buttons_panel.getSize().width/2, 
                    getSize().height);
                }
            }
            
            repaint();
        }
    }
}


