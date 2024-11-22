package main;

import object.SuperObject;

import javax.swing.*;
import java.io.File;

public class Main  {
    public static Sound se = new Sound();
    public static Sound music = new Sound();
    public static SuperObject[] obj = new SuperObject[22];


    public static String playerName;
    public static GamePanel gp = new GamePanel(true, playerName);
    public static UI ui = new UI();

    private static final String[] REQUIRED_FILES = {
            "src/entity/BaseCharacter.java",
            "src/entity/Entity.java",
            "src/entity/Gobelin.java",
            "src/entity/Jack.java",
            "src/entity/Mage.java",
            "src/entity/Minotaur.java",
            "src/entity/Monster.java",
            "src/entity/Player.java",
            "src/entity/Seraphyrax.java",
            "src/entity/Warrior.java",
            "src/main/AssetSetter.java",
            "src/main/CollisionChecker.java",
            "src/main/GamePanel.java",
            "src/main/GameStartWindow.java",
            "src/main/KeyHandler.java",
            "src/main/Main.java",
            "src/main/Sound.java",
            "src/main/UI.java",
            "src/main/UtilityTool.java",
            "src/object/Boot.java",
            "src/object/Chest.java",
            "src/object/Door.java",
            "src/object/Fireball.java",
            "src/object/Heal.java",
            "src/object/Heart.java",
            "src/object/House.java",
            "src/object/Key.java",
            "src/object/Portal.java",
            "src/object/RedDoor.java",
            "src/object/RedKey.java",
            "src/object/SuperObject.java",
            "src/object/Sword.java",
            "src/resources/fireBall/down/0.png",
            "src/resources/fireBall/down/1.png",
            "src/resources/fireBall/left/0.png",
            "src/resources/fireBall/left/1.png",
            "src/resources/fireBall/right/0.png",
            "src/resources/fireBall/right/1.png",
            "src/resources/fireBall/up/0.png",
            "src/resources/fireBall/up/1.png",
            "src/resources/gameintro/GameImage.png",
            "src/resources/mage/emotes.png",
            "src/resources/mage/attack/down/0.png",
            "src/resources/mage/attack/down/1.png",
            "src/resources/mage/attack/down/2.png",
            "src/resources/mage/attack/down/3.png",
            "src/resources/mage/attack/down/4.png",
            "src/resources/mage/attack/down/5.png",
            "src/resources/mage/attack/down/6.png",
            "src/resources/mage/attack/down/7.png",
            "src/resources/mage/attack/left/0.png",
            "src/resources/mage/attack/left/1.png",
            "src/resources/mage/attack/left/2.png",
            "src/resources/mage/attack/left/3.png",
            "src/resources/mage/attack/left/4.png",
            "src/resources/mage/attack/left/5.png",
            "src/resources/mage/attack/left/6.png",
            "src/resources/mage/attack/left/7.png",
            "src/resources/mage/attack/right/0.png",
            "src/resources/mage/attack/right/1.png",
            "src/resources/mage/attack/right/2.png",
            "src/resources/mage/attack/right/3.png",
            "src/resources/mage/attack/right/4.png",
            "src/resources/mage/attack/right/5.png",
            "src/resources/mage/attack/right/6.png",
            "src/resources/mage/attack/right/7.png",
            "src/resources/mage/attack/up/0.png",
            "src/resources/mage/attack/up/1.png",
            "src/resources/mage/attack/up/2.png",
            "src/resources/mage/attack/up/3.png",
            "src/resources/mage/attack/up/4.png",
            "src/resources/mage/attack/up/5.png",
            "src/resources/mage/attack/up/6.png",
            "src/resources/mage/attack/up/7.png",
            "src/resources/tiles/image2x1.png",
            "src/resources/tiles/image2x2.png",
            "src/resources/tiles/image2x3.png",
            "src/resources/tiles/image2x4.png",
            "src/resources/tiles/image3x1.png",
            "src/resources/tiles/image3x2.png",
            "src/resources/tiles/image3x3.png",
            "src/resources/tiles/image3x4.png",
            "src/resources/tiles/img.png",
            "src/resources/tiles/img_1.png",
            "src/resources/tiles/img_2.png",
            "src/resources/tiles/lampb.png",
            "src/resources/tiles/lamph.png",
            "src/resources/tiles/lampmid.png",
            "src/resources/tiles/m.png",
            "src/resources/tiles/md.png",
            "src/resources/tiles/mg.png",
            "src/resources/tiles/pmb.png",
            "src/resources/tiles/pmh.png",
            "src/resources/tiles/rock.png",
            "src/resources/tiles/rockd.png",
            "src/resources/tiles/rockg.png",
            "src/resources/tiles/statub.png",
            "src/resources/tiles/statuh.png",
            "src/resources/tiles/statum.png",
            "src/resources/tiles/tono.png",
            "src/resources/tiles/tronc.png",
            "src/resources/warrior/emote.png",
            "src/resources/warrior/moves.png",
            "src/resources/warrior/attack/down/0.png",
            "src/resources/warrior/attack/down/1.png",
            "src/resources/warrior/attack/down/2.png",
            "src/resources/warrior/attack/down/3.png",
            "src/resources/warrior/attack/down/4.png",
            "src/resources/warrior/attack/down/5.png",
            "src/resources/warrior/attack/left/0.png",
            "src/resources/warrior/attack/left/1.png",
            "src/resources/warrior/attack/left/2.png",
            "src/resources/warrior/attack/left/3.png",
            "src/resources/warrior/attack/left/4.png",
            "src/resources/warrior/attack/left/5.png",
            "src/resources/warrior/attack/right/0.png",
            "src/resources/warrior/attack/right/1.png",
            "src/resources/warrior/attack/right/2.png",
            "src/resources/warrior/attack/right/3.png",
            "src/resources/warrior/attack/right/4.png",
            "src/resources/warrior/attack/right/5.png",
            "src/resources/warrior/attack/up/0.png",
            "src/resources/warrior/attack/up/1.png",
            "src/resources/warrior/attack/up/2.png",
            "src/resources/warrior/attack/up/3.png",
            "src/resources/warrior/attack/up/4.png",
            "src/resources/warrior/attack/up/5.png",
            "src/tile/Tile.java",
            "src/tile/TileManager.java",
            "src/tile/TileManager2.java"

    };

    public static void main(String[] args) {

        if (!areFilesPresent(REQUIRED_FILES)) {

            JOptionPane.showMessageDialog(null, "File missing Please contact support.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }

        // If all files are present, proceed with game initialization
        SwingUtilities.invokeLater(() -> new GameStartWindow());
    }


    public static void startGame(boolean isWarrior,String playerName){
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(true);

        window.setTitle("Adventure Quest");

        GamePanel gamePanel = new GamePanel(isWarrior ,playerName);
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.setupGame();
        gamePanel.startGameThread();
    }
    private static boolean areFilesPresent(String[] files) {
        for (String filePath : files) {
            File file = new File(filePath);
            if (!file.exists() || !file.isFile()) {
                return false; // Return false if any file is missing
            }
        }
        return true; // All files are present
    }

}