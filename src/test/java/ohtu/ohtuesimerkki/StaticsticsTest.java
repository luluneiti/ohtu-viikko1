
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import ohtu.ohtuesimerkki.Player;
import ohtu.ohtuesimerkki.PlayerReader;
import ohtu.ohtuesimerkki.Statistics;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class StaticsticsTest {

    Statistics stats;

    Reader readerStub = new Reader() {

        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<Player>();

            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri", "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));

            return players;
        }

        @Override
        public int read(char[] cbuf, int off, int len) throws IOException {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void close() throws IOException {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

    };

    @Before
    public void setUp() {
        ArrayList<Player> players = new ArrayList<Player>();

            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri", "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));
        stats = new Statistics(readerStub, players);
    }

    @Test
    public void hakutestiLoytyy() {
        Player player = stats.search("Semenko");
        assertEquals("Semenko", player.getName());
    }

    @Test
    public void hakutestiEiLoydy() {
        Player player = stats.search("Semenkoooo");
        assertEquals(null, player);
    }

    @Test
    public void tiimiLoytyy() {
        List<Player> pelaajat = stats.team("EDM");
        assertEquals(3, pelaajat.size());
    }

    @Test
    public void tiimiEiLoydy() {
        List<Player> pelaajat = stats.team("EDMMMMM");
        assertEquals(0, pelaajat.size());
    }

    @Test
    public void topPisteetNeg() {
        List<Player> pelaajat = stats.topScorers(-1);
        assertEquals(0, pelaajat.size());
    }

    @Test
    public void topPisteet0() {
        List<Player> pelaajat = stats.topScorers(0);
        assertEquals(1, pelaajat.size());
    }

    @Test
    public void topPisteet4() {
        List<Player> pelaajat = stats.topScorers(4);
        assertEquals(5, pelaajat.size());
    }

}
