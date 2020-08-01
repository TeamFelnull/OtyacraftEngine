package red.felnull.otyacraftengine.handler;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.ChunkPos;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.event.server.FMLServerStoppingEvent;
import red.felnull.otyacraftengine.OtyacraftEngine;
import red.felnull.otyacraftengine.api.event.common.ResponseEvent;
import red.felnull.otyacraftengine.api.event.server.StraddleChunkEvent;
import red.felnull.otyacraftengine.api.event.server.WorldDataEvent;
import red.felnull.otyacraftengine.data.ReceiveTextureLoder;
import red.felnull.otyacraftengine.data.ServerDataSendReservation;
import red.felnull.otyacraftengine.data.ServerDataSender;
import red.felnull.otyacraftengine.util.PlayerHelper;

import java.util.HashMap;
import java.util.Map;

public class ServerHandler {
    private static final ResourceLocation SERVER_RESPONSE = new ResourceLocation(OtyacraftEngine.MODID, "server_response");

    @SubscribeEvent
    public static void onServetTick(TickEvent.ServerTickEvent e) {
        //   WorldDataManager.instance().sync(ServerHelper.getMinecraftServer());
        ServerDataSendReservation.tick();
    }

    @SubscribeEvent
    public static void onServerStarting(FMLServerStartingEvent e) {
        WorldDataEvent.load(e.getServer(), null, false);
        ServerDataSender.srlogsGziping();
    }

    @SubscribeEvent
    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent e) {
        if (!e.getPlayer().world.isRemote)
            WorldDataEvent.load(e.getPlayer().getServer(), (ServerPlayerEntity) e.getPlayer(), true);
    }

    @SubscribeEvent
    public static void onPlayerLogout(PlayerEvent.PlayerLoggedOutEvent e) {
        if (!e.getPlayer().world.isRemote) {
            WorldDataEvent.save(e.getPlayer().getServer(), (ServerPlayerEntity) e.getPlayer(), true);
            WorldDataEvent.unload(e.getPlayer().getServer(), (ServerPlayerEntity) e.getPlayer(), true);
        }
    }

    @SubscribeEvent
    public static void onServerStopping(FMLServerStoppingEvent e) {
        WorldDataEvent.save(e.getServer(), null, false);
        WorldDataEvent.unload(e.getServer(), null, false);
    }

    @SubscribeEvent
    public static void onWorldSave(WorldEvent.Save e) {
        WorldDataEvent.save(e.getWorld().getWorld().getServer(), null, true);
    }

    @SubscribeEvent
    public static void onClientResponse(ResponseEvent.Client e) {
        if (e.getLocation().equals(SERVER_RESPONSE)) {
            if (e.getId() == 0) {
                ServerDataSender.response(PlayerHelper.getUUID(e.getPlayer()), e.getMessage());
            }
        } else if (e.getLocation().equals(new ResourceLocation(OtyacraftEngine.MODID, "textuerrequest"))) {
            if (e.getId() == 0) {
                ReceiveTextureLoder.instance().requestedTextuerSendServer(e.getData().getString("index"), e.getPlayer(), new ResourceLocation(e.getData().getString("location")), e.getMessage());
            }
        }
    }

    public static Map<PlayerEntity, ChunkPos> PLAYER_CPOS = new HashMap<PlayerEntity, ChunkPos>();
    public static Map<PlayerEntity, ResourceLocation> PLAYER_DIMS = new HashMap<PlayerEntity, ResourceLocation>();

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent e) {
        if (!e.player.world.isRemote) {
            if (!PLAYER_DIMS.containsKey(e.player)) {
                PLAYER_DIMS.put(e.player, e.player.world.func_234923_W_().func_240901_a_());
            } else {
                if (!PLAYER_DIMS.get(e.player).equals(e.player.world.func_234923_W_().func_240901_a_())) {
                    PLAYER_DIMS.put(e.player, e.player.world.func_234923_W_().func_240901_a_());
                    PLAYER_CPOS.remove(e.player);
                }
            }
            if (!PLAYER_CPOS.containsKey(e.player)) {
                MinecraftForge.EVENT_BUS.post(new StraddleChunkEvent(e.player, new ChunkPos(e.player.func_233580_cy_()), new ChunkPos(e.player.func_233580_cy_())));
                PLAYER_CPOS.put(e.player, new ChunkPos(e.player.func_233580_cy_()));
            } else {
                if (!PLAYER_CPOS.get(e.player).equals(new ChunkPos(e.player.func_233580_cy_()))) {
                    MinecraftForge.EVENT_BUS.post(new StraddleChunkEvent(e.player, PLAYER_CPOS.get(e.player), new ChunkPos(e.player.func_233580_cy_())));
                    PLAYER_CPOS.put(e.player, new ChunkPos(e.player.func_233580_cy_()));
                }
            }
        }
    }
/*
    @SubscribeEvent
    public static void onStraddleChunk(StraddleChunkEvent e) {

    }
    */
}
