
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


import java.util.*;

public class main_code extends JFrame
{
    
    JPanel main_panel=new JPanel(new FlowLayout());
    
    
    JButton open_match_button=new JButton("Matching");
    JButton open_review_button=new JButton("Review");
    JButton open_match_review_button=new JButton("Both");
    
    
    
    
    JFrame match_frame=new JFrame();
    
    //Initiate all classes
    static String[] stat_names=new String[] {"HP: ", "Str: ", "Mag: ", "Skl: ", "Spd: ", "Lck: ", "Def: ", "Res: "};
    
    static int[] great_lord_f_stats=new int[] {80, 40, 30, 42, 44, 45, 40, 40};
    
    static unit_class great_lord=new unit_class("great lord", "Aether", "Attack twice consecutively, with the first strike having a Sol effect and the second strike having a Luna effect", "Rightful King", "Adds 10% to Skill activation rates", new int[] {80, 43, 30, 40, 41, 45, 42, 40});
    static unit_class lord=new unit_class("lord", "Dual Strike+", "Adds 10% to Dual Strike activation", "Charm", "Hit rate and Avoid +5 to all allies within a 3 tile radius", new int[] {60, 25, 20, 26, 28, 30, 25, 25});
    static unit_class tactician=new unit_class("tactician", "Veteran", "x1.5 experience gain when in Pair Up", "Solidarity", "Critical and Critical Avoid +10 to adjacent allies", new int[] {60, 25, 25, 25, 25, 30, 25, 25});
    static unit_class cavalier=new unit_class("cavalier", "Discipline", "Weapon Experience x2", "Outdoor Fighter", "Hit rate and Avoid +10 when fighting outdoors", new int[] {60, 26, 20, 25, 25, 30, 26, 26});
    static unit_class knight=new unit_class("knight", "Defense +2", "Defense +2", "Indoor Fighter", "Hit rate and Avoid +10 when fighting indoors", new int[] {60, 30, 20, 26, 23, 30, 30, 22});
    static unit_class myrmidon=new unit_class("myrmidon", "Avoid +10", "Avoid +10", "Vantage", "Strike first during an enemy attack when HP is under half", new int[] {60, 24, 22, 27, 28, 30, 22, 24});
    static unit_class mercenary=new unit_class("mercenary", "Armsthrift", "Weapon durability is not decreased when activated", "Patience", "Hit Rate and Avoid +10 during the enemy's Turn", new int[] {60, 26, 20, 28, 26, 30, 25, 23});
    static unit_class fighter=new unit_class("fighter", "HP +5", "Maximum HP +5", "Zeal", "Critical +5", new int[] {60, 29, 20, 26, 25, 30, 25, 23});
    static unit_class barbarian=new unit_class("barbarian", "Despoil", "Obtain a Bullion (S) from the enemy if the user defeats the enemy", "Gamble", "Hit rate -5, Critical +10", new int[] {60, 30, 20, 23, 27, 30, 22, 20});
    static unit_class archer=new unit_class("archer", "Skill +2", "Skill +2", "Prescience", "Hit rate and Avoid +15 during the user's Turn", new int[] {60, 26, 20, 29, 25, 30, 25, 21});
    static unit_class thief=new unit_class("thief", "Locktouch", "Unlock chests and doors without needing keys", "Movement +1", "Movement +1", new int[] {60, 22, 20, 30, 28, 30, 21, 20});
    static unit_class pegasus_knight=new unit_class("pegasus knight", "Speed +2", "Speed +2", "Relief", "Recover 20% HP at the start of the user's Turn if there are no units within a 3 tile radius", new int[] {60, 24, 23, 28, 27, 30, 22, 25});
    static unit_class wyvern_rider=new unit_class("wyvern rider", "Strength +2", "Strength +2", "Tantivy", "Hit rate and Avoid +10 if no allies within a 3 tile radius.", new int[] {60, 28, 20, 24, 24, 30, 28, 20});
    static unit_class mage=new unit_class("mage", "Magic +2", "Magic +2", "Focus", "Critical +10 when no allies are within a 3 tile radius", new int[] {60, 20, 28, 27, 26, 30, 21, 25});
    static unit_class dark_mage=new unit_class("dark mage", "Hex", "Avoid -15 to all adjacent enemies", "Anathema", "Avoid and Critical Avoid -10 to all enemies within a 3 tile radius.", new int[] {60, 20, 27, 25, 25, 30, 25, 27});
    static unit_class priest=new unit_class("priest", "Miracle", "Character survives with 1 HP after receiving a fatal blow. (Must have at least 2 HP or more to activate)", "Healtouch", "Restores an extra 5 HP when healing allies", new int[] {60, 22, 25, 24, 25, 30, 22, 27});
    static unit_class troubadour=new unit_class("troubadour", "Resistance +2", "Resistance +2", "Demoiselle", "Avoid and Critical Avoid +10 to all male allies within a 3 tile radius", new int[] {60, 20, 26, 24, 26, 30, 20, 28});
    static unit_class grandmaster=new unit_class("grandmaster", "Ignis", "Adds (Magic)/2 to Strength when dealing physical damage and (Strength)/2 to Magic when dealing magical damage", "Rally Spectrum", "All stats (except movement) +4 to all allies within a 3 tile radius for one Turn", new int[] {80, 40, 40, 40, 40, 45, 40, 40});
    static unit_class paladin=new unit_class("paladin", "Defender", "All Stats (except movement) +1 when in a pair up", "Aegis", "Halves damage from bows, tomes, and Dragonstones[1]", new int[] {80, 42, 30, 40, 40, 45, 42, 42});
    static unit_class great_knight=new unit_class("great knight", "Luna", "Ignores half of the enemy's Defense or Resistance", "Dual Guard+", "Adds 10% to Dual Guard rate", new int[] {80, 48, 20, 34, 37, 45, 48, 30});
    static unit_class general=new unit_class("general", "Rally Defense", "Defense +4 to all allies within a 3 tile radius for one Turn", "Pavise", "Halves damage from swords, lances, axes (including their magical variants), and Beaststones[1]", new int[] {80, 50, 30, 41, 35, 45, 50, 35});
    static unit_class swordmaster=new unit_class("swordmaster", "Astra", "Deals 5 consecutive hits at half damage.", "Swordfaire", "Strength +5 when equipped with a sword. Magic +5 when equipped with a Levin Sword", new int[] {80, 38, 34, 44, 46, 45, 33, 38});
    static unit_class hero=new unit_class("hero", "Sol", "Recover HP equal to half the damage dealt to the enemy", "Axebreaker", "Hit rate and Avoid +50 when enemy is equipped with an axe", new int[] {80, 42, 30, 46, 42, 45, 40, 36});
    static unit_class warrior=new unit_class("warrior", "Rally Strength", "Strength +4 to all allies within a 3 tile radius for one turn", "Counter", "Returns damage when attacked by an adjacent enemy (does not return damage when user is KO'd)[1]", new int[] {80, 48, 30, 42, 40, 45, 40, 35});
    static unit_class berserker=new unit_class("berserker", "Wrath", "Critical +20 when under half HP", "Axefaire", "Strength +5 when equipped with an axe. Magic +5 when equipped with a Bolt Axe", new int[] {80, 50, 30, 35, 44, 45, 34, 30});
    static unit_class sniper=new unit_class("sniper", "Hit Rate +20", "Hit Rate +20", "Bowfaire", "Strength +5 when equipped with a bow.", new int[] {80, 41, 30, 48, 40, 45, 40, 31});
    static unit_class bow_knight=new unit_class("bow knight", "Rally Skill", "Skill +4 to all allies within a 3 tile radius for one Turn", "Bowbreaker", "Hit rate and Avoid +50 when the enemy is equipped with a bow.", new int[] {80, 40, 30, 43, 41, 45, 35, 30});
    static unit_class assassin=new unit_class("assassin", "Lethality", "Instantly defeats the enemy", "Pass", "User can move through tiles occupied by an enemy unit", new int[] {80, 40, 30, 48, 46, 45, 31, 30});
    static unit_class trickster=new unit_class("trickster", "Lucky Seven", "Hit rate and Avoid +20 for the first 7 Turns of a map.", "Acrobat", "All traversable terrain costs 1 movement point to cross", new int[] {80, 35, 38, 45, 43, 45, 30, 40});
    static unit_class falcon_knight=new unit_class("falcon knight", "Rally Speed", "Speed +4 to all allies within a 3 tile radius for one Turn", "Lancefaire", "Strength +5 when equipped with a lance. Magic +5 when equipped with a Shockstick", new int[] {80, 38, 35, 45, 44, 45, 33, 40});
    static unit_class dark_flier=new unit_class("dark flier", "Rally Movement", "Movement +1 to all allies within a 3 tile radius for one Turn", "Galeforce", "Allows the user another full action after they defeat an enemy during the user's Turn. Activates only once per Turn.", new int[] {80, 36, 42, 41, 42, 45, 32, 41});
    static unit_class wyvern_lord=new unit_class("wyvern lord", "Quick Burn", "Hit rate and Avoid +15 at the start of a chapter. Effect decreases by 1 with each passing turn.", "Swordbreaker", "Hit rate and Avoid +50 when the enemy is equipped with a sword", new int[] {80, 46, 30, 38, 38, 45, 46, 30});
    static unit_class griffon_rider=new unit_class("griffon rider", "Deliverer", "Movement +2 when in a Pair Up", "Lancebreaker", "Hit rate and Avoid +50 when the enemy is equipped with a lance", new int[] {80, 40, 30, 43, 41, 45, 40, 30});
    static unit_class sage=new unit_class("sage", "Rally Magic", "Magic +4 to all alies within a 3 tile radius for one Turn", "Tomefaire", "Magic +5 when equipped with a tome", new int[] {80, 30, 46, 43, 42, 45, 31, 40});
    static unit_class sorcerer=new unit_class("sorcerer", "Vengeance", "Adds half of the user's current accrued damage to user's attack", "Tomebreaker", "Hit rate and Avoid +50 when enemy is equipped with a tome", new int[] {80, 30, 44, 38, 40, 45, 41, 44});
    static unit_class dark_knight=new unit_class("dark knight", "Slow Burn", "Hit rate and Avoid increases by 1 each Turn up to the 15th turn", "Lifetaker", "User recovers 50% Max HP after they defeat an enemy during the user's Turn.", new int[] {80, 38, 41, 40, 40, 45, 42, 38});
    static unit_class war_monk=new unit_class("war monk", "Rally Luck", "Luck +8 to all allies within a 3 tile radius for one Turn", "Renewal", "Recover 30% HP at the start of the user's Turn", new int[] {80, 40, 40, 38, 41, 45, 38, 43});
    static unit_class valkyrie=new unit_class("valkyrie", "Rally Resistance", "Resistance +4 to all allies within a 3 tile radius for one Turn", "Dual Support+", "Increases the Dual Support level of a unit by 4", new int[] {80, 30, 42, 38, 43, 45, 30, 45});
    static unit_class villager=new unit_class("villager", "Aptitude", "Adds 20% to all growth rates during Level Ups", "Underdog", "Hit rate and Avoid +15 when user's Level is lower than the enemy (promoted units count as Level +20)    ", new int[] {60, 20, 20, 20, 20, 30, 20, 20});
    static unit_class dancer=new unit_class("dancer", "Luck +4", "Luck +4", "Special Dance", "Strength, Magic, Defence and Resistance +2 for one Turn for the unit who receives the user's Dance", new int[] {80, 30, 30, 40, 40, 45, 30, 30});
    static unit_class taguel=new unit_class("taguel", "Even Rhythm", "Hit rate and Avoid +10 during even numbered Turns", "Beastbane", "Deals effective damage to Beast (beast *3) units when user is a Taguel", new int[] {80, 35, 30, 40, 40, 45, 35, 30});
    static unit_class manakete=new unit_class("manakete", "Odd Rhythm", "Hit rate and Avoid +10 during odd numbered Turns", "Wyrmsbane", "Deals effective damage to Dragon (dragon) units when user is a Manakete", new int[] {80, 40, 35, 35, 35, 45, 40, 40});
    
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
    child lucina=new child("Lucina", true, chrom);
    child owain=new child("Owain", false, lissa);
    child kjelle=new child("Kjelle", true, sully);
    child laurent=new child("Laurent", false, miriel);
    child cynthia=new child("Cynthia", true, sumia);
    child brady=new child("Brady", false, maribelle);
    child yarne=new child("Yarne", false, panne);
    child severa=new child("Severa", true, cordelia);
    child nah=new child("Nah", true, nowi);
    child noire=new child("Noire", true, tharja);
    child inigo=new child("Inigo", false, olivia);
    child gerome=new child("Gerome", false, cherche);
    
    //Build the UI variables
    JPanel main_match_panel=new JPanel(new BorderLayout());
    
    JPanel parent_panel=new JPanel(new BorderLayout());
    
    JPanel father_panel=new JPanel(new FlowLayout());
    JPanel mother_panel=new JPanel(new FlowLayout());
    JPanel child_panel=new JPanel(new FlowLayout());
    
    Matchable_Picture match_panel=new Matchable_Picture();
    
    public static parent_unit clicked_match;
    
    JFrame review_frame=new JFrame();
    JPanel main_review_panel=new JPanel(new GridLayout(0,1,5,5));

    
    
    
    public main_code()
    {
        
        super();
        
        //anonymous listener for if the user opens up the matching menu
        open_match_button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                if (match_frame.isVisible()==false){
                    match_frame.setVisible(true);
                }
        }
        }
        );
        
        main_panel.add(open_match_button);
        
        match_frame.setPreferredSize(new Dimension(1400, 280));
        match_frame.pack();
        
        
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
        
        
        //anonymous listener for if the user opens up the matching and review menu
        open_match_review_button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                if (match_frame.isVisible()==false || review_frame.isVisible()==false){
                    match_frame.setVisible(true);
                    review_frame.setVisible(true);
                }
        }
        }
        );
        
        main_panel.add(open_match_review_button);
        
        
        
        add(main_panel);
        
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
        
        
        setPreferredSize(new Dimension(200, 200));
        
        
        pack();
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    
    //Creates the panel on the matching menu which displays who is matched with who, by drawing a line to connect them
    public class Matchable_Picture extends JPanel{
        
        public void paintComponent(Graphics graph){
            super.paintComponent(graph);
            graph.setColor(Color.BLACK);
            
            for (int i=0; i<mother.all_mothers.size(); i++){
                if (mother.all_mothers.get(i).matched_with!=null){
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


