package red.felnull.otyacraftengine.client.gui.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.util.text.ITextComponent;

public abstract class IkisugiContainerScreen<T extends Container> extends ContainerScreen<T> {
    public IkisugiContainerScreen(T screenContainer, PlayerInventory playerInventory, ITextComponent titleIn) {
        super(screenContainer, playerInventory, titleIn);
    }


    //drawGuiContainerBackgroundLayer
    @Override
    protected void func_230450_a_(MatrixStack matx, float partTick, int mouseX, int mouseY) {
        drawGuiContainerBackgroundLayerByIKSG(matx, partTick, mouseX, mouseY);
    }

    //バッググラウンド描画
    protected abstract void drawGuiContainerBackgroundLayerByIKSG(MatrixStack matx, float partTick, int mouseX, int mouseY);

    //以下IkisugiScreen
    //init
    @Override
    public void func_231160_c_() {
        this.initByIKSG();
    }

    //render
    @Override
    public void func_230430_a_(MatrixStack matrix, int mouseX, int mouseY, float parTick) {
        this.renderByIKSG(matrix, mouseX, mouseY, parTick);
    }

    //keyPressed
    @Override
    public boolean func_231046_a_(int p_231046_1_, int p_231046_2_, int p_231046_3_) {
        return keyPressedByIKSG(p_231046_1_, p_231046_2_, p_231046_3_);
    }

    //onClose
    @Override
    public void func_231175_as__() {
        onCloseByIKSG();
    }

    //isPauseScreen
    @Override
    public boolean func_231177_au__() {
        return isPauseScreenByIKSG();
    }

    //tick
    @Override
    public void func_231023_e_() {
        this.tickByIKSG();
    }

    //renderHoveredToolTip
    @Override
    protected void func_230459_a_(MatrixStack matrix, int mouseX, int mouseY) {
        this.renderHoveredToolTipByIKSG(matrix, mouseX, mouseY);
    }

    //初期化
    public void initByIKSG() {
        super.func_231160_c_();
    }

    //描画
    public void renderByIKSG(MatrixStack matrix, int mouseX, int mouseY, float parTick) {
        super.func_230430_a_(matrix, mouseX, mouseY, parTick);
    }

    //キー判定
    public boolean keyPressedByIKSG(int p_keyPressed_1_, int p_keyPressed_2_, int p_keyPressed_3_) {
        return super.func_231046_a_(p_keyPressed_1_, p_keyPressed_2_, p_keyPressed_3_);
    }

    //文字を中央から描画
    public void drawCenterStringByIKSG(MatrixStack matrix, ITextComponent textComp, int x, int y, int color) {
        this.func_238472_a_(matrix, this.field_230712_o_, textComp, x, y, color);
    }

    //ボタン等のウィジェット追加
    protected <T extends Widget> T addWidgetByIKSG(T widget) {
        return func_230480_a_(widget);
    }

    //幅
    public int getWidthByIKSG() {
        return field_230708_k_;
    }

    //高さ
    public int getHeightByIKSG() {
        return field_230709_l_;
    }

    //閉じる
    public void onCloseByIKSG() {
        super.func_231175_as__();
    }

    //バッググラウ描画
    public void renderBackgroundByIKSG(MatrixStack matrix) {
        this.func_230446_a_(matrix);
    }

    //開いてる時にワールドを進めるかどうか
    public boolean isPauseScreenByIKSG() {
        return super.func_231177_au__();
    }

    public void renderDirtBackgroundByIKSG(int intege) {
        this.func_231165_f_(intege);
    }

    //ティック
    public void tickByIKSG() {
        super.func_231023_e_();
    }

    //ツールチップ？
    protected void renderHoveredToolTipByIKSG(MatrixStack matrix, int mouseX, int mouseY) {
        super.func_230459_a_(matrix, mouseX, mouseY);
    }

}