package game2048.console;

import static game2048.console.InputOption.*;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ConsoleInputTest {

    private InputStream is;

    @Before
    public void setInputStream() {
    }

    @Test
    public void inputShouldReturnOptionMoveLeftOnPressKeyA() {
        is = new ByteArrayInputStream(new byte[] {'a'});

        Input in = new ConsoleInput(is);

        assertThat(MOVE_LEFT, is(in.getInputOption()));
    }

    @Test
    public void inputShouldReturnOptionMoveRightOnPressKeyD() {
        is = new ByteArrayInputStream(new byte[] {'d'});

        Input in = new ConsoleInput(is);

        assertThat(MOVE_RIGHT, is(in.getInputOption()));
    }

    @Test
    public void inputShouldReturnOptionMoveUpOnPressKeyW() {
        is = new ByteArrayInputStream(new byte[] {'w'});

        Input in = new ConsoleInput(is);

        assertThat(MOVE_UP, is(in.getInputOption()));
    }

    @Test
    public void inputShouldReturnOptionMoveDownOnPressKeyS() {
        is = new ByteArrayInputStream(new byte[] {'s'});

        Input in = new ConsoleInput(is);

        assertThat(MOVE_DOWN, is(in.getInputOption()));
    }
}
