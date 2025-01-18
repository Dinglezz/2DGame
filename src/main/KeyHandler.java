package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class KeyHandler implements KeyListener {
    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed, spacePressed, interactKeyPressed;
    // Debug
    public boolean debug = false;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (gp.gameState == gp.titleState) {titleState(code);}
        else if (gp.gameState == gp.playState) {playState(code);}
        else if (gp.gameState == gp.pauseState) {pauseState(code);}
        else if (gp.gameState == gp.dialogueState) {dialogueState(code); playState(code);}
        else if (gp.gameState == gp.characterState) {characterState(code);}
    }
    public void titleState(int code) {
        if (gp.ui.titleScreenState == 0) {
            if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
                gp.ui.commandNum--;
                gp.playSE(8);
                if (gp.ui.commandNum < 0) {
                    gp.ui.commandNum = 2;
                }
            }
            if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
                gp.ui.commandNum++;
                gp.playSE(8);
                if (gp.ui.commandNum > 2) {
                    gp.ui.commandNum = 0;
                }
            }
            if (code == KeyEvent.VK_ENTER || code == KeyEvent.VK_SPACE) {
                if (gp.ui.commandNum == 0) {
                    gp.ui.titleScreenState = 1;
                }
                if (gp.ui.commandNum == 1) {
                    // For later
                }
                if (gp.ui.commandNum == 2) {
                    System.exit(0);
                }
            }
        } else if (gp.ui.titleScreenState == 1) {
            if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
                gp.ui.commandNum--;
                gp.playSE(8);
                if (gp.ui.commandNum < 0) {
                    gp.ui.commandNum = 3;
                }
            }
            if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
                gp.ui.commandNum++;
                gp.playSE(8);
                if (gp.ui.commandNum > 3) {
                    gp.ui.commandNum = 0;
                }
            }
            if (code == KeyEvent.VK_ENTER || code == KeyEvent.VK_SPACE) {
                if (gp.ui.commandNum == 0) {
                    gp.gameState = gp.playState;
                    gp.stopMusic();
                    gp.playMusic(0);
                    System.out.println("Imagine Picking Easy");

                    // Modified Stats
                    gp.player.strength = 2;
                    gp.player.dexterity = 2;
                    gp.player.nextLevelExp = 4;
                    gp.player.attack = gp.player.getAttack();
                    gp.player.defence = gp.player.getDefence();
                }
                if (gp.ui.commandNum == 1) {
                    gp.gameState = gp.playState;
                    gp.stopMusic();
                    gp.playMusic(0);
                    System.out.println("Kinda a mid game mode lol");

                    // No modified stats since Medium is the default
                }
                if (gp.ui.commandNum == 2) {
                    gp.gameState = gp.playState;
                    gp.stopMusic();
                    gp.playMusic(0);
                    System.out.println("You really think you are \"hardcore\"?");

                    // Modified Stats
                    gp.player.dexterity = 0;
                    gp.player.nextLevelExp = 6;
                    gp.player.defence = gp.player.getDefence();
                }
                if (gp.ui.commandNum == 3) {
                    gp.ui.titleScreenState = 0;
                }
            }
        }
    }
    public void playState(int code) {
        if (code == KeyEvent.VK_W) {
            upPressed = true;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = true;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = true;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = true;
        }
        if (code == KeyEvent.VK_ESCAPE) {
            gp.gameState = gp.pauseState;
        }
        if (code == KeyEvent.VK_E) {
            interactKeyPressed = true;
        }
        if (code == KeyEvent.VK_E) {
            interactKeyPressed = true;
        }
        if (code == KeyEvent.VK_SPACE) {
            spacePressed = true;
        }

        // Debug
        if (code == KeyEvent.VK_F3) {
            if (!debug) {
                System.out.println("Debugging Enabled");
                debug = true;
            } else if (debug) {
                System.out.println("Debugging Disabled");
                debug = false;
            }
        }
    }
    public void pauseState(int code) {
        if (code == KeyEvent.VK_ESCAPE) {
            gp.gameState = gp.playState;
        }
    }
    public void dialogueState(int code) {
        if (code == KeyEvent.VK_SPACE || code == KeyEvent.VK_ESCAPE) {
            gp.gameState = gp.playState;
        }
    }
    public void characterState(int code) {
        if (code == KeyEvent.VK_E || code == KeyEvent.VK_ESCAPE) {
            gp.gameState = gp.playState;
        }
        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            if (gp.ui.slotRow != 0) {
                gp.ui.slotRow --;
                gp.playSE(8);
            }
        }
        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
            if (gp.ui.slotCol != 0) {
                gp.ui.slotCol --;
                gp.playSE(8);
            }
        }
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            if (gp.ui.slotRow != 4) {
                gp.ui.slotRow ++;
                gp.playSE(8);
            }
        }
        if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
            if (gp.ui.slotCol != 4) {
                gp.ui.slotCol ++;
                gp.playSE(8);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }
    }
}
