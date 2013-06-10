package webkit_test;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class Perspective implements IPerspectiveFactory {

	public void createInitialLayout(IPageLayout layout) {
		layout.addView(WebView.ID, IPageLayout.LEFT, 1.0f, layout.getEditorArea());
		layout.setEditorAreaVisible(false);
	}
}
