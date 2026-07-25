package net.moddedmod16.powerum.client.entrypoints;

import net.fabricmc.api.ClientModInitializer;
import net.moddedmod16.powerum.client.Powerum;

public class PowerumInit implements ClientModInitializer {
    @Override
    public void onInitializeClient(){
        Powerum.init();
    }
}
