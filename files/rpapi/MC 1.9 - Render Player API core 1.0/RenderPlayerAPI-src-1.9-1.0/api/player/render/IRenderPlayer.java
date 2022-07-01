// ==================================================================
// This file is part of Render Player API.
//
// Render Player API is free software: you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public License
// as published by the Free Software Foundation, either version 3 of
// the License, or (at your option) any later version.
//
// Render Player API is distributed in the hope that it will be
// useful, but WITHOUT ANY WARRANTY; without even the implied
// warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
// See the GNU Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public
// License and the GNU General Public License along with Render
// Player API. If not, see <http://www.gnu.org/licenses/>.
// ==================================================================

package api.player.render;

public interface IRenderPlayer
{
	RenderPlayerBase getRenderPlayerBase(String baseId);

	java.util.Set<String> getRenderPlayerBaseIds();

	Object dynamic(String key, Object[] parameters);

	boolean realBindEntityTexture(net.minecraft.client.entity.AbstractClientPlayer paramAbstractClientPlayer);

	boolean superBindEntityTexture(net.minecraft.client.entity.AbstractClientPlayer paramAbstractClientPlayer);

	boolean localBindEntityTexture(net.minecraft.client.entity.AbstractClientPlayer paramAbstractClientPlayer);

	void realBindTexture(net.minecraft.util.ResourceLocation paramResourceLocation);

	void superBindTexture(net.minecraft.util.ResourceLocation paramResourceLocation);

	void localBindTexture(net.minecraft.util.ResourceLocation paramResourceLocation);

	boolean realCanRenderName(net.minecraft.client.entity.AbstractClientPlayer paramAbstractClientPlayer);

	boolean superCanRenderName(net.minecraft.client.entity.AbstractClientPlayer paramAbstractClientPlayer);

	boolean localCanRenderName(net.minecraft.client.entity.AbstractClientPlayer paramAbstractClientPlayer);

	void realDoRender(net.minecraft.client.entity.AbstractClientPlayer paramAbstractClientPlayer, double paramDouble1, double paramDouble2, double paramDouble3, float paramFloat1, float paramFloat2);

	void localDoRender(net.minecraft.client.entity.AbstractClientPlayer paramAbstractClientPlayer, double paramDouble1, double paramDouble2, double paramDouble3, float paramFloat1, float paramFloat2);

	void realDoRenderShadowAndFire(net.minecraft.client.entity.AbstractClientPlayer paramAbstractClientPlayer, double paramDouble1, double paramDouble2, double paramDouble3, float paramFloat1, float paramFloat2);

	void superDoRenderShadowAndFire(net.minecraft.client.entity.AbstractClientPlayer paramAbstractClientPlayer, double paramDouble1, double paramDouble2, double paramDouble3, float paramFloat1, float paramFloat2);

	void localDoRenderShadowAndFire(net.minecraft.client.entity.AbstractClientPlayer paramAbstractClientPlayer, double paramDouble1, double paramDouble2, double paramDouble3, float paramFloat1, float paramFloat2);

	net.minecraft.util.ResourceLocation realGetEntityTexture(net.minecraft.client.entity.AbstractClientPlayer paramAbstractClientPlayer);

	net.minecraft.util.ResourceLocation localGetEntityTexture(net.minecraft.client.entity.AbstractClientPlayer paramAbstractClientPlayer);

	net.minecraft.client.gui.FontRenderer realGetFontRendererFromRenderManager();

	net.minecraft.client.gui.FontRenderer superGetFontRendererFromRenderManager();

	net.minecraft.client.gui.FontRenderer localGetFontRendererFromRenderManager();

	net.minecraft.client.model.ModelPlayer realGetMainModel();

	net.minecraft.client.model.ModelPlayer superGetMainModel();

	net.minecraft.client.model.ModelPlayer localGetMainModel();

	net.minecraft.client.renderer.entity.RenderManager realGetRenderManager();

	net.minecraft.client.renderer.entity.RenderManager superGetRenderManager();

	net.minecraft.client.renderer.entity.RenderManager localGetRenderManager();

	int realGetTeamColor(net.minecraft.client.entity.AbstractClientPlayer paramAbstractClientPlayer);

	int superGetTeamColor(net.minecraft.client.entity.AbstractClientPlayer paramAbstractClientPlayer);

	int localGetTeamColor(net.minecraft.client.entity.AbstractClientPlayer paramAbstractClientPlayer);

	boolean realIsMultipass();

	boolean superIsMultipass();

	boolean localIsMultipass();

	void realPreRenderCallback(net.minecraft.client.entity.AbstractClientPlayer paramAbstractClientPlayer, float paramFloat);

	void localPreRenderCallback(net.minecraft.client.entity.AbstractClientPlayer paramAbstractClientPlayer, float paramFloat);

	void realRenderEntityName(net.minecraft.client.entity.AbstractClientPlayer paramAbstractClientPlayer, double paramDouble1, double paramDouble2, double paramDouble3, String paramString, double paramDouble4);

	void localRenderEntityName(net.minecraft.client.entity.AbstractClientPlayer paramAbstractClientPlayer, double paramDouble1, double paramDouble2, double paramDouble3, String paramString, double paramDouble4);

	void realRenderLeftArm(net.minecraft.client.entity.AbstractClientPlayer paramAbstractClientPlayer);

	void localRenderLeftArm(net.minecraft.client.entity.AbstractClientPlayer paramAbstractClientPlayer);

	void realRenderLivingAt(net.minecraft.client.entity.AbstractClientPlayer paramAbstractClientPlayer, double paramDouble1, double paramDouble2, double paramDouble3);

	void localRenderLivingAt(net.minecraft.client.entity.AbstractClientPlayer paramAbstractClientPlayer, double paramDouble1, double paramDouble2, double paramDouble3);

	void realRenderLivingLabel(net.minecraft.client.entity.AbstractClientPlayer paramAbstractClientPlayer, String paramString, double paramDouble1, double paramDouble2, double paramDouble3, int paramInt);

	void superRenderLivingLabel(net.minecraft.client.entity.AbstractClientPlayer paramAbstractClientPlayer, String paramString, double paramDouble1, double paramDouble2, double paramDouble3, int paramInt);

	void localRenderLivingLabel(net.minecraft.client.entity.AbstractClientPlayer paramAbstractClientPlayer, String paramString, double paramDouble1, double paramDouble2, double paramDouble3, int paramInt);

	void realRenderMultipass(net.minecraft.client.entity.AbstractClientPlayer paramAbstractClientPlayer, double paramDouble1, double paramDouble2, double paramDouble3, float paramFloat1, float paramFloat2);

	void superRenderMultipass(net.minecraft.client.entity.AbstractClientPlayer paramAbstractClientPlayer, double paramDouble1, double paramDouble2, double paramDouble3, float paramFloat1, float paramFloat2);

	void localRenderMultipass(net.minecraft.client.entity.AbstractClientPlayer paramAbstractClientPlayer, double paramDouble1, double paramDouble2, double paramDouble3, float paramFloat1, float paramFloat2);

	void realRenderName(net.minecraft.client.entity.AbstractClientPlayer paramAbstractClientPlayer, double paramDouble1, double paramDouble2, double paramDouble3);

	void superRenderName(net.minecraft.client.entity.AbstractClientPlayer paramAbstractClientPlayer, double paramDouble1, double paramDouble2, double paramDouble3);

	void localRenderName(net.minecraft.client.entity.AbstractClientPlayer paramAbstractClientPlayer, double paramDouble1, double paramDouble2, double paramDouble3);

	void realRenderRightArm(net.minecraft.client.entity.AbstractClientPlayer paramAbstractClientPlayer);

	void localRenderRightArm(net.minecraft.client.entity.AbstractClientPlayer paramAbstractClientPlayer);

	void realRotateCorpse(net.minecraft.client.entity.AbstractClientPlayer paramAbstractClientPlayer, float paramFloat1, float paramFloat2, float paramFloat3);

	void localRotateCorpse(net.minecraft.client.entity.AbstractClientPlayer paramAbstractClientPlayer, float paramFloat1, float paramFloat2, float paramFloat3);

	void realSetModelVisibilities(net.minecraft.client.entity.AbstractClientPlayer paramAbstractClientPlayer);

	void localSetModelVisibilities(net.minecraft.client.entity.AbstractClientPlayer paramAbstractClientPlayer);

	void realSetRenderOutlines(boolean paramBoolean);

	void superSetRenderOutlines(boolean paramBoolean);

	void localSetRenderOutlines(boolean paramBoolean);

	boolean realShouldRender(net.minecraft.client.entity.AbstractClientPlayer paramAbstractClientPlayer, net.minecraft.client.renderer.culling.ICamera paramICamera, double paramDouble1, double paramDouble2, double paramDouble3);

	boolean superShouldRender(net.minecraft.client.entity.AbstractClientPlayer paramAbstractClientPlayer, net.minecraft.client.renderer.culling.ICamera paramICamera, double paramDouble1, double paramDouble2, double paramDouble3);

	boolean localShouldRender(net.minecraft.client.entity.AbstractClientPlayer paramAbstractClientPlayer, net.minecraft.client.renderer.culling.ICamera paramICamera, double paramDouble1, double paramDouble2, double paramDouble3);

	void realTransformHeldFull3DItemLayer();

	void superTransformHeldFull3DItemLayer();

	void localTransformHeldFull3DItemLayer();

	net.minecraft.client.renderer.entity.RenderManager getRenderManagerField();

	boolean getRenderOutlinesField();

	void setRenderOutlinesField(boolean renderOutlines);

	float getShadowOpaqueField();

	void setShadowOpaqueField(float shadowOpaque);

	float getShadowSizeField();

	void setShadowSizeField(float shadowSize);

	boolean getSmallArmsField();

	void setSmallArmsField(boolean smallArms);

}
