import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.model.NPC;
import org.osbot.rs07.api.ui.Skill;
import org.osbot.rs07.api.ui.Tab;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.script.ScriptManifest;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.awt.*;
import java.util.HashSet;
import java.util.List;

@ScriptManifest(name = "GoblinsKillerInfinite", author = "dokato", version = 1.1, info = "", logo = "") 
public class GoblinsKiller extends Script { 
	
	private Random rnd = new Random();
	
	private boolean startb = true;

    private long timeRan;
    private long timeBegan;
	private long timeBotted;
	private long timeOffline;
	private String status;
	
	int[] items = {1934,1945,1928};
	
	private static final Area GOBLIN_AREA = new Area(3198,3233,3169,3252);
	private static final Area GE = new Area(3158,3495,3171,3483);
	private int checkStr;
	private int checkDef;
	private HashSet<NPC> npcSet;
	private Filer filer;
	
	@Override
    public void onStart(){
		checkStr = getRndStat();
		checkDef = getRndStat();
		npcSet = new HashSet<NPC>(); 
		filer = new Filer();
    }
	
    public int onLoop() throws InterruptedException{
    	status="loop started";
    	if(getWidgets().isVisible(153, 88) && getWidgets().isVisible(153, 106))
    		getMouse().click(random(193,327),random(310,319),false);
    	procedures();
		wieldScim();
		status="have to train";
		if(GOBLIN_AREA.contains(myPlayer())){
			status="In goblin area";
			switchStyles();
			killMonsters();
		}else{
			status="need to go to goblin area";
			goToGoblinArea();
		}
    	return 0;
    }
    
    @Override
    public void onPaint(Graphics2D g1){
    	
    	if(this.startb){
    		this.startb=false;
    		this.timeBegan=System.currentTimeMillis();
    	}
    	this.timeRan = (System.currentTimeMillis() - this.timeBegan);
		if (getClient().isLoggedIn()) {
			this.timeBotted = (this.timeRan - this.timeOffline);
		} else {
			this.timeOffline = (this.timeRan - this.timeBotted);
		}
		
		Graphics2D g = g1;
		
		g.setFont(new Font("Arial", 0, 13));
		g.setColor(new Color(255, 255, 255));
		g.drawString("Version: " + getVersion(), 20, 50);
		g.drawString("Runtime: " + ft(this.timeRan), 20, 65);
		g.drawString("Time botted: " + ft(this.timeBotted), 20, 80);
		g.drawString("Status: " + this.status, 20, 95);
		
		g.drawString("Attk: " + getSkills().getStatic(Skill.ATTACK), 20,115 );
		g.drawString("" + getSkills().experienceToLevel(Skill.ATTACK), 20,130 );
		
	    g.drawString("Str: " + getSkills().getStatic(Skill.STRENGTH), 20, 160);
	    g.drawString("" + getSkills().experienceToLevel(Skill.STRENGTH), 20, 175);
	    
	    
	    g.drawString("Def: " + getSkills().getStatic(Skill.DEFENCE), 20, 205);
	    g.drawString("" + getSkills().experienceToLevel(Skill.DEFENCE), 20, 220);
	    
	    g.drawString("Hp: " + getSkills().getStatic(Skill.HITPOINTS), 20, 250);
	    g.drawString("" + getSkills().experienceToLevel(Skill.HITPOINTS), 20, 265);
	    
	    g.drawString("Van, door en voor joden", 20, 295);
    }
    
    
    private void goToGE(){
    	status="going to the ge";
    	if(getMap().canReach(new Position(3165,3486,0))){
    		getWalking().walk(new Area(3165,3486,3163,3484));
    	}
    	else if(getMap().canReach(new Position(3165,3478,0)))
    		getWalking().walk(new Area(3165,3478,3163,3476));
    	else if(getMap().canReach(new Position(3165,3469,0)))
    		getWalking().walk(new Area(3164,3466,3165,3469));
    	else if(getMap().canReach(new Position(3172,3460,0)))
    		getWalking().walk(new Area(3172,3460,3174,3457));
    	else if(getMap().canReach(new Position(3180,3455,0)))
    		getWalking().walk(new Area(3182,3453,3180,3455));
    	else if(getMap().canReach(new Position(3191,3488,0)))
    		getWalking().walk(new Area(3191,3488,3193,3446));
    	else if(getMap().canReach(new Position(3196,3442,0)))
    		getWalking().walk(new Area(3196,3442,3199,3440));
    	else if(getMap().canReach(new Position(3199,3434,0)))
    		getWalking().walk(new Area(3199,3434,3198,3431));
    	else if(getMap().canReach(new Position(3207,3428,0)))
    		getWalking().walk(new Area(3209,3426,3207,3428));
    	else if(getMap().canReach(new Position(3211,3418,0)))
    		getWalking().walk(new Area(3211,3418,3210,3415));
    	else if(getMap().canReach(new Position(3210,3408,0)))
    		getWalking().walk(new Area(3210,3408,3212,3405));
    	else if(getMap().canReach(new Position(3212,3398,0)))
    		getWalking().walk(new Area(3212,3398,3211,3395));
    	else if(getMap().canReach(new Position(3211,3389,0)))
    		getWalking().walk(new Area(3211,3389,3212,3386));
    	else if(getMap().canReach(new Position(3209,3378,0)))
    		getWalking().walk(new Area(3207,3376,3209,3378));
    	else if(getMap().canReach(new Position(3211,3366,0)))
    		getWalking().walk(new Area(3211,3366,3212,3363));
    	else if(getMap().canReach(new Position(3208,3357,0)))
    		getWalking().walk(new Area(3208,3357,3209,3354));
    	else if(getMap().canReach(new Position(3204,3347,0)))
    		getWalking().walk(new Area(3204,3347,3205,3344));
    	else if(getMap().canReach(new Position(3207,3338,0)))
    		getWalking().walk(new Area(3207,3338,3209,3334));
    	else if(getMap().canReach(new Position(3212,3329,0)))
    		getWalking().walk(new Area(3212,3329,3215,3326));
    	else if(getMap().canReach(new Position(3220,3320,0)))
    		getWalking().walk(new Area(3220,3320,3222,3317));
    	else if(getMap().canReach(new Position(3223,3313,0)))
    		getWalking().walk(new Area(3225,3310,3223,3313));
    	else if(getMap().canReach(new Position(3229,3308,0)))
    		getWalking().walk(new Area(3229,3308,3232,3307));
    	else if(getMap().canReach(new Position(3236,3306,0)))
    		getWalking().walk(new Area(3238,3303,3236,3306));
    	else if(getMap().canReach(new Position(3238,3297,0)))
    		getWalking().walk(new Area(3238,3297,3238,3294));
    	else if(getMap().canReach(new Position(3239,3288,0)))
    		getWalking().walk(new Area(3239,3288,3238,3287));
    	else if(getMap().canReach(new Position(3241,3276,0)))
    		getWalking().walk(new Area(3241,3276,3242,3272));
    	else if(getMap().canReach(new Position(3241,3266,0)))
    		getWalking().walk(new Area(3241,3266,3242,3263));
    	else if(getMap().canReach(new Position(3234,3261,0)))
    		getWalking().walk(new Area(3234,3261,3232,3262));
    	else if(getMap().canReach(new Position(3224,3262,0)))
    		getWalking().walk(new Area(3224,3262,3222,3261));
    	else if(getMap().canReach(new Position(3216,3258,0)))
    		getWalking().walk(new Area(3216,3258,3214,3255));
    	else if(getMap().canReach(new Position(3210,3254,0)))
    		getWalking().walk(new Area(3210,3254,3208,3253));
    	else if(getMap().canReach(new Position(3201,3248,0)))
    		getWalking().walk(new Area(3201,3248,3199,3247));
    }
    
    private void wieldScim() throws InterruptedException{
    	if(getLvl(Skill.ATTACK) >= 30 && getInventory().contains("Adamant scimitar")){
    		getInventory().getItem("Adamant scimitar").interact("Wield");
    		sleep(random(200,450));
    	}else if(getLvl(Skill.ATTACK) < 30 && getInventory().contains("Iron scimitar")){
    		getInventory().getItem("Iron scimitar").interact("Wield");
    		sleep(random(200,450));
    	}
    }
    
    private void switchStyles(){ 
    	switch(getRightStyle()){
    	case "def":
    		changeFightingStyle(3);
    		break;
    	case "str":
    		changeFightingStyle(1);
    		break;
    	case "attk":
    		changeFightingStyle(0);
    		break;
    	}
    }
    
    private String getRightStyle(){
    	int attk = getLvl(Skill.ATTACK);
    	int str = getLvl(Skill.STRENGTH);
    	int def = getLvl(Skill.DEFENCE);
    	if(((attk>=40 && str>=checkStr && def<checkDef)
    			|| 
    			(attk>=20 && str>=20 && def<20)
    			||
    			(attk>=15 && str>=15 && def<15)
    			) && getFightingStyleId()!=3){ 
    		status="returned def";
    		return "def";
    	}else if(((attk>=40 && str<checkStr)
    			|| 
    			(attk>=30 && str<30)
    			||
    			(attk>=20 && str<20)
    			||
    			(attk>=15 && str<15)
    			||
    			(attk>=10 && str<10)
    			||
    			(attk>=5 && str<5)
    			) && getFightingStyleId()!=1){
    		status="returned str";
    		return "str";
    	}else if(((str>=checkStr && attk<40)
    			|| 
    			(str>=20 && attk<=20 && def>=20)
    			||
    			(str>=15 && attk<=15 && def>=15)
    			||
    			(str>=10 && attk<=10)
    			||
    			(str>=5 && attk<=5)
    			) && getFightingStyleId()!=0){
    		status="returned attk";
    		return "attk";
    	}else 
    		return "good";
    }
    
    public int getFightingStyleId()
    {
    	return configs.get(43);
    }
    	
    public void changeFightingStyle(int id)
    {
    	if (id == getFightingStyleId())
    		return;
    	Tab currentTab = tabs.getOpen();
    	if (currentTab != Tab.ATTACK)
    		tabs.open(Tab.ATTACK);
    	switch (id)
    	{
    	case 0:
    		widgets.get(593, 3).interact();
    		break;
    	case 1:
    		widgets.get(593, 7).interact();
    		break;
    	case 2:
    		widgets.get(593, 11).interact();
    		break;
    	case 3:
    		widgets.get(593, 15).interact();
    		break;
    	}
    	if (currentTab != Tab.ATTACK)
    		tabs.open(currentTab);
    }
    
    private int getLvl(Skill skill){
    	return getSkills().getStatic(skill);
    }
    
    private void goToGoblinArea(){
    	if(((!myPlayer().isUnderAttack()&&!myPlayer().isAnimating())&&!getCombat().isFighting())&&!myPlayer().isMoving()){
	    	if(myPlayer().getPosition().getX() >= 3186 && myPlayer().getPosition().getY() <= 3250){
	    		status="from the castle";
		    	if(getMap().canReach(new Position(3194,3238,0)))
		    		getWalking().walk(new Area(3194,3238,3196,3241));
		    	else if(getMap().canReach(new Position(3198,3234,0)))
		    		getWalking().walk(new Area(3197,3235,3199,3233));
		    	else if(getMap().canReach(new Position(3199,3228,0)))
		    		getWalking().walk(new Area(3198,3228,3200,3225));
		    	else if(getMap().canReach(new Position(3198,3222,0)))
		    		getWalking().walk(new Area(3198,3222,3197,3225));
		    	else if(getMap().canReach(new Position(3202,3218,0)))
		    		getWalking().walk(new Area(3202,3219,3203,3216));
		    	else if(getMap().canReach(new Position(3209,3210,0)))
		    		getWalking().walk(new Area(3208,3209,3210,3211));
		    	else if(getMap().canReach(new Position(3215,3214,0)))
		    		getWalking().walk(new Area(3213,3212,3216,3217));
	    	}else if(myPlayer().getPosition().getX() <= 3170){
	    		status="too far west";
	    		if(getMap().canReach(new Position(3174,3241,0)))
		    		getWalking().walk(new Area(3174,3241,3178,3239));
		    	else if(getMap().canReach(new Position(3166,3237,0)))
		    		getWalking().walk(new Area(3166,3237,3170,3235));
	    	}else if(myPlayer().getPosition().getY() >= 3250){
	    		status="too far north";
	    		if(getMap().canReach(new Position(3182,3251,0)))
		    		getWalking().walk(new Area(3182,3251,3184,3247));
		    	else if(getMap().canReach(new Position(3183,3262,0)))
		    		getWalking().walk(new Area(3183,3262,3182,3259));
	    	}else if(myPlayer().getPosition().getY() <= 3236){
	    		status="too far south";
	    		if(getMap().canReach(new Position(3177,3239,0)))
		    		getWalking().walk(new Area(3177,3239,3176,3241));
	    	}
    	}
    }
    
    /*
     * goblin ids: 3036, 3033, 3034, 3030, 3031, 1276, 3017
     */
    private void killMonsters() throws InterruptedException{
    	if(((!myPlayer().isUnderAttack()&&!myPlayer().isAnimating())&&!getCombat().isFighting())&&!myPlayer().isMoving()){
			status="about to get npcList";
			List<NPC> npcsList = getNpcs().getAll();
			status="just getted npcList";
			for(NPC npc : npcsList){
				status="just entered for loop";
				if(isTarget(npc)){
					status="it is a target";
					if(((!npc.isUnderAttack()&&npc.isAttackable())&&getMap().canReach(npc.getPosition()))&&npc.getHealthPercent()!=0){
						status="about to add to npcSet";
						npcSet.add(npc);
						status="just added to npcSet";
					}
				}
			}
			NPC monster = npcs.closest(npcSet);
			if(!monster.isAnimating() || monster.isMoving()){
			status="about to attack monster";
				monster.interact("Attack");
				sleep(random(300,600));
			}
		}
		status="about to clear the npcSet";
		npcSet.clear();
		status="just cleared the npcSet";
    }
    
    private boolean isTarget(NPC npc){
    	status="checking the target";
    	return npc.getId()==3036 
    			|| npc.getId()==3033 
    			|| npc.getId()==3034 
    			|| npc.getId()==3030 
    			|| npc.getId()==3031
    			|| npc.getId()==1276 
    			|| npc.getId()==3017 ;
    }
    
    private boolean hasToTrain(){
    	return !isCombatStatsOk();
    }
    
    private boolean isCombatStatsOk(){
    	return getLvl(Skill.ATTACK) == 40 && 
    			getLvl(Skill.STRENGTH) >= checkStr &&
    			getLvl(Skill.DEFENCE) >= checkDef;
    }
    
    private int getRndStat(){
    	int arnd;
    	do{
    		arnd = rnd.nextInt(38)+1;
    	}while(arnd<36);
    	return arnd;
    } 
    
    private void procedures() throws InterruptedException{
		if(getInventory().isItemSelected()){
			getInventory().deselectItem();
			sleep(random(200,400));
		}
		if(getSettings().getRunEnergy()>random(7,14)){
			getSettings().setRunning(true);
			sleep(random(200,400));
		}
	}
   
	private String ft(long duration) {
		String res = "";
		long days = TimeUnit.MILLISECONDS.toDays(duration);
		long hours = TimeUnit.MILLISECONDS.toHours(duration)
				- TimeUnit.DAYS.toHours(TimeUnit.MILLISECONDS.toDays(duration));
		long minutes = TimeUnit.MILLISECONDS.toMinutes(duration)
				- TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS
						.toHours(duration));
		long seconds = TimeUnit.MILLISECONDS.toSeconds(duration)
				- TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS
						.toMinutes(duration));
		if (days == 0L) {
			res = hours + ":" + minutes + ":" + seconds;
		} else {
			res = days + ":" + hours + ":" + minutes + ":" + seconds;
		}
		return res;
	}
}