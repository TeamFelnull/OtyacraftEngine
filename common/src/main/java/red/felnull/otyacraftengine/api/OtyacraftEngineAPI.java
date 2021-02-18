package red.felnull.otyacraftengine.api;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.resources.ResourceLocation;
import red.felnull.otyacraftengine.OtyacraftEngine;
import red.felnull.otyacraftengine.api.register.OEHandlerRegister;
import red.felnull.otyacraftengine.api.register.OEMODColorRegister;
import red.felnull.otyacraftengine.api.register.OERegistries;

import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

/**
 * OtyacraftEngineのAPI
 *
 * @author MORIMORI0317
 * @since 2.0
 */
public class OtyacraftEngineAPI {
    private static OtyacraftEngineAPI INSTANCE;
    private final List<IOEIntegration> integrations;
    private final boolean isTestMode;
    private boolean isDebugMode;
    public boolean isClient;

    public OtyacraftEngineAPI(List<IOEIntegration> integrations, boolean testmode) {
        this.integrations = integrations;
        this.isTestMode = testmode;

        INSTANCE = this;
    }

    /**
     * APIのインスタンスを取得
     *
     * @return OEAPIのインスタンス
     */
    public static OtyacraftEngineAPI getInstance() {
        if (INSTANCE == null) {
            OtyacraftEngine.apiInit();
        }
        return INSTANCE;
    }

    /**
     * OEを利用しているMODの登録情報
     *
     * @return 取得
     */
    public List<IOEIntegration> getIntegrations() {
        return integrations;
    }

    public void integrationConsumer(Consumer<IOEIntegration> consumer) {
        List<IOEIntegration> integrations = getIntegrations();
        integrations.forEach(consumer::accept);
    }

    public Set<IHandler> getHandlers() {
        OEHandlerRegister handlers = (OEHandlerRegister) OERegistries.getSingleRegistry(new ResourceLocation(OtyacraftEngine.MODID, "handler"));
        return handlers.getSet();
    }

    @Environment(EnvType.CLIENT)
    public Set<IHandler> getClientHandlers() {
        OEHandlerRegister handlers = (OEHandlerRegister) OERegistries.getSingleRegistry(new ResourceLocation(OtyacraftEngine.MODID, "client_handler"));
        return handlers.getSet();
    }


    public boolean isDebugMode() {
        return isDebugMode;
    }

    public void setDebugMode(boolean debugMode) {
        isDebugMode = debugMode;
    }

    public boolean isTestMode() {
        return isTestMode;
    }

    public boolean isClient() {
        return isClient;
    }

    public int getModColor(String modid) {
        OEMODColorRegister colors = (OEMODColorRegister) OERegistries.getDoubleRegistry(new ResourceLocation(OtyacraftEngine.MODID, "mod_color"));
        if (colors.getMap().containsKey(modid))
            return colors.getMap().get(modid);
        return 0;
    }

}