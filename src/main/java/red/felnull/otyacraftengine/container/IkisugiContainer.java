package red.felnull.otyacraftengine.container;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nullable;

public abstract class IkisugiContainer extends Container {
    protected IInventory inventory;
    protected BlockPos pos;
    protected PlayerInventory playerInventory;

    public IkisugiContainer(@Nullable ContainerType<?> type, int windowId, PlayerInventory playerInventory, IInventory inventory, BlockPos pos, int plslotX, int plslotY) {
        super(type, windowId);
        this.inventory = inventory;
        this.pos = pos;
        this.playerInventory = playerInventory;
        inventory.openInventory(playerInventory.player);
        //def 8 142
        addPlayerSlot(plslotX, plslotY);
    }

    public IkisugiContainer(@Nullable ContainerType<?> type, int windowId, PlayerInventory playerInventory, IInventory inventory, int plslotX, int plslotY) {
        super(type, windowId);
        this.inventory = inventory;
        this.playerInventory = playerInventory;
        inventory.openInventory(playerInventory.player);
        //def 8 142
        addPlayerSlot(plslotX, plslotY);
    }

    public IkisugiContainer(@Nullable ContainerType<?> type, int windowId, PlayerInventory playerInventory, int plslotX, int plslotY) {
        super(type, windowId);
        this.playerInventory = playerInventory;
        //def 8 142
        addPlayerSlot(plslotX, plslotY);
    }

    public IkisugiContainer(@Nullable ContainerType<?> type, int windowId, PlayerInventory playerInventory, BlockPos pos, int plslotX, int plslotY) {
        super(type, windowId);
        this.pos = pos;
        this.playerInventory = playerInventory;
        //def 8 142
        addPlayerSlot(plslotX, plslotY);
    }

    public IkisugiContainer(@Nullable ContainerType<?> type, int windowId, BlockPos pos) {
        super(type, windowId);
        this.pos = pos;
    }

    public IkisugiContainer(@Nullable ContainerType<?> type, int windowId) {
        super(type, windowId);
    }

    private void addPlayerSlot(int x, int y) {
        if (playerInventory == null)
            return;
        for (int k = 0; k < 3; ++k) {
            for (int i1 = 0; i1 < 9; ++i1) {
                this.addSlot(new Slot(playerInventory, i1 + k * 9 + 9, 8 + i1 * 18, 84 + k * 18));
            }
        }
        for (int l = 0; l < 9; ++l) {
            this.addSlot(new Slot(playerInventory, l, x + l * 18, y));
        }
    }

    public IInventory getIInventory() {
        return inventory;
    }

    public BlockPos getPos() {
        return pos;
    }

    public PlayerInventory getPlayerInventory() {
        return playerInventory;
    }

    @Override
    public void onContainerClosed(PlayerEntity playerEntity) {
        super.onContainerClosed(playerEntity);
        if (inventory != null) {
            this.inventory.closeInventory(playerEntity);
        }
    }
}