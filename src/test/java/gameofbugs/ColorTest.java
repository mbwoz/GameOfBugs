package gameofbugs;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ColorTest {

    @Test
    public void basicColorTest() {
        Color color = Color.WHITE;

        color = color.getOpposite();
        Assert.assertEquals(Color.BLACK, color);
        color = color.getOpposite();
        Assert.assertEquals(Color.WHITE, color);

        color = Color.NONE;

        color = color.getOpposite();
        Assert.assertEquals(Color.NONE, color);
    }
}
