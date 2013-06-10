package webkit_test;

import java.io.IOException;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.BrowserFunction;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.swt.events.MenuDetectEvent;
import org.eclipse.swt.events.MenuDetectListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class WebView extends ViewPart {
	
	public static String ID = "webkit_test.webview";

	private Browser b;
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Text text_4;
	
	
	class RGBFunctions {
		
		public void createFunction(Browser b) {
			
			new BrowserFunction(b, "r") {

				@Override
				public Object function(Object[] arguments) {
					return text.getText();
				}
				
			};
			
			new BrowserFunction(b, "g") {

				@Override
				public Object function(Object[] arguments) {
					return text_1.getText();
				}
				
			};
			
			new BrowserFunction(b, "b") {

				@Override
				public Object function(Object[] arguments) {
					return text_2.getText();
				}
				
			};
			
			new BrowserFunction(b, "setScale") {
				@Override
				public Object function(Object[] arguments) {
					text_3.setText(arguments[0].toString());
					return null;
				}
			};
			
			new BrowserFunction(b, "setRotation") {
				@Override
				public Object function(Object[] arguments) {
					text_4.setText(arguments[0].toString());
					return null;
				}
			};
		}
		
	}
	
	public WebView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new GridLayout(2, false));
		b = new Browser(parent, SWT.WEBKIT);
		b.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		b.addMenuDetectListener(new MenuDetectListener() {
			
			@Override
			public void menuDetected(MenuDetectEvent e) {
				System.out.println("Detected Menu");
				e.doit = false;
			}
		});
		
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1));
		composite.setLayout(new GridLayout(2, false));
		
		Label lblR = new Label(composite, SWT.NONE);
		lblR.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblR.setText("R");
		
		text = new Text(composite, SWT.BORDER);
		text.setText("0");
		GridData gd_text = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_text.widthHint = 50;
		text.setLayoutData(gd_text);
		
		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel.setText("G");
		
		text_1 = new Text(composite, SWT.BORDER);
		text_1.setText("200");
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblNewLabel_1 = new Label(composite, SWT.NONE);
		lblNewLabel_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_1.setText("B");
		
		text_2 = new Text(composite, SWT.BORDER);
		text_2.setText("255");
		text_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button btnDraw = new Button(composite, SWT.NONE);
		btnDraw.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				b.execute("draw()");
			}
		});
		btnDraw.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		btnDraw.setText("Draw");
		
		Label lblScale = new Label(composite, SWT.NONE);
		lblScale.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblScale.setText("scale");
		
		text_3 = new Text(composite, SWT.BORDER);
		text_3.setEditable(false);
		text_3.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblRotation = new Label(composite, SWT.NONE);
		lblRotation.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblRotation.setText("rotation");
		
		text_4 = new Text(composite, SWT.BORDER);
		text_4.setEditable(false);
		text_4.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		new RGBFunctions().createFunction(b);
		
		URL url = Activator.getDefault().getBundle().getResource("web/hello.html");
		try {
			b.setUrl(FileLocator.toFileURL(url).toString());
			
			// b.setUrl("file:///d:/web/hello.html");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		b.update();
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

}
