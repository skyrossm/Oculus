package net.irisshaders.iris.compat.sodium.impl.vertex_format;

import net.caffeinemc.mods.sodium.client.gl.attribute.GlVertexAttributeFormat;
import net.irisshaders.iris.compat.sodium.mixin.vertex_format.GlVertexAttributeFormatAccessor;
import org.lwjgl.opengl.GL20C;

public class IrisGlVertexAttributeFormat {
	public static final GlVertexAttributeFormat BYTE =
			GlVertexAttributeFormatAccessor.createGlVertexAttributeFormat(GL20C.GL_BYTE, 1);
	public static final GlVertexAttributeFormat SHORT = GlVertexAttributeFormatAccessor.createGlVertexAttributeFormat(GL20C.GL_SHORT, 2);
	public static final GlVertexAttributeFormat UNSIGNED_2_10_10_10_REV = GlVertexAttributeFormatAccessor.createGlVertexAttributeFormat(GL20C.GL_UNSIGNED_INT_2_10_10_10_REV, 4);
}
