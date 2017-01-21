
public class unit_class
{
    //A class. Each class has a name, 2 skills, and 9 stats.
    
    
    String name;
    
    String skill1;
    String skill1_info;
    
    String skill2;
    String skill2_info;
    
    int[] stats;
    String stats_text;
    
    //Initiates unit_class()
    //Takes as peramaters: a String for what the unit_class is named, a String for what the unit_class's first skill is, 
    //a String for what the unit_class's first skill does, a String for what the unit_class's second skill is, a String
    //for what the unit_class's second skill does, , and an array of ints, which refer to what this unit_class's stats
    //are.
    public unit_class(String name, String skill1, String skill1_info, String skill2, String skill2_info, int[] stats)
    {
        this.name=name;
        
        this.skill1=skill1;
        this.skill1_info=skill1_info;
        this.skill2=skill2;
        this.skill2_info=skill2_info;
        this.stats=stats;
        
        stats_text="HP: "+stats[0]+"Str: "+stats[1]+"Mag: "+stats[2]+"Skl: "+stats[3]+"Spd: "+stats[4]+"Lck: "+stats[5]+"Def: "+stats[6]+"Res: "+stats[7]+"Mov: "+stats[8];
    }
}