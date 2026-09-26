package com.example.client.mixin;

import com.example.client.ClientModules;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.fog.FogData;
import net.minecraft.client.renderer.fog.FogRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FogRenderer.class)
public abstract class NoFogMixin {
	@Inject(method = "setupFog", at = @At("RETURN"))
	private void removeFog(Camera camera, int renderDistance, DeltaTracker deltaTracker,
			float partialTick, ClientLevel level, CallbackInfoReturnable<FogData> callback) {
		if (!ClientModules.isEnabled(ClientModules.Module.NO_FOG)) {
			return;
		}

		FogData fog = callback.getReturnValue();
		fog.environmentalStart = 0.0F;
		fog.environmentalEnd = Float.MAX_VALUE;
		fog.renderDistanceStart = 0.0F;
		fog.renderDistanceEnd = Float.MAX_VALUE;
		fog.skyEnd = Float.MAX_VALUE;
		fog.cloudEnd = Float.MAX_VALUE;
	}
}