package com.chip8.model.core.sprite;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@AllArgsConstructor
@Getter
public enum SpritesEnum {

    SPRITE_0(0, 0, new Integer[]{240, 144, 144, 144, 240, 0}),
    SPRITE_1(1, 6, new Integer[]{32, 96, 32, 32, 112, 0}),
    SPRITE_2(2, 12, new Integer[]{240, 16, 240, 128, 240, 0}),
    SPRITE_3(3, 18, new Integer[]{240, 16, 240, 16, 240, 0}),
    SPRITE_4(4, 24, new Integer[]{144, 144, 240, 16, 16, 0}),
    SPRITE_5(5, 30, new Integer[]{240, 128, 240, 16, 240, 0}),
    SPRITE_6(6, 36, new Integer[]{240, 128, 240, 144, 240, 0}),
    SPRITE_7(7, 42, new Integer[]{240, 16, 96, 64, 64, 0}),
    SPRITE_8(8, 48, new Integer[]{240, 144, 240, 144, 240, 0}),
    SPRITE_9(9, 54, new Integer[]{240, 144, 240, 16, 240, 0}),
    SPRITE_A(10, 60, new Integer[]{240, 144, 240, 144, 144, 0}),
    SPRITE_B(11, 66, new Integer[]{224, 144, 224, 144, 224, 0}),
    SPRITE_C(12, 72, new Integer[]{240, 128, 128, 128, 240, 0}),
    SPRITE_D(13, 78, new Integer[]{240, 144, 144, 144, 240, 0}),
    SPRITE_E(14, 84, new Integer[]{240, 128, 240, 128, 240, 0}),
    SPRITE_F(15, 90, new Integer[]{240, 128, 240, 128, 128, 0});

    private final Integer representation;

    private final Integer positionInMemory;

    private final Integer[] sprite;

    public static Optional<SpritesEnum> findByRepresentation(final Integer representation) {
        return Arrays.stream(SpritesEnum.values()).filter(spritesEnum -> spritesEnum.getRepresentation().equals(representation)).findFirst();
    }

}
