package io.tehtotalpwnage.horseutils;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.item.recipe.ShapedRecipe;

public class RecipeSaddle {
	private static ItemStack saddle = ItemStack.of(ItemTypes.SADDLE, 1);

	public static void registerSaddle() {
		ItemStack iron = ItemStack.of(ItemTypes.IRON_INGOT, 1);
		ItemStack leather = ItemStack.of(ItemTypes.LEATHER, 1);
		
		ShapedRecipe recipeSaddle = Sponge.getRegistry().createBuilder(ShapedRecipe.Builder.class)
				.width(3).height(3)
				.row(0, leather, leather, leather)
				.row(1, leather, iron, leather)
				.row(2, iron, null, iron)
				.addResult(saddle)
				.build();
		Sponge.getRegistry().getRecipeRegistry().register(recipeSaddle);
	}
}