package vazkii.quark.management.client.gui;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.translation.I18n;
import scala.actors.threadpool.Arrays;
import vazkii.arl.util.RenderHelper;
import vazkii.quark.base.client.IParentedGui;
import vazkii.quark.base.lib.LibMisc;
import vazkii.quark.management.feature.DeleteItems;

public class GuiButtonTrash extends GuiButton implements IParentedGui {

	public final GuiScreen parent;
	public final int shiftX, shiftY;
	final boolean needsShift;
	public boolean ready;

	public GuiButtonTrash(GuiScreen parent, int id, int shiftX, int shiftY, boolean needsShift) {
		super(id, 0, 0, 16, 16, "");
		this.parent = parent;
		this.shiftX = shiftX;
		this.shiftY = shiftY;
		this.needsShift = needsShift;
	}
	
	@Override
	public void drawButton(Minecraft par1Minecraft, int par2, int par3, float pticks) {
		hovered = par2 >= x && par3 >= y && par2 < x + width && par3 < y + height;
		int k = getHoverState(hovered);

		int u = 0;
		int v = 192;
		
		boolean canDelete = false;
		boolean open = false;
		
		if(parent instanceof GuiContainer) {
			EntityPlayer player = par1Minecraft.player;
			ItemStack hovered = player.inventory.getItemStack();
			canDelete = DeleteItems.canItemBeDeleted(hovered);
			open = canDelete && (!needsShift || parent.isShiftKeyDown());
			
			if(open)
				u += 16;
		}
		
		par1Minecraft.renderEngine.bindTexture(LibMisc.GENERAL_ICONS_RESOURCE);
		GlStateManager.color(1F, 1F, 1F, 1F);
		drawIcon(u, v);
		
		ready = false;
		if(k == 2 && canDelete) {
			if(open)
				ready = true;
			
			GlStateManager.pushMatrix();
			String tooltip = I18n.translateToLocal(open ? "quarkmisc.trashButtonOpen" : "quarkmisc.trashButtonShift"); 
			int len = Minecraft.getMinecraft().fontRenderer.getStringWidth(tooltip);
			int tooltipShift = 2;
			List<String> tooltipList = Arrays.asList(new String[]{ tooltip });
			
			RenderHelper.renderTooltip(par2 + tooltipShift, par3 + 8, tooltipList);
			GlStateManager.popMatrix();
		}
	}

	protected void drawIcon(int u, int v) {
		drawTexturedModalRect(x, y, u, v, 16, 16);
	}
	
	@Override
    public void playPressSound(SoundHandler soundHandlerIn) {
        // NO-OP
    }

	
	@Override
	public GuiScreen getParent() {
		return parent;
	}
	
}
