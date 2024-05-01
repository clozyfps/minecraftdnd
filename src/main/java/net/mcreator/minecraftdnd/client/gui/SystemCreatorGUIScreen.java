package net.mcreator.minecraftdnd.client.gui;

public class SystemCreatorGUIScreen extends AbstractContainerScreen<SystemCreatorGUIMenu> {

	private final static HashMap<String, Object> guistate = SystemCreatorGUIMenu.guistate;

	private final Level world;
	private final int x, y, z;
	private final Player entity;

	Button button_empty;
	Button button_empty1;
	Button button_empty2;
	Button button_empty3;
	Button button_empty4;
	Button button_1;
	Button button_2;
	Button button_3;
	Button button_4;
	Button button_5;
	Button button_11;
	Button button_21;
	Button button_31;
	Button button_41;
	Button button_51;
	Button button_12;
	Button button_22;
	Button button_32;
	Button button_42;
	Button button_52;
	Button button_13;
	Button button_23;
	Button button_33;
	Button button_43;
	Button button_53;
	Button button_14;
	Button button_24;
	Button button_34;
	Button button_44;
	Button button_54;

	public SystemCreatorGUIScreen(SystemCreatorGUIMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 0;
		this.imageHeight = 0;
	}

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics);

		super.render(guiGraphics, mouseX, mouseY, partialTicks);

		this.renderTooltip(guiGraphics, mouseX, mouseY);

	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();

		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}

		return super.keyPressed(key, b, c);
	}

	@Override
	public void containerTick() {
		super.containerTick();
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		guiGraphics.drawString(this.font, Component.translatable("gui.minecraft_dnd.system_creator_gui.label_modifiers"), -19, -112, -1, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.minecraft_dnd.system_creator_gui.label_modifiers1"), -19, -76, -1, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.minecraft_dnd.system_creator_gui.label_modifiers2"), -19, -40, -1, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.minecraft_dnd.system_creator_gui.label_modifiers3"), -19, -4, -1, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.minecraft_dnd.system_creator_gui.label_modifiers4"), -19, 32, -1, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.minecraft_dnd.system_creator_gui.label_attr"), -208, -112, -1, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.minecraft_dnd.system_creator_gui.label_attr1"), -208, -76, -1, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.minecraft_dnd.system_creator_gui.label_attr2"), -208, -40, -1, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.minecraft_dnd.system_creator_gui.label_attr3"), -208, -4, -1, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.minecraft_dnd.system_creator_gui.label_attr4"), -208, 32, -1, false);
	}

	@Override
	public void onClose() {
		super.onClose();
	}

	@Override
	public void init() {
		super.init();

		button_empty = new PlainTextButton(this.leftPos + -208, this.topPos + -112, 61, 20, Component.translatable("gui.minecraft_dnd.system_creator_gui.button_empty"), e -> {
		}, this.font);

		guistate.put("button:button_empty", button_empty);
		this.addRenderableWidget(button_empty);

		button_empty1 = new PlainTextButton(this.leftPos + -208, this.topPos + -76, 61, 20, Component.translatable("gui.minecraft_dnd.system_creator_gui.button_empty1"), e -> {
		}, this.font);

		guistate.put("button:button_empty1", button_empty1);
		this.addRenderableWidget(button_empty1);

		button_empty2 = new PlainTextButton(this.leftPos + -208, this.topPos + -40, 61, 20, Component.translatable("gui.minecraft_dnd.system_creator_gui.button_empty2"), e -> {
		}, this.font);

		guistate.put("button:button_empty2", button_empty2);
		this.addRenderableWidget(button_empty2);

		button_empty3 = new PlainTextButton(this.leftPos + -208, this.topPos + -4, 61, 20, Component.translatable("gui.minecraft_dnd.system_creator_gui.button_empty3"), e -> {
		}, this.font);

		guistate.put("button:button_empty3", button_empty3);
		this.addRenderableWidget(button_empty3);

		button_empty4 = new PlainTextButton(this.leftPos + -208, this.topPos + 32, 61, 20, Component.translatable("gui.minecraft_dnd.system_creator_gui.button_empty4"), e -> {
		}, this.font);

		guistate.put("button:button_empty4", button_empty4);
		this.addRenderableWidget(button_empty4);

		button_1 = Button.builder(Component.translatable("gui.minecraft_dnd.system_creator_gui.button_1"), e -> {
		}).bounds(this.leftPos + 35, this.topPos + -112, 30, 20).build();

		guistate.put("button:button_1", button_1);
		this.addRenderableWidget(button_1);

		button_2 = Button.builder(Component.translatable("gui.minecraft_dnd.system_creator_gui.button_2"), e -> {
		}).bounds(this.leftPos + 71, this.topPos + -112, 30, 20).build();

		guistate.put("button:button_2", button_2);
		this.addRenderableWidget(button_2);

		button_3 = Button.builder(Component.translatable("gui.minecraft_dnd.system_creator_gui.button_3"), e -> {
		}).bounds(this.leftPos + 107, this.topPos + -112, 30, 20).build();

		guistate.put("button:button_3", button_3);
		this.addRenderableWidget(button_3);

		button_4 = Button.builder(Component.translatable("gui.minecraft_dnd.system_creator_gui.button_4"), e -> {
		}).bounds(this.leftPos + 143, this.topPos + -112, 30, 20).build();

		guistate.put("button:button_4", button_4);
		this.addRenderableWidget(button_4);

		button_5 = Button.builder(Component.translatable("gui.minecraft_dnd.system_creator_gui.button_5"), e -> {
		}).bounds(this.leftPos + 179, this.topPos + -112, 30, 20).build();

		guistate.put("button:button_5", button_5);
		this.addRenderableWidget(button_5);

		button_11 = Button.builder(Component.translatable("gui.minecraft_dnd.system_creator_gui.button_11"), e -> {
		}).bounds(this.leftPos + 35, this.topPos + -76, 30, 20).build();

		guistate.put("button:button_11", button_11);
		this.addRenderableWidget(button_11);

		button_21 = Button.builder(Component.translatable("gui.minecraft_dnd.system_creator_gui.button_21"), e -> {
		}).bounds(this.leftPos + 71, this.topPos + -76, 30, 20).build();

		guistate.put("button:button_21", button_21);
		this.addRenderableWidget(button_21);

		button_31 = Button.builder(Component.translatable("gui.minecraft_dnd.system_creator_gui.button_31"), e -> {
		}).bounds(this.leftPos + 107, this.topPos + -76, 30, 20).build();

		guistate.put("button:button_31", button_31);
		this.addRenderableWidget(button_31);

		button_41 = Button.builder(Component.translatable("gui.minecraft_dnd.system_creator_gui.button_41"), e -> {
		}).bounds(this.leftPos + 143, this.topPos + -76, 30, 20).build();

		guistate.put("button:button_41", button_41);
		this.addRenderableWidget(button_41);

		button_51 = Button.builder(Component.translatable("gui.minecraft_dnd.system_creator_gui.button_51"), e -> {
		}).bounds(this.leftPos + 179, this.topPos + -76, 30, 20).build();

		guistate.put("button:button_51", button_51);
		this.addRenderableWidget(button_51);

		button_12 = Button.builder(Component.translatable("gui.minecraft_dnd.system_creator_gui.button_12"), e -> {
		}).bounds(this.leftPos + 35, this.topPos + -40, 30, 20).build();

		guistate.put("button:button_12", button_12);
		this.addRenderableWidget(button_12);

		button_22 = Button.builder(Component.translatable("gui.minecraft_dnd.system_creator_gui.button_22"), e -> {
		}).bounds(this.leftPos + 71, this.topPos + -40, 30, 20).build();

		guistate.put("button:button_22", button_22);
		this.addRenderableWidget(button_22);

		button_32 = Button.builder(Component.translatable("gui.minecraft_dnd.system_creator_gui.button_32"), e -> {
		}).bounds(this.leftPos + 107, this.topPos + -40, 30, 20).build();

		guistate.put("button:button_32", button_32);
		this.addRenderableWidget(button_32);

		button_42 = Button.builder(Component.translatable("gui.minecraft_dnd.system_creator_gui.button_42"), e -> {
		}).bounds(this.leftPos + 143, this.topPos + -40, 30, 20).build();

		guistate.put("button:button_42", button_42);
		this.addRenderableWidget(button_42);

		button_52 = Button.builder(Component.translatable("gui.minecraft_dnd.system_creator_gui.button_52"), e -> {
		}).bounds(this.leftPos + 179, this.topPos + -40, 30, 20).build();

		guistate.put("button:button_52", button_52);
		this.addRenderableWidget(button_52);

		button_13 = Button.builder(Component.translatable("gui.minecraft_dnd.system_creator_gui.button_13"), e -> {
		}).bounds(this.leftPos + 35, this.topPos + -4, 30, 20).build();

		guistate.put("button:button_13", button_13);
		this.addRenderableWidget(button_13);

		button_23 = Button.builder(Component.translatable("gui.minecraft_dnd.system_creator_gui.button_23"), e -> {
		}).bounds(this.leftPos + 71, this.topPos + -4, 30, 20).build();

		guistate.put("button:button_23", button_23);
		this.addRenderableWidget(button_23);

		button_33 = Button.builder(Component.translatable("gui.minecraft_dnd.system_creator_gui.button_33"), e -> {
		}).bounds(this.leftPos + 107, this.topPos + -4, 30, 20).build();

		guistate.put("button:button_33", button_33);
		this.addRenderableWidget(button_33);

		button_43 = Button.builder(Component.translatable("gui.minecraft_dnd.system_creator_gui.button_43"), e -> {
		}).bounds(this.leftPos + 143, this.topPos + -4, 30, 20).build();

		guistate.put("button:button_43", button_43);
		this.addRenderableWidget(button_43);

		button_53 = Button.builder(Component.translatable("gui.minecraft_dnd.system_creator_gui.button_53"), e -> {
		}).bounds(this.leftPos + 179, this.topPos + -4, 30, 20).build();

		guistate.put("button:button_53", button_53);
		this.addRenderableWidget(button_53);

		button_14 = Button.builder(Component.translatable("gui.minecraft_dnd.system_creator_gui.button_14"), e -> {
		}).bounds(this.leftPos + 35, this.topPos + 32, 30, 20).build();

		guistate.put("button:button_14", button_14);
		this.addRenderableWidget(button_14);

		button_24 = Button.builder(Component.translatable("gui.minecraft_dnd.system_creator_gui.button_24"), e -> {
		}).bounds(this.leftPos + 71, this.topPos + 32, 30, 20).build();

		guistate.put("button:button_24", button_24);
		this.addRenderableWidget(button_24);

		button_34 = Button.builder(Component.translatable("gui.minecraft_dnd.system_creator_gui.button_34"), e -> {
		}).bounds(this.leftPos + 107, this.topPos + 32, 30, 20).build();

		guistate.put("button:button_34", button_34);
		this.addRenderableWidget(button_34);

		button_44 = Button.builder(Component.translatable("gui.minecraft_dnd.system_creator_gui.button_44"), e -> {
		}).bounds(this.leftPos + 143, this.topPos + 32, 30, 20).build();

		guistate.put("button:button_44", button_44);
		this.addRenderableWidget(button_44);

		button_54 = Button.builder(Component.translatable("gui.minecraft_dnd.system_creator_gui.button_54"), e -> {
		}).bounds(this.leftPos + 179, this.topPos + 32, 30, 20).build();

		guistate.put("button:button_54", button_54);
		this.addRenderableWidget(button_54);

	}

}
