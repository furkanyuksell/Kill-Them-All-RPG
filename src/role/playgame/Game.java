package role.playgame;


import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class Game extends JPanel implements KeyListener, Runnable {
    
    //Hit Animation
    HitAnim hit;
    //CoolDown Drawing
    Cooldowns cooldown;
    Animator cooldownAnim,cooldownControl,cooldownAnimShock;
    int cdStart = 0, cdStartShock = 0;
    
    //Walked Control
    int walked = 0;
    
    //Boss
    bossOne bossOne;
    bossOneAttack bossOneAttack;
    bossTwo bossTwo;
    bossTwoAttack bossTwoAttack;
    
    //SkillControls 
    int oneTick = 1;
    int oneTickShock = 1;
    
    //Optimize and Skill Directions
    private Thread thread;
    private boolean optimization = false;
    int imgCount = 0;
    int spellXRight = 0;
    int spellXLeft = 0;
    int spellYUp = 0;
    int spellYDown = 0;
    int coolDownTimeFire = 0;
    int coolDownTimeShock = 0;
    
    //Collision
    Collision fakePlayer;
    Collision fakeSkill;
    Collision fakeSpider;
    Collision fakeWeb;
    Collision fakeBone;
    Collision fakeSkeleton;
    Collision[] assets;
    boolean collision = false;
    
    //PlayerAnimate
    Animator playerAnimDown,playerAnimUp,playerAnimLeft,playerAnimRight,playerAnimCont, 
            playerSkillAnimR, playerSkillAnimL, playerSkillAnimD, playerSkillAnimU;
    
    //SkillAnimate
    SkillAnimator skillContFire, skillRightFire, skillLeftFire, skillUpFire, skillDownFire;
    SkillAnimatorShock skillContShock, skillRightShock, skillLeftShock, skillUpShock, skillDownShock;
    
    //BossSpiderAnimate
    int bossSpawn = 0;
    int stopPosX = 0, stopPosY = 0;
    int spiderAttackPosX = 0, spiderAttackPosY = 0;
    int spiderAttackRange = 0;
    int spiderAttackStartXRight = 0;
    int spiderAttackStartXLeft = 0;
    int spiderAttackStartYUp = 0;
    int spiderAttackStartYDown = 0;
    int webWay = 0;
    int spiderOneShot = 0;
    boolean spiderWayEquals = true;
    boolean drawSpider = true;
    boolean spawnStart = false;
    
    //BossSkeletonAnimate
    int stopPosXskeleton = 0, stopPosYskeleton = 0;
    int bossSpawnSkeleton = 0;
    int skeletonAttackPosX = 0, skeletonAttackPosY = 0;
    int skeletonAttackRange = 0;
    int skeletonAttackStartXRight = 0;
    int skeletonAttackStartXLeft = 0;
    int skeletonAttackStartYUp = 0;
    int skeletonAttackStartYDown = 0;
    int skeletonOneShot = 0;
    boolean skeletonWayEquals = true;
    boolean spawnStartSkeleton = false;
    boolean drawSkeleton = true;
    
    //Skills
    int lastKey;
    int spellX = 0, spellY = 0;
    int code;
    int codeFire;
    int codeShock;
    int skillCodeFire;
    int skillCodeShock;
    int skillEnd = 0;
    int damage = 0;
    int damageSkeleton = 0;
    boolean damageCont = false;
    boolean running = true, runningSkill = true;
    
    //PlayerHealDead
    int heal = 0;
    int stopForDead = 0;
    int dead = 0;
    int playerSpawn = 0;
    boolean healCont = false;
    boolean justOnce = true;
    boolean playerSpawnStart = false;
    
    //actionPerformed
    
    //Map
    private Map m;
    private Player p;
    private Skill skill;
    
    //Coordinate
    int pX = 0, pY = 0, pvelX = 0, pvelY = 0;
    int startX = 300, startY = 300;
    
    public Game(){
        
        try {
            music();
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        cooldown = new Cooldowns();
        bossOne = new bossOne();
        bossOneAttack = new bossOneAttack();
        bossTwo = new bossTwo();
        bossTwoAttack = new bossTwoAttack();
        hit = new HitAnim();
        start();
        m = new Map();
        p = new Player();
        skill = new Skill();
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        
    }
   
    public void paint(Graphics g){
        super.paint(g);
        
        //GRASS MAP
        for (int y = 0; y < 24; y++) {
            for (int x = 0; x < 24; x++) {
                if (m.getMap(x, y).equals("g") || m.getMap(x, y).equals("w") ) {
                   g.drawImage(m.getGrass1(), x * 96, y * 32, null);
                }
                
                
            }
        }
        //WALL MAP
        for (int z = 0; z < 24; z++) {
            for (int k = 0; k < 24; k++) {
                if (m.getMap(z, k).equals("w")) {            
                    g.drawImage(m.getWall(), z*64, k*32,null);
                }
            }
        }
        
        assets = new Collision[7];
        //WallLimit
        assets[3] = new Collision(0, 0, 64, 900); 
        assets[4] = new Collision(0, 735, 1900, 64); 
        assets[5] = new Collision(0, 0, 1900, 64);
        assets[6] = new Collision(960,0,64,1900);
        
        //Tree
        assets[1] = new Collision(550, 45, 95, 75);
        g.drawImage(m.getTree(), 550, 45, null);
        
        //Home && Door
        assets[0] = new Collision(64, 54, 94, 124); 
        g.drawImage(m.getHome(), 64, 53, null);
        g.drawImage(m.getDoor(), 93, 143, null);

        //Water
        assets[2] = new Collision(70, 630, 92, 86);
        g.drawImage(m.getWater(), 70, 630, null);
        //WaterFall
        g.drawImage(m.river, 864, 64, null);
        g.drawImage(m.river, 770, 64, null);
        g.drawImage(m.river, 770, 95, null);
        g.drawImage(m.river, 864, 95, null);
        g.drawImage(m.river, 770, 126, null);
        g.drawImage(m.river, 864, 126, null);
        g.drawImage(m.bush, 765, 58, null);
        g.drawImage(m.bush1, 790, 140, null);
        g.drawImage(m.bush1, 820, 140, null);
        g.drawImage(m.bush1, 850, 140, null);
        g.drawImage(m.bush1, 880, 140, null);
        g.drawImage(m.bush1, 910, 140, null);
        g.drawImage(m.bush1, 930, 140, null);
        g.drawImage(m.bigWall, 850, -30, null);
        g.drawImage(m.stoneFall, 810, 20, null);
        g.drawImage(m.stoneFall2, 930, 20, null);
        
        g.drawImage(m.waterFall, 850, 0,null);
        //SpiderDraw
        if (drawSpider == true) {
            bossOne.drawSpider(g);
            fakeSpider = new Collision((int) bossOne.posX, (int) bossOne.posY, bossOne.width, bossOne.height);
            //fakeSpider.drawImg(g);
        }
        //SkeletonDraw
        if (drawSkeleton == true) {
            bossTwo.drawSkeleton(g);
            fakeSkeleton = new Collision((int) bossTwo.posX, (int) bossTwo.posY, bossTwo.width, bossTwo.height);
            //fakeSkeleton.drawImg(g);
        }
        
        //SPIDER DEAD
        if (damage == 0) {
            g.drawImage(bossOne.health1, (int) bossOne.posX-12, (int)bossOne.posY-18, null);
        }
        else if (damage == 1) {
            g.drawImage(bossOne.health2, (int) bossOne.posX-12, (int)bossOne.posY-18, null);
        }
        else if (damage == 2) {
            g.drawImage(bossOne.health3, (int) bossOne.posX-12, (int)bossOne.posY-18, null);
            stopPosX = (int) bossOne.posX;
            stopPosY = (int) bossOne.posY;
        }
        else if (damage == 3) {
            g.drawImage(bossOne.health4, stopPosX-18, stopPosY-18, null);
            drawSpider = false;
            spawnStart = true;
            if (bossOne.deadPosition == 1) {
                g.drawImage(bossOne.deadSpiderD, stopPosX, stopPosY, null);
            }
            else if (bossOne.deadPosition == 2) {
                g.drawImage(bossOne.deadSpiderU, stopPosX, stopPosY, null);
            }
            else if (bossOne.deadPosition == 3) {
                g.drawImage(bossOne.deadSpiderR, stopPosX, stopPosY, null);
            }
            else if (bossOne.deadPosition == 4) {
                g.drawImage(bossOne.deadSpiderL, stopPosX, stopPosY, null);
            }
        }
        //SPIDER ATTACK
        if (damage == 1 || damage == 2) {
            if (spiderOneShot == 0) {
                fakeWeb = new Collision(spiderAttackPosX+10, spiderAttackPosY+10, 10, 10);
                //fakeWeb.drawImg(g);
                if (spiderWayEquals == true) {
                    spiderAttackPosX = (int) bossOne.posX;
                    spiderAttackPosY = (int) bossOne.posY;
                    spiderWayEquals = false;
                    bossOneAttack.posX = (int) bossOne.posX;
                    bossOneAttack.posY = (int) bossOne.posY;
                    healCont = true;
                }
                if (webWay == 1) {
                    //Right
                    spiderAttackStartXLeft = 1;
                    bossOneAttack.attackLeftStart = 1;
                    g.drawImage(bossOne.spiderWebLeftRight, spiderAttackPosX, spiderAttackPosY, null);
                    
                    fakeWeb.skillTemp(-1, 0);
                    if (fakeWeb.skillTemp.intersects(fakePlayer.collision)) {
                        if (healCont == true) {
                            System.err.println("SpiderAttack");
                            heal++;
                            healCont = false;
                        }
                    }
                    bossOneAttack.drawSpiderAttack(g);
                }
                else if (webWay == 2) {
                    //Left
                    spiderAttackStartXRight = 1;
                    bossOneAttack.attackRightStart = 1;
                    g.drawImage(bossOne.spiderWebLeftRight, spiderAttackPosX, spiderAttackPosY, null);
                    
                    fakeWeb.skillTemp(+1, 0);
                    if (fakeWeb.skillTemp.intersects(fakePlayer.collision)) {
                        if (healCont == true) {
                            System.err.println("SpiderAttack");
                            heal++;
                            healCont = false;
                        }
                    }
                    bossOneAttack.drawSpiderAttack(g);
                }
                else if (webWay == 3) {
                    //Down
                    spiderAttackStartYUp = 1;
                    bossOneAttack.attackUpStart = 1;
                    g.drawImage(bossOne.spiderWebUpDown, spiderAttackPosX, spiderAttackPosY, null);
                    
                    fakeWeb.skillTemp(0, -1);
                    if (fakeWeb.skillTemp.intersects(fakePlayer.collision)) {
                        if (healCont == true) {
                            System.err.println("SpiderAttack");
                            heal++;
                            healCont = false;
                        }
                    }
                    bossOneAttack.drawSpiderAttack(g);
                }
                else if (webWay == 4) {
                    //Up
                    spiderAttackStartYDown = 1;
                    bossOneAttack.attackDownStart = 1;
                    g.drawImage(bossOne.spiderWebUpDown, spiderAttackPosX, spiderAttackPosY, null);
                    
                    fakeWeb.skillTemp(0, +1);
                    if (fakeWeb.skillTemp.intersects(fakePlayer.collision)) {
                        if (healCont == true) {
                            System.err.println("SpiderAttack");
                            heal++;
                            healCont = false;
                        }
                    }
                    bossOneAttack.drawSpiderAttack(g);
                }
                bossOne.posX = bossOneAttack.posX;
                bossOne.posY = bossOneAttack.posY;
                drawSpider = false;
            }
        }
        
        //Skeleton Dead
        if (damageSkeleton == 0) {
            g.drawImage(bossTwo.health1, (int) bossTwo.posX-17, (int)bossTwo.posY-18, null);
        }
        else if (damageSkeleton == 1) {
            g.drawImage(bossTwo.health2, (int) bossTwo.posX-17, (int)bossTwo.posY-18, null);
        }
        else if (damageSkeleton == 2) {
            g.drawImage(bossTwo.health3, (int) bossTwo.posX-17, (int)bossTwo.posY-18, null);
            stopPosXskeleton = (int) bossTwo.posX;
            stopPosYskeleton = (int) bossTwo.posY;
        }
        else if (damageSkeleton == 3) {
            g.drawImage(bossTwo.health4, stopPosXskeleton+5, stopPosYskeleton-18, null);
            drawSkeleton = false;
            spawnStartSkeleton = true;
            g.drawImage(bossTwo.deadSkeleton, stopPosXskeleton, stopPosYskeleton, null);
        }
        //SKELETON ATTACK
        if (damageSkeleton == 1 || damageSkeleton == 2) {
            if (skeletonOneShot == 0) {
                fakeBone = new Collision(skeletonAttackPosX+10, skeletonAttackPosY+10, 10, 15);
                //fakeBone.drawImg(g);
                if (skeletonWayEquals == true) {
                    skeletonAttackPosX = (int) bossTwo.posX;
                    skeletonAttackPosY = (int) bossTwo.posY;
                    skeletonWayEquals = false;
                    bossTwoAttack.posX = (int) bossTwo.posX;
                    bossTwoAttack.posY = (int) bossTwo.posY;
                    healCont = true;
                }
                if (webWay == 1) {
                    //Right
                    skeletonAttackStartXLeft = 1;
                    bossTwoAttack.attackLeftStart = 1;
                    g.drawImage(bossTwo.skeletonBoneLeftRight, skeletonAttackPosX, skeletonAttackPosY, null);
                    
                    fakeBone.skillTemp(-1, 0);
                    if (fakeBone.skillTemp.intersects(fakePlayer.collision)) {
                        if (healCont == true) {
                            System.err.println("SkeletonAttack");
                            heal++;
                            healCont = false;
                        }
                    }
                    bossTwoAttack.drawSkeletonAttack(g);
                }
                else if (webWay == 2) {
                    //Left
                    skeletonAttackStartXRight = 1;
                    bossTwoAttack.attackRightStart = 1;
                    g.drawImage(bossTwo.skeletonBoneLeftRight, skeletonAttackPosX, skeletonAttackPosY, null);
                    
                    fakeBone.skillTemp(+1, 0);
                    if (fakeBone.skillTemp.intersects(fakePlayer.collision)) {
                        if (healCont == true) {
                            System.err.println("SkeletonAttack");
                            heal++;
                            healCont = false;
                        }
                    }
                    bossTwoAttack.drawSkeletonAttack(g);
                }
                else if (webWay == 3) {
                    //Down
                    skeletonAttackStartYUp = 1;
                    bossTwoAttack.attackUpStart = 1;
                    g.drawImage(bossTwo.skeletonBoneUpDown, skeletonAttackPosX, skeletonAttackPosY, null);
                    
                    fakeBone.skillTemp(0, -1);
                    if (fakeBone.skillTemp.intersects(fakePlayer.collision)) {
                        if (healCont == true) {
                            System.err.println("SkeletonAttack");
                            heal++;
                            healCont = false;
                        }
                    }
                    bossTwoAttack.drawSkeletonAttack(g);
                }
                else if (webWay == 4) {
                    //Up
                    skeletonAttackStartYDown = 1;
                    bossTwoAttack.attackDownStart = 1;
                    g.drawImage(bossTwo.skeletonBoneUpDown, skeletonAttackPosX, skeletonAttackPosY, null);
                    
                    fakeBone.skillTemp(0, +1);
                    if (fakeBone.skillTemp.intersects(fakePlayer.collision)) {
                        if (healCont == true) {
                            System.err.println("SkeletonAttack");
                            heal++;
                            healCont = false;
                        }
                    }
                    bossTwoAttack.drawSkeletonAttack(g);
                }
                bossTwo.posX = bossTwoAttack.posX;
                bossTwo.posY = bossTwoAttack.posY;
                drawSkeleton = false;
            }
        }

        
        //Player
        //---------------------------------------------------------------------------------------------------------------------------------------------
        //---------------------------------------------------------------------------------------------------------------------------------------------
        //---------------------------------------------------------------------------------------------------------------------------------------------
        //HealDead
        if (heal == 0) {
            g.drawImage(p.health0, pX-5, pY-20, null);
        }
        else if (heal == 1) {
            g.drawImage(p.health1, pX-5, pY-20, null);
        }
        else if (heal == 2) {
            g.drawImage(p.health2, pX-5, pY-20, null);
        }
        else if (heal == 3) {
            g.drawImage(p.health3, pX-5, pY-20, null);
        }
        else if (heal == 4) {
            g.drawImage(p.health4, pX-5, pY-20, null);
        }
        else if (heal == 5) {
            if (justOnce == true) {
                p.posX = pX+15;
                p.posY = pY;
                justOnce = false;
                dead = 0;
            }
            stopForDead = 1;
            g.drawImage(p.health5, pX-5, pY-20, null);
            p.playerDeadDraw(g);
            if (dead == 70) {
                playerSpawnStart = true;
                p.deadAnim.stop();
           }
        }
        
        //PlayerMove
        if (stopForDead == 0) {
            if (startX == 300 && startY == 300) {
            g.drawImage(p.playerDown, startX, startY, null);
            fakePlayer = new Collision(startX+20,  startY+40, 20, 10);
            pX = startX;
            pY = startY;
            spellX = startX+25;
            spellY = startY+20;
            playerAnimDown = new Animator(p.moveDown);
            playerAnimCont = playerAnimDown;
            }

            if (codeFire == KeyEvent.VK_Q) {
                if(playerAnimCont != null){
                    playerAnimCont.update(System.currentTimeMillis());
                    g.drawImage(playerAnimCont.sprite, pX, pY, null); 
                    fakePlayer = new Collision(pX+20,  pY+40, 20, 10);
                    cdStart = 1;

                    if (playerAnimCont == playerSkillAnimR) {
                        webWay = 1;
                        skillContFire.update(System.currentTimeMillis());
                        fakeSkill = new Collision(spellXRight+30, spellY, 10,10);
                        //fakeSkill.drawImg(g);
                        fakeSkill.skillTemp(+1, 0);
                        if (fakeSkill.skillTemp.intersects(fakeSpider.collision)) {
                            //System.err.println("CARPISMA");
                            skillEnd = 1;
                            if (damageCont == true) {
                                hit.posX = (int) bossOne.posX-20;
                                hit.posY = (int) bossOne.posY-20;
                                damage++;
                                damageCont = false;
                                spiderOneShot = 0;
                            }
                            hit.drawHit(g);
                        }
                        else if (fakeSkill.skillTemp.intersects(fakeSkeleton.collision)) {
                            //System.err.println("Skeleton");
                            skillEnd = 1;
                            if (damageCont == true) {
                                hit.posX = (int) bossTwo.posX-20;
                                hit.posY = (int) bossTwo.posY-20;
                                damageSkeleton++;
                                damageCont = false;
                                skeletonOneShot = 0;
                            }
                            hit.drawHit(g);
                        }
                        else if(skillEnd == 0){
                            g.drawImage(skillContFire.sprite, spellXRight, spellY, null);
                        }
                    }
                    else if (playerAnimCont == playerSkillAnimL) {
                        webWay = 2;
                        skillContFire.update(System.currentTimeMillis());
                        fakeSkill = new Collision(spellXLeft+30, spellY, 10,10);
                        //fakeSkill.drawImg(g);
                        fakeSkill.skillTemp(-1, 0);
                        if (fakeSkill.skillTemp.intersects(fakeSpider.collision)) {
                            //System.err.println("CARPISMA");
                            skillEnd = 1;
                            if (damageCont == true) {
                                hit.posX = (int) bossOne.posX-20;
                                hit.posY = (int) bossOne.posY-20;
                                damage++;
                                damageCont = false;
                                spiderOneShot = 0;
                            }
                            hit.drawHit(g);
                        }
                        else if (fakeSkill.skillTemp.intersects(fakeSkeleton.collision)) {
                            //System.err.println("Skeleton");
                            skillEnd = 1;
                            if (damageCont == true) {
                                hit.posX = (int) bossTwo.posX-20;
                                hit.posY = (int) bossTwo.posY-20;
                                damageSkeleton++;
                                damageCont = false;
                                skeletonOneShot = 0;
                            }
                            hit.drawHit(g);
                        }
                        else if(skillEnd == 0){
                            g.drawImage(skillContFire.sprite, spellXLeft, spellY, null);
                        }
                    }
                    else if (playerAnimCont == playerSkillAnimD) {
                        webWay = 3;
                        skillContFire.update(System.currentTimeMillis());
                        fakeSkill = new Collision(spellX, spellYDown+20, 20, 10);
                        //fakeSkill.drawImg(g);
                        fakeSkill.skillTemp(0, +1);
                        if (fakeSkill.skillTemp.intersects(fakeSpider.collision)) {
                            //System.err.println("CARPISMA");
                            skillEnd = 1;
                            if (damageCont == true) {
                                hit.posX = (int) bossOne.posX-20;
                                hit.posY = (int) bossOne.posY-20;
                                damage++;
                                damageCont = false;
                                spiderOneShot = 0;
                            }
                            hit.drawHit(g);
                        }
                        else if (fakeSkill.skillTemp.intersects(fakeSkeleton.collision)) {
                            //System.err.println("Skeleton");
                            skillEnd = 1;
                            if (damageCont == true) {
                                hit.posX = (int) bossTwo.posX-20;
                                hit.posY = (int) bossTwo.posY-20;
                                damageSkeleton++;
                                damageCont = false;
                                skeletonOneShot = 0;
                            }
                            hit.drawHit(g);
                        }
                        else if(skillEnd == 0){
                            g.drawImage(skillContFire.sprite, spellX, spellYDown, null);
                        }
                    }
                    else if (playerAnimCont == playerSkillAnimU) {
                        webWay = 4;
                        skillContFire.update(System.currentTimeMillis());
                        fakeSkill = new Collision(spellX, spellYUp+20, 20, 10);
                        //fakeSkill.drawImg(g);
                        fakeSkill.skillTemp(0, -1);
                        if (fakeSkill.skillTemp.intersects(fakeSpider.collision)) {
                            //System.err.println("CARPISMA");
                            skillEnd = 1;
                            if (damageCont == true) {
                                hit.posX = (int) bossOne.posX-20;
                                hit.posY = (int) bossOne.posY-20;
                                damage++;
                                damageCont = false;
                                spiderOneShot = 0;
                            }
                            hit.drawHit(g);
                        }
                        else if (fakeSkill.skillTemp.intersects(fakeSkeleton.collision)) {
                            //System.err.println("Skeleton");
                            skillEnd = 1;
                            if (damageCont == true) {
                                hit.posX = (int) bossTwo.posX-20;
                                hit.posY = (int) bossTwo.posY-20;
                                damageSkeleton++;
                                damageCont = false;
                                skeletonOneShot = 0;
                            }
                            hit.drawHit(g);
                        }
                        else if(skillEnd == 0){
                            g.drawImage(skillContFire.sprite, spellX, spellYUp, null);
                        }
                    }

                    if (imgCount == 35) {
                        hit.start = 1;
                        codeFire = KeyEvent.VK_P;
                        if (playerAnimCont == playerSkillAnimL) {
                            spellX = pX-25;
                            spellY = pY+20;
                            playerAnimCont = playerAnimLeft; 
                        }
                        else if (playerAnimCont == playerSkillAnimR) {
                            spellX = pX+25;
                            spellY = pY+20;
                            playerAnimCont = playerAnimRight;
                        }
                        else if (playerAnimCont == playerSkillAnimD) {
                            spellX = pX+25;
                            spellY = pY+20;
                            playerAnimCont = playerAnimDown;
                        }
                        else if (playerAnimCont == playerSkillAnimU) {
                            spellX = pX+25;
                            spellY = pY-20;
                            playerAnimCont = playerAnimUp;
                        }
                    }
                    repaint();
                }
            }
            else if (codeShock == KeyEvent.VK_W) {
                if(playerAnimCont != null){
                    playerAnimCont.update(System.currentTimeMillis());
                    g.drawImage(playerAnimCont.sprite, pX, pY, null); 
                    fakePlayer = new Collision(pX+20,  pY+40, 20, 10);
                    cdStartShock = 1;

                    if (playerAnimCont == playerSkillAnimR) {
                        webWay = 1;
                        skillContShock.update(System.currentTimeMillis());
                        fakeSkill = new Collision(spellXRight+30, spellY, 10,10);
                        //fakeSkill.drawImg(g);
                        fakeSkill.skillTemp(+1, 0);
                        if (fakeSkill.skillTemp.intersects(fakeSpider.collision)) {
                            //System.err.println("CARPISMA");
                            skillEnd = 1;
                            if (damageCont == true) {
                                hit.posX = (int) bossOne.posX-20;
                                hit.posY = (int) bossOne.posY-20;
                                damage++;
                                damageCont = false;
                                spiderOneShot = 0;
                            }
                            hit.drawHit(g);
                        }
                        else if (fakeSkill.skillTemp.intersects(fakeSkeleton.collision)) {
                            //System.err.println("Skeleton");
                            skillEnd = 1;
                            if (damageCont == true) {
                                hit.posX = (int) bossTwo.posX-20;
                                hit.posY = (int) bossTwo.posY-20;
                                damageSkeleton++;
                                damageCont = false;
                                skeletonOneShot = 0;
                            }
                            hit.drawHit(g);
                        }
                        else if(skillEnd == 0){
                            g.drawImage(skillContShock.sprite, spellXRight, spellY, null);
                        }
                    }
                    else if (playerAnimCont == playerSkillAnimL) {
                        webWay = 2;
                        skillContShock.update(System.currentTimeMillis());
                        fakeSkill = new Collision(spellXLeft+30, spellY, 10,10);
                        //fakeSkill.drawImg(g);
                        fakeSkill.skillTemp(-1, 0);
                        if (fakeSkill.skillTemp.intersects(fakeSpider.collision)) {
                            //System.err.println("CARPISMA");
                            skillEnd = 1;
                            if (damageCont == true) {
                                hit.posX = (int) bossOne.posX-20;
                                hit.posY = (int) bossOne.posY-20;
                                damage++;
                                damageCont = false;
                                spiderOneShot = 0;
                            }
                            hit.drawHit(g);
                        }
                        else if (fakeSkill.skillTemp.intersects(fakeSkeleton.collision)) {
                            //System.err.println("Skeleton");
                            skillEnd = 1;
                            if (damageCont == true) {
                                hit.posX = (int) bossTwo.posX-20;
                                hit.posY = (int) bossTwo.posY-20;
                                damageSkeleton++;
                                damageCont = false;
                                skeletonOneShot = 0;
                            }
                            hit.drawHit(g);
                        }
                        else if(skillEnd == 0){
                            g.drawImage(skillContShock.sprite, spellXLeft, spellY, null);
                        }
                    }
                    else if (playerAnimCont == playerSkillAnimD) {
                        webWay = 3;
                        skillContShock.update(System.currentTimeMillis());
                        fakeSkill = new Collision(spellX, spellYDown+20, 20, 10);
                        //fakeSkill.drawImg(g);
                        fakeSkill.skillTemp(0, +1);
                        if (fakeSkill.skillTemp.intersects(fakeSpider.collision)) {
                            //System.err.println("CARPISMA");
                            skillEnd = 1;
                            if (damageCont == true) {
                                hit.posX = (int) bossOne.posX-20;
                                hit.posY = (int) bossOne.posY-20;
                                damage++;
                                damageCont = false;
                                spiderOneShot = 0;
                            }
                            hit.drawHit(g);
                        }
                        else if (fakeSkill.skillTemp.intersects(fakeSkeleton.collision)) {
                            //System.err.println("Skeleton");
                            skillEnd = 1;
                            if (damageCont == true) {
                                hit.posX = (int) bossTwo.posX-20;
                                hit.posY = (int) bossTwo.posY-20;
                                damageSkeleton++;
                                damageCont = false;
                                skeletonOneShot = 0;
                            }
                            hit.drawHit(g);
                        }
                        else if(skillEnd == 0){
                            g.drawImage(skillContShock.sprite, spellX, spellYDown, null);
                        }

                    }
                    else if (playerAnimCont == playerSkillAnimU) {
                        webWay = 4;
                        skillContShock.update(System.currentTimeMillis());
                        fakeSkill = new Collision(spellX, spellYUp+20, 20, 10);
                        //fakeSkill.drawImg(g);
                        fakeSkill.skillTemp(0, -1);
                        if (fakeSkill.skillTemp.intersects(fakeSpider.collision)) {
                            //System.err.println("CARPISMA");
                            skillEnd = 1;
                            if (damageCont == true) {
                                hit.posX = (int) bossOne.posX-20;
                                hit.posY = (int) bossOne.posY-20;
                                damage++;
                                damageCont = false;
                                spiderOneShot = 0;
                            }
                            hit.drawHit(g);
                        }
                        else if (fakeSkill.skillTemp.intersects(fakeSkeleton.collision)) {
                            //System.err.println("Skeleton");
                            skillEnd = 1;
                            if (damageCont == true) {
                                hit.posX = (int) bossTwo.posX-20;
                                hit.posY = (int) bossTwo.posY-20;
                                damageSkeleton++;
                                damageCont = false;
                                skeletonOneShot = 0;
                            }
                            hit.drawHit(g);
                        }
                        else if(skillEnd == 0){
                            g.drawImage(skillContShock.sprite, spellX, spellYUp, null);
                        }
                    }

                    if (imgCount == 35) {
                        hit.start = 1;
                        codeShock = KeyEvent.VK_O;
                        if (playerAnimCont == playerSkillAnimL) {
                            spellX = pX-25;
                            spellY = pY+20;
                            playerAnimCont = playerAnimLeft;
                        }
                        else if (playerAnimCont == playerSkillAnimR) {
                            spellX = pX+25;
                            spellY = pY+20;
                            playerAnimCont = playerAnimRight;
                        }
                        else if (playerAnimCont == playerSkillAnimD) {
                            spellX = pX+25;
                            spellY = pY+20;
                            playerAnimCont = playerAnimDown;
                        }
                        else if (playerAnimCont == playerSkillAnimU) {
                            spellX = pX+25;
                            spellY = pY-20;
                            playerAnimCont = playerAnimUp;
                        }
                    }
                    repaint();
                }
            }
        }
        
        if (stopForDead == 0) {
            if (walked == 1) { 
                if(playerAnimCont != null){
                    playerAnimCont.update(System.currentTimeMillis());
                    g.drawImage(playerAnimCont.sprite, pX, pY, null);
                }
            }
            else{
                if (imgCount == 35) {
                    g.drawImage(playerAnimCont.sprite, pX, pY, null);
                    playerAnimCont.stop();
                }
                else
                    g.drawImage(playerAnimCont.sprite, pX, pY, null);
            }
        }
        fakePlayer = new Collision(pX+20,  pY+3, 20, 50);
        //fakePlayer.drawImg(g);
        //---------------------------------------------------------------------------------------------------------------------------------------------
        //---------------------------------------------------------------------------------------------------------------------------------------------
        //---------------------------------------------------------------------------------------------------------------------------------------------
        
        //COOLDOWNS
        
        cooldown.drawCoolDowns(g);
        if (cdStart == 1) {
            cooldownControl.update(System.currentTimeMillis());
            g.drawImage(cooldownControl.sprite, 404, 740, null);
        }
        if (cdStartShock == 1) {
            cooldownAnimShock.update(System.currentTimeMillis());
            g.drawImage(cooldownAnimShock.sprite, 460, 740, null);
        }
        
        
        
        repaint();
        
        //For Asset Limit
        /*fakePlayer.drawImg(g);
        for (int i = 0; i < assets.length; i++) {
            assets[i].drawImg(g);
        }*/
        
    }
    
    
    
    public void keyPressed(KeyEvent e) {
        startX = 0;
        startY = 0;
        walked = 1;
	code = e.getKeyCode();
        codeFire = e.getKeyCode();
        codeShock= e.getKeyCode();
        if (stopForDead == 0) {
            //S DOWN <-------
            if (code == KeyEvent.VK_DOWN){
                walked = 1;
                fakePlayer.temp(0, pvelY+1);
                for (int i = 0; i < assets.length; i++) {
                    if (fakePlayer.temp.intersects(assets[i].collision)) {
                        collision = true;
                    }
                }
                if(collision == false){
                    pvelY = 2;
                    if (running) {
                        playerAnimDown = new Animator(p.moveDown);
                        playerAnimDown.setSpeed(100);
                        playerAnimCont = playerAnimDown;
                        playerAnimCont.play();
                        running = false;
                    }
                    pX += pvelX;
                    pY += pvelY;
                    spellX = pX+25;
                    spellY = pY+20;
                    repaint();
                }
            }
            //W UP<-------
            if (code == KeyEvent.VK_UP){
                walked = 1;
                fakePlayer.temp(0,pvelY-1);
                for(int i = 0; i < assets.length; i++){
                    if (fakePlayer.temp.intersects(assets[i].collision)) {
                        collision = true;
                    }
                }
                if(collision == false){
                    pvelY = -2;
                    if (running) {
                        playerAnimUp = new Animator(p.moveUp);
                        playerAnimUp.setSpeed(100);
                        playerAnimCont = playerAnimUp;
                        playerAnimCont.play();
                        running = false;
                    }
                    pX += pvelX;
                    pY += pvelY;
                    spellX = pX+25;
                    spellY = pY-20;
                    repaint();
                }
            }


            //A LEFT<-------
            if (code == KeyEvent.VK_LEFT){
                walked = 1;
                fakePlayer.temp( pvelX-1, 0);
                for(int i = 0; i< assets.length; i++){
                    if (fakePlayer.temp.intersects(assets[i].collision)) {
                        collision = true;
                    }
                }
                if(collision == false){
                    pvelX = -2;        
                    if (running) {
                        playerAnimLeft = new Animator(p.moveLeft);
                        playerAnimLeft.setSpeed(100);
                        playerAnimCont = playerAnimLeft;
                        playerAnimCont.play();
                        running = false;
                    }
                    pX += pvelX;
                    pY += pvelY;
                    spellX = pX-25;
                    spellY = pY+20;
                    repaint();
                }
            }


            //D RIGHT <-----------
            if (code == KeyEvent.VK_RIGHT){
                walked = 1;
                fakePlayer.temp(pvelX+1, 0);
                for(int i = 0; i < assets.length; i++){
                    if (fakePlayer.temp.intersects(assets[i].collision)) {
                        collision = true;
                    }
                }
                if (collision == false) {
                    pvelX = 2;
                    if (running) {
                        playerAnimRight = new Animator(p.moveRight);
                        playerAnimRight.setSpeed(100);
                        playerAnimCont = playerAnimRight;
                        playerAnimCont.play();
                        running = false;
                    }
                    pX += pvelX;
                    pY += pvelY;
                    spellX = pX+25;
                    spellY = pY+20;
                    repaint();
                }
            }

            //Skill Fire
            if (coolDownTimeFire == 0) {
                if (oneTick == 1) {
                    oneTick = 0;
                    if(codeFire == KeyEvent.VK_Q){
                        
                        try {
                            fireHit();
                        } catch (IOException ex) {
                            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                        }
                
                        cooldownAnim = new Animator(cooldown.cooldowns);
                        cooldownAnim.setSpeed(210);
                        cooldownControl = cooldownAnim;
                        cooldownControl.play();
                        imgCount = 0;
                        skillEnd = 0;
                        damageCont = true;
                        skillCodeFire = KeyEvent.VK_Q;
                        spellXLeft = pX-25;
                        spellXRight = pX+25;
                        spellYDown = pY+20;
                        spellYUp = pY-20;
                        if (runningSkill) {
                            if (playerAnimCont == playerAnimRight) {
                               playerSkillAnimR = new Animator(p.attackRight);
                               playerSkillAnimR.setSpeed(100);
                               playerAnimCont = playerSkillAnimR;
                               playerAnimCont.play(); 
                               runningSkill = false;

                               skillRightFire = new SkillAnimator(skill.fireSpellRight);
                               skillRightFire.setSpeed(100);
                               skillContFire = skillRightFire;
                               skillContFire.play();
                            }
                            else if (playerAnimCont == playerAnimLeft) {
                               playerSkillAnimL = new Animator(p.attackLeft);
                               playerSkillAnimL.setSpeed(100);
                               playerAnimCont = playerSkillAnimL;
                               playerAnimCont.play(); 
                               runningSkill = false; 

                               skillLeftFire = new SkillAnimator(skill.fireSpellLeft);
                               skillLeftFire.setSpeed(100);
                               skillContFire = skillLeftFire;
                               skillContFire.play();
                            }
                            else if (playerAnimCont == playerAnimDown) {
                               playerSkillAnimD = new Animator(p.attackDown);
                               playerSkillAnimD.setSpeed(100);
                               playerAnimCont = playerSkillAnimD;
                               playerAnimCont.play(); 
                               runningSkill = false; 

                               skillDownFire = new SkillAnimator(skill.fireSpellDown);
                               skillDownFire.setSpeed(100);
                               skillContFire = skillDownFire;
                               skillContFire.play();
                            }
                            else if (playerAnimCont == playerAnimUp) {
                               playerSkillAnimU = new Animator(p.attackUp);
                               playerSkillAnimU.setSpeed(100);
                               playerAnimCont = playerSkillAnimU;
                               playerAnimCont.play(); 
                               runningSkill = false; 

                               skillUpFire = new SkillAnimator(skill.fireSpellUp);
                               skillUpFire.setSpeed(100);
                               skillContFire = skillUpFire;
                               skillContFire.play();
                            }
                        }   
                        repaint();
                    }
                }
            }
            //Skill Shock
            if (coolDownTimeShock == 0) {
                if (oneTickShock == 1) {
                    oneTickShock = 0;
                    if(codeShock == KeyEvent.VK_W){
                        
                        try {
                            lightningHit();
                        } catch (IOException ex) {
                            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                        cooldownAnimShock  = new Animator(cooldown.cooldowns);
                        cooldownAnimShock.setSpeed(210);
                        cooldownAnimShock.play();
                        imgCount = 0;
                        skillEnd = 0;
                        damageCont = true;
                        skillCodeShock = KeyEvent.VK_W;
                        spellXLeft = pX-25;
                        spellXRight = pX+25;
                        spellYDown = pY+20;
                        spellYUp = pY-20;
                        if (runningSkill) {
                            if (playerAnimCont == playerAnimRight) {
                               playerSkillAnimR = new Animator(p.attackRight);
                               playerSkillAnimR.setSpeed(100);
                               playerAnimCont = playerSkillAnimR;
                               playerAnimCont.play(); 
                               runningSkill = false;

                               skillRightShock = new SkillAnimatorShock(skill.shockSpellRight);
                               skillRightShock.setSpeed(100);
                               skillContShock = skillRightShock;
                               skillContShock.play();
                            }
                            else if (playerAnimCont == playerAnimLeft) {
                               playerSkillAnimL = new Animator(p.attackLeft);
                               playerSkillAnimL.setSpeed(100);
                               playerAnimCont = playerSkillAnimL;
                               playerAnimCont.play(); 
                               runningSkill = false; 

                               skillLeftShock = new SkillAnimatorShock(skill.shockSpellLeft);
                               skillLeftShock.setSpeed(100);
                               skillContShock = skillLeftShock;
                               skillContShock.play();
                            }
                            else if (playerAnimCont == playerAnimDown) {
                               playerSkillAnimD = new Animator(p.attackDown);
                               playerSkillAnimD.setSpeed(100);
                               playerAnimCont = playerSkillAnimD;
                               playerAnimCont.play(); 
                               runningSkill = false; 

                               skillDownShock = new SkillAnimatorShock(skill.shockSpellDown);
                               skillDownShock.setSpeed(100);
                               skillContShock = skillDownShock;
                               skillContShock.play();
                            }
                            else if (playerAnimCont == playerAnimUp) {
                               playerSkillAnimU = new Animator(p.attackUp);
                               playerSkillAnimU.setSpeed(100);
                               playerAnimCont = playerSkillAnimU;
                               playerAnimCont.play(); 
                               runningSkill = false; 

                               skillUpShock = new SkillAnimatorShock(skill.shockSpellUp);
                               skillUpShock.setSpeed(100);
                               skillContShock = skillUpShock;
                               skillContShock.play();
                            }
                        }   
                        repaint();
                    }
                }
            }
        }
    }

        public void keyTyped(KeyEvent e) {}
        public void keyReleased(KeyEvent e) {
                walked = 0;
                oneTick = 1;
                oneTickShock = 1;
                pvelX = 0;
                pvelY = 0;
                running = true;
                runningSkill= true;
                collision = false;
        }
        
        public void run(){
            
        int fps = 60;
        double timePerTick = 1000000000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;
        
        while(optimization){
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;
            
             if (delta >= 1) {
                ticks++;
                delta--;
                dead();
                countTime();
                spellXLeft();
                spellXRight();
                spelllYUp();
                spellYDown();
                
                coolDownTimeFire();
                coolDownTimeShock();
                
                spiderAttackPosXRight();
                spiderAttackPosXLeft();
                spiderAttackPosYUp();
                spiderAttackPosYDown();
                
                skeletonAttackPosXRight();
                skeletonAttackPosXLeft();
                skeletonAttackPosYUp();
                skeletonAttackPosYDown();
             }
             if(timer >= 1000000000){
                 playerSpawn();
                 spawnStart();
                 spawnStartSkeleton();
                 ticks = 0;
                 timer = 0;
             }
        }
        
        stop();
    }
        
        public synchronized void start(){
        if (optimization)
            return;
        
        optimization = true;
        thread = new Thread(this);
        thread.start();
    }
    
    public synchronized void stop(){
        if (!optimization)
            return;
        optimization = false;
        
        try {
            thread.join();
        } catch(InterruptedException e){
            e.printStackTrace();
        }
        
    }
    
    public int playerSpawn(){
        if (playerSpawnStart == true) {
            playerSpawn++;
        }
        if (playerSpawn == 10) {
            stopForDead = 0;
            heal = 0;
            p.start = 1;
            playerSpawn = 0;
            justOnce = true;
            playerSpawnStart = false;
        }
        return playerSpawn;
    }
    public int dead(){
        return dead++;
    }
    public int countTime(){
        return imgCount++;
    }
    public int spellXRight(){
        return spellXRight+=3;
    }
    public int spellXLeft(){
        return spellXLeft-=3;
    }
    public int spelllYUp(){
        return spellYUp-=3;
    }
    public int spellYDown(){
        return spellYDown+=3;
    }
    public int coolDownTimeFire(){
        if (skillCodeFire == KeyEvent.VK_Q) {
            coolDownTimeFire++;
            if (coolDownTimeFire == 300) {
                cdStart = 0;
                cooldownAnim.stop();
                coolDownTimeFire = 0;
                skillCodeFire = KeyEvent.VK_P;
            }
        }
        return coolDownTimeFire;
    }
    public int coolDownTimeShock(){
        if (skillCodeShock == KeyEvent.VK_W) {
            coolDownTimeShock++;
            if (coolDownTimeShock == 300) {
                cdStartShock = 0;
                cooldownAnimShock.stop();
                coolDownTimeShock = 0;
                skillCodeShock = KeyEvent.VK_O;
            }
        }
        return coolDownTimeShock;
    }
    
    public int spawnStart(){
        if (spawnStart == true) {
            bossSpawn++;
        }
        if (bossSpawn == 10) {
            damage = 0;
            bossSpawn = 0;
            spawnStart = false;
            drawSpider = true;
        }
        return bossSpawn;
    }
    
    public int spawnStartSkeleton(){
        if (spawnStartSkeleton == true) {
            bossSpawnSkeleton++;
        }
        if (bossSpawnSkeleton == 10) {
            damageSkeleton = 0;
            bossSpawnSkeleton = 0;
            spawnStartSkeleton = false;
            drawSkeleton = true;
        }
        return bossSpawnSkeleton;
    }
    
    public int spiderAttackPosXRight(){
        if (spiderAttackStartXRight == 1) {
            if (spiderAttackRange <= 100) {
                spiderAttackPosX++;
                spiderAttackRange++;
            }
            if (spiderAttackRange >= 101) {
                spiderAttackRange = 0;
                spiderAttackStartXRight = 0;
                spiderOneShot = 1;
                spiderWayEquals = true;
                drawSpider = true;
                bossOneAttack.attackRightStart = 0;
                bossOneAttack.attackStart = 1;
            }
        }        
        return spiderAttackPosX;
    }
    public int spiderAttackPosXLeft(){
        if (spiderAttackStartXLeft == 1) {
            if (spiderAttackRange <= 100) {
                spiderAttackPosX--;
                spiderAttackRange++;
            }
            if (spiderAttackRange >= 101) {
                spiderAttackRange = 0;
                spiderAttackStartXLeft = 0;
                spiderOneShot = 1;
                spiderWayEquals = true;
                drawSpider = true;
                bossOneAttack.attackLeftStart = 0;
                bossOneAttack.attackStart = 1;
            }
        }        
        return spiderAttackPosX;
    }
    
    public int spiderAttackPosYUp(){
        if (spiderAttackStartYUp == 1) {
            if (spiderAttackRange <= 100) {
                spiderAttackPosY--;
                spiderAttackRange++;
            }
            if (spiderAttackRange >= 101) {
                spiderAttackRange = 0;
                spiderAttackStartYUp = 0;
                spiderOneShot = 1;
                spiderWayEquals = true;
                drawSpider = true;
                bossOneAttack.attackUpStart = 0;
                bossOneAttack.attackStart = 1;
            }
        }        
        return spiderAttackPosY;
    }
    public int spiderAttackPosYDown(){
        if (spiderAttackStartYDown == 1) {
            if (spiderAttackRange <= 100) {
                spiderAttackPosY++;
                spiderAttackRange++;
            }
            if (spiderAttackRange >= 101) {
                spiderAttackRange = 0;
                spiderAttackStartYDown = 0;
                spiderOneShot = 1;
                spiderWayEquals = true;
                drawSpider = true;
                bossOneAttack.attackDownStart = 0;
                bossOneAttack.attackStart = 1;
            }
        }        
        return spiderAttackPosY;
    }
    public int skeletonAttackPosXRight(){
        if (skeletonAttackStartXRight == 1) {
            if (skeletonAttackRange <= 100) {
                skeletonAttackPosX++;
                skeletonAttackRange++;
            }
            if (skeletonAttackRange >= 101) {
                skeletonAttackRange = 0;
                skeletonAttackStartXRight = 0;
                skeletonOneShot = 1;
                skeletonWayEquals = true;
                drawSkeleton = true;
                bossTwoAttack.attackRightStart = 0;
                bossTwoAttack.attackStart = 1;
            }
        }        
        return skeletonAttackPosX;
    }
    public int skeletonAttackPosXLeft(){
        if (skeletonAttackStartXLeft == 1) {
            if (skeletonAttackRange <= 100) {
                skeletonAttackPosX--;
                skeletonAttackRange++;
            }
            if (skeletonAttackRange >= 101) {
                skeletonAttackRange = 0;
                skeletonAttackStartXLeft = 0;
                skeletonOneShot = 1;
                skeletonWayEquals = true;
                drawSkeleton = true;
                bossTwoAttack.attackLeftStart = 0;
                bossTwoAttack.attackStart = 1;
            }
        }        
        return skeletonAttackPosX;
    }
    
    public int skeletonAttackPosYUp(){
        if (skeletonAttackStartYUp == 1) {
            if (skeletonAttackRange <= 100) {
                skeletonAttackPosY--;
                skeletonAttackRange++;
            }
            if (skeletonAttackRange >= 101) {
                skeletonAttackRange = 0;
                skeletonAttackStartYUp = 0;
                skeletonOneShot = 1;
                skeletonWayEquals = true;
                drawSkeleton = true;
                bossTwoAttack.attackUpStart = 0;
                bossTwoAttack.attackStart = 1;
            }
        }        
        return skeletonAttackPosY;
    }
    public int skeletonAttackPosYDown(){
        if (skeletonAttackStartYDown == 1) {
            if (skeletonAttackRange <= 100) {
                skeletonAttackPosY++;
                skeletonAttackRange++;
            }
            if (skeletonAttackRange >= 101) {
                skeletonAttackRange = 0;
                skeletonAttackStartYDown = 0;
                skeletonOneShot = 1;
                skeletonWayEquals = true;
                drawSkeleton = true;
                bossTwoAttack.attackDownStart = 0;
                bossTwoAttack.attackStart = 1;
            }
        }        
        return skeletonAttackPosY;
    }
    public void music() throws IOException{
        InputStream musicWay = new FileInputStream("song.wav");
        AudioStream music = new AudioStream(musicWay);
        AudioPlayer.player.start(music);
    }
    
    public void fireHit() throws IOException{
        InputStream fireHitWay = new FileInputStream("fireHit.wav");
        AudioStream fireHit = new AudioStream(fireHitWay);
        AudioPlayer.player.start(fireHit);
    }
    public void lightningHit() throws IOException{
        InputStream lightningWay = new FileInputStream("lightning.wav");
        AudioStream lightning = new AudioStream(lightningWay);
        AudioPlayer.player.start(lightning);
    }
}
