package com.chip8.model.core.buffer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@Import(DisplayBuffer.class)
public class DisplayBufferTest {

    @Autowired
    private DisplayBuffer displayBuffer;

    @Test
    public void read_inputCoordinatesAndByte_returnSameByte_test(){
        displayBuffer.write(Short.parseShort("1"), Short.parseShort("1"), Byte.decode("1"));
        final Byte result = displayBuffer.read(Short.parseShort("1"), Short.parseShort("1"));
        Assertions.assertEquals(Byte.decode("1"), result);
    }

    @Test
    public void read_inputXCoordinateNull_returnException_test(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> displayBuffer.read(null, Short.parseShort("1")));
    }

    @Test
    public void read_test_inputYCoordinateNull_returnException_test(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> displayBuffer.read(Short.parseShort("1"), null));
    }

    @Test
    public void read_inputAllCoordinatesNull_returnException_test(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> displayBuffer.read(null, null));
    }

    @Test
    public void read_inputXCoordinateNegative_returnException_test(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> displayBuffer.read(Short.parseShort("-1"), Short.parseShort("0")));
    }

    @Test
    public void read_inputYCoordinateNegative_returnException_test(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> displayBuffer.read(Short.parseShort("0"), Short.parseShort("-1")));
    }

    @Test
    public void read_inputAllCoordinatesNegative_returnException_test(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> displayBuffer.read(Short.parseShort("-1"), Short.parseShort("-1")));
    }

    @Test
    public void read_inputXCoordinateGraterThan63_returnException_test(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> displayBuffer.read(Short.parseShort("64"), Short.parseShort("0")));
    }

    @Test
    public void read_inputYCoordinateGraterThan31_returnException_test(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> displayBuffer.read(Short.parseShort("0"), Short.parseShort("32")));
    }

    @Test
    public void read_inputXCoordinateGraterThan63AndYCoordinateGraterThan31_returnException_test(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> displayBuffer.read(Short.parseShort("64"), Short.parseShort("32")));
    }

    @Test
    public void write_inputXCoordinateNull_returnException_test(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> displayBuffer.write(null, Short.parseShort("1"), Byte.decode("1")));
    }

    @Test
    public void write_inputYCoordinateNull_returnException_test(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> displayBuffer.write(Short.parseShort("1"), null, Byte.decode("1")));
    }

    @Test
    public void write_inputDataNull_returnException_test(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> displayBuffer.write(Short.parseShort("1"), Short.parseShort("1"), null));
    }

    @Test
    public void write_inputAllCoordinatesNull_returnException_test(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> displayBuffer.write(null, null, Byte.decode("1")));
    }

    @Test
    public void write_inputAllCoordinatesAndDataNull_returnException_test(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> displayBuffer.write(null, null, null));
    }

    @Test
    public void write_inputXCoordinateNegative_returnException_test(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> displayBuffer.write(Short.parseShort("-1"), Short.parseShort("0"), Byte.decode("1")));
    }

    @Test
    public void write_inputYCoordinateNegative_returnException(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> displayBuffer.write(Short.parseShort("0"), Short.parseShort("-1"), Byte.decode("1")));
    }

    @Test
    public void write_inputAllCoordinatesNegative_returnException(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> displayBuffer.write(Short.parseShort("-1"), Short.parseShort("-1"), Byte.decode("1")));
    }

    @Test
    public void write_inputXCoordinateGraterThan63_returnException(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> displayBuffer.write(Short.parseShort("64"), Short.parseShort("0"), Byte.decode("1")));
    }

    @Test
    public void write_inputYCoordinateGraterThan31_returnException(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> displayBuffer.write(Short.parseShort("0"), Short.parseShort("32"), Byte.decode("1")));
    }

    @Test
    public void write_inputXCoordinateGraterThan63AndYCoordinateGraterThan31_returnException(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> displayBuffer.write(Short.parseShort("64"), Short.parseShort("32"), Byte.decode("1")));
    }

}
