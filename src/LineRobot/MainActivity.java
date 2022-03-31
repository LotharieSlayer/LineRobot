package LineRobot;

import java.io.IOException;

import LineRobot.robot.Robot;

public class MainActivity {
    private static MainActivity instance;
    public MainActivity() throws IOException
	{
		instance = this;
		new Robot();
        //GraphFrame graph = new GraphFrame(instance);
    }

/**
 * Démarrage
 * @param args Fichier à prendre en paramètre (si c'est Exemple1.algo, alors ce sera Exemple1 à entrer)
 * @throws Exception
 */
	public static void main (String[] args) throws Exception{
		new MainActivity();
	}
}

// lance le gui + les x robots