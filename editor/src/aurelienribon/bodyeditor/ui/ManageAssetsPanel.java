package aurelienribon.bodyeditor.ui;

import aurelienribon.bodyeditor.AssetsManager;
import aurelienribon.bodyeditor.IoManager;
import aurelienribon.bodyeditor.models.AssetModel;
import aurelienribon.utils.notifications.AutoListModel;
import aurelienribon.utils.notifications.ChangeListener;
import java.awt.Component;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * @author Aurelien Ribon | http://www.aurelienribon.com/
 */
public class ManageAssetsPanel extends javax.swing.JPanel {
	private final ImageIcon assetIcon = new ImageIcon(ManageAssetsPanel.class.getResource("gfx/ic_texture.png"));
	
    public ManageAssetsPanel() {
        initComponents();
		assetsList.setModel(new AutoListModel(AssetsManager.instance().getList()));
		assetsList.setCellRenderer(listCellRdr);

		assetsList.addListSelectionListener(new ListSelectionListener() {
			@Override public void valueChanged(ListSelectionEvent e) {
				AssetsManager.instance().setSelectedAsset((AssetModel)assetsList.getSelectedValue());
			}
		});

		AssetsManager.instance().addChangeListener(new ChangeListener() {
			@Override public void propertyChanged(Object source, String propertyName) {
				if (propertyName.equals("selectedAsset")) {
					AssetModel am = AssetsManager.instance().getSelectedAsset();
					if (am != assetsList.getSelectedValue() || assetsList.getSelectedValues().length > 1)
						assetsList.setSelectedValue(am, true);
				}
			}
		});

		IoManager.instance().addChangeListener(new ChangeListener() {
			@Override public void propertyChanged(Object source, String propertyName) {
				((AutoListModel)assetsList.getModel()).forceRefresh();
			}
		});
    }
	
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel6 = new javax.swing.JPanel();
        removeAssetBtn = new javax.swing.JButton();
        assetsListScrollPane = new javax.swing.JScrollPane();
        assetsList = new javax.swing.JList();
        addAssetsBtn = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        jPanel6.setBackground(Theme.MAIN_ALT_BACKGROUND);

        removeAssetBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aurelienribon/bodyeditor/ui/gfx/ic_delete.png"))); // NOI18N
        removeAssetBtn.setText("Remove selection");
        removeAssetBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        removeAssetBtn.setMargin(new java.awt.Insets(2, 3, 2, 2));
        removeAssetBtn.setOpaque(false);
        removeAssetBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeAssetBtnActionPerformed(evt);
            }
        });

        assetsList.setBackground(Theme.TEXTAREA_BACKGROUND);
        assetsList.setForeground(Theme.TEXTAREA_FOREGROUND);
        assetsList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        assetsListScrollPane.setViewportView(assetsList);

        addAssetsBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aurelienribon/bodyeditor/ui/gfx/ic_add.png"))); // NOI18N
        addAssetsBtn.setText("Add images files/dirs");
        addAssetsBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        addAssetsBtn.setMargin(new java.awt.Insets(2, 5, 2, 5));
        addAssetsBtn.setOpaque(false);
        addAssetsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addAssetsBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(assetsListScrollPane, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(addAssetsBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(removeAssetBtn)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addAssetsBtn)
                    .addComponent(removeAssetBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(assetsListScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                .addContainerGap())
        );

        add(jPanel6, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

	private void removeAssetBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeAssetBtnActionPerformed
		if (!assetsList.isSelectionEmpty())
			AssetsManager.instance().getList().removeAll(Arrays.asList(assetsList.getSelectedValues()));
}//GEN-LAST:event_removeAssetBtnActionPerformed

	private void addAssetsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addAssetsBtnActionPerformed
		String[] paths = promptAssetsByFiles();
		if (paths != null)
			for (String path : paths)
				if (!AssetsManager.instance().containsPath(path))
					AssetsManager.instance().getList().add(new AssetModel(path));
}//GEN-LAST:event_addAssetsBtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addAssetsBtn;
    private javax.swing.JList assetsList;
    private javax.swing.JScrollPane assetsListScrollPane;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JButton removeAssetBtn;
    // End of variables declaration//GEN-END:variables

	// -------------------------------------------------------------------------
	// ListCellRenderer
	// -------------------------------------------------------------------------

	private final ListCellRenderer listCellRdr = new ListCellRenderer() {
		private final JLabel label = new JLabel();

		{
			label.setBorder(new EmptyBorder(2, 5, 2, 5));
			label.setIcon(assetIcon);
		}
		
		@Override public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
			AssetModel am = (AssetModel)value;
			String path = IoManager.instance().relativize(am.getPath());
			label.setText(path);

			if (isSelected) {
				label.setBackground(Theme.TEXTAREA_SELECTED_BACKGROUND);
				label.setForeground(Theme.TEXTAREA_SELECTED_FOREGROUND);
				label.setOpaque(true);
			} else {
				label.setForeground(Theme.TEXTAREA_FOREGROUND);
				label.setOpaque(false);
			}

			return label;
		}
	};

	// -------------------------------------------------------------------------
	// Asset Prompter
	// -------------------------------------------------------------------------

	private final javax.swing.filechooser.FileFilter imageUiFilter = new javax.swing.filechooser.FileFilter() {
		@Override public boolean accept(File f) {
			if (f.isDirectory())
				return true;
			return isSameString(getExtension(f), "png", "jpg", "jpeg");
		}

		@Override public String getDescription() {
			return "Image files or directories";
		}
	};

	private final java.io.FileFilter imageFilter = new java.io.FileFilter() {
		@Override
		public boolean accept(File f) {
			if (f.isDirectory())
				return true;
			return isSameString(getExtension(f), "png", "jpg", "jpeg");
		}
	};

    private String[] promptAssetsByFiles() {
		File outputFile = IoManager.instance().getOutputFile();
		String startupPath = outputFile != null ? outputFile.getParent() : ".";

		JFileChooser chooser = new JFileChooser(startupPath);
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		chooser.setMultiSelectionEnabled(true);
		chooser.setFileFilter(imageUiFilter);

		if (chooser.showOpenDialog(SwingUtilities.getWindowAncestor(this)) == JFileChooser.APPROVE_OPTION) {
			List<String> assetPaths = new ArrayList<String>();
			List<File> remainingFiles = new ArrayList<File>();

			Collections.addAll(remainingFiles, chooser.getSelectedFiles());
			int deepness = 0;
			int maxDeepness = 8;

			while (!remainingFiles.isEmpty()) {
				File currentFile = remainingFiles.remove(0);
				if (currentFile.isFile()) {
					assetPaths.add(currentFile.getPath());
				} else if (deepness < maxDeepness) {
					deepness += 1;
					Collections.addAll(remainingFiles, currentFile.listFiles(imageFilter));
				}
			}

			return assetPaths.toArray(new String[assetPaths.size()]);
		}

		return null;
	}

	private boolean isSameString(String str1, String... strs) {
		for (String str : strs)
			if (str1.equalsIgnoreCase(str))
				return true;
		return false;
	}

	private String getExtension(File f) {
		int idx = f.getName().lastIndexOf(".");
		if (idx < 0 || idx == f.getName().length()-1)
			return "";
		return f.getName().substring(idx+1);
	}
}
