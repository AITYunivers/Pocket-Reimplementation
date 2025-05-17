package io.github.yunivers.pocket.util;

import net.modificationstation.stationapi.api.util.StringIdentifiable;

public enum ESlabState implements StringIdentifiable
{
    TOP("top"),
    BOTTOM("bottom"),
    DOUBLE("double");

    private final String value;

    ESlabState(String value)
    {
        this.value = value;
    }

    @Override
    public String asString()
    {
        return value;
    }
}
