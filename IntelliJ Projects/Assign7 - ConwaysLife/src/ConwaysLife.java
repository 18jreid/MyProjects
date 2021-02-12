// Reference for Lanterna 3: https://github.com/mabe02/lanterna/blob/master/docs/contents.md
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

public class ConwaysLife {
    public static void main(String[] args) {
        try {

            Terminal terminal = new DefaultTerminalFactory().createTerminal();
            Screen screen = new TerminalScreen(terminal);
            TextGraphics graphics = screen.newTextGraphics();

            TerminalSize size = screen.getTerminalSize();
            LifeSimulator simulation = new LifeSimulator(size.getColumns(), size.getRows());

            // Creates patterns
            PatternAcorn acorn = new PatternAcorn();
            PatternBlock block = new PatternBlock();
            PatternBlinker blinker = new PatternBlinker();

            // inserts patterns onto the grid
            simulation.insertPattern(acorn, 25, 8);
            simulation.insertPattern(block, 4, 12);
            simulation.insertPattern(blinker, 50, 10);

            screen.startScreen();
            screen.setCursorPosition(null);

            for (int i = 0; i < 100; i++) {
                render(simulation, screen, graphics);   // Render the current state of the simulation
                Thread.yield();                         // Let the JVM have some time to update other things
                Thread.sleep(150);                 // Sleep for a bit to make for a nicer paced animation

                // Tell the simulation to update all patterns
                simulation.update();
            }

            screen.stopScreen();
        } catch (Exception ex) {
            System.out.println("Something bad happened: " + ex.getMessage());
        }
    }

    public static void sampleRender(Screen screen, TextGraphics graphics, int xPos) {
        screen.clear();

        // Not very interesting, but showing how to set characters
        graphics.setCharacter(xPos, 10, 'X');

        // This is what causes the console to render the new state, it is required
        try {
            screen.refresh();
        } catch (Exception ex) {
        }
    }

    public static void render(LifeSimulator simulator, Screen screen, TextGraphics graphics) {
        screen.clear();

        // Goes through grid provided, and every time it finds a true value it will print an X
        for (int y = 0; y < simulator.getSizeY(); y++) {
            for (int x = 0; x < simulator.getSizeX(); x++) {
                if (simulator.getCell(x, y)) {
                    graphics.setCharacter(x, y, 'X');
                }
            }
        }

        // This is what causes the console to render the new state, it is required
        try {
            screen.refresh();
        } catch (Exception ex) {
        }
    }
}
