package net.irisshaders.iris.mixin.forge;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Shadow;

import net.irisshaders.iris.api.v0.IrisApi;
import net.irisshaders.iris.pipeline.programs.FallbackShader;
import net.irisshaders.iris.pipeline.programs.ShaderAccess;
import net.minecraft.client.renderer.ShaderInstance;

@Pseudo
@Mixin(targets = "blusunrize/immersiveengineering/client/utils/IEGLShaders", remap = false)
public class MixinVBOIE {
	@Shadow
	private static ShaderInstance vboShader;

	@Overwrite
	public static ShaderInstance getVboShader() {
		if (!IrisApi.getInstance().isShaderPackInUse()) {
			return vboShader;
		} else {
			ShaderInstance shader = ShaderAccess.getIEVBOShader();
			if (shader == null || shader instanceof FallbackShader) {
				return vboShader;
			} else {
				return shader;
			}
		}
	}
}
