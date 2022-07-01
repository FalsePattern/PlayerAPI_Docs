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

import java.util.*;

public final class RenderPlayerBaseSorting
{
	private String[] beforeLocalConstructingSuperiors = null;
	private String[] beforeLocalConstructingInferiors = null;
	private String[] afterLocalConstructingSuperiors = null;
	private String[] afterLocalConstructingInferiors = null;

	private Map<String, String[]> dynamicBeforeSuperiors = null;
	private Map<String, String[]> dynamicBeforeInferiors = null;
	private Map<String, String[]> dynamicOverrideSuperiors = null;
	private Map<String, String[]> dynamicOverrideInferiors = null;
	private Map<String, String[]> dynamicAfterSuperiors = null;
	private Map<String, String[]> dynamicAfterInferiors = null;

	private String[] beforeBindEntityTextureSuperiors = null;
	private String[] beforeBindEntityTextureInferiors = null;
	private String[] overrideBindEntityTextureSuperiors = null;
	private String[] overrideBindEntityTextureInferiors = null;
	private String[] afterBindEntityTextureSuperiors = null;
	private String[] afterBindEntityTextureInferiors = null;

	private String[] beforeBindTextureSuperiors = null;
	private String[] beforeBindTextureInferiors = null;
	private String[] overrideBindTextureSuperiors = null;
	private String[] overrideBindTextureInferiors = null;
	private String[] afterBindTextureSuperiors = null;
	private String[] afterBindTextureInferiors = null;

	private String[] beforeCanRenderNameSuperiors = null;
	private String[] beforeCanRenderNameInferiors = null;
	private String[] overrideCanRenderNameSuperiors = null;
	private String[] overrideCanRenderNameInferiors = null;
	private String[] afterCanRenderNameSuperiors = null;
	private String[] afterCanRenderNameInferiors = null;

	private String[] beforeDoRenderSuperiors = null;
	private String[] beforeDoRenderInferiors = null;
	private String[] overrideDoRenderSuperiors = null;
	private String[] overrideDoRenderInferiors = null;
	private String[] afterDoRenderSuperiors = null;
	private String[] afterDoRenderInferiors = null;

	private String[] beforeDoRenderShadowAndFireSuperiors = null;
	private String[] beforeDoRenderShadowAndFireInferiors = null;
	private String[] overrideDoRenderShadowAndFireSuperiors = null;
	private String[] overrideDoRenderShadowAndFireInferiors = null;
	private String[] afterDoRenderShadowAndFireSuperiors = null;
	private String[] afterDoRenderShadowAndFireInferiors = null;

	private String[] beforeGetEntityTextureSuperiors = null;
	private String[] beforeGetEntityTextureInferiors = null;
	private String[] overrideGetEntityTextureSuperiors = null;
	private String[] overrideGetEntityTextureInferiors = null;
	private String[] afterGetEntityTextureSuperiors = null;
	private String[] afterGetEntityTextureInferiors = null;

	private String[] beforeGetFontRendererFromRenderManagerSuperiors = null;
	private String[] beforeGetFontRendererFromRenderManagerInferiors = null;
	private String[] overrideGetFontRendererFromRenderManagerSuperiors = null;
	private String[] overrideGetFontRendererFromRenderManagerInferiors = null;
	private String[] afterGetFontRendererFromRenderManagerSuperiors = null;
	private String[] afterGetFontRendererFromRenderManagerInferiors = null;

	private String[] beforeGetMainModelSuperiors = null;
	private String[] beforeGetMainModelInferiors = null;
	private String[] overrideGetMainModelSuperiors = null;
	private String[] overrideGetMainModelInferiors = null;
	private String[] afterGetMainModelSuperiors = null;
	private String[] afterGetMainModelInferiors = null;

	private String[] beforeGetRenderManagerSuperiors = null;
	private String[] beforeGetRenderManagerInferiors = null;
	private String[] overrideGetRenderManagerSuperiors = null;
	private String[] overrideGetRenderManagerInferiors = null;
	private String[] afterGetRenderManagerSuperiors = null;
	private String[] afterGetRenderManagerInferiors = null;

	private String[] beforeGetTeamColorSuperiors = null;
	private String[] beforeGetTeamColorInferiors = null;
	private String[] overrideGetTeamColorSuperiors = null;
	private String[] overrideGetTeamColorInferiors = null;
	private String[] afterGetTeamColorSuperiors = null;
	private String[] afterGetTeamColorInferiors = null;

	private String[] beforeIsMultipassSuperiors = null;
	private String[] beforeIsMultipassInferiors = null;
	private String[] overrideIsMultipassSuperiors = null;
	private String[] overrideIsMultipassInferiors = null;
	private String[] afterIsMultipassSuperiors = null;
	private String[] afterIsMultipassInferiors = null;

	private String[] beforePreRenderCallbackSuperiors = null;
	private String[] beforePreRenderCallbackInferiors = null;
	private String[] overridePreRenderCallbackSuperiors = null;
	private String[] overridePreRenderCallbackInferiors = null;
	private String[] afterPreRenderCallbackSuperiors = null;
	private String[] afterPreRenderCallbackInferiors = null;

	private String[] beforeRenderEntityNameSuperiors = null;
	private String[] beforeRenderEntityNameInferiors = null;
	private String[] overrideRenderEntityNameSuperiors = null;
	private String[] overrideRenderEntityNameInferiors = null;
	private String[] afterRenderEntityNameSuperiors = null;
	private String[] afterRenderEntityNameInferiors = null;

	private String[] beforeRenderLeftArmSuperiors = null;
	private String[] beforeRenderLeftArmInferiors = null;
	private String[] overrideRenderLeftArmSuperiors = null;
	private String[] overrideRenderLeftArmInferiors = null;
	private String[] afterRenderLeftArmSuperiors = null;
	private String[] afterRenderLeftArmInferiors = null;

	private String[] beforeRenderLivingAtSuperiors = null;
	private String[] beforeRenderLivingAtInferiors = null;
	private String[] overrideRenderLivingAtSuperiors = null;
	private String[] overrideRenderLivingAtInferiors = null;
	private String[] afterRenderLivingAtSuperiors = null;
	private String[] afterRenderLivingAtInferiors = null;

	private String[] beforeRenderLivingLabelSuperiors = null;
	private String[] beforeRenderLivingLabelInferiors = null;
	private String[] overrideRenderLivingLabelSuperiors = null;
	private String[] overrideRenderLivingLabelInferiors = null;
	private String[] afterRenderLivingLabelSuperiors = null;
	private String[] afterRenderLivingLabelInferiors = null;

	private String[] beforeRenderMultipassSuperiors = null;
	private String[] beforeRenderMultipassInferiors = null;
	private String[] overrideRenderMultipassSuperiors = null;
	private String[] overrideRenderMultipassInferiors = null;
	private String[] afterRenderMultipassSuperiors = null;
	private String[] afterRenderMultipassInferiors = null;

	private String[] beforeRenderNameSuperiors = null;
	private String[] beforeRenderNameInferiors = null;
	private String[] overrideRenderNameSuperiors = null;
	private String[] overrideRenderNameInferiors = null;
	private String[] afterRenderNameSuperiors = null;
	private String[] afterRenderNameInferiors = null;

	private String[] beforeRenderRightArmSuperiors = null;
	private String[] beforeRenderRightArmInferiors = null;
	private String[] overrideRenderRightArmSuperiors = null;
	private String[] overrideRenderRightArmInferiors = null;
	private String[] afterRenderRightArmSuperiors = null;
	private String[] afterRenderRightArmInferiors = null;

	private String[] beforeRotateCorpseSuperiors = null;
	private String[] beforeRotateCorpseInferiors = null;
	private String[] overrideRotateCorpseSuperiors = null;
	private String[] overrideRotateCorpseInferiors = null;
	private String[] afterRotateCorpseSuperiors = null;
	private String[] afterRotateCorpseInferiors = null;

	private String[] beforeSetModelVisibilitiesSuperiors = null;
	private String[] beforeSetModelVisibilitiesInferiors = null;
	private String[] overrideSetModelVisibilitiesSuperiors = null;
	private String[] overrideSetModelVisibilitiesInferiors = null;
	private String[] afterSetModelVisibilitiesSuperiors = null;
	private String[] afterSetModelVisibilitiesInferiors = null;

	private String[] beforeSetRenderOutlinesSuperiors = null;
	private String[] beforeSetRenderOutlinesInferiors = null;
	private String[] overrideSetRenderOutlinesSuperiors = null;
	private String[] overrideSetRenderOutlinesInferiors = null;
	private String[] afterSetRenderOutlinesSuperiors = null;
	private String[] afterSetRenderOutlinesInferiors = null;

	private String[] beforeShouldRenderSuperiors = null;
	private String[] beforeShouldRenderInferiors = null;
	private String[] overrideShouldRenderSuperiors = null;
	private String[] overrideShouldRenderInferiors = null;
	private String[] afterShouldRenderSuperiors = null;
	private String[] afterShouldRenderInferiors = null;

	private String[] beforeTransformHeldFull3DItemLayerSuperiors = null;
	private String[] beforeTransformHeldFull3DItemLayerInferiors = null;
	private String[] overrideTransformHeldFull3DItemLayerSuperiors = null;
	private String[] overrideTransformHeldFull3DItemLayerInferiors = null;
	private String[] afterTransformHeldFull3DItemLayerSuperiors = null;
	private String[] afterTransformHeldFull3DItemLayerInferiors = null;


	public String[] getBeforeLocalConstructingSuperiors()
	{
		return beforeLocalConstructingSuperiors;
	}

	public String[] getBeforeLocalConstructingInferiors()
	{
		return beforeLocalConstructingInferiors;
	}

	public String[] getAfterLocalConstructingSuperiors()
	{
		return afterLocalConstructingSuperiors;
	}

	public String[] getAfterLocalConstructingInferiors()
	{
		return afterLocalConstructingInferiors;
	}

	public void setBeforeLocalConstructingSuperiors(String[] value)
	{
		beforeLocalConstructingSuperiors = value;
	}

	public void setBeforeLocalConstructingInferiors(String[] value)
	{
		beforeLocalConstructingInferiors = value;
	}

	public void setAfterLocalConstructingSuperiors(String[] value)
	{
		afterLocalConstructingSuperiors = value;
	}

	public void setAfterLocalConstructingInferiors(String[] value)
	{
		afterLocalConstructingInferiors = value;
	}

	public Map<String, String[]> getDynamicBeforeSuperiors()
	{
		return dynamicBeforeSuperiors;
	}

	public Map<String, String[]> getDynamicBeforeInferiors()
	{
		return dynamicBeforeInferiors;
	}

	public Map<String, String[]> getDynamicOverrideSuperiors()
	{
		return dynamicOverrideSuperiors;
	}

	public Map<String, String[]> getDynamicOverrideInferiors()
	{
		return dynamicOverrideInferiors;
	}

	public Map<String, String[]> getDynamicAfterSuperiors()
	{
		return dynamicAfterSuperiors;
	}

	public Map<String, String[]> getDynamicAfterInferiors()
	{
		return dynamicAfterInferiors;
	}

	public void setDynamicBeforeSuperiors(String name, String[] superiors)
	{
		dynamicBeforeSuperiors = setDynamic(name, superiors, dynamicBeforeSuperiors);
	}

	public void setDynamicBeforeInferiors(String name, String[] inferiors)
	{
		dynamicBeforeInferiors = setDynamic(name, inferiors, dynamicBeforeInferiors);
	}

	public void setDynamicOverrideSuperiors(String name, String[] superiors)
	{
		dynamicOverrideSuperiors = setDynamic(name, superiors, dynamicOverrideSuperiors);
	}

	public void setDynamicOverrideInferiors(String name, String[] inferiors)
	{
		dynamicOverrideInferiors = setDynamic(name, inferiors, dynamicOverrideInferiors);
	}

	public void setDynamicAfterSuperiors(String name, String[] superiors)
	{
		dynamicAfterSuperiors = setDynamic(name, superiors, dynamicAfterSuperiors);
	}

	public void setDynamicAfterInferiors(String name, String[] inferiors)
	{
		dynamicAfterInferiors = setDynamic(name, inferiors, dynamicAfterInferiors);
	}

	private Map<String, String[]> setDynamic(String name, String[] names, Map<String, String[]> map)
	{
		if(name == null)
			throw new IllegalArgumentException("Parameter 'name' may not be null");

		if(names == null)
		{
			if(map != null)
				map.remove(name);
			return map;
		}

		if(map == null)
			map = new HashMap<String, String[]>();
		map.put(name, names);

		return map;
	}

	public String[] getBeforeBindEntityTextureSuperiors()
	{
		return beforeBindEntityTextureSuperiors;
	}

	public String[] getBeforeBindEntityTextureInferiors()
	{
		return beforeBindEntityTextureInferiors;
	}

	public String[] getOverrideBindEntityTextureSuperiors()
	{
		return overrideBindEntityTextureSuperiors;
	}

	public String[] getOverrideBindEntityTextureInferiors()
	{
		return overrideBindEntityTextureInferiors;
	}

	public String[] getAfterBindEntityTextureSuperiors()
	{
		return afterBindEntityTextureSuperiors;
	}

	public String[] getAfterBindEntityTextureInferiors()
	{
		return afterBindEntityTextureInferiors;
	}

	public void setBeforeBindEntityTextureSuperiors(String[] value)
	{
		beforeBindEntityTextureSuperiors = value;
	}

	public void setBeforeBindEntityTextureInferiors(String[] value)
	{
		beforeBindEntityTextureInferiors = value;
	}

	public void setOverrideBindEntityTextureSuperiors(String[] value)
	{
		overrideBindEntityTextureSuperiors = value;
	}

	public void setOverrideBindEntityTextureInferiors(String[] value)
	{
		overrideBindEntityTextureInferiors = value;
	}

	public void setAfterBindEntityTextureSuperiors(String[] value)
	{
		afterBindEntityTextureSuperiors = value;
	}

	public void setAfterBindEntityTextureInferiors(String[] value)
	{
		afterBindEntityTextureInferiors = value;
	}

	public String[] getBeforeBindTextureSuperiors()
	{
		return beforeBindTextureSuperiors;
	}

	public String[] getBeforeBindTextureInferiors()
	{
		return beforeBindTextureInferiors;
	}

	public String[] getOverrideBindTextureSuperiors()
	{
		return overrideBindTextureSuperiors;
	}

	public String[] getOverrideBindTextureInferiors()
	{
		return overrideBindTextureInferiors;
	}

	public String[] getAfterBindTextureSuperiors()
	{
		return afterBindTextureSuperiors;
	}

	public String[] getAfterBindTextureInferiors()
	{
		return afterBindTextureInferiors;
	}

	public void setBeforeBindTextureSuperiors(String[] value)
	{
		beforeBindTextureSuperiors = value;
	}

	public void setBeforeBindTextureInferiors(String[] value)
	{
		beforeBindTextureInferiors = value;
	}

	public void setOverrideBindTextureSuperiors(String[] value)
	{
		overrideBindTextureSuperiors = value;
	}

	public void setOverrideBindTextureInferiors(String[] value)
	{
		overrideBindTextureInferiors = value;
	}

	public void setAfterBindTextureSuperiors(String[] value)
	{
		afterBindTextureSuperiors = value;
	}

	public void setAfterBindTextureInferiors(String[] value)
	{
		afterBindTextureInferiors = value;
	}

	public String[] getBeforeCanRenderNameSuperiors()
	{
		return beforeCanRenderNameSuperiors;
	}

	public String[] getBeforeCanRenderNameInferiors()
	{
		return beforeCanRenderNameInferiors;
	}

	public String[] getOverrideCanRenderNameSuperiors()
	{
		return overrideCanRenderNameSuperiors;
	}

	public String[] getOverrideCanRenderNameInferiors()
	{
		return overrideCanRenderNameInferiors;
	}

	public String[] getAfterCanRenderNameSuperiors()
	{
		return afterCanRenderNameSuperiors;
	}

	public String[] getAfterCanRenderNameInferiors()
	{
		return afterCanRenderNameInferiors;
	}

	public void setBeforeCanRenderNameSuperiors(String[] value)
	{
		beforeCanRenderNameSuperiors = value;
	}

	public void setBeforeCanRenderNameInferiors(String[] value)
	{
		beforeCanRenderNameInferiors = value;
	}

	public void setOverrideCanRenderNameSuperiors(String[] value)
	{
		overrideCanRenderNameSuperiors = value;
	}

	public void setOverrideCanRenderNameInferiors(String[] value)
	{
		overrideCanRenderNameInferiors = value;
	}

	public void setAfterCanRenderNameSuperiors(String[] value)
	{
		afterCanRenderNameSuperiors = value;
	}

	public void setAfterCanRenderNameInferiors(String[] value)
	{
		afterCanRenderNameInferiors = value;
	}

	public String[] getBeforeDoRenderSuperiors()
	{
		return beforeDoRenderSuperiors;
	}

	public String[] getBeforeDoRenderInferiors()
	{
		return beforeDoRenderInferiors;
	}

	public String[] getOverrideDoRenderSuperiors()
	{
		return overrideDoRenderSuperiors;
	}

	public String[] getOverrideDoRenderInferiors()
	{
		return overrideDoRenderInferiors;
	}

	public String[] getAfterDoRenderSuperiors()
	{
		return afterDoRenderSuperiors;
	}

	public String[] getAfterDoRenderInferiors()
	{
		return afterDoRenderInferiors;
	}

	public void setBeforeDoRenderSuperiors(String[] value)
	{
		beforeDoRenderSuperiors = value;
	}

	public void setBeforeDoRenderInferiors(String[] value)
	{
		beforeDoRenderInferiors = value;
	}

	public void setOverrideDoRenderSuperiors(String[] value)
	{
		overrideDoRenderSuperiors = value;
	}

	public void setOverrideDoRenderInferiors(String[] value)
	{
		overrideDoRenderInferiors = value;
	}

	public void setAfterDoRenderSuperiors(String[] value)
	{
		afterDoRenderSuperiors = value;
	}

	public void setAfterDoRenderInferiors(String[] value)
	{
		afterDoRenderInferiors = value;
	}

	public String[] getBeforeDoRenderShadowAndFireSuperiors()
	{
		return beforeDoRenderShadowAndFireSuperiors;
	}

	public String[] getBeforeDoRenderShadowAndFireInferiors()
	{
		return beforeDoRenderShadowAndFireInferiors;
	}

	public String[] getOverrideDoRenderShadowAndFireSuperiors()
	{
		return overrideDoRenderShadowAndFireSuperiors;
	}

	public String[] getOverrideDoRenderShadowAndFireInferiors()
	{
		return overrideDoRenderShadowAndFireInferiors;
	}

	public String[] getAfterDoRenderShadowAndFireSuperiors()
	{
		return afterDoRenderShadowAndFireSuperiors;
	}

	public String[] getAfterDoRenderShadowAndFireInferiors()
	{
		return afterDoRenderShadowAndFireInferiors;
	}

	public void setBeforeDoRenderShadowAndFireSuperiors(String[] value)
	{
		beforeDoRenderShadowAndFireSuperiors = value;
	}

	public void setBeforeDoRenderShadowAndFireInferiors(String[] value)
	{
		beforeDoRenderShadowAndFireInferiors = value;
	}

	public void setOverrideDoRenderShadowAndFireSuperiors(String[] value)
	{
		overrideDoRenderShadowAndFireSuperiors = value;
	}

	public void setOverrideDoRenderShadowAndFireInferiors(String[] value)
	{
		overrideDoRenderShadowAndFireInferiors = value;
	}

	public void setAfterDoRenderShadowAndFireSuperiors(String[] value)
	{
		afterDoRenderShadowAndFireSuperiors = value;
	}

	public void setAfterDoRenderShadowAndFireInferiors(String[] value)
	{
		afterDoRenderShadowAndFireInferiors = value;
	}

	public String[] getBeforeGetEntityTextureSuperiors()
	{
		return beforeGetEntityTextureSuperiors;
	}

	public String[] getBeforeGetEntityTextureInferiors()
	{
		return beforeGetEntityTextureInferiors;
	}

	public String[] getOverrideGetEntityTextureSuperiors()
	{
		return overrideGetEntityTextureSuperiors;
	}

	public String[] getOverrideGetEntityTextureInferiors()
	{
		return overrideGetEntityTextureInferiors;
	}

	public String[] getAfterGetEntityTextureSuperiors()
	{
		return afterGetEntityTextureSuperiors;
	}

	public String[] getAfterGetEntityTextureInferiors()
	{
		return afterGetEntityTextureInferiors;
	}

	public void setBeforeGetEntityTextureSuperiors(String[] value)
	{
		beforeGetEntityTextureSuperiors = value;
	}

	public void setBeforeGetEntityTextureInferiors(String[] value)
	{
		beforeGetEntityTextureInferiors = value;
	}

	public void setOverrideGetEntityTextureSuperiors(String[] value)
	{
		overrideGetEntityTextureSuperiors = value;
	}

	public void setOverrideGetEntityTextureInferiors(String[] value)
	{
		overrideGetEntityTextureInferiors = value;
	}

	public void setAfterGetEntityTextureSuperiors(String[] value)
	{
		afterGetEntityTextureSuperiors = value;
	}

	public void setAfterGetEntityTextureInferiors(String[] value)
	{
		afterGetEntityTextureInferiors = value;
	}

	public String[] getBeforeGetFontRendererFromRenderManagerSuperiors()
	{
		return beforeGetFontRendererFromRenderManagerSuperiors;
	}

	public String[] getBeforeGetFontRendererFromRenderManagerInferiors()
	{
		return beforeGetFontRendererFromRenderManagerInferiors;
	}

	public String[] getOverrideGetFontRendererFromRenderManagerSuperiors()
	{
		return overrideGetFontRendererFromRenderManagerSuperiors;
	}

	public String[] getOverrideGetFontRendererFromRenderManagerInferiors()
	{
		return overrideGetFontRendererFromRenderManagerInferiors;
	}

	public String[] getAfterGetFontRendererFromRenderManagerSuperiors()
	{
		return afterGetFontRendererFromRenderManagerSuperiors;
	}

	public String[] getAfterGetFontRendererFromRenderManagerInferiors()
	{
		return afterGetFontRendererFromRenderManagerInferiors;
	}

	public void setBeforeGetFontRendererFromRenderManagerSuperiors(String[] value)
	{
		beforeGetFontRendererFromRenderManagerSuperiors = value;
	}

	public void setBeforeGetFontRendererFromRenderManagerInferiors(String[] value)
	{
		beforeGetFontRendererFromRenderManagerInferiors = value;
	}

	public void setOverrideGetFontRendererFromRenderManagerSuperiors(String[] value)
	{
		overrideGetFontRendererFromRenderManagerSuperiors = value;
	}

	public void setOverrideGetFontRendererFromRenderManagerInferiors(String[] value)
	{
		overrideGetFontRendererFromRenderManagerInferiors = value;
	}

	public void setAfterGetFontRendererFromRenderManagerSuperiors(String[] value)
	{
		afterGetFontRendererFromRenderManagerSuperiors = value;
	}

	public void setAfterGetFontRendererFromRenderManagerInferiors(String[] value)
	{
		afterGetFontRendererFromRenderManagerInferiors = value;
	}

	public String[] getBeforeGetMainModelSuperiors()
	{
		return beforeGetMainModelSuperiors;
	}

	public String[] getBeforeGetMainModelInferiors()
	{
		return beforeGetMainModelInferiors;
	}

	public String[] getOverrideGetMainModelSuperiors()
	{
		return overrideGetMainModelSuperiors;
	}

	public String[] getOverrideGetMainModelInferiors()
	{
		return overrideGetMainModelInferiors;
	}

	public String[] getAfterGetMainModelSuperiors()
	{
		return afterGetMainModelSuperiors;
	}

	public String[] getAfterGetMainModelInferiors()
	{
		return afterGetMainModelInferiors;
	}

	public void setBeforeGetMainModelSuperiors(String[] value)
	{
		beforeGetMainModelSuperiors = value;
	}

	public void setBeforeGetMainModelInferiors(String[] value)
	{
		beforeGetMainModelInferiors = value;
	}

	public void setOverrideGetMainModelSuperiors(String[] value)
	{
		overrideGetMainModelSuperiors = value;
	}

	public void setOverrideGetMainModelInferiors(String[] value)
	{
		overrideGetMainModelInferiors = value;
	}

	public void setAfterGetMainModelSuperiors(String[] value)
	{
		afterGetMainModelSuperiors = value;
	}

	public void setAfterGetMainModelInferiors(String[] value)
	{
		afterGetMainModelInferiors = value;
	}

	public String[] getBeforeGetRenderManagerSuperiors()
	{
		return beforeGetRenderManagerSuperiors;
	}

	public String[] getBeforeGetRenderManagerInferiors()
	{
		return beforeGetRenderManagerInferiors;
	}

	public String[] getOverrideGetRenderManagerSuperiors()
	{
		return overrideGetRenderManagerSuperiors;
	}

	public String[] getOverrideGetRenderManagerInferiors()
	{
		return overrideGetRenderManagerInferiors;
	}

	public String[] getAfterGetRenderManagerSuperiors()
	{
		return afterGetRenderManagerSuperiors;
	}

	public String[] getAfterGetRenderManagerInferiors()
	{
		return afterGetRenderManagerInferiors;
	}

	public void setBeforeGetRenderManagerSuperiors(String[] value)
	{
		beforeGetRenderManagerSuperiors = value;
	}

	public void setBeforeGetRenderManagerInferiors(String[] value)
	{
		beforeGetRenderManagerInferiors = value;
	}

	public void setOverrideGetRenderManagerSuperiors(String[] value)
	{
		overrideGetRenderManagerSuperiors = value;
	}

	public void setOverrideGetRenderManagerInferiors(String[] value)
	{
		overrideGetRenderManagerInferiors = value;
	}

	public void setAfterGetRenderManagerSuperiors(String[] value)
	{
		afterGetRenderManagerSuperiors = value;
	}

	public void setAfterGetRenderManagerInferiors(String[] value)
	{
		afterGetRenderManagerInferiors = value;
	}

	public String[] getBeforeGetTeamColorSuperiors()
	{
		return beforeGetTeamColorSuperiors;
	}

	public String[] getBeforeGetTeamColorInferiors()
	{
		return beforeGetTeamColorInferiors;
	}

	public String[] getOverrideGetTeamColorSuperiors()
	{
		return overrideGetTeamColorSuperiors;
	}

	public String[] getOverrideGetTeamColorInferiors()
	{
		return overrideGetTeamColorInferiors;
	}

	public String[] getAfterGetTeamColorSuperiors()
	{
		return afterGetTeamColorSuperiors;
	}

	public String[] getAfterGetTeamColorInferiors()
	{
		return afterGetTeamColorInferiors;
	}

	public void setBeforeGetTeamColorSuperiors(String[] value)
	{
		beforeGetTeamColorSuperiors = value;
	}

	public void setBeforeGetTeamColorInferiors(String[] value)
	{
		beforeGetTeamColorInferiors = value;
	}

	public void setOverrideGetTeamColorSuperiors(String[] value)
	{
		overrideGetTeamColorSuperiors = value;
	}

	public void setOverrideGetTeamColorInferiors(String[] value)
	{
		overrideGetTeamColorInferiors = value;
	}

	public void setAfterGetTeamColorSuperiors(String[] value)
	{
		afterGetTeamColorSuperiors = value;
	}

	public void setAfterGetTeamColorInferiors(String[] value)
	{
		afterGetTeamColorInferiors = value;
	}

	public String[] getBeforeIsMultipassSuperiors()
	{
		return beforeIsMultipassSuperiors;
	}

	public String[] getBeforeIsMultipassInferiors()
	{
		return beforeIsMultipassInferiors;
	}

	public String[] getOverrideIsMultipassSuperiors()
	{
		return overrideIsMultipassSuperiors;
	}

	public String[] getOverrideIsMultipassInferiors()
	{
		return overrideIsMultipassInferiors;
	}

	public String[] getAfterIsMultipassSuperiors()
	{
		return afterIsMultipassSuperiors;
	}

	public String[] getAfterIsMultipassInferiors()
	{
		return afterIsMultipassInferiors;
	}

	public void setBeforeIsMultipassSuperiors(String[] value)
	{
		beforeIsMultipassSuperiors = value;
	}

	public void setBeforeIsMultipassInferiors(String[] value)
	{
		beforeIsMultipassInferiors = value;
	}

	public void setOverrideIsMultipassSuperiors(String[] value)
	{
		overrideIsMultipassSuperiors = value;
	}

	public void setOverrideIsMultipassInferiors(String[] value)
	{
		overrideIsMultipassInferiors = value;
	}

	public void setAfterIsMultipassSuperiors(String[] value)
	{
		afterIsMultipassSuperiors = value;
	}

	public void setAfterIsMultipassInferiors(String[] value)
	{
		afterIsMultipassInferiors = value;
	}

	public String[] getBeforePreRenderCallbackSuperiors()
	{
		return beforePreRenderCallbackSuperiors;
	}

	public String[] getBeforePreRenderCallbackInferiors()
	{
		return beforePreRenderCallbackInferiors;
	}

	public String[] getOverridePreRenderCallbackSuperiors()
	{
		return overridePreRenderCallbackSuperiors;
	}

	public String[] getOverridePreRenderCallbackInferiors()
	{
		return overridePreRenderCallbackInferiors;
	}

	public String[] getAfterPreRenderCallbackSuperiors()
	{
		return afterPreRenderCallbackSuperiors;
	}

	public String[] getAfterPreRenderCallbackInferiors()
	{
		return afterPreRenderCallbackInferiors;
	}

	public void setBeforePreRenderCallbackSuperiors(String[] value)
	{
		beforePreRenderCallbackSuperiors = value;
	}

	public void setBeforePreRenderCallbackInferiors(String[] value)
	{
		beforePreRenderCallbackInferiors = value;
	}

	public void setOverridePreRenderCallbackSuperiors(String[] value)
	{
		overridePreRenderCallbackSuperiors = value;
	}

	public void setOverridePreRenderCallbackInferiors(String[] value)
	{
		overridePreRenderCallbackInferiors = value;
	}

	public void setAfterPreRenderCallbackSuperiors(String[] value)
	{
		afterPreRenderCallbackSuperiors = value;
	}

	public void setAfterPreRenderCallbackInferiors(String[] value)
	{
		afterPreRenderCallbackInferiors = value;
	}

	public String[] getBeforeRenderEntityNameSuperiors()
	{
		return beforeRenderEntityNameSuperiors;
	}

	public String[] getBeforeRenderEntityNameInferiors()
	{
		return beforeRenderEntityNameInferiors;
	}

	public String[] getOverrideRenderEntityNameSuperiors()
	{
		return overrideRenderEntityNameSuperiors;
	}

	public String[] getOverrideRenderEntityNameInferiors()
	{
		return overrideRenderEntityNameInferiors;
	}

	public String[] getAfterRenderEntityNameSuperiors()
	{
		return afterRenderEntityNameSuperiors;
	}

	public String[] getAfterRenderEntityNameInferiors()
	{
		return afterRenderEntityNameInferiors;
	}

	public void setBeforeRenderEntityNameSuperiors(String[] value)
	{
		beforeRenderEntityNameSuperiors = value;
	}

	public void setBeforeRenderEntityNameInferiors(String[] value)
	{
		beforeRenderEntityNameInferiors = value;
	}

	public void setOverrideRenderEntityNameSuperiors(String[] value)
	{
		overrideRenderEntityNameSuperiors = value;
	}

	public void setOverrideRenderEntityNameInferiors(String[] value)
	{
		overrideRenderEntityNameInferiors = value;
	}

	public void setAfterRenderEntityNameSuperiors(String[] value)
	{
		afterRenderEntityNameSuperiors = value;
	}

	public void setAfterRenderEntityNameInferiors(String[] value)
	{
		afterRenderEntityNameInferiors = value;
	}

	public String[] getBeforeRenderLeftArmSuperiors()
	{
		return beforeRenderLeftArmSuperiors;
	}

	public String[] getBeforeRenderLeftArmInferiors()
	{
		return beforeRenderLeftArmInferiors;
	}

	public String[] getOverrideRenderLeftArmSuperiors()
	{
		return overrideRenderLeftArmSuperiors;
	}

	public String[] getOverrideRenderLeftArmInferiors()
	{
		return overrideRenderLeftArmInferiors;
	}

	public String[] getAfterRenderLeftArmSuperiors()
	{
		return afterRenderLeftArmSuperiors;
	}

	public String[] getAfterRenderLeftArmInferiors()
	{
		return afterRenderLeftArmInferiors;
	}

	public void setBeforeRenderLeftArmSuperiors(String[] value)
	{
		beforeRenderLeftArmSuperiors = value;
	}

	public void setBeforeRenderLeftArmInferiors(String[] value)
	{
		beforeRenderLeftArmInferiors = value;
	}

	public void setOverrideRenderLeftArmSuperiors(String[] value)
	{
		overrideRenderLeftArmSuperiors = value;
	}

	public void setOverrideRenderLeftArmInferiors(String[] value)
	{
		overrideRenderLeftArmInferiors = value;
	}

	public void setAfterRenderLeftArmSuperiors(String[] value)
	{
		afterRenderLeftArmSuperiors = value;
	}

	public void setAfterRenderLeftArmInferiors(String[] value)
	{
		afterRenderLeftArmInferiors = value;
	}

	public String[] getBeforeRenderLivingAtSuperiors()
	{
		return beforeRenderLivingAtSuperiors;
	}

	public String[] getBeforeRenderLivingAtInferiors()
	{
		return beforeRenderLivingAtInferiors;
	}

	public String[] getOverrideRenderLivingAtSuperiors()
	{
		return overrideRenderLivingAtSuperiors;
	}

	public String[] getOverrideRenderLivingAtInferiors()
	{
		return overrideRenderLivingAtInferiors;
	}

	public String[] getAfterRenderLivingAtSuperiors()
	{
		return afterRenderLivingAtSuperiors;
	}

	public String[] getAfterRenderLivingAtInferiors()
	{
		return afterRenderLivingAtInferiors;
	}

	public void setBeforeRenderLivingAtSuperiors(String[] value)
	{
		beforeRenderLivingAtSuperiors = value;
	}

	public void setBeforeRenderLivingAtInferiors(String[] value)
	{
		beforeRenderLivingAtInferiors = value;
	}

	public void setOverrideRenderLivingAtSuperiors(String[] value)
	{
		overrideRenderLivingAtSuperiors = value;
	}

	public void setOverrideRenderLivingAtInferiors(String[] value)
	{
		overrideRenderLivingAtInferiors = value;
	}

	public void setAfterRenderLivingAtSuperiors(String[] value)
	{
		afterRenderLivingAtSuperiors = value;
	}

	public void setAfterRenderLivingAtInferiors(String[] value)
	{
		afterRenderLivingAtInferiors = value;
	}

	public String[] getBeforeRenderLivingLabelSuperiors()
	{
		return beforeRenderLivingLabelSuperiors;
	}

	public String[] getBeforeRenderLivingLabelInferiors()
	{
		return beforeRenderLivingLabelInferiors;
	}

	public String[] getOverrideRenderLivingLabelSuperiors()
	{
		return overrideRenderLivingLabelSuperiors;
	}

	public String[] getOverrideRenderLivingLabelInferiors()
	{
		return overrideRenderLivingLabelInferiors;
	}

	public String[] getAfterRenderLivingLabelSuperiors()
	{
		return afterRenderLivingLabelSuperiors;
	}

	public String[] getAfterRenderLivingLabelInferiors()
	{
		return afterRenderLivingLabelInferiors;
	}

	public void setBeforeRenderLivingLabelSuperiors(String[] value)
	{
		beforeRenderLivingLabelSuperiors = value;
	}

	public void setBeforeRenderLivingLabelInferiors(String[] value)
	{
		beforeRenderLivingLabelInferiors = value;
	}

	public void setOverrideRenderLivingLabelSuperiors(String[] value)
	{
		overrideRenderLivingLabelSuperiors = value;
	}

	public void setOverrideRenderLivingLabelInferiors(String[] value)
	{
		overrideRenderLivingLabelInferiors = value;
	}

	public void setAfterRenderLivingLabelSuperiors(String[] value)
	{
		afterRenderLivingLabelSuperiors = value;
	}

	public void setAfterRenderLivingLabelInferiors(String[] value)
	{
		afterRenderLivingLabelInferiors = value;
	}

	public String[] getBeforeRenderMultipassSuperiors()
	{
		return beforeRenderMultipassSuperiors;
	}

	public String[] getBeforeRenderMultipassInferiors()
	{
		return beforeRenderMultipassInferiors;
	}

	public String[] getOverrideRenderMultipassSuperiors()
	{
		return overrideRenderMultipassSuperiors;
	}

	public String[] getOverrideRenderMultipassInferiors()
	{
		return overrideRenderMultipassInferiors;
	}

	public String[] getAfterRenderMultipassSuperiors()
	{
		return afterRenderMultipassSuperiors;
	}

	public String[] getAfterRenderMultipassInferiors()
	{
		return afterRenderMultipassInferiors;
	}

	public void setBeforeRenderMultipassSuperiors(String[] value)
	{
		beforeRenderMultipassSuperiors = value;
	}

	public void setBeforeRenderMultipassInferiors(String[] value)
	{
		beforeRenderMultipassInferiors = value;
	}

	public void setOverrideRenderMultipassSuperiors(String[] value)
	{
		overrideRenderMultipassSuperiors = value;
	}

	public void setOverrideRenderMultipassInferiors(String[] value)
	{
		overrideRenderMultipassInferiors = value;
	}

	public void setAfterRenderMultipassSuperiors(String[] value)
	{
		afterRenderMultipassSuperiors = value;
	}

	public void setAfterRenderMultipassInferiors(String[] value)
	{
		afterRenderMultipassInferiors = value;
	}

	public String[] getBeforeRenderNameSuperiors()
	{
		return beforeRenderNameSuperiors;
	}

	public String[] getBeforeRenderNameInferiors()
	{
		return beforeRenderNameInferiors;
	}

	public String[] getOverrideRenderNameSuperiors()
	{
		return overrideRenderNameSuperiors;
	}

	public String[] getOverrideRenderNameInferiors()
	{
		return overrideRenderNameInferiors;
	}

	public String[] getAfterRenderNameSuperiors()
	{
		return afterRenderNameSuperiors;
	}

	public String[] getAfterRenderNameInferiors()
	{
		return afterRenderNameInferiors;
	}

	public void setBeforeRenderNameSuperiors(String[] value)
	{
		beforeRenderNameSuperiors = value;
	}

	public void setBeforeRenderNameInferiors(String[] value)
	{
		beforeRenderNameInferiors = value;
	}

	public void setOverrideRenderNameSuperiors(String[] value)
	{
		overrideRenderNameSuperiors = value;
	}

	public void setOverrideRenderNameInferiors(String[] value)
	{
		overrideRenderNameInferiors = value;
	}

	public void setAfterRenderNameSuperiors(String[] value)
	{
		afterRenderNameSuperiors = value;
	}

	public void setAfterRenderNameInferiors(String[] value)
	{
		afterRenderNameInferiors = value;
	}

	public String[] getBeforeRenderRightArmSuperiors()
	{
		return beforeRenderRightArmSuperiors;
	}

	public String[] getBeforeRenderRightArmInferiors()
	{
		return beforeRenderRightArmInferiors;
	}

	public String[] getOverrideRenderRightArmSuperiors()
	{
		return overrideRenderRightArmSuperiors;
	}

	public String[] getOverrideRenderRightArmInferiors()
	{
		return overrideRenderRightArmInferiors;
	}

	public String[] getAfterRenderRightArmSuperiors()
	{
		return afterRenderRightArmSuperiors;
	}

	public String[] getAfterRenderRightArmInferiors()
	{
		return afterRenderRightArmInferiors;
	}

	public void setBeforeRenderRightArmSuperiors(String[] value)
	{
		beforeRenderRightArmSuperiors = value;
	}

	public void setBeforeRenderRightArmInferiors(String[] value)
	{
		beforeRenderRightArmInferiors = value;
	}

	public void setOverrideRenderRightArmSuperiors(String[] value)
	{
		overrideRenderRightArmSuperiors = value;
	}

	public void setOverrideRenderRightArmInferiors(String[] value)
	{
		overrideRenderRightArmInferiors = value;
	}

	public void setAfterRenderRightArmSuperiors(String[] value)
	{
		afterRenderRightArmSuperiors = value;
	}

	public void setAfterRenderRightArmInferiors(String[] value)
	{
		afterRenderRightArmInferiors = value;
	}

	public String[] getBeforeRotateCorpseSuperiors()
	{
		return beforeRotateCorpseSuperiors;
	}

	public String[] getBeforeRotateCorpseInferiors()
	{
		return beforeRotateCorpseInferiors;
	}

	public String[] getOverrideRotateCorpseSuperiors()
	{
		return overrideRotateCorpseSuperiors;
	}

	public String[] getOverrideRotateCorpseInferiors()
	{
		return overrideRotateCorpseInferiors;
	}

	public String[] getAfterRotateCorpseSuperiors()
	{
		return afterRotateCorpseSuperiors;
	}

	public String[] getAfterRotateCorpseInferiors()
	{
		return afterRotateCorpseInferiors;
	}

	public void setBeforeRotateCorpseSuperiors(String[] value)
	{
		beforeRotateCorpseSuperiors = value;
	}

	public void setBeforeRotateCorpseInferiors(String[] value)
	{
		beforeRotateCorpseInferiors = value;
	}

	public void setOverrideRotateCorpseSuperiors(String[] value)
	{
		overrideRotateCorpseSuperiors = value;
	}

	public void setOverrideRotateCorpseInferiors(String[] value)
	{
		overrideRotateCorpseInferiors = value;
	}

	public void setAfterRotateCorpseSuperiors(String[] value)
	{
		afterRotateCorpseSuperiors = value;
	}

	public void setAfterRotateCorpseInferiors(String[] value)
	{
		afterRotateCorpseInferiors = value;
	}

	public String[] getBeforeSetModelVisibilitiesSuperiors()
	{
		return beforeSetModelVisibilitiesSuperiors;
	}

	public String[] getBeforeSetModelVisibilitiesInferiors()
	{
		return beforeSetModelVisibilitiesInferiors;
	}

	public String[] getOverrideSetModelVisibilitiesSuperiors()
	{
		return overrideSetModelVisibilitiesSuperiors;
	}

	public String[] getOverrideSetModelVisibilitiesInferiors()
	{
		return overrideSetModelVisibilitiesInferiors;
	}

	public String[] getAfterSetModelVisibilitiesSuperiors()
	{
		return afterSetModelVisibilitiesSuperiors;
	}

	public String[] getAfterSetModelVisibilitiesInferiors()
	{
		return afterSetModelVisibilitiesInferiors;
	}

	public void setBeforeSetModelVisibilitiesSuperiors(String[] value)
	{
		beforeSetModelVisibilitiesSuperiors = value;
	}

	public void setBeforeSetModelVisibilitiesInferiors(String[] value)
	{
		beforeSetModelVisibilitiesInferiors = value;
	}

	public void setOverrideSetModelVisibilitiesSuperiors(String[] value)
	{
		overrideSetModelVisibilitiesSuperiors = value;
	}

	public void setOverrideSetModelVisibilitiesInferiors(String[] value)
	{
		overrideSetModelVisibilitiesInferiors = value;
	}

	public void setAfterSetModelVisibilitiesSuperiors(String[] value)
	{
		afterSetModelVisibilitiesSuperiors = value;
	}

	public void setAfterSetModelVisibilitiesInferiors(String[] value)
	{
		afterSetModelVisibilitiesInferiors = value;
	}

	public String[] getBeforeSetRenderOutlinesSuperiors()
	{
		return beforeSetRenderOutlinesSuperiors;
	}

	public String[] getBeforeSetRenderOutlinesInferiors()
	{
		return beforeSetRenderOutlinesInferiors;
	}

	public String[] getOverrideSetRenderOutlinesSuperiors()
	{
		return overrideSetRenderOutlinesSuperiors;
	}

	public String[] getOverrideSetRenderOutlinesInferiors()
	{
		return overrideSetRenderOutlinesInferiors;
	}

	public String[] getAfterSetRenderOutlinesSuperiors()
	{
		return afterSetRenderOutlinesSuperiors;
	}

	public String[] getAfterSetRenderOutlinesInferiors()
	{
		return afterSetRenderOutlinesInferiors;
	}

	public void setBeforeSetRenderOutlinesSuperiors(String[] value)
	{
		beforeSetRenderOutlinesSuperiors = value;
	}

	public void setBeforeSetRenderOutlinesInferiors(String[] value)
	{
		beforeSetRenderOutlinesInferiors = value;
	}

	public void setOverrideSetRenderOutlinesSuperiors(String[] value)
	{
		overrideSetRenderOutlinesSuperiors = value;
	}

	public void setOverrideSetRenderOutlinesInferiors(String[] value)
	{
		overrideSetRenderOutlinesInferiors = value;
	}

	public void setAfterSetRenderOutlinesSuperiors(String[] value)
	{
		afterSetRenderOutlinesSuperiors = value;
	}

	public void setAfterSetRenderOutlinesInferiors(String[] value)
	{
		afterSetRenderOutlinesInferiors = value;
	}

	public String[] getBeforeShouldRenderSuperiors()
	{
		return beforeShouldRenderSuperiors;
	}

	public String[] getBeforeShouldRenderInferiors()
	{
		return beforeShouldRenderInferiors;
	}

	public String[] getOverrideShouldRenderSuperiors()
	{
		return overrideShouldRenderSuperiors;
	}

	public String[] getOverrideShouldRenderInferiors()
	{
		return overrideShouldRenderInferiors;
	}

	public String[] getAfterShouldRenderSuperiors()
	{
		return afterShouldRenderSuperiors;
	}

	public String[] getAfterShouldRenderInferiors()
	{
		return afterShouldRenderInferiors;
	}

	public void setBeforeShouldRenderSuperiors(String[] value)
	{
		beforeShouldRenderSuperiors = value;
	}

	public void setBeforeShouldRenderInferiors(String[] value)
	{
		beforeShouldRenderInferiors = value;
	}

	public void setOverrideShouldRenderSuperiors(String[] value)
	{
		overrideShouldRenderSuperiors = value;
	}

	public void setOverrideShouldRenderInferiors(String[] value)
	{
		overrideShouldRenderInferiors = value;
	}

	public void setAfterShouldRenderSuperiors(String[] value)
	{
		afterShouldRenderSuperiors = value;
	}

	public void setAfterShouldRenderInferiors(String[] value)
	{
		afterShouldRenderInferiors = value;
	}

	public String[] getBeforeTransformHeldFull3DItemLayerSuperiors()
	{
		return beforeTransformHeldFull3DItemLayerSuperiors;
	}

	public String[] getBeforeTransformHeldFull3DItemLayerInferiors()
	{
		return beforeTransformHeldFull3DItemLayerInferiors;
	}

	public String[] getOverrideTransformHeldFull3DItemLayerSuperiors()
	{
		return overrideTransformHeldFull3DItemLayerSuperiors;
	}

	public String[] getOverrideTransformHeldFull3DItemLayerInferiors()
	{
		return overrideTransformHeldFull3DItemLayerInferiors;
	}

	public String[] getAfterTransformHeldFull3DItemLayerSuperiors()
	{
		return afterTransformHeldFull3DItemLayerSuperiors;
	}

	public String[] getAfterTransformHeldFull3DItemLayerInferiors()
	{
		return afterTransformHeldFull3DItemLayerInferiors;
	}

	public void setBeforeTransformHeldFull3DItemLayerSuperiors(String[] value)
	{
		beforeTransformHeldFull3DItemLayerSuperiors = value;
	}

	public void setBeforeTransformHeldFull3DItemLayerInferiors(String[] value)
	{
		beforeTransformHeldFull3DItemLayerInferiors = value;
	}

	public void setOverrideTransformHeldFull3DItemLayerSuperiors(String[] value)
	{
		overrideTransformHeldFull3DItemLayerSuperiors = value;
	}

	public void setOverrideTransformHeldFull3DItemLayerInferiors(String[] value)
	{
		overrideTransformHeldFull3DItemLayerInferiors = value;
	}

	public void setAfterTransformHeldFull3DItemLayerSuperiors(String[] value)
	{
		afterTransformHeldFull3DItemLayerSuperiors = value;
	}

	public void setAfterTransformHeldFull3DItemLayerInferiors(String[] value)
	{
		afterTransformHeldFull3DItemLayerInferiors = value;
	}

}
